package com.ncs503.Babybook.service.impl;

import com.ncs503.Babybook.auth.filter.JwtUtils;
import com.ncs503.Babybook.models.entity.EventEntity;
import com.ncs503.Babybook.models.entity.RoleEntity;
import com.ncs503.Babybook.models.entity.SubjectEntity;
import com.ncs503.Babybook.models.entity.UserEntity;
import com.ncs503.Babybook.models.mapper.EventMapper;
import com.ncs503.Babybook.models.request.EventRequest;
import com.ncs503.Babybook.models.request.specification.EventByUserAndSubjectRequest;
import com.ncs503.Babybook.models.response.EventFilterBySubjectResponse;
import com.ncs503.Babybook.models.response.EventResponse;
import com.ncs503.Babybook.models.response.PaginationResponse;
import com.ncs503.Babybook.models.utility.TagsEventEnum;
import com.ncs503.Babybook.repository.EventRepository;
import com.ncs503.Babybook.repository.SubjectRepository;
import com.ncs503.Babybook.repository.UserRepository;
import com.ncs503.Babybook.repository.specification.EventByUserAndSubjectSpecification;
import com.ncs503.Babybook.service.AwsService;
import com.ncs503.Babybook.service.EventService;

import com.ncs503.Babybook.utils.PaginationByEvent;
import com.ncs503.Babybook.utils.PaginationByFiltersUtil;
import com.ncs503.Babybook.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private JwtUtils jwtUtils;
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
    @Autowired
    private EventByUserAndSubjectSpecification eventByUserAndSubjectSpecification;

    @Override
    public EventResponse create(String token, String title, String body, LocalDate date,
                                List<MultipartFile> media, Boolean highlightMoment, Long subjectId, TagsEventEnum eventEnum) throws Exception {

        token = token.substring(7);
        String username = jwtUtils.extractUsername(token);
//        System.out.println("username : " + username);

        UserEntity userEntity = userRepository.findByEmail(username).get();
        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElse(null);

        System.out.println("\nId del User: " + userEntity.getId());
        System.out.println("rol del User : " + roleEntity.getName());
        System.out.println("ID de User del subject : " + subjectEntity.getUsers().getId());
        System.out.println("media : " + media);

        if (roleEntity.getName().equalsIgnoreCase("USER") && userEntity.getId() == subjectEntity.getUsers().getId()) {

            EventRequest request = new EventRequest();
            request.setUserId(userEntity);
            request.setBody(body);
            request.setDate(date);
            request.setEventEnum(eventEnum);
            request.setTitle(title);
            request.setHighlightMoment(highlightMoment);

            if (media != null) {
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

        }

        throw new Exception("el Token del USER no coincide con el token del User del Subject ");

    }

    @Override
    public EventResponse update(String token, Long eventId, String title, String body, LocalDate date,
                                List<MultipartFile> media, Boolean highlightMoment, Long subjectId, TagsEventEnum eventEnum) throws Exception {

        token = token.substring(7);
        String username = jwtUtils.extractUsername(token);

        UserEntity userEntity = userRepository.findByEmail(username).get();
        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElse(null);

        if (roleEntity.getName().equalsIgnoreCase("USER") && userEntity.getId() == subjectEntity.getUsers().getId()) {

            EventEntity eventEntity = eventRepository.getById(eventId);

            if (eventEntity.getUserId().getId() == userEntity.getId()) {

                EventRequest request = new EventRequest();
                request.setUserId(userEntity);

                String bodyAux = (body == null) ? eventEntity.getBody() : body;
                request.setBody(bodyAux);
                LocalDate dateAux = (date == null) ? eventEntity.getDate() : date;
                request.setDate(dateAux);
                String titleAux = (title == null) ? eventEntity.getTitle() : title;
                request.setTitle(titleAux);
                TagsEventEnum eventAux = (eventEnum == null) ? eventEntity.getEventEnum() : eventEnum;
                request.setEventEnum(eventAux);
                Boolean highlightMomentAux = (highlightMoment == null) ? eventEntity.getHighlightMoment() : highlightMoment;
                request.setHighlightMoment(highlightMomentAux);

                if (media != null) {
                    List<String> mediasAux = new ArrayList<>();
                    for (MultipartFile aux : media) {
                        String file = awsService.uploadFile(aux);
                        mediasAux.add(file);
                    }
                    mediasAux = (media == null) ? eventEntity.getMedia() : mediasAux;
                    request.setMedia(mediasAux);
                } else {
                    request.setMedia(eventEntity.getMedia());
                }

                request.setSubject(subjectRepository.getById(subjectId));

                EventEntity entity = eventMapper.EntityUpdate(eventEntity, request);
                entity.setId(eventId);
                EventEntity eventLoad = eventRepository.save(entity);
                EventResponse response = eventMapper.Entity2Response(eventLoad);

                return response;
            }

            throw new Exception("el Evento no pertenece a este Uuario ");

        }

        throw new Exception("el Token del USER no coincide con el token del User del Subject ");

    }

    @Override
    public void delete(String token, Long subjectId, Long eventId) throws Exception {

        token = token.substring(7);
        String username = jwtUtils.extractUsername(token);

        UserEntity userEntity = userRepository.findByEmail(username).get();
        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElse(null);

        if (roleEntity.getName().equalsIgnoreCase("USER") && userEntity.getId() == subjectEntity.getUsers().getId()) {

            EventEntity eventEntity = eventRepository.getById(eventId);

            System.out.println("\n id user : " + userEntity.getId());
            System.out.println("id user de events : " + eventEntity.getUserId().getId());

            if (eventEntity.getUserId().getId() == userEntity.getId()) {

                eventRepository.delete(eventEntity);

            } else {
                throw new Exception("el Evento No se puede Eliminar porque NO pertenece a este Uuario ");
            }

        } else {
            throw new Exception("el Token del USER no coincide con el token del User del Subject ");
        }
    }

    @Override
    public List<EventFilterBySubjectResponse> findByIdSubject(String token, Long subjectId) throws Exception {

        token = token.substring(7);
        String username = jwtUtils.extractUsername(token);

        UserEntity userEntity = userRepository.findByEmail(username).get();
        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElse(null);

        if (roleEntity.getName().equalsIgnoreCase("USER") && userEntity.getId() == subjectEntity.getUsers().getId()) {

            List<EventEntity> eventEntities = eventRepository.findAllBySubject(subjectId);
            List<EventFilterBySubjectResponse> responses = eventMapper.EntityList2ResponsePage(eventEntities);

            if (responses.isEmpty()) {
                throw new Exception("No Existen Eventos para " + subjectEntity.getFirstName() + " " + subjectEntity.getLastName());
            }
            return responses;

        }

        throw new Exception("No Existen Eventos para " + subjectEntity.getFirstName() + " " + subjectEntity.getLastName());

    }

    @Override
    public List<EventFilterBySubjectResponse> findAllByTags(String token, Long subjectId, TagsEventEnum eventEnum) throws Exception {

        token = token.substring(7);
        String username = jwtUtils.extractUsername(token);
        UserEntity userEntity = userRepository.findByEmail(username).get();
        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElse(null);

        if (roleEntity.getName().equalsIgnoreCase("USER") && userEntity.getId() == subjectEntity.getUsers().getId()) {

            List<EventEntity> eventEntityList = eventRepository.findAllByTags(subjectId, eventEnum.ordinal());

            if (!eventEntityList.isEmpty()) {
                List<EventFilterBySubjectResponse> responses = eventMapper.EntityList2ResponsePage(eventEntityList);
                return responses;
            }

            SubjectEntity subjectEntity1 = subjectRepository.findById(subjectId).orElse(null);
            throw new Exception("No Existen Eventos de tipo " + eventEnum + " para " + subjectEntity.getFirstName() + " " + subjectEntity.getLastName());

        }

        throw new Exception("No Existen Eventos para " + subjectEntity.getFirstName() + " " + subjectEntity.getLastName());

    }

    @Override
    public List<EventFilterBySubjectResponse> findAllByHighlightMoment(String token, Long subjectId) throws Exception {

        token = token.substring(7);
        String username = jwtUtils.extractUsername(token);

        UserEntity userEntity = userRepository.findByEmail(username).get();
        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElse(null);

        if (roleEntity.getName().equalsIgnoreCase("USER") && userEntity.getId() == subjectEntity.getUsers().getId()) {

            Boolean highlightMoment = true;
            List<EventEntity> eventEntityList = eventRepository.findAllByHighlightMoment(subjectId, highlightMoment);

            if (!eventEntityList.isEmpty()) {
                List<EventFilterBySubjectResponse> responses = eventMapper.EntityList2ResponsePage(eventEntityList);
                return responses;
            } else {
                throw new Exception("No Existen EVENTOS DESTACADOS ");
            }

//            SubjectEntity subjectEntity1 = subjectRepository.findById(subjectId).orElse(null);
//            throw new Exception("No Existen Eventos " + subjectEntity.getFirstName() + " " + subjectEntity.getLastName());

        }

        throw new Exception("No Existen Eventos para " + subjectEntity.getFirstName() + " " + subjectEntity.getLastName());

    }

}








//    @Override
//    public PaginationResponse findAllByTagsPage(String token, Long subjectId, TagsEventEnum eventEnum, Optional<Integer> pageNumber, Optional<Integer> size) throws Exception {
//
//        token = token.substring(7);
//        String username = jwtUtils.extractUsername(token);
//        UserEntity userEntity = userRepository.findByEmail(username).get();
//        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
//        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElse(null);
//
//        if (roleEntity.getName().equalsIgnoreCase("USER") && userEntity.getId() == subjectEntity.getUsers().getId()) {
//
//            List<EventEntity> eventEntityList = eventRepository.findAllByTags(subjectId, eventEnum.ordinal());
//
//            PaginationByEvent pagination = new PaginationByEvent(eventEntityList, eventRepository, pageNumber, size,
//                    "/events?page=%d&size=%d");
//            Page page = pagination.getPage();
//
//            List<EventEntity> events = page.getContent();
//
//            if (!events.isEmpty()) {
//                List<EventFilterBySubjectResponse> responses = eventMapper.EntityList2ResponsePage(events);
//
//                return PaginationResponse.builder()
//                        .entities(responses)
//                        .nextPageURI(pagination.getNext())
//                        .prevPageURI(pagination.getPrevious())
//                        .build();
//
//            }
//
//            SubjectEntity subjectEntity1 = subjectRepository.findById(subjectId).orElse(null);
//            throw new Exception("No Existen Eventos de tipo " + eventEnum + " para " + subjectEntity.getFirstName() + " " + subjectEntity.getLastName());
//
//        }
//
//        throw new Exception("No Existen Eventos para " + subjectEntity.getFirstName() + " " + subjectEntity.getLastName());
//
//    }
//
//    @Transactional
//    @Override
//    public PaginationResponse findAllPage(String token, TagsEventEnum eventEnum, Long subjectId, Optional<Integer> pageNumber, Optional<Integer> size, String order) throws Exception {
//
//        token = token.substring(7);
//        String username = jwtUtils.extractUsername(token);
//        UserEntity userEntity = userRepository.findByEmail(username).get();
//        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
//
//        EventByUserAndSubjectRequest filtersRequest = new EventByUserAndSubjectRequest(eventEnum, subjectId, order);
//
//        Specification<EventEntity> specification = eventByUserAndSubjectSpecification.getByUserAndSubject(filtersRequest);
//
//        PaginationByFiltersUtil pagination = new PaginationByFiltersUtil(specification, eventRepository, pageNumber, size,
//                "/events/findAllPage/page=%d&size=%d");
//
//        System.out.println("\npagination : " + pagination.getPage().toString());
//
//        Page page = pagination.getPage();
//
//        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElse(null);
//        System.out.println("\nId del User: " + userEntity.getId());
//        System.out.println("rol del User : " + roleEntity.getName());
//        System.out.println("Id del User del subject : " + subjectEntity.getUsers().getId());
//
//        if (roleEntity.getName().equalsIgnoreCase("USER") && userEntity.getId() == subjectEntity.getUsers().getId()) {
//
//            List<EventEntity> events = page.getContent();
//
//            List<EventFilterBySubjectResponse> responses = eventMapper.EntityList2ResponsePage(events);
//
//            return PaginationResponse.builder()
//                    .entities(responses)
//                    .nextPageURI(pagination.getNext())
//                    .prevPageURI(pagination.getPrevious())
//                    .build();
//        }
//
//        throw new Exception("el Token del USER no coincide con el token del User del Subject ");
//
//    }
//}

    /*
      @Override
    public PaginationResponse getSubjectByName(String name, String order, Optional<Integer> pageNumber, Optional<Integer> size,
                                               String token) {
        token = token.substring(7);
        String username = jwtUtils.extractUsername(token);
        UserEntity userEntity = userRepository.findByEmail(username).get();

        Long userId = userEntity.getId();

        if (userEntity.getId()!=null){

            SubjectByNameRequest filtersRequest = new SubjectByNameRequest(name, order,userId);
            Specification<EventEntity> specification = subjectByNameSpecification.getByName(filtersRequest);
            PaginationByFiltersUtil pagination = new PaginationByFiltersUtil(specification, subjectRepository, pageNumber, size,
                    "/business/getByName/page=%d&size=%d");
            Page page = pagination.getPage();

            List<SubjectResponse> responses = page.getContent();

            return PaginationResponse.builder()
                    .entities(responses)
                    .nextPageURI(pagination.getNext())
                    .prevPageURI(pagination.getPrevious())
                    .build();

        }else {
            throw new RuntimeExceptionCustom("404 ::subject name does not belong to user");
        }
    }
     */

//    @Override
//    public EventResponse findById(String token, Long subjectId, Long eventId) throws Exception {
//
//        token = token.substring(7);
//        String username = jwtUtils.extractUsername(token);
////        System.out.println("username : " + username);
//
//        UserEntity userEntity = userRepository.findByEmail(username).get();
//        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
//        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElse(null);
//
////        System.out.println("\nId del User: " + userEntity.getId());
////        System.out.println("rol del User : " + roleEntity.getName());
////        System.out.println("ID de User del subject : " + subject.getUserId().getId());
////        System.out.println("media : " + media);
//
//        if (roleEntity.getName().equalsIgnoreCase("USER") && userEntity.getId() == subjectEntity.getUsers().getId()) {
//
//            EventEntity eventEntity = eventRepository.findById(eventId).orElse(null);
//
//            if(subjectId == eventEntity.getId()) {
//                EventResponse response = eventMapper.Entity2Response(eventEntity);
//                return response;
//            }
//
//            throw new Exception("No coinciden el sujeto ingresado con el sujeto del evento : " + subjectId);
//
//        }
//
//        throw new Exception("el Token del USER no coincide con el token del User del Subject ");
//
//    }


//    @Override
//    public List<EventFilterBySubjectResponse> findAllByDate(String token, Long subjectId, LocalDate date) throws Exception {
//
//        token = token.substring(7);
//        String username = jwtUtils.extractUsername(token);
////        System.out.println("username : " + username);
//
//        UserEntity userEntity = userRepository.findByEmail(username).get();
//        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
//        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElse(null);
//
////        System.out.println("\nId del User: " + userEntity.getId());
////        System.out.println("rol del User : " + roleEntity.getName());
////        System.out.println("ID de User del subject : " + subject.getUserId().getId());
////        System.out.println("media : " + media);
//
//        if (roleEntity.getName().equalsIgnoreCase("USER") && userEntity.getId() == subjectEntity.getUsers().getId()) {
//
//            System.out.println("\nsubject_id : " + subjectId);
//            System.out.println("date : " + date);
//
//            List<EventEntity> eventEntityList = eventRepository.findAllByDate(subjectId, date);
//            System.out.println("eventEntityList : " + eventEntityList);
//
//            if(!eventEntityList.isEmpty()) {
//                List<EventFilterBySubjectResponse> responses = eventMapper.EntityList2ResponsePage(eventEntityList);
//                return responses;
//            }
//
//            SubjectEntity subjectEntity1 = subjectRepository.findById(subjectId).orElse(null);
//            throw new Exception("No Existen Eventos en la fecha : " + date +  " para " + subjectEntity1);
//
//        }
//
//        throw new Exception("No Existen Eventos para " + subjectEntity.getFirstName() + " " + subjectEntity.getLastName());
//
//    }


//  metodo que muestra TODAS las imagenes del bucket
/*
            List<String> images = awsService.getObjectOfFromS3();
            for(String aux : images){
                System.out.println("aux : " + aux);
            }
 */


