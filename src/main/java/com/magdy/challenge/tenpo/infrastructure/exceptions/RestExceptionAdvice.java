package com.magdy.challenge.tenpo.infrastructure.exceptions;

import com.magdy.challenge.tenpo.infrastructure.exceptions.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionAdvice {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        if (ex.getBindingResult().hasErrors()) {
            List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(err -> {
                return "El campo  : '" + err.getField() + "' " + err.getDefaultMessage();
            }).collect(Collectors.toList());
            response.put("errors", errors);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Map<String, ErrorResponse>> userNotFoundException(UserNotFoundException ex) {
        Map<String, ErrorResponse> response = new HashMap<>();
        ErrorResponse errorResponse = new ErrorResponse();
        String className = ex.getStackTrace()[0].getClassName();
        int line = ex.getStackTrace()[0].getLineNumber();
        logger.error("Exception: " + ex.getUserMessage() + " - Class: " + className + " - line: " + line);
        errorResponse.setCode(ex.getCode());
        errorResponse.setDetail(ex.getUserMessage());
        errorResponse.setTimestamp(ex.getTimestamp());
        response.put("error", errorResponse);
        return new ResponseEntity<Map<String, ErrorResponse>>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExist.class)
    @ResponseBody
    public ResponseEntity<Map<String, ErrorResponse>> userAlreadyExist(UserAlreadyExist ex) {
        Map<String, ErrorResponse> response = new HashMap<>();
        ErrorResponse errorResponse = new ErrorResponse();
        String className = ex.getStackTrace()[0].getClassName();
        int line = ex.getStackTrace()[0].getLineNumber();
        logger.error("Exception: " + ex.getUserMessage() + " - Class: " + className + " - line: " + line);
        errorResponse.setCode(ex.getCode());
        errorResponse.setDetail(ex.getUserMessage());
        errorResponse.setTimestamp(ex.getTimestamp());
        response.put("error", errorResponse);
        return new ResponseEntity<Map<String, ErrorResponse>>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataIntegrationViolationException.class)
    @ResponseBody
    public ResponseEntity<Map<String, ErrorResponse>> dataIntegrationViolationException(
            DataIntegrationViolationException ex) {
        Map<String, ErrorResponse> response = new HashMap<>();
        ErrorResponse errorResponse = new ErrorResponse();
        String className = ex.getStackTrace()[0].getClassName();
        int line = ex.getStackTrace()[0].getLineNumber();
        logger.error("Exception: " + ex.getCause() + " - Class: " + className + " - line: " + line + " systemMessage" + ex.getSystemMessage());
        errorResponse.setCode(ex.getCode());
        errorResponse.setDetail(ex.getUserMessage());
        errorResponse.setTimestamp(ex.getTimestamp());
        response.put("error", errorResponse);
        return new ResponseEntity<Map<String, ErrorResponse>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    @ResponseBody
    public ResponseEntity<Map<String, ErrorResponse>> authenticationFailedException(AuthenticationFailedException ex) {
        Map<String, ErrorResponse> response = new HashMap<>();
        ErrorResponse errorResponse = new ErrorResponse();
        String className = ex.getStackTrace()[0].getClassName();
        int line = ex.getStackTrace()[0].getLineNumber();
        logger.error("Exception: " + ex.getCause() + " - Class: " + className + " - line: " + line + " systemMessage" + ex.getSystemMessage());
        errorResponse.setCode(ex.getCode());
        errorResponse.setDetail(ex.getUserMessage());
        errorResponse.setTimestamp(ex.getTimestamp());
        response.put("error", errorResponse);
        return new ResponseEntity<Map<String, ErrorResponse>>(response, HttpStatus.OK);
    }

    @ExceptionHandler(ErrorValidationJWTException.class)
    @ResponseBody
    public ResponseEntity<Map<String, ErrorResponse>> errorValidationJWTException(ErrorValidationJWTException ex) {
        Map<String, ErrorResponse> response = new HashMap<>();
        ErrorResponse errorResponse = new ErrorResponse();
        String className = ex.getStackTrace()[0].getClassName();
        int line = ex.getStackTrace()[0].getLineNumber();
        logger.error("Exception: " + ex.getCause() + " - Class: " + className + " - line: " + line + " systemMessage" + ex.getSystemMessage());
        errorResponse.setCode(ex.getCode());
        errorResponse.setDetail(ex.getUserMessage());
        errorResponse.setTimestamp(ex.getTimestamp());
        response.put("error", errorResponse);
        return new ResponseEntity<Map<String, ErrorResponse>>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MissingTokenException.class)
    @ResponseBody
    public ResponseEntity<Map<String, ErrorResponse>> missingTokenException(MissingTokenException ex) {
        Map<String, ErrorResponse> response = new HashMap<>();
        ErrorResponse errorResponse = new ErrorResponse();
        String className = ex.getStackTrace()[0].getClassName();
        int line = ex.getStackTrace()[0].getLineNumber();
        logger.error("Exception: " + ex.getCause() + " - Class: " + className + " - line: " + line + " systemMessage" + ex.getSystemMessage());
        errorResponse.setCode(ex.getCode());
        errorResponse.setDetail(ex.getUserMessage());
        errorResponse.setTimestamp(ex.getTimestamp());
        response.put("error", errorResponse);
        return new ResponseEntity<Map<String, ErrorResponse>>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundPercentage.class)
    @ResponseBody
    public ResponseEntity<Map<String, ErrorResponse>> notFoundPercentage(NotFoundPercentage ex) {
        Map<String, ErrorResponse> response = new HashMap<>();
        ErrorResponse errorResponse = new ErrorResponse();
        String className = ex.getStackTrace()[0].getClassName();
        int line = ex.getStackTrace()[0].getLineNumber();
        logger.error("Exception: " + ex.getCause() + " - Class: " + className + " - line: " + line + " systemMessage" + ex.getSystemMessage());
        errorResponse.setCode(ex.getCode());
        errorResponse.setDetail(ex.getUserMessage());
        errorResponse.setTimestamp(ex.getTimestamp());
        response.put("error", errorResponse);
        return new ResponseEntity<Map<String, ErrorResponse>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
