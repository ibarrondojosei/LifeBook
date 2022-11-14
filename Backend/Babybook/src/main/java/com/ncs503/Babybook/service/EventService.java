package com.ncs503.Babybook.service;

import com.ncs503.Babybook.models.response.EventResponse;
import com.ncs503.Babybook.models.utility.TagsEventEnum;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface EventService {

    public EventResponse create(String token,String title, String body, LocalDate date,
                                List<MultipartFile> media, Long subjectId, List<TagsEventEnum> eventEnumList) throws IOException;

}
