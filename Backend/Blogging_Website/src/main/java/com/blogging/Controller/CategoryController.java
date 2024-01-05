package com.blogging.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.blogging.Service.CategoryService;
import com.blogging.dto.CategoryDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

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
@Tag(
        name = "CRUD APIs for the Category Management"
)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(
        summary = "Create Category REST API",
        description = "The API is used to create new category and save it to database"
    )
    @ApiResponse(
        responseCode = "201",
        description = "HTTP Status code 201 Created Successfully"
    )
    @SecurityRequirement(
        name = "Bearer Authentication"
    )
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryDto createCategory(@RequestBody CategoryDto category) {

        return categoryService.addCategory(category);

    }

    @Operation(
        summary = "Read category using category Id REST API",
        description = "The API is used to read a category from the database using ID of the category"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status Code 200 Success"
    )
    @GetMapping("{id}")
    public CategoryDto getById(@PathVariable("id") int id) {

        return categoryService.readCategoryById(id);

    }

    @Operation(
        summary = "Read All Posts REST API",
        description = "The API is used to read a all the posts from the database"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 Success"
    )
    @GetMapping
    public List<CategoryDto> getAll() {

        return categoryService.readAllCategory();

    }

    @Operation(
        summary = "Update Category using Category Id and CategoryDTO model REST API",
        description = "The API is used to update the category by passing the category Id as pathvariable and the data to be updated in the request body"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 Success"
    )
    @SecurityRequirement(
        name = "Bearer Authentication"
    )
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryDto updateCategory(@PathVariable int id, @RequestBody CategoryDto category) {

        return categoryService.updateCategory(category, id);

    }

    @Operation(
        summary = "Delete Category using Category Id REST API",
        description = "Delete Category from database using category id along with all the comments associated with the category"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 Success"
    )
    @SecurityRequirement(
        name = "Bearer Authentication"
    )
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryDto delete(@PathVariable("id") int id) {

        return categoryService.deleteCategory(id);
    }

}
