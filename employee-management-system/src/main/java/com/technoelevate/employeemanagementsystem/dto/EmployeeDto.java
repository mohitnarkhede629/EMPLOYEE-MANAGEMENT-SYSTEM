package com.technoelevate.employeemanagementsystem.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.technoelevate.employeemanagementsystem.entity.Address;
import com.technoelevate.employeemanagementsystem.entity.BankAccount;
import com.technoelevate.employeemanagementsystem.entity.Department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class EmployeeDto {

	private String firstName;
	private String lastName;
	private String email;
	private Long phoneNumber;
	private int age;
	private double salary;
	private String experience;
	private List<Address> address;
	private List<Department> department;
	private List<BankAccount> account;

}
