package com.blogging.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogging.Model.CommentModel;
import com.blogging.Repository.CommentRepo;
import com.blogging.dto.CommentInputAPIDto;
import com.blogging.exception.ResourceNotFoundException;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepo repo;
    private PostService postService;

    public CommentServiceImpl(CommentRepo repo, PostService postService){
        this.repo = repo;
        this.postService = postService;
    }

	@Override
	public CommentModel createComment(CommentInputAPIDto comment, int postId) {
		CommentModel commentModel = this.commentDtoToCommentModel(comment, postId);
		System.out.println("Service -> "+commentModel);
		return repo.save(commentModel);
	}

	@Override
	public CommentModel readComment(int id) {
		return repo.findById(id).orElseThrow(
			() -> new ResourceNotFoundException("Comment", "Comment Id", String.valueOf(id))
		);
	}

	@Override
	public List<CommentModel> readCommentByPostId(int postId) {
		return repo.findByPostId(postId);
	}

	@Override
	public CommentModel updateComment(CommentInputAPIDto comment, int commentId) {
		CommentModel commentModel = readComment(commentId);

		if(comment.getEmail() != null && 
			comment.getEmail().equals(commentModel.getEmail()) == false) 
				throw new RuntimeException("The email cannot be change once the comment is created");

		if(comment.getName() != null && 
			comment.getName().equals(commentModel.getName()) == false) 
				throw new RuntimeException("The name cannot be change once the comment is created");

		if(comment.getBody() != null) commentModel.setBody(comment.getBody());
		
		return repo.save(commentModel);
	}

	@Override
	public CommentModel deleteComment(int id) {
		CommentModel temp = readComment(id);
		repo.deleteById(id);
		return temp;
	}

	private CommentModel commentDtoToCommentModel(CommentInputAPIDto comment,int postId) {
		
		CommentModel commentModel = new CommentModel();
		commentModel.setName(comment.getName());
		commentModel.setBody(comment.getBody());
		commentModel.setEmail(comment.getEmail());
		commentModel.setPost(postService.readPostModel(postId));
		
		return commentModel;
	}
    
}
