package com.ncs503.Babybook.models.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Leonardo Terlizzi
 */
@Data
@NoArgsConstructor
@ApiModel(description = "Class used to represent an Register User Request.")
public class RegisterUserRequest {

    @NotNull(message = "the firstName can't be null")
    @NotEmpty(message = "the firstName can't be empty")
    @NotBlank(message = "the firstName can't  be blank")
    @ApiModelProperty(notes = "First name of the User.",
            example = "Juan", required = true)
    private String firstName;

    @NotNull(message = "the lastName can't be null")
    @NotEmpty(message = "the lastName can't be empty")
    @NotBlank(message = "the lastName can't  be blank")
    @ApiModelProperty(notes = "Last name of the User.",
            example = "Perez", required = true)
    private String lastName;

    @NotNull(message = "the username can't be null")
    @NotEmpty(message = "the username can't be empty")
    @NotBlank(message = "the username can't  be blank")
    @ApiModelProperty(notes = "THE Username of the User.",
            example = "juanperez84", required = true)
    private String username;

    @NotNull(message = "The e-mail address can't be null")
    @Email(message = "Use a valid e-mail address")
    @ApiModelProperty(notes = "The user's e-mail address",
            example = "juanperez84@gmail.com", required = true)
    private String email;

    @NotNull(message = "The password can't be null")
    @NotEmpty(message = "The password can't be empty")
    @NotBlank(message = "The password can't be blank")
    @ApiModelProperty(notes = "The user's password",
            example = "juancho123", required = true)
    private String password;
}
