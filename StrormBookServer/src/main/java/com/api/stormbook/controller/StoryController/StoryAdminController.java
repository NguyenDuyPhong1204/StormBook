package com.api.stormbook.controller.StoryController;

import com.api.stormbook.dto.StoryDTO.StoryDTO;
import com.api.stormbook.entity.Category;
import com.api.stormbook.entity.Story;
import com.api.stormbook.entity.response.ApiMailResponse;
import com.api.stormbook.entity.response.ApiResponse;
import com.api.stormbook.exception.ErrorException;
import com.api.stormbook.exception.NotFoundException;
import com.api.stormbook.repository.StoryRepository.StoryRepository;
import com.api.stormbook.service.StoryService.StoryAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/a/story")
public class StoryAdminController {

    @Autowired
    private StoryAdminService storyAdminService;
    @Autowired
    private StoryRepository storyRepository;

    @PostMapping("/create-story")
    public ResponseEntity<?> createStory(
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("transGroup") String transGroup,
            @RequestParam("categories") List<Long> categoryIds, // Truyền danh sách ID thay vì object
            @RequestParam("description") String description,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile // Có thể không bắt buộc
    ) {
        try {
            StoryDTO storyDTO = new StoryDTO();
            storyDTO.setTitle(title);
            storyDTO.setAuthor(author);
            storyDTO.setTransGroup(transGroup);
            storyDTO.setDescription(description);
            storyDTO.setCategoryId(categoryIds); // Set danh sách ID thay vì object
            storyDTO.setImageFile(imageFile);

            StoryDTO result = storyAdminService.createStory(storyDTO);
            ApiResponse<StoryDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Thêm mới truyện thành công!", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi tạo truyện: " + e.getMessage());
        }
    }

    @PutMapping("/{storyId}")
    public ResponseEntity<?> updateStory(
            @PathVariable Long storyId,
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("transGroup") String transGroup,
            @RequestParam("categories") List<Long> categoryIds, // Truyền danh sách ID thay vì object
            @RequestParam("description") String description,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile
    ){
        try {
            StoryDTO storyDTO = new StoryDTO();
            storyDTO.setTitle(title);
            storyDTO.setAuthor(author);
            storyDTO.setTransGroup(transGroup);
            storyDTO.setDescription(description);
            storyDTO.setCategoryId(categoryIds); // Set danh sách ID thay vì object
            storyDTO.setImageFile(imageFile);

            StoryDTO result = storyAdminService.updateStory(storyId, storyDTO);

            ApiResponse<StoryDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Cập nhật truyện thành công", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi chỉnh sửa truyện: " + e.getMessage());
        }
    }

    @DeleteMapping("/{storyId}")
    public ResponseEntity<?> deleteStory(
            @PathVariable Long storyId
    ){
        try{
            storyAdminService.deleteStory(storyId);
            ApiMailResponse response = new ApiMailResponse(HttpStatus.OK.value(), "Xóa truyện thành công");
            return ResponseEntity.ok(response);
        }catch (Exception e){
            throw new ErrorException("Lỗi xóa truyện: "+ e.getMessage());
        }
    }
}
