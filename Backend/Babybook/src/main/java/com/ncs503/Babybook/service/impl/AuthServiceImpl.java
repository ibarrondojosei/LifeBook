package com.ncs503.Babybook.service.impl;

import com.ncs503.Babybook.exception.InvalidUserException;
import com.ncs503.Babybook.models.mapper.UserMapper;
import com.ncs503.Babybook.models.request.UserRequest;
import com.ncs503.Babybook.repository.UserRepository;
import com.ncs503.Babybook.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Leonardo Terlizzi
 */

public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void saveUser(UserRequest userReq) throws InvalidUserException {
        userRepo.save(userMapper.toUserEntity(userReq));
    }
}
