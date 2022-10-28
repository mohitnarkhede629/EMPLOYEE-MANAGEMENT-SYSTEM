package com.technoelevate.employeemanagementsystem;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.technoelevate.employeemanagementsystem.customexceptions.EmployeeNotFoundException;
import com.technoelevate.employeemanagementsystem.dao.EmployeeDao;
import com.technoelevate.employeemanagementsystem.dto.EmployeeDto;
import com.technoelevate.employeemanagementsystem.entity.Employee;
import com.technoelevate.employeemanagementsystem.services.EmployeeServicesImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

	@Mock
	private EmployeeDao dao;

	@InjectMocks
	private EmployeeServicesImpl serviceTest;

	@BeforeEach
	void setup() {
		serviceTest = new EmployeeServicesImpl(dao);
	}

	@Test
	void canGetAllEmployee() {

		serviceTest.getAllEmp();
		verify(dao).findAll();
		when(serviceTest.getAllEmp())
				.thenReturn(Stream.of(Employee.builder().firstName("adbkSJKSBD").build()).toList());
		assertThat(serviceTest.getAllEmp()).hasSize(1);

	}

	@Test
	void doesAddEmployeeWorksCorrectly() {

		EmployeeDto employeeDto = EmployeeDto.builder().firstName("Mohit").userName("Mohit123").lastName("Narkhede")
				.build();
		Employee employee = Employee.builder().firstName("Mohit").userName("Mohit123").lastName("Narkhede").build();
		when(dao.save(Mockito.any())).thenReturn(employee);
		Employee addEmployee = serviceTest.addEmployee(employeeDto);
		assertThat(addEmployee).isEqualTo(employee);

	}
	
	@Test 
	void doesAddEmployeeHandlesException(){
		
		Exception e= assertThrows(Exception.class, ()-> serviceTest.addEmployee(Mockito.any()));
		when(dao.save(Mockito.any())).thenThrow(new Exception("Sometihing Went Wrong"));
		
	}

	@Test
	void checkIfGetEmployeeByIdWorksFine() {
		Employee employee = Employee.builder().firstName("Mohit").userName("Mohit123").lastName("Narkhede").build();
		int id = 1;
		when(dao.findById(Mockito.anyInt())).thenReturn(Optional.of(employee));
		Employee addEmployee = serviceTest.getEmployee(id);
		assertThat(addEmployee).isEqualTo(employee);

	}

	@Test
	void CheckIfIdIsNotPresentThenExceptionIsHandled() {

		Employee employee = Employee.builder().firstName("Mohit").userName("Mohit123").lastName("Narkhede")
				.employeeId(10).build();
		dao.save(employee);
		int id = 9;
		EmployeeNotFoundException e = assertThrows(EmployeeNotFoundException.class, () -> serviceTest.getEmployee(9));
		String expectedMessege = "No Record With Emp Id " + id + " Was Found";
		assertThat(e.getMessage()).isEqualTo(expectedMessege);

	}

}
