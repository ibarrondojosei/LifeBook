package com.ncs503.Babybook.models.mapper;
import com.ncs503.Babybook.models.entity.MedicalDataEntity;
import com.ncs503.Babybook.models.entity.SubjectEntity;
import com.ncs503.Babybook.models.request.MedicalDataRequest;
import com.ncs503.Babybook.models.request.SubjectRequest;
import com.ncs503.Babybook.models.response.MedicalDataResponse;
import com.ncs503.Babybook.models.response.SubjectResponse;
import com.ncs503.Babybook.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MedicalDataMapper {

     /*  @Autowired
    AwsService awsService;*/

    @Autowired
    SubjectRepository subjectRepository;

    public MedicalDataEntity Request2Entity (MedicalDataRequest request) throws IOException {

        MedicalDataEntity entity = MedicalDataEntity.builder().bloodType(request.getBloodType())
                .alergies(request.getAlergies())
                .relevantInfo(request.getRelevantInfo())
                .subject(subjectRepository.getReferenceById(request.getSubject()))
                .build();


        return entity;

    }

    public MedicalDataResponse Entity2Response (MedicalDataEntity entity) throws IOException {

        MedicalDataResponse response = MedicalDataResponse.builder().bloodType(entity.getBloodType())
                .alergies(entity.getAlergies())
                .relevantInfo(entity.getRelevantInfo())
                .subject(entity.getSubject().getId())
                .createDate(entity.getTimestamp())
                .build();

        return response;
    }

    public MedicalDataEntity EntityRefreshValues (MedicalDataEntity entity, MedicalDataRequest request) throws IOException {

        entity =MedicalDataEntity.builder().bloodType(request.getBloodType())
                .alergies(request.getAlergies())
                .relevantInfo(request.getRelevantInfo())
                .build();


        return entity;
    }


    public List<MedicalDataResponse> EntityList2Response(List<MedicalDataEntity> entities) throws IOException {
        List<MedicalDataResponse> responses = new ArrayList<>();
        for ( MedicalDataEntity medicals: entities){
            responses.add(Entity2Response(medicals));
        }

        return responses;
    }

}
