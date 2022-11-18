package com.ncs503.Babybook.service.impl;

import com.ncs503.Babybook.models.entity.EventEntity;
import com.ncs503.Babybook.models.entity.SubjectEntity;
import com.ncs503.Babybook.models.entity.UserEntity;
import com.ncs503.Babybook.models.mapper.EventMapper;
import com.ncs503.Babybook.models.request.EventRequest;
import com.ncs503.Babybook.models.response.EventResponse;
import com.ncs503.Babybook.models.utility.TagsEventEnum;
import com.ncs503.Babybook.repository.EventRepository;
import com.ncs503.Babybook.repository.SubjectRepository;
import com.ncs503.Babybook.repository.UserRepository;
import com.ncs503.Babybook.service.AwsService;
import com.ncs503.Babybook.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

//    @Autowired
//    private JwtUtils jwtUtils;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private AwsService awsService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public EventResponse create(String token, String title, String body, LocalDate date,
                                List<MultipartFile> media, Long subjectId, TagsEventEnum eventEnum) throws IOException {

//        token = token.substring(7);
//        String username = jwtUtils.extractUsername(token);
//        UserEntity userEntity = userRepository.findByEmail(username).get();
//        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
//        EventEntity event = eventRepository.getById(id)


//        System.out.println("\nid del user token : " + userEntity.getId());
//        System.out.println("roleEntity del token : " + roleEntity.getName());
//        System.out.println("user event.getId() : " + event.getUsers().getId());
//
//        if (roleEntity.getName().equalsIgnoreCase("USER") && userEntity.getId() == event.getUsers().getId()) {

            EventRequest request = new EventRequest();
//            request.setUserId(userEntity.getId());
            request.setBody(body);
            request.setDate(date);
            request.setEventEnum(eventEnum);
            request.setTitle(title);

            if(media != null ) {
                List<String> medias = new ArrayList<>();
                for (MultipartFile aux : media) {
                    String file = awsService.uploadFile(aux);
                    medias.add(file);
                }
                request.setMedia(medias);
            }

            request.setSubject(subjectRepository.getById(subjectId));

            EventEntity entity = eventMapper.Request2Entity(request);
            EventEntity eventLoad = eventRepository.save(entity);
            EventResponse response = eventMapper.Entity2Response(eventLoad);

            return response;

//        }


    }

    @Override
    public EventResponse update(String token, Long eventId, String title, String body, LocalDate date,
                                List<MultipartFile> media, Long subjectId, TagsEventEnum eventEnum) throws IOException {

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

        EventEntity eventEntity = eventRepository.getById(eventId);
        EventRequest request = new EventRequest();
//        request.setUserId(userEntity.getId());

        String bodyAux = (body == null) ? eventEntity.getBody() :  body;
        request.setBody(bodyAux);
        LocalDate dateAux = (date == null) ? eventEntity.getDate() : date;
        request.setDate(dateAux);
        String titleAux = (title == null) ? eventEntity.getTitle() :  title;
        request.setTitle(titleAux);
        TagsEventEnum eventAux = ( eventEnum == null) ? eventEntity.getEventEnum() : eventEnum;
        request.setEventEnum(eventAux);

        if(media != null ) {
            List<String> mediasAux = new ArrayList<>();
            for (MultipartFile aux : media) {
                String file = awsService.uploadFile(aux);
                mediasAux.add(file);
            }
            mediasAux = (media == null) ? eventEntity.getMedia() : mediasAux;
            request.setMedia(mediasAux);
        }else{
            request.setMedia(eventEntity.getMedia());
        }

        request.setSubject(subjectRepository.getById(subjectId));

        EventEntity entity = eventMapper.EntityUpdate(eventEntity, request);
        entity.setId(eventId);
        EventEntity eventLoad = eventRepository.save(entity);
        EventResponse response = eventMapper.Entity2Response(eventLoad);

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

            EventEntity eventEntity = eventRepository.findById(eventId).orElse(null);
            eventRepository.delete(eventEntity);

//        }

    }

    @Override
    public EventResponse findById(String token, Long subjectId, Long eventId) throws Exception {

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

            EventEntity eventEntity = eventRepository.findById(eventId).orElse(null);
            if(subjectId == eventEntity.getSubject().getId()) {
                EventResponse response = eventMapper.Entity2Response(eventEntity);
                return response;
            }
            throw new Exception("No coinciden el sujeto ingresado con el sujeto del evento : " + subjectId);

//        }

    }

    @Override
    public List<EventResponse> findAllByDate(String token, Long subjectId, LocalDate date) throws Exception {

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

            List<EventEntity> eventEntityList = eventRepository.findAllByDate(subjectId, date);
            System.out.println("eventEntityList : " + eventEntityList);

            if(!eventEntityList.isEmpty()) {
                List<EventResponse> response = eventMapper.EntityList2ResponsePage(eventEntityList);
                return response;
            }
            SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElse(null);
            throw new Exception("No Existen Eventos en la fecha : " + date +  " para " + subjectEntity.getTitleee());

//        }

    }

    @Override
    public List<EventResponse> findAllByTags(String token, Long subjectId, TagsEventEnum eventEnum) throws Exception {

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
            System.out.println("eventEnum : " + eventEnum.ordinal());

            List<EventEntity> eventEntityList = eventRepository.findAllByTags(subjectId, eventEnum.ordinal());
            System.out.println("eventEntityList : " + eventEntityList);

            if(!eventEntityList.isEmpty()) {
                List<EventResponse> response = eventMapper.EntityList2ResponsePage(eventEntityList);
                return response;
            }
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElse(null);
        throw new Exception("No Existen Eventos de tipo " + eventEnum +  " para " + subjectEntity.getTitleee());

//        }

    }

}


//  metodo que muestra TODAS las imagenes del bucket
/*
            List<String> images = awsService.getObjectOfFromS3();
            for(String aux : images){
                System.out.println("aux : " + aux);
            }
 */


