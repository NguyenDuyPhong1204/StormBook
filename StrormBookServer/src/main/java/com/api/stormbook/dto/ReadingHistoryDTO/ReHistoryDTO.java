package com.api.stormbook.dto.ReadingHistoryDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReHistoryDTO {
    private Long id;
    private Long userId;
    private Long storyId;
    private Long chapterId;
    private Boolean isRead;
    private Instant last_read_at;
}
