package com.ncs503.Babybook.service.impl;

import com.ncs503.Babybook.auth.filter.JwtUtils;
import com.ncs503.Babybook.models.entity.*;
import com.ncs503.Babybook.models.mapper.MedicalRecordMapper;
import com.ncs503.Babybook.models.request.MedicalRecordRequest;
import com.ncs503.Babybook.models.response.MedicalRecordResponse;
import com.ncs503.Babybook.models.response.medicalRecordFilterByMedicalDataResponse;
import com.ncs503.Babybook.models.utility.TagsMedicalRecordEnum;
import com.ncs503.Babybook.repository.MedicalDataRepository;
import com.ncs503.Babybook.repository.MedicalRecordRepository;
import com.ncs503.Babybook.repository.UserRepository;
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
    private MedicalDataRepository medicalDataRepository;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;


    @Override
    public MedicalRecordResponse create(String token, String title, String body, LocalDate date,
                                List<MultipartFile> media, Boolean highlightMoment, Long medicalDataId, TagsMedicalRecordEnum medicalRecordEnum) throws Exception {


        token = token.substring(7);
        String username = jwtUtils.extractUsername(token);

        UserEntity userEntity = userRepository.findByEmail(username).get();
        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
        MedicalDataEntity medicalDataEntity = medicalDataRepository.findById(medicalDataId).orElse(null);

//        System.out.println("\nId del User: " + userEntity.getId());
//        System.out.println("rol del User : " + roleEntity.getName());
//        System.out.println("ID de User del subject : " + medicalDataEntity.getSubject().getUsers().getId());
//        System.out.println("media : " + media);

        if (roleEntity.getName().equalsIgnoreCase("USER") && userEntity.getId() == medicalDataEntity.getSubject().getUsers().getId()) {

            MedicalRecordRequest request = new MedicalRecordRequest();
            request.setUserId(userEntity);
            request.setBody(body);
            request.setDate(date);
            request.setMedicalRecordEnum(medicalRecordEnum);
            request.setTitle(title);
            request.setHighlightMoment(highlightMoment);

            if(media != null ) {
                List<String> medias = new ArrayList<>();
                for (MultipartFile aux : media) {
                    String file = awsService.uploadFile(aux);
                    medias.add(file);
                }
                request.setMedia(medias);
            }

            request.setMedicalData(medicalDataRepository.findById(medicalDataId).orElse(null));

            MedicalRecordEntity entity = medicalRecordMapper.Request2Entity(request);
            MedicalRecordEntity medicalRecordLoad = medicalRecordRepository.save(entity);
            MedicalRecordResponse response = medicalRecordMapper.Entity2Response(medicalRecordLoad);

            return response;

        }

        throw new Exception("el Token del USER no coincide con el token del User del MedicalData ");

    }

    @Override
    public MedicalRecordResponse update(String token, Long medicalRecordId, String title, String body, LocalDate date,
                                List<MultipartFile> media, Boolean highlightMoment, Long medicalDataId, TagsMedicalRecordEnum medicalRecordEnum) throws Exception {


        token = token.substring(7);
        String username = jwtUtils.extractUsername(token);

        UserEntity userEntity = userRepository.findByEmail(username).get();
        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
        MedicalDataEntity medicalDataEntity = medicalDataRepository.findById(medicalDataId).orElse(null);

        if (roleEntity.getName().equalsIgnoreCase("USER") && userEntity.getId() == medicalDataEntity.getSubject().getUsers().getId()) {

            MedicalRecordEntity medicalRecordEntity = medicalRecordRepository.getById(medicalRecordId);
            MedicalRecordRequest request = new MedicalRecordRequest();
            request.setUserId(userEntity);

            String bodyAux = (body == null) ? medicalRecordEntity.getBody() :  body;
            request.setBody(bodyAux);
            LocalDate dateAux = (date == null) ? medicalRecordEntity.getDate() : date;
            request.setDate(dateAux);
            String titleAux = (title == null) ? medicalRecordEntity.getTitle() :  title;
            request.setTitle(titleAux);
            TagsMedicalRecordEnum medicalRecordEnumAux = ( medicalRecordEnum == null) ? medicalRecordEntity.getMedicalRecordEnums() : medicalRecordEnum;
            request.setMedicalRecordEnum(medicalRecordEnumAux);
            Boolean highlightMomentAux = (highlightMoment == null) ? medicalRecordEntity.getHighlightMoment() : highlightMoment;
            request.setHighlightMoment(highlightMomentAux);

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

            request.setMedicalData(medicalDataRepository.findById(medicalDataId).orElse(null));

            MedicalRecordEntity entity = medicalRecordMapper.EntityUpdate(medicalRecordEntity, request);
            entity.setId(medicalRecordId);
            MedicalRecordEntity eventLoad = medicalRecordRepository.save(entity);
            MedicalRecordResponse response = medicalRecordMapper.Entity2Response(eventLoad);

            return response;

        }

        throw new Exception("el Token del USER no coincide con el token del User del MedicalData ");

    }

    @Override
    public void delete(String token, Long medicalDataId, Long eventId) throws Exception {

        token = token.substring(7);
        String username = jwtUtils.extractUsername(token);

        UserEntity userEntity = userRepository.findByEmail(username).get();
        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
        MedicalDataEntity medicalDataEntity = medicalDataRepository.findById(medicalDataId).orElse(null);

        if (roleEntity.getName().equalsIgnoreCase("USER") && userEntity.getId() == medicalDataEntity.getSubject().getUsers().getId()) {

            MedicalRecordEntity medicalRecordEntity = medicalRecordRepository.findById(eventId).orElse(null);
            medicalRecordRepository.delete(medicalRecordEntity);

        }

        throw new Exception("el Token del USER no coincide con el token del User del MedicalData ");

    }

    @Override
    public List<medicalRecordFilterByMedicalDataResponse> findBySubject(String token, Long subjectId) throws Exception {

        token = token.substring(7);
        String username = jwtUtils.extractUsername(token);

        UserEntity userEntity = userRepository.findByEmail(username).get();
        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
        MedicalDataEntity medicalDataEntity = medicalDataRepository.findById(subjectId).orElse(null);

        if (roleEntity.getName().equalsIgnoreCase("USER") && userEntity.getId() == medicalDataEntity.getSubject().getUsers().getId()) {

            List<MedicalRecordEntity> medicalRecordEntityList = medicalRecordRepository.findBySubject(subjectId);

            if(!medicalRecordEntityList.isEmpty()) {
                List<medicalRecordFilterByMedicalDataResponse> response = medicalRecordMapper.EntityList2ResponsePage1(medicalRecordEntityList);
                return response;
            }
        }

        throw new Exception("el Token del USER no coincide con el token del User del MedicalData ");

    }

    @Override
    public List<medicalRecordFilterByMedicalDataResponse> findAllByTags(String token, Long subjectId, TagsMedicalRecordEnum medicalRecordEnum) throws Exception {

        token = token.substring(7);
        String username = jwtUtils.extractUsername(token);

        UserEntity userEntity = userRepository.findByEmail(username).get();
        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
        MedicalDataEntity medicalDataEntity = medicalDataRepository.findById(subjectId).orElse(null);

        System.out.println("\nId del User: " + userEntity.getId());
        System.out.println("rol del User : " + roleEntity.getName());
        System.out.println("ID de User del subject : " + medicalDataEntity.getSubject().getUsers().getId());

        if (roleEntity.getName().equalsIgnoreCase("USER") && userEntity.getId() == medicalDataEntity.getSubject().getUsers().getId()) {

            List<MedicalRecordEntity> medicalRecordEntityList = medicalRecordRepository.findAllByTags(subjectId, medicalRecordEnum.ordinal());

            if(!medicalRecordEntityList.isEmpty()) {
                List<medicalRecordFilterByMedicalDataResponse> response = medicalRecordMapper.EntityList2ResponsePage1(medicalRecordEntityList);
                return response;
            }

            MedicalRecordEntity medicalRecord = medicalRecordRepository.findById(subjectId).orElse(null);

            throw new Exception("No Existen Registros Medicos de tipo " + medicalRecordEnum );

        }

        throw new Exception("el Token del USER no coincide con el token del User del MedicalData ");

    }

    @Override
    public List<medicalRecordFilterByMedicalDataResponse> findAllByHighlightMoment(String token, Long subjectId) throws Exception {

            token = token.substring(7);
            String username = jwtUtils.extractUsername(token);

            UserEntity userEntity = userRepository.findByEmail(username).get();
            RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
            MedicalDataEntity medicalDataEntity = medicalDataRepository.findById(subjectId).orElse(null);

            if (roleEntity.getName().equalsIgnoreCase("USER") && userEntity.getId() == medicalDataEntity.getSubject().getUsers().getId() ) {

                Boolean highlightMoment = true;

                List<MedicalRecordEntity> medicalRecordEntityList = medicalRecordRepository.findAllByHighlightMoment(subjectId, highlightMoment);

                if (!medicalRecordEntityList.isEmpty()) {
                    List<medicalRecordFilterByMedicalDataResponse> response = medicalRecordMapper.EntityList2ResponsePage1(medicalRecordEntityList);
                    return response;
                }else {
                    throw new Exception("No Existen Registros Medicos DESTACADOS ");
                }

            }

            throw new Exception("el Token del USER no coincide con el token del User del MedicalData ");

    }

}







//    @Override
//    public MedicalRecordResponse findById(String token, Long medicalDataId, Long eventId) throws Exception {
//
//        token = token.substring(7);
//        String username = jwtUtils.extractUsername(token);
//
//        UserEntity userEntity = userRepository.findByEmail(username).get();
//        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
//        MedicalDataEntity medicalDataEntity = medicalDataRepository.findById(medicalDataId).orElse(null);
//
//        if (roleEntity.getName().equalsIgnoreCase("USER") && userEntity.getId() == medicalDataEntity.getSubject().getUsers().getId()) {
//
//            MedicalRecordEntity medicalRecordEntity = medicalRecordRepository.findById(eventId).orElse(null);
//
//            if(medicalDataId == medicalRecordEntity.getId()) {
//                MedicalRecordResponse response = medicalRecordMapper.Entity2Response(medicalRecordEntity);
//                return response;
//            }
//
//            throw new Exception("No coinciden el sujeto ingresado con el sujeto del evento : " + medicalDataId);
//
//        }
//
//        throw new Exception("el Token del USER no coincide con el token del User del MedicalData ");
//
//    }

