package com.ncs503.Babybook.models.mapper;

import com.ncs503.Babybook.models.entity.SubjectEntity;
import com.ncs503.Babybook.models.request.SubjectRequest;
import com.ncs503.Babybook.models.response.SubjectResponse;
import com.ncs503.Babybook.repository.SubjectRepository;
import com.ncs503.Babybook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@Component
public class SubjectMapper {

    @Autowired
    UserRepository userRepository;

  /*  @Autowired
    AwsService awsService;*/

    @Autowired
    SubjectRepository subjectRepository;


    public SubjectEntity Request2Entity (SubjectRequest request, Long userID) throws IOException {

        SubjectEntity entity = SubjectEntity.builder().firstName(request.getFirstName())
                .lastName(request.getLastName())
             //   .image(awsService.uploadFileFromBase64(request.getImage()))
                .birthDate(request.getBirthDate())
                .dni(request.getDni())
                .users(userRepository.findById(userID).get())
                .build();


        return entity;

    }

    public SubjectResponse Entity2Response (SubjectEntity entity) throws IOException {

        SubjectResponse response = SubjectResponse.builder().firstName(entity.getFirstName())
                .id(entity.getId())
               // .image(awsService.uploadFileFromBase64(entity.getImage()))
                .lastName(entity.getLastName())
                .dni(entity.getDni())
                .birthDate(entity.getBirthDate())
                .idUser(entity.getUsers().getId())
                .createDate(entity.getTimestamp())
                .build();

        return response;

    }

    public SubjectEntity EntityRefreshValues (SubjectEntity entity, SubjectRequest request) throws IOException {

        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        //entity.setImage(awsService.uploadFileFromBase64(request.getImage()));TODO Ver tratamiento de imagen
        entity.setBirthDate(request.getBirthDate());
        entity.setDni(request.getDni());
        entity.setTimestamp(new Timestamp(System.currentTimeMillis()));

        return entity;
    }

 /*   public  Set<SubjectEntity> subjectRequestToEntitySet(Long id){

        Set<SubjectEntity> entitylist = new HashSet<>();

        Optional<SubjectEntity> entity = this.subjectRepository.findById(id);

        entitylist.add(entity.get());



        return entitylist;
    }

    public SubjectResponse subjectEntitySetToResponse(Set<SubjectEntity> entities) throws IOException {
        SubjectResponse response= new SubjectResponse();


        for (SubjectEntity entity : entities) {

            response = this.Entity2Response(entity);

        }
        return response;*/
    }





