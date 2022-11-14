package com.ncs503.Babybook.service.impl;

import com.ncs503.Babybook.models.entity.MedicalRecordEntity;
import com.ncs503.Babybook.models.mapper.MedicalRecordMapper;
import com.ncs503.Babybook.models.request.MedicalRecordRequest;
import com.ncs503.Babybook.models.response.MedicalRecordResponse;
import com.ncs503.Babybook.models.utility.TagsMedicalRecordEnum;
import com.ncs503.Babybook.repository.MedicalRecordRepository;
import com.ncs503.Babybook.service.AwsService;
import com.ncs503.Babybook.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    private AwsService awsService;
    @Autowired
    private MedicalRecordMapper medicalRecordMapper;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Override
    public MedicalRecordResponse create(String token, String title, String body, LocalDate date,
                                List<MultipartFile> media, Long subjectId, List<TagsMedicalRecordEnum> medicalRecordEnumList) throws IOException {

//        token = token.substring(7);
//        String username = jwtUtils.extractUsername(token);
//        UserEntity userEntity = userRepository.findByEmail(username).get();
//        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
//        MedicalRecord event = eventRepository.getById(id)
//
//
//        System.out.println("\nid del user token : " + userEntity.getId());
//        System.out.println("roleEntity del token : " + roleEntity.getName());
//        System.out.println("user Business.getId() : " + business.getUsers().getId());
//
//        if (roleEntity.getName().equalsIgnoreCase("business") && userEntity.getId() == business.getUsers().getId()) {

        MedicalRecordRequest request = new MedicalRecordRequest();
        request.setBody(body);
        request.setDate(date);
        List<String> medias = new ArrayList<>();
        for(MultipartFile aux : media){
            String file = awsService.uploadFile(aux);
            medias.add(file);
        }
        request.setMedia(medias);
        request.setMedicalRecordEnumList(medicalRecordEnumList);
        request.setTitle(title);

//            request.setSubjectId(subjectRepository.getById(subjectId));

        MedicalRecordEntity entity = medicalRecordMapper.Request2Entity(request);
        MedicalRecordEntity eventLoad = medicalRecordRepository.save(entity);
        MedicalRecordResponse response = medicalRecordMapper.Entity2Response(eventLoad);

        return response;

//        }

    }

}
