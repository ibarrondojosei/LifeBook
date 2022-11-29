package com.ncs503.Babybook.service;

import com.ncs503.Babybook.exception.GuestNotFoundException;
import com.ncs503.Babybook.exception.InvalidUserException;
import com.ncs503.Babybook.exception.UserNotFoundException;
import com.ncs503.Babybook.exception.UserProfileAlreadyExistsException;
import com.ncs503.Babybook.models.request.LoginRequest;
import com.ncs503.Babybook.models.request.UserRequest;
import com.ncs503.Babybook.models.response.LoginResponse;
import com.ncs503.Babybook.models.response.UserResponse;



//@Service
public interface AuthService {


    LoginResponse login(LoginRequest request);
    public UserResponse saveUser(UserRequest userReq) throws InvalidUserException, UserProfileAlreadyExistsException, UserNotFoundException, GuestNotFoundException;

    public UserResponse saveAdminUser(UserRequest userReq) throws InvalidUserException, UserProfileAlreadyExistsException, UserNotFoundException, GuestNotFoundException;

}


