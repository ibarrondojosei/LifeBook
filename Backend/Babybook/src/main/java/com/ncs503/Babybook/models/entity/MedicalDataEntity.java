package com.ncs503.Babybook.models.entity;


import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SQLDelete(sql = "UPDATE subjects SET soft_delete = true Where id=?")
@Where(clause = "soft_delete=false")
@Table( name= "subjects")
public class MedicalDataEntity {


    private Long id;

    private String bloodType;






   /* ID Long
    BLOODTYPE String
    ALERGIES String
    RELEVANTINFO String
    TIMESTAMP timeStamp
    SOFTDELETE Boolean
    MEDICALRECORD List<MedicalRecord>*/

}
