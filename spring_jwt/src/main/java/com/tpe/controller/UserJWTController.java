package com.tpe.controller;

import com.tpe.controller.dto.LoginRequest;
import com.tpe.controller.dto.RegisterRequest;
import com.tpe.security.JwtUtils;
import com.tpe.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping
@AllArgsConstructor
public class UserJWTController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;


//************Register*************
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequest registerRequest){

        userService.registerRequest(registerRequest);
        String message = "Kullanici kaydiniz basari ile gerceklesmistir!";

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login (@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword()));

        String token = jwtUtils.generateToken(authentication);
        return new ResponseEntity<>(token,HttpStatus.CREATED);
    }















}
