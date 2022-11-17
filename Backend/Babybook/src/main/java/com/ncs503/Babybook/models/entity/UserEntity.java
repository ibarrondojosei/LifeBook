
package com.ncs503.Babybook.models.entity;



import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

@Data
@NoArgsConstructor
@Entity
@Table ( name = "users")
@SQLDelete(sql = "UPDATE users SET deleted = true Where id=?")
@Where(clause="deleted=false")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull(message = "The first name can't be null")
    @NotEmpty(message = "The first name can't  be empty")
    @NotBlank(message = "The first name can't be blank")
    @Column( name = "first_name", nullable = false)
    private String firstName;
    
    @NotNull(message = "The last name can't be null")
    @NotEmpty(message = "The last name can't  be empty")
    @NotBlank(message = "The last name can't be blank")
    @Column( name = "last_name", nullable = false)
    private String lastName;
    
    
    @NotNull(message = "The username can't be null")
    @NotEmpty(message = "The username can't be empty")
    @NotBlank(message = "The username can't be blank")
    @Column( name = "username", nullable = false)
    private String username;
    
    @Column( name = "photo", nullable = true)
    private String photo;
    
    @NotNull(message = "The email can't be null")
    @Email(message ="Please use a valid email")
    @NotEmpty(message = "The e-mail can't be empty")
    @Column( name = "email", nullable = false)
    private String email;
    
    @NotNull(message = "The password can't be null")
    @NotEmpty(message = "The password can't be empty")
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
    
    private Boolean deleted = false;

    
    
    
}
