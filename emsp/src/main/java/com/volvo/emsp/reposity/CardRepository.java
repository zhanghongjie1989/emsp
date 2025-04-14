package com.volvo.emsp.reposity;

import com.volvo.emsp.reposity.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CardRepository extends JpaRepository<CardEntity, Long> {
}