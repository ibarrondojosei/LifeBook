
package com.ncs503.Babybook.service.impl;

import com.ncs503.Babybook.exception.InvalidUserException;
import com.ncs503.Babybook.exception.UserNotFoundException;
import com.ncs503.Babybook.models.entity.UserEntity;
import com.ncs503.Babybook.models.mapper.UserMapper;
import com.ncs503.Babybook.models.request.UserRequest;
import com.ncs503.Babybook.models.response.UserResponse;
import com.ncs503.Babybook.repository.UserRepository;
import com.ncs503.Babybook.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Leonardo Terlizzi
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserMapper mapper;

    @Override
    public List<UserResponse> getUsers() throws UserNotFoundException {
        return mapper.usersToUserResponseList(userRepo.findAll());
    }

    @Override
    public UserResponse getUser(Long id) throws UserNotFoundException {
        return mapper.toUserResponse(userRepo.findById(id).orElse(null));
    }

    @Override
    public void deleteUser(Long id) throws UserNotFoundException {
        userRepo.deleteById(id);
    }

    @Override
    public UserResponse updateUser(UserRequest userReq, Long id) throws InvalidUserException, UserNotFoundException {
        UserEntity user = mapper.toUserEntity(userReq);
        user.setId(id);
        userRepo.save(user);
        return mapper.toUserResponse(user);

    }


}
