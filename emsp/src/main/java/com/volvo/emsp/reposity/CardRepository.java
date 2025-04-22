package com.volvo.emsp.reposity;

import com.volvo.emsp.reposity.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CardRepository extends JpaRepository<CardEntity, Long> {

    Optional<CardEntity> findByUID(String uid);
}