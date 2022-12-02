package com.ncs503.Babybook.models.mapper;

import com.ncs503.Babybook.models.entity.MedicalRecordEntity;
import com.ncs503.Babybook.models.request.MedicalRecordRequest;
import com.ncs503.Babybook.models.response.MedicalRecordResponse;
import com.ncs503.Babybook.models.response.medicalRecordFilterByMedicalDataResponse;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class MedicalRecordMapper {

    public MedicalRecordEntity Request2Entity (MedicalRecordRequest request)  {

        MedicalRecordEntity entity = new MedicalRecordEntity();

        return entity.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .date(request.getDate())
                .media(request.getMedia())
                .highlightMoment(request.getHighlightMoment())
                .medicalRecordEnums(request.getMedicalRecordEnum())
                .medicalDataEntity(request.getMedicalData())
                .userId(request.getUserId())
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
                .highlightMoment(entity.getHighlightMoment())
                .medicalRecordEnum(entity.getMedicalRecordEnums())
                .medicalDataEntity(entity.getMedicalDataEntity())
                .userId(entity.getUserId())
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
                .highlightMoment(request.getHighlightMoment())
                .medicalRecordEnums(request.getMedicalRecordEnum())
                .medicalDataEntity(request.getMedicalData())
                .userId(request.getUserId())
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .sofdelete(false)
                .build();

    }



    public List<MedicalRecordResponse> EntityList2Response(List<MedicalRecordEntity> MedicalRecordList){

        List<MedicalRecordResponse> responses = new ArrayList<>();

        for ( MedicalRecordEntity MedicalRecord: MedicalRecordList){
            responses.add(Entity2Response(MedicalRecord));
        }

        return responses;
    }

    public medicalRecordFilterByMedicalDataResponse Entity2ResponseFilter (MedicalRecordEntity entity){

        return medicalRecordFilterByMedicalDataResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .body(entity.getBody())
                .date(entity.getDate())
                .media(entity.getMedia())
                .highlightMoment(entity.getHighlightMoment())
                .medicalRecordEnum(entity.getMedicalRecordEnums())
                .build();
    }

    public List<medicalRecordFilterByMedicalDataResponse> EntityList2ResponsePage1(List<MedicalRecordEntity> MedicalRecordList){

        List<medicalRecordFilterByMedicalDataResponse> responses = new ArrayList<>();

        for ( MedicalRecordEntity MedicalRecord: MedicalRecordList){
            responses.add(Entity2ResponseFilter(MedicalRecord));
        }

        return responses;
    }

}
