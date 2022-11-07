package com.magdy.challenge.tenpo.core.user.service.port;

import com.magdy.challenge.tenpo.infrastructure.delivery.dto.UserResponseDTO;
import com.magdy.challenge.tenpo.infrastructure.repository.entity.UserEntity;

public interface UserRepository {

    UserResponseDTO save(UserEntity user);

    UserResponseDTO findUserByEmailAndPassword(String username, String password);

}