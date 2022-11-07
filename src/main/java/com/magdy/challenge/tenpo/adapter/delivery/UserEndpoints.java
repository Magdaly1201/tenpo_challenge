package com.magdy.challenge.tenpo.adapter.delivery;

import com.magdy.challenge.tenpo.core.user.service.UserService;
import com.magdy.challenge.tenpo.infrastructure.delivery.dto.UserDataResponseLogin;
import com.magdy.challenge.tenpo.infrastructure.delivery.dto.UserRequestDTO;
import com.magdy.challenge.tenpo.infrastructure.delivery.dto.UserResponseDTO;

public class UserEndpoints {

    private final UserService userService;

    public UserEndpoints(UserService userService) {
        this.userService = userService;
    }

    public UserResponseDTO create(UserRequestDTO userRequestDTO) {
        return userService.create(userRequestDTO);
    }

    public UserDataResponseLogin login(String name, String password) {
        return userService.login(name, password);
    }
}
