package com.api.stormbook.mapper.CategoryMapper;

import com.api.stormbook.dto.CategoryDTO.CategoryDTO;
import com.api.stormbook.entity.Category;

public class CategoryMapper {
    public static CategoryDTO toCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        return categoryDTO;
    }
}
