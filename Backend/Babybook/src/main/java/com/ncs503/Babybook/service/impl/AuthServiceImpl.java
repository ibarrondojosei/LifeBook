package com.ncs503.Babybook.service.impl;

import com.ncs503.Babybook.auth.filter.JwtUtils;
import com.ncs503.Babybook.models.request.LoginRequest;
import com.ncs503.Babybook.models.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthServiceImpl {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtUtils jwtUtils;



    public LoginResponse login (LoginRequest request){
        try{
            String token = jwtUtils.generateToken(authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())));
            return LoginResponse.builder()
                    .email(request.getEmail())
                    .token(token)
                    .build();
        } catch (Exception e){
            return LoginResponse.builder().build();
        }

    }

}
