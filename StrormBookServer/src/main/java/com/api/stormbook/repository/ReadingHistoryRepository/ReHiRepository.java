package com.api.stormbook.repository.ReadingHistoryRepository;

import com.api.stormbook.entity.Chapter;
import com.api.stormbook.entity.ReadingHistory;
import com.api.stormbook.entity.Story;
import com.api.stormbook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReHiRepository extends JpaRepository<ReadingHistory, Long> {
    ReadingHistory findByUserAndStoryAndChapter(User user, Story story, Chapter chapter);
    //chuong da doc cua 1 truyen
    @Query("SELECT rh.chapter.id FROM ReadingHistory rh WHERE rh.user.id = :userId AND rh.story.id = :storyId")
    List<Long> findReadChapterIdsByUserAndStory(@Param("userId") Long userId, @Param("storyId") Long storyId);

    //kiem tra ton tai hay khong
    boolean existsReadingById(Long id);
}
