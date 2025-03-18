package com.api.stormbook.repository.ChapterRepository;

import com.api.stormbook.dto.ChapterDTO.ChapterDTO;
import com.api.stormbook.entity.Chapter;
import com.api.stormbook.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    Optional<Chapter> findById(Long id);

    List<Chapter> findByStoryId(Long storyId);
    //lay danh sach anh
    @Query("SELECT ci.imageUrl FROM ChapterImage ci WHERE ci.chapter.id = :chapterId")
    List<String> findImageUrl(@Param("chapterId") Long chapterId);

    //lay thong tin chapter
    @Query("SELECT c FROM Chapter c WHERE c.id = :chapterId")
    Chapter findChapterById(@Param("chapterId") Long chapterId);

    //check chuong
    boolean existsById(Long id);

    //lay danh sach chuong cua 1 truyen
    @Query("SELECT new com.api.stormbook.dto.ChapterDTO.ChapterDTO(" +
            "c.id, c.chapterNumber, c.title, c.story.id, " +
            "c.view_count, c.is_read,c.createdAt,c.updatedAt) FROM Chapter c WHERE c.story.id = :storyId")
    List<ChapterDTO> listChapterByStoryId(@Param("storyId") Long storyId);
}
