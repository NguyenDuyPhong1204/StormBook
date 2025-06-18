package com.api.stormbook.dto.ChapterDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChapterReadStatusDTO {
//    chapter.getId(),
//            chapter.getChapterNumber(),
//            chapter.getTitle(),
//            readChapterIds.contains(chapter.getId())
    private long id;
    private int chapterNumber;
    private String title;
    private long storyId;
    private int view_count = 0;
    private Boolean is_read = false;
    private List<String> image_url;
    private Instant createdAt;
    private Instant updatedAt;

}
