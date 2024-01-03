package com.blogging.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogging.Model.UserModel;
import com.blogging.Repository.UserRepo;

@Service
public class CustomUserDetailsServcie implements UserDetailsService{

    private UserRepo userRepo;

    public CustomUserDetailsServcie(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        
        UserModel user = userRepo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(
                    () -> new UsernameNotFoundException("User with username or email "+ usernameOrEmail + " not found.")
                );

        Set<GrantedAuthority> authorities =
                            user.getRoles().stream().map(role -> 
                                new SimpleGrantedAuthority(role.getName())
                            ).collect(Collectors.toSet());

        return new User(user.getEmail(), user.getPassword(), authorities);


    }
    
}
