package com.ncs503.Babybook.service.impl;

import com.ncs503.Babybook.auth.utility.RoleEnum;
import com.ncs503.Babybook.exception.InvalidUserException;
import com.ncs503.Babybook.exception.UserProfileAlreadyExistsException;
import com.ncs503.Babybook.models.entity.RoleEntity;
import com.ncs503.Babybook.models.entity.UserEntity;
import com.ncs503.Babybook.models.mapper.UserMapper;
import com.ncs503.Babybook.models.request.UserRequest;
import com.ncs503.Babybook.repository.RoleRepository;
import com.ncs503.Babybook.repository.UserRepository;
import com.ncs503.Babybook.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

/**
 * @author Leonardo Terlizzi
 */

public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private  PasswordEncoder passEnc;

    @Override
    public void saveUser(UserRequest userReq) throws InvalidUserException, UserProfileAlreadyExistsException {
        if(userRepo.findByEmail(userReq.getEmail()).isPresent())
            throw new UserProfileAlreadyExistsException("E-mail already used");
        String pass = userReq.getPassword();
        userReq.setPassword(passEnc.encode(pass));
        UserEntity user = userMapper.toUserEntity(userReq);

        Set<RoleEntity> roles = roleRepo.findByName(RoleEnum.USER.getSimpleRoleName());
        if(roles.isEmpty()){
            RoleEntity rol = new RoleEntity();
            rol.setName(RoleEnum.USER.getSimpleRoleName());
            rol = roleRepo.save(rol);
            roles.add(rol);
        }

        userRepo.save(user);
    }
}
