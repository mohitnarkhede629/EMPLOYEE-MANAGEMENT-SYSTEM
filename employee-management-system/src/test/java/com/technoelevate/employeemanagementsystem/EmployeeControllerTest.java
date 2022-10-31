package com.technoelevate.employeemanagementsystem;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technoelevate.employeemanagementsystem.controller.EmployeeController;
import com.technoelevate.employeemanagementsystem.dto.EmployeeDto;
import com.technoelevate.employeemanagementsystem.entity.Employee;
import com.technoelevate.employeemanagementsystem.response.Response;
import com.technoelevate.employeemanagementsystem.services.EmployeeServices;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

	private MockMvc mvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private ObjectMapper objectMapper;

	@InjectMocks
	private EmployeeController employeeController;

	@Mock
	private EmployeeServices employeeServices;

	@BeforeEach
	public void setup() {

		this.objectMapper = new ObjectMapper();
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		mvc = MockMvcBuilders.standaloneSetup(employeeController).build();

		employeeController = new EmployeeController(employeeServices);
	}

	@Test
	void addEmployeeTest() throws UnsupportedEncodingException, Exception {
		EmployeeDto employeeDto = EmployeeDto.builder().firstName("Mohit").userName("mohit123").build();
		Employee employee = Employee.builder().firstName("Mohit").userName("mohit123").build();
		String writeValueAsString = objectMapper.writeValueAsString(employeeDto);

		when(employeeServices.addEmployee(employeeDto)).thenReturn(employee);

		String contentAsString = mvc
				.perform(post("/token").content(writeValueAsString).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();

		Response readValue = objectMapper.readValue(contentAsString, Response.class);

		assertThat(readValue.isError()).isFalse();

	}

	@Test
	void getEmployeeTest() throws UnsupportedEncodingException, Exception {

		EmployeeDto employeeDto = EmployeeDto.builder().firstName("Mohit").userName("mohit123").build();

		Employee employee = Employee.builder().firstName("Mohit").userName("mohit123").employeeId(1).build();
		objectMapper.writeValueAsString(employeeDto);

		when(employeeServices.getEmployee(Mockito.anyInt())).thenReturn(employee);

		String contentAsString = mvc.perform(get("/getemployee").param("id", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		Response readValue = objectMapper.readValue(contentAsString, Response.class);
		assertThat(readValue.isError()).isFalse();

	}

	@Test
	void getAllEmployeeTest() throws UnsupportedEncodingException, Exception {

		Employee employee1 = Employee.builder().firstName("Mohit").userName("mohit123").build();
		Employee employee2 = Employee.builder().firstName("Rohit").userName("Rohit123").build();
		List<Employee> list = Arrays.asList(employee1, employee2);

		when(employeeServices.getAllEmp()).thenReturn(list);

		String contentAsString = mvc.perform(get("/getallemp")).andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getResponse().getContentAsString();
		Response readValue = objectMapper.readValue(contentAsString, Response.class);
		assertThat(readValue.isError()).isFalse();

	}

	@Test
	void deleteRecordTest() throws UnsupportedEncodingException, Exception {
		Employee employee1 = Employee.builder().firstName("Mohit").userName("mohit123").employeeId(1).build();
		when(employeeServices.deleteRecord(employee1.getEmployeeId())).thenReturn("Data Deleted Successfully");

		String contentAsString = mvc.perform(delete("/deleterecord").param("id", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		Response readValue = objectMapper.readValue(contentAsString, Response.class);
		assertThat(readValue.isError()).isFalse();

	}

	@Test
	void updateTecordTest() throws UnsupportedEncodingException, Exception {

		EmployeeDto employeeDto = EmployeeDto.builder().firstName("Mohit").userName("mohit123").build();
		Employee employee = Employee.builder().firstName("Mohit").userName("mohit123").employeeId(1).build();
		String writeValueAsString = objectMapper.writeValueAsString(employeeDto);
		when(employeeServices.updateRecord(1, employeeDto)).thenReturn(employee);

		String contentAsString = mvc
				.perform(put("/update/1").content(writeValueAsString).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		Response readValue = objectMapper.readValue(contentAsString, Response.class);
		assertThat(readValue.isError()).isFalse();

	}

}
