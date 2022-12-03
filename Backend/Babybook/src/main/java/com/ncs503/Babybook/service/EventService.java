package com.ncs503.Babybook.service;


import com.ncs503.Babybook.models.response.EventFilterBySubjectResponse;
import com.ncs503.Babybook.models.response.EventResponse;
import com.ncs503.Babybook.models.utility.TagsEventEnum;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;


public interface EventService {

    public EventResponse create(String token,String title, String body, LocalDate date,
                                List<MultipartFile> media, Boolean highlightMoment, Long subjectId, TagsEventEnum eventEnum) throws Exception;
    public EventResponse update(String token, Long eventId, String title, String body, LocalDate date,
                                List<MultipartFile> media, Boolean highlightMoment, Long subjectId, TagsEventEnum eventEnum) throws Exception;
    public void delete(String token, Long subjectId, Long eventId) throws Exception;

    public List<EventFilterBySubjectResponse> findByIdSubject(String token, Long subjectId) throws Exception;
    public List<EventFilterBySubjectResponse> findAllByTags(String token, Long subjectId, TagsEventEnum eventEnum) throws Exception;
    public List<EventFilterBySubjectResponse> findAllByHighlightMoment(String token, Long subjectId) throws Exception;

}

//    public PaginationResponse findAllPage( Optional<Integer> pageNumber, Optional<Integer> size, String order) throws Exception ;
//    public EventResponse findById(String token, Long subjectId, Long eventId) throws Exception;
//    public List<EventFilterBySubjectResponse> findAllByDate(String token, Long subjectId, LocalDate date) throws Exception;
//    public PaginationResponse findAllByTagsPage(String token, Long subjectId, TagsEventEnum eventEnum, Optional<Integer> pageNumber, Optional<Integer> size) throws Exception;
//    public PaginationResponse findAllPage( String token, TagsEventEnum eventEnum, Long subjectId, Optional<Integer> pageNumber, Optional<Integer> size, String order) throws Exception ;

