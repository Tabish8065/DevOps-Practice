package com.blogging.Service;

import com.blogging.dto.LoginDto;
import com.blogging.dto.RegisterDto;

public interface AuthService{

    public String login(LoginDto loginDto);
    public String register(RegisterDto registerDto);
    
}
