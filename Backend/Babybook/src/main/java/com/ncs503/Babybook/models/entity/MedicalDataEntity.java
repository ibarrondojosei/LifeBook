package com.ncs503.Babybook.models.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ncs503.Babybook.models.utility.BloodTypeEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SQLDelete(sql = "UPDATE medicalData SET soft_delete = true Where id=?")
@Where(clause = "soft_delete=false")
@Table( name= "medicalData")
public class MedicalDataEntity {


    private Long id;

    private BloodTypeEnum bloodType;

    private String alergies;

    private String relevantInfo;

    /* @JsonIgnore
    @OneToMany(mappedBy = "MedicalDataEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MedicalRecordEntity> medicalRecords;*/

    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private boolean deleted = Boolean.FALSE;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    private SubjectEntity subject;



   /* ID Long
    BLOODTYPE String
    ALERGIES String
    RELEVANTINFO String
    TIMESTAMP timeStamp
    SOFTDELETE Boolean
    MEDICALRECORD List<MedicalRecord>*/

}
