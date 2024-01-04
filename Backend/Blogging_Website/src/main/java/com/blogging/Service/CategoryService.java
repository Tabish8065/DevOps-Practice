package com.blogging.Service;

import java.util.List;

import com.blogging.dto.CategoryDto;

public interface CategoryService {
    
    public CategoryDto addCategory(CategoryDto category);
    public CategoryDto readCategoryById(int id);
    public List<CategoryDto> readAllCategory();
    public CategoryDto updateCategory(CategoryDto category, int id);
    public CategoryDto deleteCategory(int id);

}
