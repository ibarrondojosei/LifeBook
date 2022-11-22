package com.ncs503.Babybook.controller;

import com.ncs503.Babybook.exception.InvalidUserException;
import com.ncs503.Babybook.exception.UserNotFoundException;
import com.ncs503.Babybook.exception.UserProfileAlreadyExistsException;
import com.ncs503.Babybook.models.request.UserRequest;
import com.ncs503.Babybook.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

/**
 * @author Leonardo Terlizzi
 */
@RestController
@RequestMapping(path = "/auth")

public class AuthController {

    @Autowired
    private AuthService authServ;

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody UserRequest userReq) throws InvalidUserException, UserProfileAlreadyExistsException, UserNotFoundException {
        authServ.saveUser(userReq);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
