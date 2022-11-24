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
@ApiModel(description = "Class representing an User Request.")
public class UpdateUserRequest {


    @ApiModelProperty(notes = "First name of the User.",
            example = "Juan", required = true)
    private String firstName;

    @ApiModelProperty(notes = "Last name of the User.",
            example = "Perez", required = true)
    private String lastName;

    @ApiModelProperty(notes = "THE Username of the User.",
            example = "juanperez84", required = true)
    private String username;


    @Email(message = "Use a valid e-mail address")
    @ApiModelProperty(notes = "The user's e-mail address",
            example = "juanperez84@gmail.com", required = true)
    private String email;

    @ApiModelProperty(notes = "The user's password",
            example = "juancho123", required = true)
    private String password;

    @ApiModelProperty(notes = "Base64 encoded profile picture",
            example= "data:image/png;base64, iVBORw0KGgoAAAANSUhEUgAAAAUA")
    private String photo;

    //NYI
    //    private List<Subject> subjects;
    //    private List<Guest> guests;

}
