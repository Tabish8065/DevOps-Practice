package com.blogging.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogging.Model.CommentModel;

@Repository
public interface CommentRepo extends JpaRepository<CommentModel, Integer> {

	public List<CommentModel> findByPostId(int id);
}
