package com.technoelevate.employeemanagementsystem.services;

import java.util.List;

import com.technoelevate.employeemanagementsystem.dto.EmployeeDto;
import com.technoelevate.employeemanagementsystem.entity.Employee;

public interface EmployeeServices {
	
	Employee addEmployee(EmployeeDto employeeDto);

	Employee getEmployee(int id);
	
	List<Employee> getAllEmp();

	String deleteRecord(int id);

	Employee updateRecord(int id,EmployeeDto dto);
	

}
