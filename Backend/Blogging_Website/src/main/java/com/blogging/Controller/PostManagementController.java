package com.blogging.Controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogging.Service.PostService;
import com.blogging.dto.PostAPIOutputDto;
import com.blogging.dto.PostInputAPIDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/post")
@Tag(
    name = "CRUD APIs for the Post Management"
)
public class PostManagementController {

    private PostService servcie;
    
    // @Autowired
    public PostManagementController(PostService service){
        this.servcie = service;
    }

    @Operation(
        summary = "Create Post REST API",
        description = "The API is used to create new post and save it to database"
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
    public PostAPIOutputDto createPost(@Valid @RequestBody PostInputAPIDto post) {
    	
    	return servcie.createPost(post);
    }

    @Operation(
        summary = "Read Post using Post Id REST API",
        description = "The API is used to read a post from the database using ID of the post"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status Code 200 Success"
    )
    @GetMapping("{id}")
    public PostAPIOutputDto readPost(@PathVariable int id) {
        return  servcie.readPost(id);
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
    public List<PostAPIOutputDto> readAllPost() {
        return servcie.readAllPost();
    }

    @Operation(
        summary = "Read Posts for a category using category Id",
        description = "The API is used to read posts from the database for any specific category"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 Success"
    )
    @GetMapping("/category/{id}")
    public List<PostAPIOutputDto> readPostByCategory(@PathVariable int id){

        return servcie.readPostByCategory(id);

    }

    @Operation(
        summary = "Update Post using Post Id and PostDTO model REST API",
        description = "The API is used to update the post by passing the post Id as pathvariable and the data to be updated in the request body"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 Success"
    )
    @SecurityRequirement(
        name = "Bearer Authentication"
    )
    @PutMapping("{postId}")
    @PreAuthorize("hasRole('ADMIN')")
    public PostAPIOutputDto updatePost(@RequestBody PostInputAPIDto post, @PathVariable int postId) {
    	
        return servcie.updatePost(post,postId);
    }

    @Operation(
        summary = "Delete Post using Post Id REST API",
        description = "Delete Post from database using post id along with all the comments associated with the post"
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
    public PostAPIOutputDto deletePost(@PathVariable int id) {
        return servcie.deletePost(id);
    }
    
   

}
