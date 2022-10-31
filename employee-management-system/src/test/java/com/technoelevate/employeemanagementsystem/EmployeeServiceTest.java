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

import com.technoelevate.employeemanagementsystem.customexceptions.EmployeeNotFoundException;
import com.technoelevate.employeemanagementsystem.dao.EmployeeDao;
import com.technoelevate.employeemanagementsystem.dto.EmployeeDto;
import com.technoelevate.employeemanagementsystem.entity.Employee;
import com.technoelevate.employeemanagementsystem.services.EmployeeServicesImpl;

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
	void doesGetAllEmployeeWorksFine() {

		when(dao.findAll()).thenReturn(Stream.of(Employee.builder().firstName("adbkSJKSBD").build()).toList());
		assertThat(serviceTest.getAllEmp()).hasSize(1);
	}

	@Test
	void does_GetAllEmp_ThrowsException_IfNoRecordIsPresent() {

		EmployeeNotFoundException e = assertThrows(EmployeeNotFoundException.class, () -> serviceTest.getAllEmp());
		assertThat(e.getMessage()).isEqualTo("No Data Is Present");

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
	void doesAddEmployeeThrowsException() {
		assertThrows(Exception.class, () -> serviceTest.addEmployee(null));
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
	void CheckIfIdIsNotPresentThenExceptionIsThrown() {

		int id = 9;
		EmployeeNotFoundException e = assertThrows(EmployeeNotFoundException.class, () -> serviceTest.getEmployee(9));
		assertThat(e.getMessage()).isEqualTo("No Record With Emp Id " + id + " Was Found");

	}

	@Test
	void checkIfDeleteRecordIsWorkingFine() {
		Employee employee = Employee.builder().firstName("Mohit").userName("Mohit123").lastName("Narkhede")
				.employeeId(10).build();
		when(dao.findById(employee.getEmployeeId())).thenReturn(Optional.of(employee));
		serviceTest.deleteRecord(employee.getEmployeeId());
		verify(dao).deleteById(employee.getEmployeeId());

	}

	@Test
	void checkIf_ExceptionIsHandled_ForDeleteByIdApi() {
		int id = 9;
		EmployeeNotFoundException e = assertThrows(EmployeeNotFoundException.class, () -> serviceTest.deleteRecord(id));
		assertThat(e.getMessage()).isEqualTo("Record with employee id " + id + " was not found");

	}

	@Test
	void checkIf_UpdateRecord_WorksFine() {
		Employee employee = Employee.builder().firstName("Mohit").userName("Mohit123").lastName("Narkhede")
				.employeeId(10).build();
		EmployeeDto employeeDto = EmployeeDto.builder().firstName("Mohit").userName("Mohit123").lastName("Narkhede")
				.build();
		when(dao.findById(employee.getEmployeeId())).thenReturn(Optional.of(employee));
		Employee updateRecord = serviceTest.updateRecord(10, employeeDto);
		assertThat(updateRecord).isEqualTo(employee);

	}

	@Test
	void checkIf_UpdateRecord_handlesException() {
		EmployeeDto employeeDto = EmployeeDto.builder().firstName("Mohit").userName("Mohit123").lastName("Narkhede")
				.build();
		int id = 1;
		RuntimeException e = assertThrows(RuntimeException.class, () -> serviceTest.updateRecord(id, employeeDto));
		assertThat(e.getMessage()).isEqualTo("Record With Employee Id " + id + " Was Not Found");

	}

}
