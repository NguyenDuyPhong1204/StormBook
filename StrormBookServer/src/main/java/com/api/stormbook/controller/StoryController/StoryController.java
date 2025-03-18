package com.api.stormbook.controller.StoryController;

import com.api.stormbook.dto.StoryDTO.StoryDTO;
import com.api.stormbook.entity.Chapter;
import com.api.stormbook.entity.Story;
import com.api.stormbook.entity.response.ApiResponse;
import com.api.stormbook.exception.NotFoundException;
import com.api.stormbook.repository.StoryRepository.StoryRepository;
import com.api.stormbook.service.StoryService.StoryAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/story")
public class StoryController {
    @Autowired
    private StoryAdminService storyAdminService;
    @Autowired
    private StoryRepository storyRepository;

    //lay danh sach truyen theo id the loai
    @GetMapping("/list-by-categoryId/{categoryId}")
    public ResponseEntity<?> listStoryByCategoryId(@PathVariable Long categoryId,
                                                   @RequestParam(defaultValue = "1") int page,
                                                   @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Story> result = storyRepository.findStoriesByCategoryId(categoryId, pageable);
        if(result.isEmpty()){
            throw new NotFoundException("Không có truyện nào!");
        }
        ApiResponse<List<Story>> response = new ApiResponse<>(HttpStatus.OK.value(), "Lấy danh sách truyện thành công", result.getContent());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all-story")
    public ResponseEntity<?> getAllStory(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Story> result = storyRepository.findAllStory(pageable);
        if(result.isEmpty()){
            throw new NotFoundException("Không có truyện nào!");
        }
        ApiResponse<List<Story>> response = new ApiResponse<>(HttpStatus.OK.value(), "Lấy danh sách truyện thành công", result.getContent());
        return ResponseEntity.ok(response);

    }

    //lay thong tin truyen theo id
    @GetMapping("/{storyId}")
    public ResponseEntity<?> getStoryById(@PathVariable Long storyId) {
        Story result = storyRepository.findStoryById(storyId);
        if(result == null){
            throw new NotFoundException("Không tìm thấy truyện!");
        }
       ApiResponse<Story> response = new ApiResponse<>(HttpStatus.OK.value(), "Lấy thông tin truyện thành công!", result);
        return ResponseEntity.ok(response);
    }

}
