package com.api.stormbook.service.CategoryService;

import com.api.stormbook.dto.AuthDTO.UserDTO;
import com.api.stormbook.dto.CategoryDTO.CategoryDTO;
import com.api.stormbook.dto.CategoryDTO.CategoryRequest;
import com.api.stormbook.entity.Category;
import com.api.stormbook.exception.ExistingException;
import com.api.stormbook.mapper.CategoryMapper.CategoryMapper;
import com.api.stormbook.repository.CategoryRepository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO createCategory(CategoryRequest request) {
        //kiem tra xem ten da ton tai hay chua
        Optional<Category> existingCategory = categoryRepository.findByName(request.getName().toLowerCase());
        if (existingCategory.isPresent()) {
            throw new ExistingException("Thể loại này đã tồn tại vui lòng nhập thể loại khác!");
        }
        //luu the loai vao database
        Category newCategory = new Category(request.getName(), request.getDescription());
        categoryRepository.save(newCategory);
        return new CategoryDTO(
                newCategory.getId(),
                newCategory.getName(),
                newCategory.getDescription()
        );
    }

    public List<CategoryDTO>getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(CategoryMapper::toCategoryDTO).toList();
    }

}
