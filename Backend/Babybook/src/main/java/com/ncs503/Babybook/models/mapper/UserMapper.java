
package com.ncs503.Babybook.models.mapper;


import com.ncs503.Babybook.exception.InvalidUserException;
import com.ncs503.Babybook.exception.UserNotFoundException;
import com.ncs503.Babybook.models.entity.RoleEntity;
import com.ncs503.Babybook.models.entity.UserEntity;
import com.ncs503.Babybook.models.request.UpdateUserRequest;
import com.ncs503.Babybook.models.request.UserRequest;
import com.ncs503.Babybook.models.response.UserResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author Leonardo Terlizzi
 */
@Component
public class UserMapper {
    
    public UserEntity toUserEntity(UserRequest userReq) throws InvalidUserException{
        UserEntity user = new UserEntity();
        user.setEmail(userReq.getEmail());
        user.setFirstName(userReq.getFirstName());
        user.setLastName(userReq.getLastName());
        user.setPhoto(userReq.getPhoto());
        user.setUsername(userReq.getUsername());
        user.setPassword(userReq.getPassword());
        return user;

    }

    public UserEntity toUserEntityWithRoles(UserRequest userReq, Set<RoleEntity> roles) throws InvalidUserException{
        UserEntity user = new UserEntity();
        user.setEmail(userReq.getEmail());
        user.setFirstName(userReq.getFirstName());
        user.setLastName(userReq.getLastName());
        user.setPhoto(userReq.getPhoto());
        user.setUsername(userReq.getUsername());
        user.setPassword(userReq.getPassword());
        user.setRoleId(roles);
        return user;

    }

    public UserEntity toUserEntity(UpdateUserRequest userReq) throws InvalidUserException{
        UserEntity user = new UserEntity();

        if(userReq.getEmail() != null & !userReq.getEmail().isEmpty() & !userReq.getEmail().isBlank())
            user.setEmail(userReq.getEmail());
        if(userReq.getFirstName() != null & !userReq.getFirstName().isEmpty() & !userReq.getFirstName().isBlank())
            user.setFirstName(userReq.getFirstName());
        if(userReq.getLastName() != null & !userReq.getLastName().isEmpty() & !userReq.getLastName().isBlank())
            user.setLastName(userReq.getLastName());
        if(userReq.getPhoto() != null & !userReq.getPhoto().isEmpty() & !userReq.getPhoto().isBlank())
            user.setPhoto(userReq.getPhoto());
        if(userReq.getUsername() != null & !userReq.getUsername().isEmpty() & !userReq.getUsername().isBlank())
            user.setUsername(userReq.getUsername());
        if(userReq.getPassword() != null & !userReq.getPassword().isEmpty() & !userReq.getPassword().isBlank())
            user.setPassword(userReq.getPassword());
        //user.setRoleId(roles);
        return user;

    }
    
    public UserResponse toUserResponse(UserEntity user) throws UserNotFoundException {
        UserResponse userRes = new UserResponse();
        userRes.setId(user.getId());
        userRes.setFirstName(user.getFirstName());
        userRes.setLastName(user.getLastName());
        userRes.setUsername(user.getUsername());
        userRes.setPhoto(user.getPhoto());
        userRes.setEmail(user.getEmail());
        return userRes;
    }
    
    public List<UserResponse> usersToUserResponseList(List<UserEntity> users) throws UserNotFoundException {
        
        List<UserResponse> usersRes = new ArrayList<>();
        users.forEach(user-> {
         
            try {
                usersRes.add(toUserResponse(user));
            } catch (UserNotFoundException ex) {
                Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
           
           
        });
        return usersRes;
    }


    
}
