
package com.ncs503.Babybook.controller;

import com.ncs503.Babybook.exception.InvalidUserException;
import com.ncs503.Babybook.exception.UserNotFoundException;
import com.ncs503.Babybook.models.request.UpdateUserRequest;
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
import org.springframework.web.bind.annotation.*;

/**
 * @author Leonardo Terlizzi
 */
@RestController
@RequestMapping(path = "/user")
//@Api(description = "User CRUD" , tags = "Users")
public class UserEntityController {

    @Autowired
    private UserService userServ;


    @GetMapping("/all")
    @ApiOperation(value = "List all the users", notes = "Endpoint that return a list of users")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Users's list"),
            @ApiResponse(code = 403, message = "Forbidden action")
    })
    public ResponseEntity<List<UserResponse>> getUsers() throws UserNotFoundException {
        List<UserResponse> users = userServ.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/get")
    @ApiOperation(value = "Get an user", notes = "Endpoint that return an user by the info inside the token")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User information"),
            @ApiResponse(code = 403, message = "Forbidden action")
    })
    public ResponseEntity<UserResponse> getUser(@RequestHeader(name="Authorization") String token
                                                ) throws UserNotFoundException, InvalidUserException {
        return new ResponseEntity<>(userServ.getUser(token), HttpStatus.OK);
    }




    @DeleteMapping("/delete")
    @ApiOperation(value = "Delete an User", notes = "Endpoint that allows an user to delete himself/herself")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User deleted"),
            @ApiResponse(code = 403, message = "Forbbiden action")
    })
    public ResponseEntity<Void> deleteUser(@RequestParam Long id,
                                           @RequestHeader(name="Authorization") String token)
            throws UserNotFoundException, InvalidUserException {
        userServ.deleteUser(id, token);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/su/delete")
    @ApiOperation(value = "Delete an User(admin)", notes ="Endpoint that allows an admin to delete an user")
    @ApiResponses(value = {
            @ApiResponse( code = 201, message = "User deleted"),
            @ApiResponse( code = 403, message = "Forbbiden action")
    })
    public ResponseEntity<Void> adminDeleteUser(@RequestHeader(name="Authorizaation") String token,
                                                @RequestParam Long id)
            throws UserNotFoundException, InvalidUserException {
        userServ.deleteUser(id, token);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PatchMapping("/update")
    @ApiOperation(value = "Update an User", notes ="Endpoint that allows to update user's information")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User updated"),
            @ApiResponse(code = 403, message = "Forbbiden action")
    })
    public ResponseEntity<UserResponse> editUser(@RequestHeader(name="Authorization") String token,
                                                    @RequestBody UpdateUserRequest userReq,
                                                    @RequestParam Long id)
                                        throws InvalidUserException, UserNotFoundException {
        userServ.updateUser(userReq, id, token);
        return new ResponseEntity<>(userServ.getUser(token), HttpStatus.OK);

    }

    @PatchMapping("/su/update")
    @ApiOperation(value = "Update an User(admin)", notes ="Endpoint that allows an admin to update an user's information")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User updated"),
            @ApiResponse(code = 403, message = "Forbbiden action")
    })
    public ResponseEntity<UserResponse> adminEditUser(@RequestBody UpdateUserRequest userReq,
                                                      @RequestHeader(name="Authorization") String token,
                                                      @RequestParam Long id)
                                        throws InvalidUserException, UserNotFoundException {
        userServ.updateUser(userReq, id, token);
        return new ResponseEntity<>(userServ.getUser(token), HttpStatus.OK);

    }


}
