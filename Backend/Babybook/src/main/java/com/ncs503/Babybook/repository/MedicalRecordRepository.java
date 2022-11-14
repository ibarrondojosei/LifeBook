package com.ncs503.Babybook.repository;

import com.ncs503.Babybook.models.entity.MedicalRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecordEntity, Long> {
}
