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

import com.blogging.Model.CommentModel;
import com.blogging.Service.CommentService;
import com.blogging.dto.CommentInputAPIDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RequestMapping("/api/post/{postId}/comment/")
@RestController
@Tag(
        name = "CRUD APIs for the Comment Management"
)
public class CommentManagementController {

    private CommentService service;

    public CommentManagementController(CommentService service) {
        this.service = service;
    }

    @Operation(
        summary = "Create Comment REST API",
        description = "The API is used to create new comment and save it to database"
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
    public CommentModel createComment(@Valid @RequestBody CommentInputAPIDto comment, @PathVariable int postId) {

        System.out.println("Comment : " + comment + ", Post Id: " + postId);

        return service.createComment(comment, postId);
    }

    @Operation(
        summary = "Read comment using comment Id REST API",
        description = "The API is used to read a comment from the database using ID of the comment"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status Code 200 Success"
    )
    @GetMapping("{id}")
    public CommentModel readComment(@PathVariable int id) {
        return service.readComment(id);
        // return "Post id" + postId +", CommentId "+id;
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
    public List<CommentModel> readCommentByPost(@PathVariable int postId) {
        return service.readCommentByPostId(postId);
    }

    @Operation(
        summary = "Update Comment using Comment Id and CommentDTO model REST API",
        description = "The API is used to update the comment by passing the comment Id as pathvariable and the data to be updated in the request body"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 Success"
    )
    @SecurityRequirement(
        name = "Bearer Authentication"
    )
    @PutMapping("{commentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public CommentModel updateComment(@RequestBody CommentInputAPIDto comment, @PathVariable int commentId) {
        return service.updateComment(comment, commentId);
    }

    @Operation(
        summary = "Delete Comment using Comment Id REST API",
        description = "Delete Comment from database using comment id along with all the comments associated with the comment"
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
    public CommentModel deleteComment(@PathVariable int id) {
        return service.deleteComment(id);
    }
}
