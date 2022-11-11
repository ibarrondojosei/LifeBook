
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
        return UserEntity.builder()
                .id(userReq.getId())
                .firstName(userReq.getFirstName())
                .lastName(userReq.getLastName())
                .username(userReq.getUsername())
                .password(userReq.getPassword())
                .email(userReq.getEmail())
                .photo(userReq.getPhoto())
                .build();
               
    }
    
    public UserResponse toUserResponse(UserEntity user) throws UserNotFoundException {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())  
         //      .password(user.getPassword()) 
                .email(user.getEmail())
                .photo(user.getPhoto())
                .build();
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
