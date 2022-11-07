package com.magdy.challenge.tenpo.infrastructure.delivery;

import com.magdy.challenge.tenpo.adapter.delivery.UserEndpoints;
import com.magdy.challenge.tenpo.infrastructure.delivery.dto.LoginRequest;
import com.magdy.challenge.tenpo.infrastructure.delivery.dto.UserRequestDTO;
import com.magdy.challenge.tenpo.infrastructure.exceptions.UserEmailAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {

    private final UserEndpoints userEndpoints;

    public UserRestController(UserEndpoints userEndpoints) {
        this.userEndpoints = userEndpoints;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> create(@Valid @RequestBody UserRequestDTO userRequestDTO) throws UserEmailAlreadyExistsException {
        return new ResponseEntity<>(userEndpoints.create(userRequestDTO), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        return new ResponseEntity<>(userEndpoints.login(loginRequest.getEmail(), loginRequest.getPassword()), HttpStatus.OK);
    }
}
