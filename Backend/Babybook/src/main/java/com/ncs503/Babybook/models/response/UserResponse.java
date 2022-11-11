
package com.ncs503.Babybook.models.response;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author Leonardo Terlizzi
 */
@Data
@Builder
public class UserResponse {
    
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    //private String password;
    private String email;
    private String photo;
    //NYI
    //private Set<Rol> rol_id;
    //private List<Subject> subjects;
    //private List<Guest> guests;
    
}
