package com.greetlabs.swiftcart.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<Object> handleInvalidJwtSignature(SignatureException ex, WebRequest request) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Invalid JWT signature");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<Object> handleMalformedJwtException(MalformedJwtException ex, WebRequest request) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Malformed JWT");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Expired JWT token");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Internal server error");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
