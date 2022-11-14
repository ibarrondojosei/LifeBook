package com.ncs503.Babybook.service.impl;

import com.ncs503.Babybook.models.entity.EventEntity;
import com.ncs503.Babybook.models.mapper.EventMapper;
import com.ncs503.Babybook.models.request.EventRequest;
import com.ncs503.Babybook.models.response.EventResponse;
import com.ncs503.Babybook.models.utility.TagsEventEnum;
import com.ncs503.Babybook.repository.EventRepository;
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

    @Override
    public EventResponse create(String token, String title, String body, LocalDate date,
                                List<MultipartFile> media, Long subjectId, List<TagsEventEnum> eventEnumList) throws IOException {

//        token = token.substring(7);
//        String username = jwtUtils.extractUsername(token);
//        UserEntity userEntity = userRepository.findByEmail(username).get();
//        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
//        EventEntity event = eventRepository.getById(id)
//
//
//        System.out.println("\nid del user token : " + userEntity.getId());
//        System.out.println("roleEntity del token : " + roleEntity.getName());
//        System.out.println("user Business.getId() : " + business.getUsers().getId());
//
//        if (roleEntity.getName().equalsIgnoreCase("business") && userEntity.getId() == business.getUsers().getId()) {

            EventRequest request = new EventRequest();
            request.setBody(body);
            request.setDate(date);
            List<String> medias = new ArrayList<>();
            for(MultipartFile aux : media){
                String file = awsService.uploadFile(aux);
                medias.add(file);
            }
            request.setMedia(medias);
            request.setEventEnumList(eventEnumList);
            request.setTitle(title);

//            request.setSubjectId(subjectRepository.getById(subjectId));

            EventEntity entity = eventMapper.Request2Entity(request);
            EventEntity eventLoad = eventRepository.save(entity);
            EventResponse response = eventMapper.Entity2Response(eventLoad);

            return response;

//        }

    }


}
