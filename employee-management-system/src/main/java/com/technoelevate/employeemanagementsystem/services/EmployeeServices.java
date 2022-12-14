package com.technoelevate.employeemanagementsystem.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.technoelevate.employeemanagementsystem.dto.EmployeeDto;
import com.technoelevate.employeemanagementsystem.entity.Employee;

public interface EmployeeServices {
	
	Employee addEmployee(EmployeeDto employeeDto);

	Employee getEmployee(int id);
	
	List<Employee> getAllEmp();

	Employee deleteRecord(int id);

	Employee updateRecord(int id,EmployeeDto dto);
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
