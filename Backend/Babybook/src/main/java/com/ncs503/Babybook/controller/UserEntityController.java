
package com.ncs503.Babybook.controller;

import com.ncs503.Babybook.exception.InvalidUserException;
import com.ncs503.Babybook.exception.UserNotFoundException;
import com.ncs503.Babybook.models.request.UserRequest;
import com.ncs503.Babybook.models.response.UserResponse;
import com.ncs503.Babybook.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Leonardo Terlizzi
 */
@RestController
@RequestMapping(path = "/user")
@Api(description = "User CRUD" , tags = "Users")
public class UserEntityController {
    
    @Autowired
    private UserService userServ;
    

    
    @GetMapping("/all")
    @ApiOperation(value = "List all the users", notes = "Endpoint that return a list of users")
    @ApiResponses(value = {
            @ApiResponse( code = 201, message = "Users's list"),
            @ApiResponse( code = 403, message = "Forbidden action")
            })
    public ResponseEntity<List<UserResponse>> getUsers() throws UserNotFoundException {
        List<UserResponse> users = userServ.getUsers();
        return new ResponseEntity(users, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Get an user", notes = "Endpoint that return an user by id")
    @ApiResponses(value = {
            @ApiResponse( code = 201, message= "User information"),
            @ApiResponse( code = 403, message = "Forbidden action")
    })
    public ResponseEntity<UserResponse> getUser(@PathVariable("id")Long id) throws UserNotFoundException{
        return new ResponseEntity(userServ.getUser(id), HttpStatus.OK);
    }
    
    //new user in register controller
    
    @DeleteMapping("/delete")
    @ApiOperation(value = "Delete an User", notes ="Endpoint that allows an user to delete himself/herself")
    @ApiResponses(value = {
            @ApiResponse( code = 201, message = "User deleted"),
            @ApiResponse( code = 403, message = "Forbbiden action")
    })
    public ResponseEntity<Void> deleteUser(@RequestParam Long id) throws UserNotFoundException{
        userServ.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    @DeleteMapping("/su/delete")
    @ApiOperation(value = "Delete an User(admin)", notes ="Endpoint that allows an admin to delete an user")
    @ApiResponses(value = {
            @ApiResponse( code = 201, message = "User deleted"),
            @ApiResponse( code = 403, message = "Forbbiden action")
    })
    public ResponseEntity<Void> adminDeleteUser(@RequestParam Long id) throws UserNotFoundException{
        userServ.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
 
    
    @PatchMapping("/update")
    @ApiOperation(value = "Update an User", notes ="Endpoint that allows to update user's information")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User updated"),
            @ApiResponse(code = 403, message = "Forbbiden action")
    })
    public ResponseEntity<Void> editUser(@RequestBody UserRequest userReq, @RequestParam Long id) throws InvalidUserException{
        userServ.updateUser(userReq, id);
        return ResponseEntity.status(HttpStatus.OK).build();
        
    }
    
    @PatchMapping("/su/update")
    @ApiOperation(value = "Update an User(admin)", notes ="Endpoint that allows an admin to update an user's information")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User updated"),
            @ApiResponse(code = 403, message = "Forbbiden action")
    })
    public ResponseEntity<Void> adminEditUser(@RequestBody UserRequest userReq, @RequestParam Long id) throws InvalidUserException{
        userServ.updateUser(userReq, id);
        return ResponseEntity.status(HttpStatus.OK).build();
        
    }
    
    
}
