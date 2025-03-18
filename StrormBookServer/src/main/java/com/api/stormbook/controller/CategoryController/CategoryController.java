package com.api.stormbook.controller.CategoryController;

import com.api.stormbook.dto.CategoryDTO.CategoryDTO;
import com.api.stormbook.dto.CategoryDTO.CategoryRequest;
import com.api.stormbook.entity.Category;
import com.api.stormbook.entity.response.ApiResponse;
import com.api.stormbook.exception.NotFoundException;
import com.api.stormbook.repository.StoryRepository.StoryRepository;
import com.api.stormbook.service.CategoryService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private StoryRepository storyRepository;

    //them the loai moi
    @PostMapping("/create-category")
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest request) {
        CategoryDTO result = categoryService.createCategory(request);
        ApiResponse<CategoryDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Thêm mới thể loại thành công!", result);
        return ResponseEntity.ok(response);
    }

    //lay danh sach the loai
    @GetMapping("/list-category")
    public ResponseEntity<?> listCategory() {
        List<CategoryDTO> result = categoryService.getAllCategories();
        ApiResponse<List<CategoryDTO>> response = new ApiResponse<>(HttpStatus.OK.value(), "Lấy danh sách thành công!", result);
        return ResponseEntity.ok(response);
    }

    //lay danh sach the loai theo id truyen
    @GetMapping("/category-by-storeId/{id}")
    public ResponseEntity<?> getStoryCategories(@PathVariable Long id) {
        List<Category> categories = storyRepository.findCategoriesByStoryId(id);
        ApiResponse<List<Category>> response = new ApiResponse<>(HttpStatus.OK.value(), "Lấy danh sách thể loại thành công!", categories);
        if (categories.isEmpty()) {
            throw new NotFoundException("Không tìm thấy thể loại cho truyện này");
        }
        return ResponseEntity.ok(response);
    }
}
