package com.nthuy.healthinsurancemanager.repository;

import com.nthuy.healthinsurancemanager.dto.response.GetSystemUserResponse;
import com.nthuy.healthinsurancemanager.repository.entity.SystemUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Long>, JpaSpecificationExecutor<SystemUser> {

    Optional<SystemUser> findByUserName(String name);
    boolean existsByUserName(String name);
    boolean existsById(Long id);


}
