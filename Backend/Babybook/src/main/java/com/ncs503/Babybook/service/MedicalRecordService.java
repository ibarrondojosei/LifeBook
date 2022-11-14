package com.ncs503.Babybook.service;

import com.ncs503.Babybook.models.response.EventResponse;
import com.ncs503.Babybook.models.response.MedicalRecordResponse;
import com.ncs503.Babybook.models.utility.TagsEventEnum;
import com.ncs503.Babybook.models.utility.TagsMedicalRecordEnum;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface MedicalRecordService {

    public MedicalRecordResponse create(String token, String title, String body, LocalDate date,
                                        List<MultipartFile> media, Long subjectId, List<TagsMedicalRecordEnum> medicalRecordEnumList) throws IOException;


}
