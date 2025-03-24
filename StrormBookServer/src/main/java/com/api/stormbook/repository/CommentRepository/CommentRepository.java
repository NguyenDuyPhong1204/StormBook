package com.api.stormbook.repository.CommentRepository;

import com.api.stormbook.dto.CommentDTO.CommentResponseDTO;
import com.api.stormbook.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    //lay danh sach binh luan cua chapter
    @Query("SELECT new com.api.stormbook.dto.CommentDTO.CommentResponseDTO(" +
            "c.id, c.user.id, c.story.id, c.chapter.id, c.parent.id, " +
            "c.content,c.likes, c.user.avatar, c.user.fullName, c.createdAt) " +
            "FROM Comment c " +
            "JOIN c.user u " +
            "WHERE c.chapter.id = :chapterId " +
            "ORDER BY c.createdAt DESC")
    List<CommentResponseDTO> findListCommentChapter(Long chapterId);

    //lay danh sach binh luan cua truyen
    @Query("SELECT c FROM Comment c WHERE c.story.id = :storyId ORDER BY c.createdAt DESC")
    List<Comment> findListCommentStory(Long storyId);
    //lay danh sach tra loi binh luan cua 1 binh luan
//    List<Comment> findListReplyComment(Long commentId);
    @Query("SELECT new com.api.stormbook.dto.CommentDTO.CommentResponseDTO(" +
            "c.id, c.user.id, c.story.id, c.chapter.id, c.parent.id, " +
            "c.content,c.likes, c.user.fullName, c.user.avatar, c.createdAt) " +
            "FROM Comment c " +
            "JOIN c.user u " +
            "WHERE c.parent.id = :commentId " +
            "ORDER BY c.createdAt ASC")
    List<CommentResponseDTO> findRepliesByParentId(Long commentId);

    //xoa tat ca binh luan
    void deleteAllByChapterId(Long chapterId);
}
