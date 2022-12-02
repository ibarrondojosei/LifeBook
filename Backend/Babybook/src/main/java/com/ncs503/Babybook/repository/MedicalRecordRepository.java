package com.ncs503.Babybook.repository;

import com.ncs503.Babybook.models.entity.EventEntity;
import com.ncs503.Babybook.models.entity.MedicalRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecordEntity, Long> {

//    @Query(value = "SELECT * FROM medicalrecords mr INNER JOIN medical_data md \n" +
//            "ON mr.medical_data_id = md.id WHERE md.subject_subject_id = ? AND mr.date = ? ORDER BY mr.date DESC" , nativeQuery = true)
//    List<MedicalRecordEntity> findAllByDate(Long subject_id, LocalDate date);

    @Query(value =  "SELECT * FROM medicalrecords mr INNER JOIN medical_data md \n" +
                    "ON mr.medical_data_id = md.id WHERE md.subject_subject_id = ? " +
                    "ORDER BY mr.date DESC", nativeQuery = true)
    List<MedicalRecordEntity> findBySubject(Long subject);

    @Query(value =  "SELECT * FROM medicalrecords mr INNER JOIN medical_data md \n" +
                    "ON mr.medical_data_id = md.id WHERE md.subject_subject_id = ? " +
                    "AND mr.medical_record_enums = ? ORDER BY mr.date DESC", nativeQuery = true)
    List<MedicalRecordEntity> findAllByTags(Long subject_id, int medicalRecordEnum);

    @Query(value =  "SELECT * FROM medicalrecords mr INNER JOIN medical_data md \n" +
                    "ON mr.medical_data_id = md.id WHERE mr.medical_data_id = ? " +
                    "AND mr.highlight_moment = ? ORDER BY mr.date DESC", nativeQuery = true)
    List<MedicalRecordEntity> findAllByHighlightMoment(Long subject_id, Boolean highlightMoment);


}
