package com.ncs503.Babybook.models.mapper;

import com.ncs503.Babybook.models.entity.MedicalRecordEntity;
import com.ncs503.Babybook.models.request.MedicalRecordRequest;
import com.ncs503.Babybook.models.response.MedicalRecordResponse;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordMapper {

    public MedicalRecordEntity Request2Entity (MedicalRecordRequest request)  {

        MedicalRecordEntity entity = new MedicalRecordEntity();

        return entity.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .date(request.getDate())
                .media(request.getMedia())
                .medicalRecordEnums(request.getMedicalRecordEnumList())
//                .subjectId(request.getSubjectId())
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .sofdelete(false)
                .build();
    }

    public MedicalRecordResponse Entity2Response (MedicalRecordEntity entity){

        return MedicalRecordResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .body(entity.getBody())
                .date(entity.getDate())
                .media(entity.getMedia())
                .medicalRecordEnumList(entity.getMedicalRecordEnums())
//                .subjectId(entity.getSubjectId())
                .timestamp(entity.getTimestamp())
                .build();
    }

    public MedicalRecordEntity EntityUpdate (MedicalRecordEntity entity, MedicalRecordRequest request)  {

        return MedicalRecordEntity.builder()
                .id(request.getId())
                .title(request.getTitle())
                .body(request.getBody())
                .date(request.getDate())
                .media(request.getMedia())
                .medicalRecordEnums(request.getMedicalRecordEnumList())
//                .subjectId(request.getSubjectId())
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .sofdelete(false)
                .build();

    }

    public List<MedicalRecordResponse> EntityList2ResponsePage(List<MedicalRecordEntity> MedicalRecordList){

        List<MedicalRecordResponse> responses = new ArrayList<>();

        for ( MedicalRecordEntity MedicalRecord: MedicalRecordList){
            responses.add(Entity2Response(MedicalRecord));
        }

        return responses;
    }
}
