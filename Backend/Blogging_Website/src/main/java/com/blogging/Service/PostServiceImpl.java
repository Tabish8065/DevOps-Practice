package com.blogging.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.blogging.Model.CategoryModel;
import com.blogging.Model.PostModel;
import com.blogging.Repository.CategoryRepo;
import com.blogging.Repository.PostRepo;
import com.blogging.dto.CommentInputAPIDto;
import com.blogging.dto.PostAPIOutputDto;
import com.blogging.dto.PostInputAPIDto;
import com.blogging.exception.ResourceNotFoundException;

@Service
public class PostServiceImpl implements PostService {

	private PostRepo repo;
	private ModelMapper modelMapper;
	private CategoryRepo categoryRepo;

	// @Autowired
	public PostServiceImpl(PostRepo repo, ModelMapper modelMapper, CategoryRepo categoryRepo) {
		this.repo = repo;
		this.modelMapper = modelMapper;
		this.categoryRepo = categoryRepo;
	}

	@Override
	public PostAPIOutputDto createPost(PostInputAPIDto post) {
		
		/**
		*Mapping the PostInputAPIDto to Post for creation of new Post
		*with no comments
		*with default id
		*/
		
		PostModel postModel = postDTOToPostModel(post);

		//Set Category to the post
		CategoryModel category = categoryRepo.findById(post.getCategoryId()).orElseThrow(
			() -> new ResourceNotFoundException("Category", "Id", ""+post.getCategoryId());
		);
		postModel.setCategory(category);
		
		return postModeltoPostDto(repo.save(postModel));
	}

	@Override
	public PostAPIOutputDto readPost(int id) {
		return postModeltoPostDto(repo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Post", "Post Id", String.valueOf(id))));
	}

	@Override
	public PostModel readPostModel(int id) {
		return repo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Post", "Post Id", String.valueOf(id)));
	}

	@Override
	public List<PostAPIOutputDto> readAllPost() {
		return repo.findAll().stream()
				.map(post -> postModeltoPostDto(post))
				.collect(Collectors.toList());
	}

	@Override
	public List<PostAPIOutputDto> readPostByCategory(int id) {
		
		return repo.findByCategoryId(id).stream()
				.map(post -> postModeltoPostDto(post))
				.toList();

	}

	@Override
	public PostAPIOutputDto updatePost(PostInputAPIDto post, int id) {

		/**
		 * Post should be mapped with the old post so that
		 * if any value is not to be updated then it should
		 * be fetched from previous value
		 */

		PostModel postModel = readPostModel(id);
		if (post.getTitle() != null)
			postModel.setTitle(post.getTitle());
		if (post.getDescription() != null)
			postModel.setDescription(post.getDescription());
		if (post.getContent() != null)
			postModel.setContent(post.getContent());
		if (post.getCategoryId() != postModel.getCategory().getId()) {
			if(categoryRepo.existsById(post.getCategoryId()))
				postModel.getCategory().setId(post.getCategoryId());
			else
				throw new ResourceNotFoundException("Category", "Id", String.valueOf(post.getCategoryId()));
		}

		return postModeltoPostDto(repo.save(postModel));
	}

	@Override
	public PostAPIOutputDto deletePost(int id) {
		PostModel post = readPostModel(id);
		repo.deleteById(id);
		return postModeltoPostDto(post);
	}

	private PostModel postDTOToPostModel(PostInputAPIDto postDto) {
		// PostModel post = new PostModel();
		// post.setTitle(postDto.getTitle());
		// post.setDescription(postDto.getDescription());
		// post.setContent(postDto.getContent());
		// post.setComments(new ArrayList<CommentModel>());

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

		CategoryModel category = categoryRepo.findById(post.getCategoryId()).orElseThrow(
			() -> new ResourceNotFoundException("Category", "Id", ""+post.getCategoryId());
		);

		posts.setCategory(new CategoryDto(category.getName(), category.getDescription()));

		return posts;

	}

}
