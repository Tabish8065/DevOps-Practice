package com.blogging.Service;

import java.util.List;

import com.blogging.Model.CommentModel;
import com.blogging.dto.CommentInputAPIDto;

public interface CommentService {
    
    public CommentModel createComment(CommentInputAPIDto comment, int postId);
    public CommentModel readComment(int id);
    public List<CommentModel> readCommentByPostId(int postId);
    public CommentModel updateComment(CommentInputAPIDto post, int commentId);
    public CommentModel deleteComment(int id);

}