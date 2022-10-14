package com.technoelevate.employeemanagementsystem.services;

import java.util.List;

import com.technoelevate.employeemanagementsystem.dto.EmployeeDto;
import com.technoelevate.employeemanagementsystem.entity.Employee;

public interface EmployeeServices {
	
	Employee addEmployee(EmployeeDto employeeDto);

	Employee getEmployee(Long id);
	
	List<Employee> getAllEmp();

	Employee deleteRecord(Long id);

	Employee updateRecord(Long id,EmployeeDto dto);

}
