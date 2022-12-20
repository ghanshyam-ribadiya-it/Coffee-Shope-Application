package com.coffee.shop.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.coffee.shop.constants.AppMessage;
import com.coffee.shop.constants.ErrorAppMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.info("GlobalExceptionHandler -> handleMethodArgumentNotValid method call");
		List<String> errors = exception.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());

		AppMessage appMessage = new ErrorAppMessage(HttpStatus.BAD_REQUEST.value(), null, errors);
		return new ResponseEntity<Object>(appMessage, headers, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ShopException.class)
	public ResponseEntity<Object> handleShopException(ShopException shopException, WebRequest request) {
		log.info("GlobalExceptionHandler -> handleOvisException method call");
		return new ResponseEntity<Object>(shopException.getAppMessage(), new HttpHeaders(), HttpStatus.PRECONDITION_FAILED);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleOtherException(Exception exception, WebRequest request) {
		log.info("GlobalExceptionHandler -> handleException method call");
		log.error("Exception : ", exception);
		return new ResponseEntity<Object>(ErrorAppMessage.SERVER_ERROR, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}