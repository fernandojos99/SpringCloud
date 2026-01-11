package com.udemy.auth_server.services;

import com.udemy.auth_server.dtos.TokenDto;
import com.udemy.auth_server.dtos.UserDto;

public interface AuthService {

    TokenDto login(UserDto user);
    TokenDto validateToken(TokenDto token);
}