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

import com.blogging.Model.PostModel;
import com.blogging.Service.PostService;
import com.blogging.dto.PostInputAPIDto;

@RestController
@RequestMapping("post/")
public class PostManagementController {

    private PostService servcie;
    
    // @Autowired
    public PostManagementController(PostService service){
        this.servcie = service;
    }

    @PostMapping("create")
    public PostModel createPost(@RequestBody PostInputAPIDto post) {
    	
    	return servcie.createPost(post);
    }

    @GetMapping("getPost/{id}")
    public PostModel readPost(@PathVariable int id) {
        return servcie.readPost(id);
    }

    @GetMapping("getAll")
    public List<PostModel> readAllPost() {
        return servcie.readAllPost();
    }

    @PutMapping("update/{postId}")
    public PostModel updatePost(@RequestBody PostInputAPIDto post, @PathVariable int postId) {
    	
        return servcie.updatePost(post,postId);
    }

    @DeleteMapping("delete/{id}")
    public PostModel deletePost(@PathVariable int id) {
        return servcie.deletePost(id);
    }
    
   

}
