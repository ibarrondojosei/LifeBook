package com.ncs503.Babybook.models.mapper;

import com.ncs503.Babybook.models.entity.EventEntity;
import com.ncs503.Babybook.models.request.EventRequest;

import com.ncs503.Babybook.models.response.EventFilterBySubjectResponse;

import com.ncs503.Babybook.models.response.EventResponse;
import com.ncs503.Babybook.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class EventMapper {

    @Autowired
    private SubjectRepository subjectRepository;

    public EventEntity Request2Entity (EventRequest request)  {

        EventEntity entity = new EventEntity();

        return entity.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .date(request.getDate())
                .media(request.getMedia())
                .eventEnum(request.getEventEnum())
                .subjectEntity((request.getSubject()))
                .userId(request.getUserId())
                .highlightMoment(request.getHighlightMoment())
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .sofdelete(false)
                .build();
    }

    public EventResponse Entity2Response (EventEntity entity){

        return EventResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .body(entity.getBody())
                .date(entity.getDate())
                .media(entity.getMedia())
                .highlightMoment(entity.getHighlightMoment())
                .eventEnum(entity.getEventEnum())
                .subjectId(entity.getSubjectEntity().getId())
                .userId(entity.getUserId().getId())
                .timestamp(entity.getTimestamp())
                .build();
    }

    public EventEntity EntityUpdate (EventEntity eventEntity, EventRequest request)  {

        return EventEntity.builder()
                .id(request.getId())
                .title(request.getTitle())
                .body(request.getBody())
                .date(request.getDate())
                .media(request.getMedia())
                .eventEnum(request.getEventEnum())
                .subjectEntity(request.getSubject())
                .highlightMoment(request.getHighlightMoment())
                .userId(request.getUserId())
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .sofdelete(false)
                .build();

    }

    public List<EventResponse> EntityList2Response(List<EventEntity> EventList){


        List<EventResponse> responses = new ArrayList<>();

        for ( EventEntity Event: EventList){
            responses.add(Entity2Response(Event));
        }

        return responses;
    }

    public EventFilterBySubjectResponse Entity2ResponsePage (EventEntity entity){

        return EventFilterBySubjectResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .body(entity.getBody())
                .date(entity.getDate())
                .media(entity.getMedia())
//                .highlightMoment(entity.getHighlightMoment())
                .build();
    }

    public List<EventFilterBySubjectResponse> EntityList2ResponsePage(List<EventEntity> EventList){

        List<EventFilterBySubjectResponse> responses = new ArrayList<>();

        for ( EventEntity Event: EventList){
            responses.add(Entity2ResponsePage(Event));
        }

        return responses;
    }

}




