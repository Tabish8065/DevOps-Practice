package com.blogging.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogging.Model.PostModel;
import com.blogging.Repository.PostRepo;
import com.blogging.dto.CommentInputAPIDto;
import com.blogging.dto.PostAPIOutputDto;
import com.blogging.dto.PostInputAPIDto;

@Service
public class PostServiceImpl implements PostService {

    private PostRepo repo;
    private ModelMapper modelMapper;

    // @Autowired
    public PostServiceImpl(PostRepo repo, ModelMapper modelMapper){
        this.repo = repo;
        this.modelMapper = modelMapper;
    }

	@Override
	public PostAPIOutputDto createPost(PostInputAPIDto post) {
		
		/**
		*Mapping the PostInputAPIDto to Post for creation of new Post
		*with no comments
		*with default id
		*/
		
		PostModel postModel = postDTOToPostModel(post);
		
		return postModeltoPostDto(repo.save(postModel));
	}

	@Override
	public PostAPIOutputDto readPost(int id) {
		return postModeltoPostDto(repo.findById(id).orElse(null));
	}

	@Override
	public PostModel readPostModel(int id){
		return repo.findById(id).orElse(null).;
	} 

	@Override
	public List<PostAPIOutputDto> readAllPost() {
		return repo.findAll().stream()
					.map(post -> postModeltoPostDto(post))
					.collect(Collectors.toList());
	}

	@Override
	public PostAPIOutputDto updatePost(PostInputAPIDto post, int id) {
		
		/**
		 * Post should be mapped with the old post so that
		 * if any value is not to be updated then it should
		 * be fetched from previous value
		 */
		
		PostModel postModel = readPostModel(id);
		if(post.getTitle() != null) postModel.setTitle(post.getTitle()); 
		if(post.getDescription() != null) postModel.setDescription(post.getDescription()); 
		if(post.getContent() != null) postModel.setContent(post.getContent()); 
		
		return postModeltoPostDto(repo.save(postModel));
	}

	@Override
	public PostAPIOutputDto deletePost(int id) {
		PostModel post = readPostModel(id);
		repo.deleteById(id);
		return postModeltoPostDto(post);
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
    
	private PostAPIOutputDto postModeltoPostDto(PostModel postModel){

		PostAPIOutputDto posts = new PostAPIOutputDto();
		posts.setTitle(postModel.getTitle());
		posts.setContent(postModel.getContent());
		posts.setDescription(postModel.getDescription());

		posts.setComments(
			postModel.getComments().stream().map(
				commentModel -> {
					return new CommentInputAPIDto(
						commentModel.getEmail(),
						commentModel.getName(),
						commentModel.getBody()
					);
				}
			).collect(Collectors.toSet())
		);

		return posts;

	}
}
