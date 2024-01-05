package com.blogging.Service;

import java.util.ArrayList;
import java.util.List;

import com.blogging.Model.CategoryModel;
import com.blogging.Repository.CategoryRepo;
import com.blogging.dto.CategoryDto;
import com.blogging.exception.ResourceNotFoundException;

import io.micrometer.common.util.StringUtils;

public class CategoryServiceImpl implements CategoryService {

    private CategoryRepo repo;

    public CategoryServiceImpl(CategoryRepo repo){
        this.repo = repo;
    }

    @Override
    public CategoryDto addCategory(CategoryDto category) {

        //Check if the category already exist
        if(repo.existsByName(category.getName())){
            throw new RuntimeException("Category already exist with the name "+category.getName());
        }

        CategoryModel model = repo.save(dtoToModel(category));

        return modelToDto(model);

    }

    @Override
    public CategoryDto readCategoryById(int id) {
        
        return modelToDto(
            repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", String.valueOf(id)))
        );
    }

    @Override
    public List<CategoryDto> readAllCategory() {
        
        return repo.findAll().stream().map(
            category -> modelToDto(category)
        ).toList();

    }

    @Override
    public CategoryDto updateCategory(CategoryDto category, int id) {
        CategoryModel existingCategory = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", String.valueOf(id)));

        if(StringUtils.isNotBlank(category.getName())) existingCategory.setName(category.getName());
        if(StringUtils.isNotBlank(category.getDescription())) existingCategory.setDescription(category.getDescription());

        return modelToDto(existingCategory);
        
    }

    @Override
    public CategoryDto deleteCategory(int id) {
        
        CategoryDto category = readCategoryById(id);
        repo.deleteById(id);
        return category;

    }

    //Mapper

    private CategoryModel dtoToModel(CategoryDto dto){
        CategoryModel model = new CategoryModel();

        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        model.setPosts(new ArrayList<>());

        return model;
    }

    private CategoryDto modelToDto(CategoryModel model){
        CategoryDto dto = new CategoryDto();

        dto.setName(model.getName());
        dto.setDescription(model.getDescription());

        return dto;
    }
    
}
