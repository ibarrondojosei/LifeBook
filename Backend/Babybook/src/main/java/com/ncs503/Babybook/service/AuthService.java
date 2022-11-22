package com.ncs503.Babybook.service;

import com.ncs503.Babybook.exception.InvalidUserException;
import com.ncs503.Babybook.exception.UserProfileAlreadyExistsException;
import com.ncs503.Babybook.models.request.UserRequest;
import org.springframework.stereotype.Service;

/**
 * @author Leonardo Terlizzi
 */

//@Service
public interface AuthService {

    public void saveUser(UserRequest userReq) throws InvalidUserException, UserProfileAlreadyExistsException;
}
