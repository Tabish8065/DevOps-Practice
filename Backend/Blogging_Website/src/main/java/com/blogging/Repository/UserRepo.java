package com.blogging.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogging.Model.UserModel;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Integer> {
    
    Optional<UserModel> findByEmail(String email);
    Optional<UserModel> findByUsername(String username);
    Optional<UserModel> findByUsernameOrEmail(String username, String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
