package com.product.main.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public HashMap<String, Object> handlerMethodArgumentNotValid(MethodArgumentNotValidException ex) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("time", new Date());

		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();

		for (FieldError fieldError : fieldErrors) {
			map.put(fieldError.getField(), fieldError.getDefaultMessage());

		}
		return map;

	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> productNotFound(ProductNotFoundException ex) {

		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.OK);
	}
	
	@ExceptionHandler(ProductNotSaveException.class)
	public ResponseEntity<String> productNotSave(ProductNotSaveException save)
	{
		return new ResponseEntity<String>(save.getMessage(), HttpStatus.OK);
	}

}
