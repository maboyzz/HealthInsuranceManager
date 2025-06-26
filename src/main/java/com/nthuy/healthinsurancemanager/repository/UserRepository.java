package com.nthuy.healthinsurancemanager.repository;


import com.nthuy.healthinsurancemanager.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    boolean existsByUserName(String name);

    Optional<UserEntity> findByIdCardNumber(String idCardNumber);

}
