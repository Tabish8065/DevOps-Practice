package com.blogging.Controller;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("post/")
public class PostManagementController {

    private PostService servcie;
    
    // @Autowired
    public PostManagementController(PostService service){
        this.servcie = service;
    }

    @PostMapping("create")
    public ResponseEntity<PostAPIOutputDto> createPost(@RequestBody PostInputAPIDto post) {
    	
    	return new ResponseEntity<PostAPIOutputDto>(servcie.createPost(post),
                        HttpStatusCode.valueOf(201));
    }

    @GetMapping("getPost/{id}")
    public ResponseEntity<PostAPIOutputDto> readPost(@PathVariable int id) {
        return new ResponseEntity<PostAPIOutputDto>(servcie.readPost(id),
                        HttpStatusCode.valueOf(201));
    }

    @GetMapping("getAll")
    public List<PostAPIOutputDto> readAllPost() {
        return servcie.readAllPost();
    }

    @PutMapping("update/{postId}")
    public ResponseEntity<PostAPIOutputDto> updatePost(@RequestBody PostInputAPIDto post, @PathVariable int postId) {
    	
        return new ResponseEntity<PostAPIOutputDto>(servcie.updatePost(post,postId),
                        HttpStatusCode.valueOf(201));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<PostAPIOutputDto> deletePost(@PathVariable int id) {
        return new ResponseEntity<PostAPIOutputDto>(servcie.deletePost(id),
                        HttpStatusCode.valueOf(201));
    }
    
   

}
