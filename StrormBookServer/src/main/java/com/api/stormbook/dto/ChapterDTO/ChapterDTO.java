package com.api.stormbook.dto.ChapterDTO;

import com.api.stormbook.entity.Chapter;
import com.api.stormbook.entity.Story;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChapterDTO {
    private long id;
    private int chapterNumber;
    private String title;
    private long storyId;
    private int view_count = 0;
    private Boolean is_read = false;
    private List<String> image_url;
    private Instant createdAt;
    private Instant updatedAt;
    private List<MultipartFile> imageFiles;

    public ChapterDTO(Chapter chapter) {
        this.id = chapter.getId();
        this.title = chapter.getTitle();
        this.chapterNumber = chapter.getChapterNumber();
        this.storyId = chapter.getStory().getId();
        this.view_count = chapter.getView_count();
        this.is_read = chapter.getIs_read();
        this.image_url = chapter.getImage_url();
        this.createdAt = chapter.getCreatedAt();
        this.updatedAt = chapter.getUpdatedAt();
    }

    public ChapterDTO(long id, int chapterNumber, String title, long storyId, int view_count, Boolean is_read, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.chapterNumber = chapterNumber;
        this.title = title;
        this.storyId = storyId;
        this.view_count = view_count;
        this.is_read = is_read;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
