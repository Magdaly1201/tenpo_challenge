package com.magdy.challenge.tenpo.core.user.service;

import com.magdy.challenge.tenpo.core.user.service.port.UserRepository;
import com.magdy.challenge.tenpo.infrastructure.delivery.dto.UserDataResponseLogin;
import com.magdy.challenge.tenpo.infrastructure.delivery.dto.UserRequestDTO;
import com.magdy.challenge.tenpo.infrastructure.delivery.dto.UserResponseDTO;
import com.magdy.challenge.tenpo.infrastructure.repository.entity.UserEntity;
import com.magdy.challenge.tenpo.infrastructure.security.GeneratorJWT;
import com.magdy.challenge.tenpo.infrastructure.security.model.UserJWT;

import java.time.LocalDateTime;

public class UserService {

    private final UserRepository userRepository;
    private final GeneratorJWT generatorJWT;

    public UserService(UserRepository userRepository, GeneratorJWT generatorJWT) {
        this.userRepository = userRepository;
        this.generatorJWT = generatorJWT;
    }

    public UserResponseDTO create(UserRequestDTO userRequestDTO) {
        UserEntity userEntity = new UserEntity(userRequestDTO.getName(), userRequestDTO.getEmail(), userRequestDTO.getPassword(), LocalDateTime.now(), null, true,"ROLE_USER");
        return this.userRepository.save(userEntity);
    }

    public UserDataResponseLogin login(String email, String password) {
        UserResponseDTO user = this.userRepository.findUserByEmailAndPassword(email, password);
        return new UserDataResponseLogin(generatorJWT.getJWTToken(new UserJWT(user.getId(), user.getRoleId())));
    }
}
