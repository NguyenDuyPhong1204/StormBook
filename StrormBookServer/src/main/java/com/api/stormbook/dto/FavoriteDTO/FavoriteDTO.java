package com.api.stormbook.dto.FavoriteDTO;

import com.api.stormbook.entity.Story;
import com.api.stormbook.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDTO {
    private Long id;

    private Long userId;

    private Long storyId;

    private Instant createdAt;
}
