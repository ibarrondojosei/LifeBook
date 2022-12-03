package com.ncs503.Babybook.models.response;

import com.ncs503.Babybook.models.entity.MedicalDataEntity;
import com.ncs503.Babybook.models.entity.UserEntity;
import com.ncs503.Babybook.models.utility.TagsMedicalRecordEnum;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class medicalRecordFilterByMedicalDataResponse {

    private Long id;
    private String title;
    private String body;
    private LocalDate date;
    private List<String> media;
    private Boolean highlightMoment;
    private TagsMedicalRecordEnum medicalRecordEnum;

}
