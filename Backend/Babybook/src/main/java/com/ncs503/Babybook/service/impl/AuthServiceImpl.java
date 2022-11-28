package com.ncs503.Babybook.service.impl;

import com.ncs503.Babybook.auth.filter.JwtUtils;
import com.ncs503.Babybook.auth.utility.RoleEnum;
import com.ncs503.Babybook.exception.InvalidUserException;
import com.ncs503.Babybook.exception.UserNotFoundException;
import com.ncs503.Babybook.exception.UserProfileAlreadyExistsException;
import com.ncs503.Babybook.models.entity.RoleEntity;
import com.ncs503.Babybook.models.entity.UserEntity;
import com.ncs503.Babybook.models.mapper.UserMapper;
import com.ncs503.Babybook.models.request.LoginRequest;
import com.ncs503.Babybook.models.request.UserRequest;
import com.ncs503.Babybook.models.response.LoginResponse;
import com.ncs503.Babybook.models.response.UserResponse;
import com.ncs503.Babybook.repository.RoleRepository;
import com.ncs503.Babybook.repository.UserRepository;
import com.ncs503.Babybook.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passEnc;



    public LoginResponse login (LoginRequest request){
        try{
            String token = jwtUtils.generateToken(authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())), request.getEmail());
            return LoginResponse.builder()
                    .email(request.getEmail())
                    .token(token)
                    .build();
        } catch (Exception e){
            return LoginResponse.builder().build();
        }

    }


    @Override
    public UserResponse saveUser(UserRequest userReq) throws InvalidUserException, UserProfileAlreadyExistsException, UserNotFoundException {
        if (userRepo.findByEmail(userReq.getEmail()).isPresent())
            throw new UserProfileAlreadyExistsException("E-mail already used");
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
        return userMapper.toUserResponse(user);
    }
}
