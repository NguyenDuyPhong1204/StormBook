package com.api.stormbook.dto.ReportDTO;

import com.api.stormbook.entity.Chapter;
import com.api.stormbook.entity.Report;
import com.api.stormbook.entity.Story;
import com.api.stormbook.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {
    private Long id;

    private Long userId;

    private Long storyId;

    private Long chapterId;

    private String type;
    
    private String content;

    private Report.Status status;

    private Instant createdAt;

    private Instant updatedAt;
}
