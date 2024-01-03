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

import com.blogging.Service.PostService;
import com.blogging.dto.PostAPIOutputDto;
import com.blogging.dto.PostInputAPIDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("post/")
public class PostManagementController {

    private PostService servcie;
    
    // @Autowired
    public PostManagementController(PostService service){
        this.servcie = service;
    }

    @PostMapping("create")
    public PostAPIOutputDto createPost(@Valid @RequestBody PostInputAPIDto post) {
    	
    	return servcie.createPost(post);
    }

    @GetMapping("getPost/{id}")
    public PostAPIOutputDto readPost(@PathVariable int id) {
        return  servcie.readPost(id);
    }

    @GetMapping("getAll")
    public List<PostAPIOutputDto> readAllPost() {
        return servcie.readAllPost();
    }

    @PutMapping("update/{postId}")
    public PostAPIOutputDto updatePost(@RequestBody PostInputAPIDto post, @PathVariable int postId) {
    	
        return servcie.updatePost(post,postId);
    }

    @DeleteMapping("delete/{id}")
    public PostAPIOutputDto deletePost(@PathVariable int id) {
        return servcie.deletePost(id);
    }
    
   

}
