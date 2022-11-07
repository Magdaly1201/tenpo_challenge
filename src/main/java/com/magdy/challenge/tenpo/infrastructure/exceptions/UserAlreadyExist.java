package com.magdy.challenge.tenpo.infrastructure.exceptions;

public class UserAlreadyExist extends CustomException {

    private static final long serialVersionUID = -50510158400280774L;
    private static final ExceptionCodes exceptionCode = ExceptionCodes.EMAIL_USER_ALREADY_EXIST;

    public UserAlreadyExist() {
        super(exceptionCode.getCode(), exceptionCode.getUserMessage(), exceptionCode.getSystemMessage());
    }

}
