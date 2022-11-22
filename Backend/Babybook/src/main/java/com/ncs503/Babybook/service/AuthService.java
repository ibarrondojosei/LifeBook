package com.ncs503.Babybook.service;

import com.ncs503.Babybook.models.request.LoginRequest;
import com.ncs503.Babybook.models.response.LoginResponse;
import org.springframework.stereotype.Service;
import com.ncs503.Babybook.exception.InvalidUserException;
import com.ncs503.Babybook.exception.UserProfileAlreadyExistsException;
import com.ncs503.Babybook.models.request.UserRequest;

@Service
public interface AuthService {

    LoginResponse login(LoginRequest request);

    void saveUser(UserRequest userReq) throws InvalidUserException, UserProfileAlreadyExistsException;
}


