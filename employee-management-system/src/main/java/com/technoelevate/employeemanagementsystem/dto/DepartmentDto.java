package com.technoelevate.employeemanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

	private int departmentNumber;
	private String departmentName;
	private String departmentHead;
}
