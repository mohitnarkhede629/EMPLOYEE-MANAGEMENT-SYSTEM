package com.technoelevate.employeemanagementsystem.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@JsonInclude(value = Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class Response {

	private boolean error;
	private String  messege;
	private Object data;
	
	
	
}
