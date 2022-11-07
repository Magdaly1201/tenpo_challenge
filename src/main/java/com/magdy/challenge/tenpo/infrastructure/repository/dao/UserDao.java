package com.magdy.challenge.tenpo.infrastructure.repository.dao;

import com.magdy.challenge.tenpo.infrastructure.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmailAndPassword(String name, String password);

    Optional<UserEntity> findByEmail(String email);

}