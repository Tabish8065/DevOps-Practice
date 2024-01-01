package com.blogging.Controller;

import java.util.List;

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

@RequestMapping("post/{postId}/comment/")
@RestController
public class CommentManagementController {

    private CommentService service;

    public CommentManagementController(CommentService service){
        this.service = service;
    }

    @PostMapping("create")
    public CommentModel createComment(@RequestBody CommentInputAPIDto comment, @PathVariable int postId) {
    	
    	System.out.println("Comment : "+comment+", Post Id: "+postId);
    	
        return service.createComment(comment, postId);
    }

    @GetMapping("{id}")
    public CommentModel readComment(@PathVariable int id) {
        return service.readComment(id);
        // return "Post id" + postId +", CommentId "+id;
    }

    @GetMapping("")
    public List<CommentModel> readCommentByPost(@PathVariable int postId) {
        return service.readCommentByPostId(postId);
    }

    @PutMapping("{commentId}")
    public CommentModel updateComment(@RequestBody CommentInputAPIDto comment, @PathVariable int commentId) {
        return service.updateComment(comment, commentId);
    }

    @DeleteMapping("{id}")
    public CommentModel deleteComment(@PathVariable int id) {
        return service.deleteComment(id);
    }
}
