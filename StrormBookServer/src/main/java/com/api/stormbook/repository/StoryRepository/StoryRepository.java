package com.api.stormbook.repository.StoryRepository;

import com.api.stormbook.entity.Category;
import com.api.stormbook.entity.Story;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
    Optional<Story> findById(Long id);

    //lay danh sach the loai theo id truyen
    @Query("SELECT s.categories FROM Story s WHERE s.id = :storyId")
    List<Category> findCategoriesByStoryId(@Param("storyId") Long storyId);

    //lay danh sach truyen theo id the loai
    @Query("SELECT s FROM Story s JOIN s.categories c WHERE c.id = :categoryId")
    Page<Story> findStoriesByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);

    //lay tat ca danh sach truyen
    @Query("SELECT s FROM Story s")
    Page<Story> findAllStory(Pageable pageable);

    //lay thong tin truyen theo id truyen
    @Query("SELECT s FROM Story s WHERE s.id = :storyId")
    Story findStoryById(@Param("storyId") Long storyId);

    //check xem truyen co ton tai hay khong
    boolean existsById(Long id);

}
