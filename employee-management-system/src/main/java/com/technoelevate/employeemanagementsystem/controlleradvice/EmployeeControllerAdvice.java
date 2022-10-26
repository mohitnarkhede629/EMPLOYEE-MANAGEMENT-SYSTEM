package com.technoelevate.employeemanagementsystem.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.technoelevate.employeemanagementsystem.customexceptions.EmployeeNotFoundException;
import com.technoelevate.employeemanagementsystem.response.Response;

@RestControllerAdvice
public class EmployeeControllerAdvice {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<Response> empNotFound(EmployeeNotFoundException e) {

		return new ResponseEntity<>(new Response(true,e.getMessage(), null),HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> noRecordFoundException(Exception e) {

		return new ResponseEntity<>(new Response(true,"Something Went Wrong", null),HttpStatus.BAD_REQUEST);

	}
	
	

}
