package com.ncs503.Babybook.models.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SQLDelete(sql = "UPDATE subjects SET soft_delete = true Where id=?")
@Where(clause = "soft_delete = false")
@Table( name= "subjects")
public class SubjectEntity implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String titleee;

    @Column(name = "soft_delete")
    private Boolean sofdelete = Boolean.FALSE;

}
