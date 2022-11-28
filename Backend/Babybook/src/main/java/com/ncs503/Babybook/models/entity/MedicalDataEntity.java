package com.ncs503.Babybook.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SQLDelete(sql = "UPDATE medicalDatas SET soft_delete = true Where id=?")
@Where(clause = "soft_delete = false")
@Table( name= "medicalDatas")
public class MedicalDataEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titleee;

    @Column(name = "soft_delete")
    private Boolean sofdelete = Boolean.FALSE;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;
}
