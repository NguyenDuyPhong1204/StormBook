package com.api.stormbook.service.CommentService;

import com.api.stormbook.dto.CommentDTO.CommentDTO;
import com.api.stormbook.dto.CommentDTO.CommentResponseDTO;
import com.api.stormbook.entity.Chapter;
import com.api.stormbook.entity.Comment;
import com.api.stormbook.entity.Story;
import com.api.stormbook.entity.User;
import com.api.stormbook.exception.NotFoundException;
import com.api.stormbook.repository.AuthRepository.AuthUserRepository;
import com.api.stormbook.repository.ChapterRepository.ChapterRepository;
import com.api.stormbook.repository.CommentRepository.CommentRepository;
import com.api.stormbook.repository.StoryRepository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private AuthUserRepository authUserRepository;
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private ChapterRepository chapterRepository;

    public CommentDTO createComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        //lay thong tin user
        User user = authUserRepository.findById(commentDTO.getUserId()).orElseThrow(() -> new NotFoundException("Không tìm thấy thông tin người dùng với ID: " + commentDTO.getUserId()));
        //lay thong tin truyen
        Story story = storyRepository.findStoryById(commentDTO.getStoryId());
        if(story == null) {
            throw new NotFoundException("Không tìm thấy truyện với ID: "+ commentDTO.getStoryId());
        }
        //lay thong tin chapter
        Chapter chapter = chapterRepository.findChapterById(commentDTO.getChapterId());
        if(chapter == null) {
            throw new NotFoundException("Không tìm thấy chương với ID: " + commentDTO.getChapterId());
        }

        comment.setUser(user);
        comment.setStory(story);
        comment.setChapter(chapter);
        comment.setContent(commentDTO.getContent());
        comment.setReplies(commentDTO.getReplies());
        comment.setLikes(commentDTO.getLikes());
        commentRepository.save(comment);
        return new CommentDTO(
          comment.getId(),
          comment.getUser().getId(),
          comment.getStory().getId(),
          comment.getChapter().getId(),
          comment.getReplies(),
          comment.getContent(),
          comment.getLikes(),
          comment.getCreatedAt(),
          comment.getUpdatedAt()
        );
    }

    public CommentDTO replyComment(Long commentId, CommentDTO commentDTO) {
        Comment comment = new Comment();
        //lay thong tin user
        User user = authUserRepository.findById(commentDTO.getUserId()).orElseThrow(() -> new NotFoundException("Không tìm thấy thông tin người dùng với ID: " + commentDTO.getUserId()));
        //lay thong tin truyen
        Story story = storyRepository.findStoryById(commentDTO.getStoryId());
        if(story == null) {
            throw new NotFoundException("Không tìm thấy truyện với ID: "+ commentDTO.getStoryId());
        }
        //lay thong tin chapter
        Chapter chapter = chapterRepository.findChapterById(commentDTO.getChapterId());
        if(chapter == null) {
            throw new NotFoundException("Không tìm thấy chương với ID: " + commentDTO.getChapterId());
        }
        //lay thong tin comment
        Comment parentComment = commentRepository.findById(commentId)
                        .orElseThrow(() -> new NotFoundException("Không tìm thấy bình luận với ID: " + commentId));
        comment.setUser(user);
        comment.setStory(story);
        comment.setChapter(chapter);
        comment.setParent(parentComment);
        comment.setContent(commentDTO.getContent());
        comment.setReplies(commentDTO.getReplies());
        comment.setLikes(commentDTO.getLikes());
        commentRepository.save(comment);

        //cap nhat danh sach tra loi comment
        parentComment.getReplies().add(comment);
        commentRepository.save(parentComment);

        return new CommentDTO(
                comment.getId(),
                comment.getUser().getId(),
                comment.getStory().getId(),
                comment.getChapter().getId(),
                comment.getParent().getId(),
//                comment.getReplies(),
                comment.getContent(),
                comment.getLikes(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }

    //chinh sua comment
    public CommentDTO updateComment(Long commentId, CommentDTO commentDTO) {
        Comment commentInfo = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Không có comment với ID: " + commentId));
        commentInfo.setContent(commentDTO.getContent());
        commentRepository.save(commentInfo);
        return new CommentDTO(
                commentInfo.getId(),
                commentInfo.getUser().getId(),
                commentInfo.getStory().getId(),
                commentInfo.getChapter().getId(),
                commentInfo.getReplies(),
                commentInfo.getContent(),
                commentInfo.getLikes(),
                commentInfo.getCreatedAt(),
                commentInfo.getUpdatedAt()
        );
    }



    public List<CommentResponseDTO> getCommentChapter(Long chapterId){
        List<CommentResponseDTO> listComment = commentRepository.findListCommentChapter(chapterId);
        if(listComment.isEmpty()){
            throw new NotFoundException("Không có bình luận trong chương này!");
        }
        //lay reply comment
        for (CommentResponseDTO comment : listComment) {
            List<CommentResponseDTO> replies = commentRepository.findRepliesByParentId(comment.getId());
            comment.setReplies(replies);
        }
        return listComment;
    }

    //xoa comment
    public void deleteComment(Long id){
        if(!commentRepository.existsById(id)) {
            throw new NotFoundException("Không tìm thấy comment với ID: " +id);
        }
        commentRepository.deleteById(id);
    }

    public void deleteAllComment(Long chapterId){
        if(!chapterRepository.existsById(chapterId)) {
            throw new NotFoundException("Không tìm thấy chương với ID: " +chapterId);
        }
        commentRepository.deleteAllByChapterId(chapterId);
    }
}
