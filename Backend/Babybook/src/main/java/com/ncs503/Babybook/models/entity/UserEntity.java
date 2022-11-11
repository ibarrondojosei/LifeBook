
package com.ncs503.Babybook.models.entity;

import com.sun.istack.NotNull;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

/**
 *
 * @author Leonardo Terlizzi
 */

@Data @NoArgsConstructor
@Entity
@Table ( name = "users")
@SQLDelete(sql = "UPDATE users SET deleted = true Where id=?")
@Where(clause="deleted=false")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @NotEmpty(message = "The first name can't  be null")
    @NotBlank(message = "The first name can't be blank")
    @Column( name = "first_name", nullable = false)
    private String firstName;
    
    @NotNull
    @NotEmpty(message = "The last name can't  be null")
    @NotBlank(message = "The last name can't be blank")
    @Column( name = "last_name", nullable = false)
    private String lastName;
    
    
    @NotNull
    @NotEmpty(message = "The username can't be null")
    @NotBlank(message = "The username can't be blank")
    @Column( name = "username", nullable = false)
    private String username;
    
    @Column( name = "photo", nullable = true)
    private String photo;
    
    @NotNull
    @NotEmpty(message = "The e-mail can't be null")
    @Column( name = "email", nullable = false)
    private String email;
    
    @NotNull
    @NotEmpty(message = "The password can't be null")
    @Column( name = "password", nullable = false)
    private String password;
    
    //NYI
    //private Set<Rol> rol_id;
    //private List<Subject> subjects;
    //private List<Guest> guests;
    
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;
    
    @UpdateTimestamp
    private Timestamp updateAt;
    
    private Boolean deleted = Boolean.FALSE;
    
    
    
    
}
