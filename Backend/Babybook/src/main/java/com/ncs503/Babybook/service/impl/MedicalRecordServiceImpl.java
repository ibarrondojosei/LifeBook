package com.ncs503.Babybook.service.impl;

import com.ncs503.Babybook.models.entity.EventEntity;
import com.ncs503.Babybook.models.entity.MedicalRecordEntity;
import com.ncs503.Babybook.models.entity.SubjectEntity;
import com.ncs503.Babybook.models.mapper.MedicalRecordMapper;
import com.ncs503.Babybook.models.request.EventRequest;
import com.ncs503.Babybook.models.request.MedicalRecordRequest;
import com.ncs503.Babybook.models.response.EventResponse;
import com.ncs503.Babybook.models.response.MedicalRecordResponse;
import com.ncs503.Babybook.models.utility.TagsEventEnum;
import com.ncs503.Babybook.models.utility.TagsMedicalRecordEnum;
import com.ncs503.Babybook.repository.MedicalRecordRepository;
import com.ncs503.Babybook.repository.SubjectRepository;
import com.ncs503.Babybook.service.AwsService;
import com.ncs503.Babybook.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    private AwsService awsService;
    @Autowired
    private MedicalRecordMapper medicalRecordMapper;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public MedicalRecordResponse create(String token, String title, String body, LocalDate date,
                                List<MultipartFile> media, Long medicalDataId, TagsMedicalRecordEnum medicalRecordEnum) throws IOException {

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
        if(media != null ) {
            List<String> medias = new ArrayList<>();
            for (MultipartFile aux : media) {
                String file = awsService.uploadFile(aux);
                medias.add(file);
            }
            request.setMedia(medias);
        }
        request.setMedicalRecordEnum(medicalRecordEnum);
        request.setTitle(title);

//        request.setSubject(subjectRepository.getById(subjectId));

        MedicalRecordEntity entity = medicalRecordMapper.Request2Entity(request);
        MedicalRecordEntity eventLoad = medicalRecordRepository.save(entity);
        MedicalRecordResponse response = medicalRecordMapper.Entity2Response(eventLoad);

        return response;

//        }

    }

    @Override
    public MedicalRecordResponse update(String token, Long medicalRecordId, String title, String body, LocalDate date,
                                List<MultipartFile> media, Long subjectId, TagsMedicalRecordEnum medicalRecordEnum) throws IOException {

//        token = token.substring(7);
//        String username = jwtUtils.extractUsername(token);
//        UserEntity userEntity = userRepository.findByEmail(username).get();
//        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
//        EventEntity event = eventRepository.getById(eventId)
//
//
//        System.out.println("\nid del user token : " + userEntity.getId());
//        System.out.println("roleEntity del token : " + roleEntity.getName());
//        System.out.println("user event.getId() : " + event.getUsers().getId());
//
//        if (roleEntity.getName().equalsIgnoreCase("USER") && userEntity.getId() == event.getUsers().getId()) {

        MedicalRecordEntity medicalRecordEntity = medicalRecordRepository.getById(medicalRecordId);
        MedicalRecordRequest request = new MedicalRecordRequest();
//        request.setUserId(userEntity.getId());

        String bodyAux = (body == null) ? medicalRecordEntity.getBody() :  body;
        request.setBody(bodyAux);
        LocalDate dateAux = (date == null) ? medicalRecordEntity.getDate() : date;
        request.setDate(dateAux);
        String titleAux = (title == null) ? medicalRecordEntity.getTitle() :  title;
        request.setTitle(titleAux);
        TagsMedicalRecordEnum medicalRecordEnumAux = ( medicalRecordEnum == null) ? medicalRecordEntity.getMedicalRecordEnums() : medicalRecordEnum;
        request.setMedicalRecordEnum(medicalRecordEnumAux);

        if(media != null) {
            List<String> mediasAux = new ArrayList<>();
            for (MultipartFile aux : media) {
                String file = awsService.uploadFile(aux);
                mediasAux.add(file);
            }
            mediasAux = (media == null) ? medicalRecordEntity.getMedia() : mediasAux;
            request.setMedia(mediasAux);
        }else{
            request.setMedia(medicalRecordEntity.getMedia());
        }

//            request.setSubjectId(subjectRepository.getById(subjectId));

        MedicalRecordEntity entity = medicalRecordMapper.EntityUpdate(medicalRecordEntity, request);
        entity.setId(medicalRecordId);
        MedicalRecordEntity eventLoad = medicalRecordRepository.save(entity);
        MedicalRecordResponse response = medicalRecordMapper.Entity2Response(eventLoad);

        return response;

//        }

    }

    @Override
    public void delete(String token, Long subjectId, Long eventId) throws IOException {

//        token = token.substring(7);
//        String username = jwtUtils.extractUsername(token);
//        UserEntity userEntity = userRepository.findByEmail(username).get();
//        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
//        EventEntity event = eventRepository.getById(eventId)
//
//
//        System.out.println("\nid del user token : " + userEntity.getId());
//        System.out.println("roleEntity del token : " + roleEntity.getName());
//        System.out.println("user event.getId() : " + event.getUsers().getId());
//
//        if (roleEntity.getName().equalsIgnoreCase("USER") && userEntity.getId() == event.getUsers().getId()) {

        MedicalRecordEntity medicalRecordEntity = medicalRecordRepository.findById(eventId).orElse(null);
        medicalRecordRepository.delete(medicalRecordEntity);

//        }

    }

    @Override
    public MedicalRecordResponse findById(String token, Long subjectId, Long eventId) throws Exception {

//        token = token.substring(7);
//        String username = jwtUtils.extractUsername(token);
//        UserEntity userEntity = userRepository.findByEmail(username).get();
//        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
//        EventEntity event = eventRepository.getById(eventId)
//
//
//        System.out.println("\nid del user token : " + userEntity.getId());
//        System.out.println("roleEntity del token : " + roleEntity.getName());
//        System.out.println("user event.getId() : " + event.getUsers().getId());
//
//        if (roleEntity.getName().equalsIgnoreCase("USER") && userEntity.getId() == event.getUsers().getId()) {

        MedicalRecordEntity medicalRecordEntity = medicalRecordRepository.findById(eventId).orElse(null);
//        if(subjectId == medicalRecordEntity.getSubject().getId()) {
//            MedicalRecordResponse response = medicalRecordMapper.Entity2Response(medicalRecordEntity);
//            return response;
//        }
        throw new Exception("No coinciden el sujeto ingresado con el sujeto del evento : " + subjectId);

//        }

    }

    @Override
    public List<MedicalRecordResponse> findAllByDate(String token, Long subjectId, LocalDate date) throws Exception {

//        token = token.substring(7);
//        String username = jwtUtils.extractUsername(token);
//        UserEntity userEntity = userRepository.findByEmail(username).get();
//        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
//        EventEntity event = eventRepository.getById(eventId)
//
//
//        System.out.println("\nid del user token : " + userEntity.getId());
//        System.out.println("roleEntity del token : " + roleEntity.getName());
//        System.out.println("user event.getId() : " + event.getUsers().getId());
//
//        if (roleEntity.getName().equalsIgnoreCase("USER") && userEntity.getId() == event.getUsers().getId()) {

        System.out.println("\nsubject_id : " + subjectId);
        System.out.println("date : " + date);
        List<MedicalRecordEntity> medicalRecordEntityList = medicalRecordRepository.findAllByDate(subjectId, date);
        System.out.println("medicalRecordEntityList : " + medicalRecordEntityList);

        if(!medicalRecordEntityList.isEmpty()) {
            List<MedicalRecordResponse> response = medicalRecordMapper.EntityList2ResponsePage(medicalRecordEntityList);
            return response;
        }

        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElse(null);
        throw new Exception("No Existen Registros Medicos en la fecha : " + date +  " para " + subjectEntity.getTitleee());
//        }

    }

    @Override
    public List<MedicalRecordResponse> findAllByTags(String token, Long subjectId, TagsMedicalRecordEnum medicalRecordEnum) throws Exception {

//        token = token.substring(7);
//        String username = jwtUtils.extractUsername(token);
//        UserEntity userEntity = userRepository.findByEmail(username).get();
//        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
//        EventEntity event = eventRepository.getById(eventId)
//
//
//        System.out.println("\nid del user token : " + userEntity.getId());
//        System.out.println("roleEntity del token : " + roleEntity.getName());
//        System.out.println("user event.getId() : " + event.getUsers().getId());
//
//        if (roleEntity.getName().equalsIgnoreCase("USER") && userEntity.getId() == event.getUsers().getId()) {

        System.out.println("\nsubject_id : " + subjectId);
        System.out.println("eventEnum : " + medicalRecordEnum.ordinal());

        List<MedicalRecordEntity> medicalRecordEntityList = medicalRecordRepository.findAllByTags(subjectId, medicalRecordEnum.ordinal());
        System.out.println("medicalRecordEntityList : " + medicalRecordEntityList);

        if(!medicalRecordEntityList.isEmpty()) {
            List<MedicalRecordResponse> response = medicalRecordMapper.EntityList2ResponsePage(medicalRecordEntityList);
            return response;
        }
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElse(null);
        throw new Exception("No Existen Registros Medicos de tipo " + medicalRecordEnum +  " para " + subjectEntity.getTitleee());
//        }

    }

}
