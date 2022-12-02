package com.ncs503.Babybook.service;

import com.ncs503.Babybook.models.response.EventResponse;
import com.ncs503.Babybook.models.response.MedicalRecordResponse;
import com.ncs503.Babybook.models.response.medicalRecordFilterByMedicalDataResponse;
import com.ncs503.Babybook.models.utility.TagsEventEnum;
import com.ncs503.Babybook.models.utility.TagsMedicalRecordEnum;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface MedicalRecordService {

    public MedicalRecordResponse create(String token, String title, String body, LocalDate date,
                                        List<MultipartFile> media, Boolean highlightMoment, Long subjectId, TagsMedicalRecordEnum medicalRecordEnum) throws Exception;
    public MedicalRecordResponse update(String token, Long medicalRecordId, String title, String body, LocalDate date,
              List<MultipartFile> media, Boolean highlightMoment, Long subjectId, TagsMedicalRecordEnum medicalRecordEnum) throws Exception;
    public void delete(String token, Long subjectId, Long eventId) throws Exception;

    public List<medicalRecordFilterByMedicalDataResponse> findBySubject(String token, Long subject) throws Exception;
    public List<medicalRecordFilterByMedicalDataResponse> findAllByTags(String token, Long subjectId, TagsMedicalRecordEnum medicalRecordEnum) throws Exception;
    public List<medicalRecordFilterByMedicalDataResponse> findAllByHighlightMoment(String token, Long subjectId) throws Exception;
}



//    public MedicalRecordResponse findById(String token, Long subjectId, Long eventId) throws Exception;