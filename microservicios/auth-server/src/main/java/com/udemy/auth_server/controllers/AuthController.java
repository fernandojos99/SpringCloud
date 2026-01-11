package com.udemy.auth_server.controllers;



import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.udemy.auth_server.dtos.TokenDto;
import com.udemy.auth_server.dtos.UserDto;
import com.udemy.auth_server.services.AuthService;

@RestController
@RequestMapping(path = "auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "login") //password secret
    public ResponseEntity<TokenDto> jwtCreate(@RequestBody UserDto user) {
        return ResponseEntity.ok(this.authService.login(user));
    }

    @PostMapping(path = "jwt")
    //Aqui no lo recibimos por body si no por header ya que es una convencion
    
    public ResponseEntity<TokenDto> jwtValidate(@RequestHeader String accessToken) {
        return
                ResponseEntity.ok(
                        this.authService.validateToken(TokenDto.builder().accessToken(accessToken).build()));
    }
}