package com.blogging.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogging.Model.CategoryModel;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryModel, Integer> {

    boolean existsByName(String name);
    
}
