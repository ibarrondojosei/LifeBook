package com.ncs503.Babybook.controller;

import com.ncs503.Babybook.models.request.LoginRequest;
import com.ncs503.Babybook.models.response.LoginResponse;
import com.ncs503.Babybook.service.AuthService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    @ApiOperation(value = "Login a user",
            response = LoginResponse.class)
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
