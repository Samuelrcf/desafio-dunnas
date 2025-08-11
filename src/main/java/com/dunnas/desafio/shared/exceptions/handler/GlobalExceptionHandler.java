package com.dunnas.desafio.shared.exceptions.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dunnas.desafio.components.user.application.exceptions.UsernameOrPasswordInvalidException;
import com.dunnas.desafio.shared.response.ApiErrorResponse;
import com.dunnas.desafio.shared.response.ResponseUtil;
import com.dunnas.desafio.shared.response.ValidationField;

import jakarta.servlet.http.HttpServletRequest;

public class GlobalExceptionHandler {
	
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse<Void>> handleException(
            HttpServletRequest request, Exception ex) {
        List<ValidationField> errors = Arrays.asList(new ValidationField("Erro", ex.getMessage()));

        ApiErrorResponse<Void> response =
                ResponseUtil.error(errors, "Ocorreu um erro interno no servidor", request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse<Void>> handleValidationExceptions(
            MethodArgumentNotValidException exception, HttpServletRequest request) {
        List<ValidationField> validationFields = new ArrayList<>();

        for (ObjectError error : exception.getBindingResult().getAllErrors()) {
            String fieldName = ((FieldError) error).getField();
            String fieldMessage = error.getDefaultMessage();
            validationFields.add(new ValidationField(fieldName, fieldMessage));
        }

        ApiErrorResponse<Void> response =
                ResponseUtil.error(
                        validationFields,
                        "Há campos que foram preenchidos incorretamente",
                        request.getRequestURI());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameOrPasswordInvalidException.class)
    public ResponseEntity<ApiErrorResponse<Void>> handleUsernameOrPasswordInvalidException(
            HttpServletRequest request, UsernameOrPasswordInvalidException ex) {

        ApiErrorResponse<Void> response =
                ResponseUtil.error(
                        new ValidationField("Erro", ex.getMessage()),
                        "Credenciais de usuário incorretas.",
                        request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
