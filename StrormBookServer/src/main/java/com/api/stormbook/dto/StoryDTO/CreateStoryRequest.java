package com.api.stormbook.dto.StoryDTO;

import com.api.stormbook.entity.Category;
import com.api.stormbook.entity.Story;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateStoryRequest {
//    @RequestParam("title") String title,
//    @RequestParam("author") String author,
//    @RequestParam("transGroup") String transGroup,
//    @RequestParam("categories")List<Category> categories,
//    @RequestParam("description") String description,
    private String title;
    private String author;
    private String transGroup;
    private List<Category> categories;
    private String description;
}
