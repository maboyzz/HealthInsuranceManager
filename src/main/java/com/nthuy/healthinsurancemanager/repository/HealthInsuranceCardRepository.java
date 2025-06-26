package com.nthuy.healthinsurancemanager.repository;

import com.nthuy.healthinsurancemanager.repository.entity.HealthInsuranceCardEntity;
import com.nthuy.healthinsurancemanager.repository.entity.SystemUserEntity;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface HealthInsuranceCardRepository extends JpaRepository<HealthInsuranceCardEntity, Long>, JpaSpecificationExecutor<HealthInsuranceCardEntity> {
    Optional<HealthInsuranceCardEntity> findByUserId(Long userId);

    Optional<HealthInsuranceCardEntity> findByCardNumber(String cardNumber);

    boolean existsByCardNumber(String cardNumber);


}
