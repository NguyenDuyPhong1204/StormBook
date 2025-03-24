package com.api.stormbook.dto.RatingDTO;

import com.api.stormbook.entity.Story;
import com.api.stormbook.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO {
    private Long id;

    private Long userId;

    private Long storyId;

    private double rating;

    private String review;

    private Instant createdAt;

}
