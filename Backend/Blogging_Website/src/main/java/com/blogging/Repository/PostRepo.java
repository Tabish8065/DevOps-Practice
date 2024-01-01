package com.blogging.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogging.Model.PostModel;

@Repository
public interface PostRepo extends JpaRepository<PostModel, Integer>{

}
