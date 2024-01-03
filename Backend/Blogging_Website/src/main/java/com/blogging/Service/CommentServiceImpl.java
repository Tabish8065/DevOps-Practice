package com.blogging.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogging.Model.CommentModel;
import com.blogging.Repository.CommentRepo;
import com.blogging.dto.CommentInputAPIDto;

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
		return repo.findById(id).orElse(null);
	}

	@Override
	public List<CommentModel> readCommentByPostId(int postId) {
		return repo.findByPostId(postId);
	}

	@Override
	public CommentModel updateComment(CommentInputAPIDto comment, int commentId) {
		CommentModel commentModel = readComment(commentId);
		if(comment.getEmail() != null) commentModel.setEmail(comment.getEmail());
		if(comment.getBody() != null) commentModel.setBody(comment.getBody());
		if(comment.getName() != null) commentModel.setName(comment.getName());
		
		return repo.save(commentModel);
	}

	@Override
	public CommentModel deleteComment(int id) {
		// TODO Auto-generated method stub
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
