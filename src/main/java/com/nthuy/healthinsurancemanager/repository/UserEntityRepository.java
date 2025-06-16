package com.nthuy.healthinsurancemanager.repository;

import com.nthuy.healthinsurancemanager.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUserName(String name);

}
