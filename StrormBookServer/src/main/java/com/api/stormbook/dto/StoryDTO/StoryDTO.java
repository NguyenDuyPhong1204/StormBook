package com.api.stormbook.dto.StoryDTO;

import com.api.stormbook.entity.Category;
import com.api.stormbook.entity.Story;
import com.api.stormbook.entity.Story.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoryDTO {
    private long id;
    private String title;
    private String author;
    private String transGroup;
    private List<Long> categoryId;
    private String cover_image;
    private Status status = Status.OnGoing;
    private int view_count = 0;
    private int rating = 0;
    private int total_chapters = 0;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;

    private MultipartFile imageFile;

}
