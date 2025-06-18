package com.api.stormbook.service.ChapterService;

import com.api.stormbook.dto.ChapterDTO.ChapterReadStatusDTO;
import com.api.stormbook.entity.Chapter;
import com.api.stormbook.entity.Story;
import com.api.stormbook.entity.User;
import com.api.stormbook.exception.NotFoundException;
import com.api.stormbook.repository.AuthRepository.AuthUserRepository;
import com.api.stormbook.repository.ChapterRepository.ChapterRepository;
import com.api.stormbook.repository.ReadingHistoryRepository.ReHiRepository;
import com.api.stormbook.repository.StoryRepository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChapterUserService {
    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private ReHiRepository rehiRepository;

    public List<ChapterReadStatusDTO> getAllChapterReadStatus(Long userId, Long storyId){
        User user = authUserRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("Người dùng không tồn tại"));
        Story story = storyRepository.findById(storyId)
                .orElseThrow(() -> new NotFoundException("Truyện không tồn tại"));

        //lay danh sach chuong cua truyen
        List<Chapter> chapters = chapterRepository.findByStory(story);
        //lay danh sach chuong da doc cua user
        List<Long> readChapterIds =  rehiRepository.findReadChapterIdsByUserAndStory(user.getId(), story.getId());
        //tao danh sach chuong voi trang thai da doc
        return chapters.stream()
                .map(chapter -> new ChapterReadStatusDTO(
                        chapter.getId(),
                        chapter.getChapterNumber(),
                        chapter.getTitle(),
                        chapter.getStory().getId(),
                        chapter.getView_count(),
                        chapter.getIs_read(),
                        chapter.getImage_url(),
                        chapter.getCreatedAt(),
                        chapter.getUpdatedAt()
                        ))
                .collect(Collectors.toList());
    }

}
