
package com.ncs503.Babybook.models.mapper;


import com.ncs503.Babybook.exception.InvalidUserException;
import com.ncs503.Babybook.exception.UserNotFoundException;
import com.ncs503.Babybook.models.entity.UserEntity;
import com.ncs503.Babybook.models.request.UserRequest;
import com.ncs503.Babybook.models.response.UserResponse;
import java.util.ArrayList;
import java.util.List;
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
        user.setId(userReq.getId());
        user.setEmail(userReq.getEmail());
        user.setFirstName(userReq.getFirstName());
        user.setLastName(userReq.getLastName());
        user.setPhoto(userReq.getPhoto());
        user.setUsername(userReq.getUsername());
       // user.setPassword(userReq.getPassword());
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
