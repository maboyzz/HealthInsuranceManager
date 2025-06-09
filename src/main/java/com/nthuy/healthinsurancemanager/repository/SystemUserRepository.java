package com.nthuy.healthinsurancemanager.repository;

import com.nthuy.healthinsurancemanager.repository.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {
}
