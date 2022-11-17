package com.ncs503.Babybook.repository;

import com.ncs503.Babybook.models.entity.GuestEntity;
import com.ncs503.Babybook.models.response.GuestResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Leonardo Terlizzi
 */

@Repository
public interface GuestRepository extends JpaRepository<GuestEntity, Long> {

    @Query(value = "SELECT * from guests WHERE guests.email LIKE %:email% AND softDelete = false", nativeQuery = true)
    Optional<GuestEntity> findByEmail(String email);

   // Optional<List<GuestResponse>> findByUser(UserEntity user);
}
