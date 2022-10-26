package com.technoelevate.employeemanagementsystem.customexceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmployeeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(String messege) {
		super(messege);
	}

}
