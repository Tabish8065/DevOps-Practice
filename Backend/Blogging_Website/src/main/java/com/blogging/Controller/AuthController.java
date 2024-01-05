package com.blogging.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogging.Model.JwtAuthResponse;
import com.blogging.Service.AuthService;
import com.blogging.dto.LoginDto;
import com.blogging.dto.RegisterDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@Tag(
    name = "REST APIs for authentication services"
)
public class AuthController {
    
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    };

    @Operation(
        summary = "Login API to get JWT Token",
        description = "Login using the username or email with password to get Token as reponse"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 Success"
    )
    @PostMapping("/login")
    public JwtAuthResponse login(@RequestBody LoginDto loginDto){
        
    	System.out.println("Login dto " + loginDto);
        String token = authService.login(loginDto);

        JwtAuthResponse resp = new JwtAuthResponse();
        resp.setAccessToken(token);

        return resp;
    }

    @Operation(
        summary = "REST API to register in database",
        description = "Register for accessing full REST APIs of blogging website"
    )
    @ApiResponse(
        responseCode = "201",
        description = "HTTP Status 201 Created"
    )
    @PostMapping("/register")
    public String postMethodName(@RequestBody RegisterDto registerDto) {
        
    	System.out.println("Resgister DTO : " + registerDto);
        return authService.register(registerDto);

    }
    

}
