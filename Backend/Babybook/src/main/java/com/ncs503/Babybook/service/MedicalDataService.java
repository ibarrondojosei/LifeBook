package com.ncs503.Babybook.service;

import com.ncs503.Babybook.models.request.MedicalDataRequest;
import com.ncs503.Babybook.models.request.SubjectRequest;
import com.ncs503.Babybook.models.response.MedicalDataResponse;
import com.ncs503.Babybook.models.response.SubjectResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


public interface MedicalDataService {

    MedicalDataResponse create (MedicalDataRequest request) throws IOException;
    void delete (Long id);
    MedicalDataResponse update (Long id, MedicalDataRequest request) throws IOException;
    MedicalDataResponse getById (Long id) throws IOException;

}
