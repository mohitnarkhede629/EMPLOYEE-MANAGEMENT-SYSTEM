package com.technoelevate.employeemanagementsystem;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;

import com.technoelevate.employeemanagementsystem.controller.EmployeeController;
import com.technoelevate.employeemanagementsystem.entity.Employee;
import com.technoelevate.employeemanagementsystem.services.EmployeeServices;

@SpringBootTest
public class EmployeeControllerTest {

	@InjectMocks
	private EmployeeController employeeController;

	@Mock
	private EmployeeServices employeeServices;

	@BeforeEach
	public void detup() {

		MockitoAnnotations.openMocks(this);

	}

	@Test
	public void getEmployee(int id) {

		OngoingStubbing<Employee> when2 = when(employeeServices.getEmployee(id));

	}
	
	

}
