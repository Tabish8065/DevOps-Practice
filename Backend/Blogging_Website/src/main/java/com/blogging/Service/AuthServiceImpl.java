package com.blogging.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogging.Model.RoleModel;
import com.blogging.Model.UserModel;
import com.blogging.Repository.RoleRepo;
import com.blogging.Repository.UserRepo;
import com.blogging.dto.LoginDto;
import com.blogging.dto.RegisterDto;
import com.blogging.exception.BlogAPIException;

@Service
public class AuthServiceImpl implements AuthService{

    private AuthenticationManager authenticationManager;
    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepo userRepo, RoleRepo roleRepo,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            loginDto.usernameOrEmail(), loginDto.password()
        ));

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        return "User Logged-in successfully";

    }

    @Override
    public String register(RegisterDto registerDto) {
        
        if(userRepo.existsByUsername(registerDto.username())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "UserName Already exist");
        }

        if(userRepo.existsByEmail(registerDto.email())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email Already exist");
        }

        UserModel user = new UserModel();
        user.setName(registerDto.name());
        user.setEmail(registerDto.email());
        user.setPassword(registerDto.password());
        user.setUsername(registerDto.username());

        Set<RoleModel> roles = new HashSet<>();
        RoleModel usrRole = roleRepo.findByName("ROLE_USER").get();
        roles.add(usrRole);

        user.setRoles(roles);
        userRepo.save(user);

        return "User has been successfully added";
    }
    
}
