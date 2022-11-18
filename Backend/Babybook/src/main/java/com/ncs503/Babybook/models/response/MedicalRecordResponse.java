package com.ncs503.Babybook.models.response;

import com.ncs503.Babybook.models.entity.UserEntity;
import com.ncs503.Babybook.models.utility.TagsMedicalRecordEnum;
import lombok.Builder;

import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicalRecordResponse {

    private Long id;
    private String title;
    private String body;
    private LocalDate date;
    private List<String> media;
    private Timestamp timestamp;
//    private Subjectenenene subjectId;
    private TagsMedicalRecordEnum medicalRecordEnum;
    private UserEntity userId;

}
