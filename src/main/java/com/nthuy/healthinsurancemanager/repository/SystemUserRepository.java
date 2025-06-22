package com.nthuy.healthinsurancemanager.repository;

import com.nthuy.healthinsurancemanager.repository.entity.SystemUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUserEntity, Long>, JpaSpecificationExecutor<SystemUserEntity> {

    Optional<SystemUserEntity> findByUserName(String name);
    boolean existsByUserName(String name);
    boolean existsById(Long id);


}
