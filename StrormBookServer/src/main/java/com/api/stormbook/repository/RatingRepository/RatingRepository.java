package com.api.stormbook.repository.RatingRepository;

import com.api.stormbook.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    @Query("SELECT COALESCE(AVG(r.rating), 0) FROM Rating r WHERE r.story.id = :storyId")
    Double findAverageRatingByStoryId(@Param("storyId") Long storyId);

}
