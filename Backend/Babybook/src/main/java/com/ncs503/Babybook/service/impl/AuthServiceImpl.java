package com.ncs503.Babybook.service.impl;

import com.ncs503.Babybook.auth.utility.RoleEnum;
import com.ncs503.Babybook.exception.InvalidUserException;
import com.ncs503.Babybook.exception.UserNotFoundException;
import com.ncs503.Babybook.exception.UserProfileAlreadyExistsException;
import com.ncs503.Babybook.models.entity.RoleEntity;
import com.ncs503.Babybook.models.entity.UserEntity;
import com.ncs503.Babybook.models.mapper.UserMapper;
import com.ncs503.Babybook.models.request.UserRequest;
import com.ncs503.Babybook.models.response.UserResponse;
import com.ncs503.Babybook.repository.RoleRepository;
import com.ncs503.Babybook.repository.UserRepository;
import com.ncs503.Babybook.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Leonardo Terlizzi
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passEnc;

    @Override
    public UserResponse saveUser(UserRequest userReq) throws InvalidUserException, UserProfileAlreadyExistsException, UserNotFoundException {  //TODO que devuelva response
        if (userRepo.findByEmail(userReq.getEmail()).isPresent())
            throw new UserProfileAlreadyExistsException("E-mail already used");
        if(userRepo.findByUsername(userReq.getUsername()).isPresent())
            throw new UserProfileAlreadyExistsException("Username already taken, choose a new one");

        String pass = userReq.getPassword();
        userReq.setPassword(passEnc.encode(pass));

        Set<RoleEntity> roles = roleRepo.findByName(RoleEnum.USER.getSimpleRoleName());
        if (roles.isEmpty()) {
            RoleEntity rol = new RoleEntity();
            rol.setName(RoleEnum.USER.getSimpleRoleName());
            rol = roleRepo.save(rol);
            roles.add(rol);
        }
        UserEntity user = userMapper.toUserEntityWithRoles(userReq, roles);

        userRepo.save(user);
        UserEntity userWithId = userRepo.findByEmail(userReq.getEmail()).orElse(null);
        return userMapper.toUserResponse(userWithId);
    }
}
