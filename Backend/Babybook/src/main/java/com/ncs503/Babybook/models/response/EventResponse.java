package com.ncs503.Babybook.models.response;

import com.ncs503.Babybook.models.utility.TagsEventEnum;
import lombok.Builder;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Builder
public class EventResponse {

    private Long id;
    private String title;
    private String body;
    private LocalDate date;
    private List<String> media;
    private Timestamp timestamp;
    private Subjectenenene subjectId;
    private List<TagsEventEnum> eventEnumList;

}
