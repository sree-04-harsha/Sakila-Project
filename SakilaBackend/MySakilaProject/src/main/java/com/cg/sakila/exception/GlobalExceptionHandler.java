package com.cg.sakila.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.sakila.Payload.ErrorResp;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// custom exception
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<?> handleCustomException(CustomException ex) {
		ErrorResp errRes = new ErrorResp(ex.getMessage());
		return new ResponseEntity<>(errRes, HttpStatus.BAD_REQUEST);
	}

	// validation exception
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<?> handleValidationException(ValidationException ex) {
		ErrorResp errRes = new ErrorResp(ex.getMessage());
		return new ResponseEntity<>(errRes, HttpStatus.BAD_REQUEST);
	}

	// Resource not found exception
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<ErrorResp> handleResourceNotFound(Exception e) {

		ErrorResp errorResp = new ErrorResp();
		errorResp.setStatusCode(HttpStatus.NOT_FOUND.value());

		errorResp.setMessage(e.getMessage());

		return new ResponseEntity<>(errorResp, HttpStatus.NOT_FOUND);

	}

	// constraint violation handler
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("status", HttpStatus.BAD_REQUEST.value());

		List<String> errors = ex.getConstraintViolations().stream()
				.map(violation -> String.format(" %s", violation.getMessage())).collect(Collectors.toList());

		body.put("errors", errors);
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
}
