package com.api.stormbook.dto.ReportDTO;

import com.api.stormbook.entity.Report;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponseDTO {
    private Long id;

    private Long userId;

    private Long storyId;

    private Long chapterId;

    private String type;

    private String content;

    private Report.Status status;

    private String fullName;

    private String avatar;

    private String nameStory;

    private String coverImage;

    private int chapterNumber;

    private Instant createdAt;

    private Instant updatedAt;
}
