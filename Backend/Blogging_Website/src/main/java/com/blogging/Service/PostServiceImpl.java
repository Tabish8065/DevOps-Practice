package com.blogging.Service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogging.Model.CommentModel;
import com.blogging.Model.PostModel;
import com.blogging.Repository.PostRepo;
import com.blogging.dto.PostInputAPIDto;

@Service
public class PostServiceImpl implements PostService {

    private PostRepo repo;
    
    @Autowired
    private ModelMapper modelMapper;

    // @Autowired
    public PostServiceImpl(PostRepo repo){
        this.repo = repo;
        //this.modelMapper = modelMapper;
    }

	@Override
	public PostModel createPost(PostInputAPIDto post) {
		
		/**
		*Mapping the PostInputAPIDto to Post for creation of new Post
		*with no comments
		*with default id
		*/
		
		PostModel postModel = postDTOToPostModel(post);
		
		return repo.save(postModel);
	}

	@Override
	public PostModel readPost(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

	@Override
	public List<PostModel> readAllPost() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public PostModel updatePost(PostInputAPIDto post, int id) {
		
		/**
		 * Post should be mapped with the old post so that
		 * if any value is not to be updated then it should
		 * be fetched from previous value
		 */
		
		PostModel postModel = readPost(id);
		if(post.getTitle() != null) postModel.setTitle(post.getTitle()); 
		if(post.getDescription() != null) postModel.setDescription(post.getDescription()); 
		if(post.getContent() != null) postModel.setContent(post.getContent()); 
		
		return repo.save(postModel);
	}

	@Override
	public PostModel deletePost(int id) {
		// TODO Auto-generated method stub
		PostModel post = readPost(id);
		repo.deleteById(id);
		return post;
	}

	 private PostModel postDTOToPostModel(PostInputAPIDto postDto) {
//	    	PostModel post = new PostModel();
//	    	post.setTitle(postDto.getTitle());
//	    	post.setDescription(postDto.getDescription());
//	    	post.setContent(postDto.getContent());
//	    	post.setComments(new ArrayList<CommentModel>());
		 
		 	PostModel post = modelMapper.map(postDto, PostModel.class);
		 	System.out.println(post);
	    	
	    	return post;
	    }
    
}