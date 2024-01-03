package com.blogging.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogging.Model.RoleModel;

@Repository
public interface RoleRepo extends JpaRepository<RoleModel, Integer> {
    
    Optional<RoleModel> findByName(String name);

}
