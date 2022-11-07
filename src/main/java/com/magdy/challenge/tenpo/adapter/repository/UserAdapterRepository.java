package com.magdy.challenge.tenpo.adapter.repository;

import com.magdy.challenge.tenpo.core.user.service.port.UserRepository;
import com.magdy.challenge.tenpo.infrastructure.delivery.dto.UserResponseDTO;
import com.magdy.challenge.tenpo.infrastructure.repository.dao.UserDao;
import com.magdy.challenge.tenpo.infrastructure.repository.entity.UserEntity;
import org.modelmapper.ModelMapper;

public class UserAdapterRepository implements UserRepository {

    private final ModelMapper modelMapper;
    private final UserDao userDao;

    public UserAdapterRepository(ModelMapper modelMapper, UserDao userDao) {
        this.modelMapper = modelMapper;
        this.userDao = userDao;
    }

    @Override
    public UserResponseDTO save(UserEntity user) {
        userDao.save(user);
        return modelMapper.map(user, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO findUserByEmailAndPassword(String email, String password) {
        return modelMapper.map(userDao.findByEmailAndPassword(email, password).orElseThrow(() -> new RuntimeException("Error: user not found")), UserResponseDTO.class);
    }
}
