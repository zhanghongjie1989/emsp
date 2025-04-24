package com.volvo.emsp.reposity;

import com.volvo.emsp.reposity.entity.AccountEntity;
import com.volvo.emsp.reposity.entity.CardEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface CardRepository extends JpaRepository<CardEntity, Long> {

    Optional<CardEntity> findByUID(String uid);

    List<CardEntity> findByUpdatedAtGreaterThanEqual(LocalDateTime lastUpdated, Pageable pageable);
}