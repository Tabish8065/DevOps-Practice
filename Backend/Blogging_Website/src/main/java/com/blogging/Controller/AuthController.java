package com.blogging.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogging.Model.JwtAuthResponse;
import com.blogging.Service.AuthService;
import com.blogging.dto.LoginDto;
import com.blogging.dto.RegisterDto;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    };

    @PostMapping("/login")
    public JwtAuthResponse login(@RequestBody LoginDto loginDto){
        
        String token = authService.login(loginDto);

        JwtAuthResponse resp = new JwtAuthResponse();
        resp.setAccessToken(token);

        return resp;
    }

    @PostMapping("/register")
    public String postMethodName(@RequestBody RegisterDto registerDto) {
        
        return authService.register(registerDto);

    }
    

}
