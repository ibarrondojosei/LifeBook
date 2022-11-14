package com.ncs503.Babybook.models.mapper;

import com.ncs503.Babybook.models.entity.EventEntity;
import com.ncs503.Babybook.models.request.EventRequest;
import com.ncs503.Babybook.models.response.EventResponse;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class EventMapper {

    public EventEntity Request2Entity (EventRequest request)  {

        EventEntity entity = new EventEntity();

        return entity.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .date(request.getDate())
                .media(request.getMedia())
                .eventEnumList(request.getEventEnumList())
//                .subjectId(request.getSubjectId())
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
                .eventEnumList(entity.getEventEnumList())
//                .subjectId(entity.getSubjectId())
                .timestamp(entity.getTimestamp())
                .build();
    }

    public EventEntity EntityUpdate (EventEntity entity, EventRequest request)  {

        return EventEntity.builder()
                .id(request.getId())
                .title(request.getTitle())
                .body(request.getBody())
                .date(request.getDate())
                .media(request.getMedia())
                .eventEnumList(request.getEventEnumList())
//                .subjectId(request.getSubjectId())
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .sofdelete(false)
                .build();

    }

    public List<EventResponse> EntityList2ResponsePage(List<EventEntity> EventList){

        List<EventResponse> responses = new ArrayList<>();

        for ( EventEntity Event: EventList){
            responses.add(Entity2Response(Event));
        }

        return responses;
    }

}




