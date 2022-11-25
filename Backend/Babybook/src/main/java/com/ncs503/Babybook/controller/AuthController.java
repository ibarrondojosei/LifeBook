package com.ncs503.Babybook.controller;

import com.ncs503.Babybook.exception.InvalidUserException;
import com.ncs503.Babybook.exception.UserNotFoundException;
import com.ncs503.Babybook.exception.UserProfileAlreadyExistsException;
import com.ncs503.Babybook.models.entity.UserEntity;
import com.ncs503.Babybook.models.mapper.UserMapper;
import com.ncs503.Babybook.models.request.UserRequest;
import com.ncs503.Babybook.models.response.UserResponse;
import com.ncs503.Babybook.repository.UserRepository;
import com.ncs503.Babybook.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leonardo Terlizzi
 */
@RestController
@RequestMapping(path = "/auth")

public class AuthController {

    @Autowired
    private AuthService authServ;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest userReq) throws InvalidUserException, UserProfileAlreadyExistsException, UserNotFoundException {

        return new ResponseEntity<>(authServ.saveUser(userReq), HttpStatus.OK);

    }
}
