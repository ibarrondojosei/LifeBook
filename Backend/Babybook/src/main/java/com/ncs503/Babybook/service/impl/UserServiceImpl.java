
package com.ncs503.Babybook.service.impl;

import com.ncs503.Babybook.auth.filter.JwtUtils;
import com.ncs503.Babybook.exception.InvalidUserException;
import com.ncs503.Babybook.exception.UserNotFoundException;
import com.ncs503.Babybook.models.entity.GuestEntity;
import com.ncs503.Babybook.models.entity.UserEntity;
import com.ncs503.Babybook.models.mapper.UserMapper;
import com.ncs503.Babybook.models.request.UpdateUserRequest;
import com.ncs503.Babybook.models.request.UserRequest;
import com.ncs503.Babybook.models.response.UserResponse;
import com.ncs503.Babybook.repository.UserRepository;
import com.ncs503.Babybook.service.UserService;

import java.util.List;
import java.util.Objects;

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

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public List<UserResponse> getUsers() throws UserNotFoundException {
        return mapper.usersToUserResponseList(userRepo.findAll());
    }

    @Override
    public UserResponse getUser(String token) throws UserNotFoundException, InvalidUserException {
        String userToken = getToken(token);
        UserEntity user = userRepo.findByEmail(jwtUtils.extractUsername(userToken)).get();
        return mapper.toUserResponse(user);
    }

    @Override
    public void deleteUser(Long id, String token) throws UserNotFoundException {
        userRepo.deleteById(id);
    }

    @Override
    public UserResponse updateUser(UpdateUserRequest userReq, Long id, String token) throws InvalidUserException, UserNotFoundException {
        String tokenUsername = jwtUtils.extractUsername(getToken(token));
        if(!Objects.equals(tokenUsername, userReq.getEmail())){
            throw new InvalidUserException("Invalid user");
        }
        UserEntity user = mapper.toUserEntity(userReq);
        user.setId(id);
        userRepo.save(user);
        return mapper.toUserResponse(user);

    }



    public String getToken(String token) {
        String[] part = token.split(" ");
        String tokenWithoutBearer = part[1];
        return tokenWithoutBearer;
    }


    }
