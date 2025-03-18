package com.api.stormbook.dto.ChapterDTO;

import java.time.Instant;

public interface ResponseChapterDTO {
    Long getId();
    String getTitle();
    Long getStoryId();
    int getViewCount();
    Boolean getIsRead();
    Instant getCreatedAt();
    Instant getUpdatedAt();

    // Trả về đường dẫn ảnh dạng String (sẽ xử lý ở Controller)
    String getImageUrls();
}
