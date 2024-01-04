package com.blogging.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.blogging.Service.CategoryService;
import com.blogging.dto.CategoryDto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryDto createCategory(@RequestBody CategoryDto category) {

        return categoryService.addCategory(category);

    }

    @GetMapping("/{id}")
    public CategoryDto getById(@PathVariable("id") int id) {

        return categoryService.readCategoryById(id);

    }

    @GetMapping("/all")
    public List<CategoryDto> getAll() {

        return categoryService.readAllCategory();

    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryDto updateCategory(@PathVariable int id, @RequestBody CategoryDto category) {

        return categoryService.updateCategory(category, id);

    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryDto delete(@PathVariable("id") int id) {

        return categoryService.deleteCategory(id);
    }

}
