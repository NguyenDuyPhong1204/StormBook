package com.api.stormbook.service.ReadingHistoryService;

import com.api.stormbook.dto.ReadingHistoryDTO.ReHistoryDTO;
import com.api.stormbook.entity.Chapter;
import com.api.stormbook.entity.ReadingHistory;
import com.api.stormbook.entity.Story;
import com.api.stormbook.entity.User;
import com.api.stormbook.exception.NotFoundException;
import com.api.stormbook.repository.AuthRepository.AuthUserRepository;
import com.api.stormbook.repository.ChapterRepository.ChapterRepository;
import com.api.stormbook.repository.ReadingHistoryRepository.ReHiRepository;
import com.api.stormbook.repository.StoryRepository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ReHiService {
    @Autowired
    private AuthUserRepository authUserRepository;
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private ReHiRepository reHiRepository;
    public ReHistoryDTO markChapterAsRead(Long userId, Long storyId, Long chapterId) {
        User user = authUserRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Người dùng không tồn tại"));
        Story story = storyRepository.findStoryById(storyId);
        if(story == null) {
            throw new NotFoundException("Truyện không tồn tại");
        }
        Chapter chapter = chapterRepository.findChapterById(chapterId);
        if(chapter == null) {
            throw new NotFoundException("Chương không tồn tại");
        }
        //kiem tra xem chuong da co trong lich su hay chua
        ReadingHistory history = reHiRepository.findByUserAndStoryAndChapter(user,story,chapter);

        if(history == null) {
            // Nếu chưa có lịch sử thì tạo mới
            history = new ReadingHistory();
            history.setUser(user);
            history.setStory(story);
            history.setChapter(chapter);
        }

        // Cập nhật trạng thái đã đọc
        history.setIsRead(true);
        history.setLast_read_at(Instant.now());
        reHiRepository.save(history);

        // Trả về thông tin đã đọc
        return new ReHistoryDTO(
                history.getId(),
                history.getUser().getId(),
                history.getStory().getId(),
                history.getChapter().getId(),
                history.getIsRead(),
                history.getLast_read_at());
    }

    public void deleteStoryHistory(Long id){
        if(!reHiRepository.existsReadingById(id)){
            throw new NotFoundException("Không tìm thấy lịch sử đọc với ID: "+ id);
        }
        reHiRepository.deleteById(id);
    }

}
