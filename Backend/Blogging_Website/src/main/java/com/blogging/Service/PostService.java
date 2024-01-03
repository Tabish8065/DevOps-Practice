package com.blogging.Service;

import java.util.List;

import com.blogging.Model.PostModel;
import com.blogging.dto.PostAPIOutputDto;
import com.blogging.dto.PostInputAPIDto;

public interface PostService {
    
    public PostAPIOutputDto createPost(PostInputAPIDto post);
    public PostAPIOutputDto readPost(int id);
    public PostModel readPostModel(int id);
    public List<PostAPIOutputDto> readAllPost();
    public PostAPIOutputDto updatePost(PostInputAPIDto post, int id);
    public PostAPIOutputDto deletePost(int id);

}
