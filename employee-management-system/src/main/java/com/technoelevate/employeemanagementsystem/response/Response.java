package com.technoelevate.employeemanagementsystem.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
@AllArgsConstructor
public class Response {

	private boolean error;
	private String  messege;
	private Object data;
	
	
	
}
