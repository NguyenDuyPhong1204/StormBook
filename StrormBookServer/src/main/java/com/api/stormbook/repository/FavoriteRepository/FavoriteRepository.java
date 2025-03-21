package com.api.stormbook.repository.FavoriteRepository;

import com.api.stormbook.entity.Favorite;
import com.api.stormbook.entity.Story;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    // Lấy danh sách truyện yêu thích của người dùng
    @Query("SELECT f.story FROM Favorite f WHERE f.user.id = :userId")
    Page<Story> findFavoriteStoriesByUserId(@Param("userId") Long userId, Pageable pageable);

    //kiem tra ton tai
    boolean existsById(Long id);

    //kiem tra da ton tai trong favorite
    boolean existsByStoryIdAndUserId(Long storyId, Long userId);
}
