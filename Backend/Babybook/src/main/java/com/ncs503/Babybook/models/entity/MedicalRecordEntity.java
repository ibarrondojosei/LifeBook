package com.ncs503.Babybook.models.entity;

import com.ncs503.Babybook.models.utility.TagsEventEnum;
import com.ncs503.Babybook.models.utility.TagsMedicalRecordEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SQLDelete(sql = "UPDATE medicalrecords SET soft_delete = true Where id=?")
@Where(clause = "soft_delete = false")
@Table( name= "medicalrecords")
public class MedicalRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String body;

    private LocalDate date;

    private List<String> media;

    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private Boolean sofdelete = Boolean.FALSE;

//    @ManyToOne
//    @JoinColumn(name = "subject_id")
//    private Subject subjectId;

    private List<TagsMedicalRecordEnum> medicalRecordEnums;


}
