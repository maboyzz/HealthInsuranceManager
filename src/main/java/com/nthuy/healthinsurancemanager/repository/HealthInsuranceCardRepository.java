package com.nthuy.healthinsurancemanager.repository;

import com.nthuy.healthinsurancemanager.repository.entity.HealthInsuranceCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HealthInsuranceCardRepository extends JpaRepository<HealthInsuranceCardEntity, Long> {
    Optional<HealthInsuranceCardEntity> findByUserId(Long userId);
    boolean existsByCardNumber(String cardNumber);
}
