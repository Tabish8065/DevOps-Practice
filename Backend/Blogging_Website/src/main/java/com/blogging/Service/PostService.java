package com.blogging.Service;

import java.util.List;

import com.blogging.Model.PostModel;
import com.blogging.dto.PostInputAPIDto;

public interface PostService {
    
    public PostModel createPost(PostInputAPIDto post);
    public PostModel readPost(int id);
    public List<PostModel> readAllPost();
    public PostModel updatePost(PostInputAPIDto post, int id);
    public PostModel deletePost(int id);

}
