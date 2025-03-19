package com.api.stormbook.dto.ChapterDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChapterReadStatusDTO {
//    chapter.getId(),
//            chapter.getChapterNumber(),
//            chapter.getTitle(),
//            readChapterIds.contains(chapter.getId())
    private Long chapterId;
    private int chapterNumber;
    private String title;
    private boolean readStatus;
}
