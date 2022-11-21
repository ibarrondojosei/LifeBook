package com.ncs503.Babybook.service.impl;

import com.ncs503.Babybook.exception.RuntimeExceptionCustom;
import com.ncs503.Babybook.models.entity.MedicalDataEntity;
import com.ncs503.Babybook.models.entity.SubjectEntity;
import com.ncs503.Babybook.models.mapper.MedicalDataMapper;
import com.ncs503.Babybook.models.request.MedicalDataRequest;
import com.ncs503.Babybook.models.response.MedicalDataResponse;
import com.ncs503.Babybook.models.response.SubjectResponse;
import com.ncs503.Babybook.repository.MedicalDataRepository;
import com.ncs503.Babybook.service.MedicalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalDataServiceImpl implements MedicalDataService {

    @Autowired
    MedicalDataMapper medicalDataMapper;

    @Autowired
    MedicalDataRepository medicalDataRepository;

    @Override
    public MedicalDataResponse create(MedicalDataRequest request) throws IOException {
       MedicalDataEntity entity = this.medicalDataMapper.Request2Entity(request);
       MedicalDataEntity entitySave = this.medicalDataRepository.save(entity);
       MedicalDataResponse responseCreated = this.medicalDataMapper.Entity2Response(entitySave);

        return responseCreated;
    }

    @Override
    public void delete(Long id) {

        Optional<MedicalDataEntity> entity = this.medicalDataRepository.findBySubjectId(id);

        if (!entity.isPresent()) {

            throw new RuntimeExceptionCustom("404 ::the id  does not belong to a Subject");

        }

        this.medicalDataRepository.delete(entity.get());
    }

    @Override
    public MedicalDataResponse update(Long id, MedicalDataRequest request) throws IOException {
        Optional<MedicalDataEntity> entity = this.medicalDataRepository.findBySubjectId(id);

        if (!entity.isPresent()) {

            throw new RuntimeExceptionCustom("404 ::the id  does not belong to a Subject");
        }

        MedicalDataEntity entityUpdate = this.medicalDataMapper.EntityRefreshValues(entity.get(), request);

        MedicalDataEntity entitySave = this.medicalDataRepository.save(entityUpdate);

        MedicalDataResponse response = this.medicalDataMapper.Entity2Response(entitySave);

        return response;
    }

    @Override
    public MedicalDataResponse getById(Long id) throws IOException {
        Optional<MedicalDataEntity> entity = this.medicalDataRepository.findBySubjectId(id);

        if (!entity.isPresent()) {

            throw new RuntimeExceptionCustom("404 ::the id  does not belong to aMedical Data");
        }

        MedicalDataResponse response = this.medicalDataMapper.Entity2Response(entity.get());

        return response;
    }


}
