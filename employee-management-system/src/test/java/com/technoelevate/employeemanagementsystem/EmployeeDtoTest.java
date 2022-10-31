package com.technoelevate.employeemanagementsystem;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technoelevate.employeemanagementsystem.dto.EmployeeDto;

@JsonTest
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeDtoTest {

	@Autowired
	private JacksonTester<EmployeeDto> json;
	private EmployeeDto employeeDto;
	private ObjectMapper mapper;

	@BeforeEach
	void setup() {
		
		mapper=new ObjectMapper();
	}
	
	@Test
	void testDtoSerialization() throws JsonProcessingException {
		
		EmployeeDto employee = EmployeeDto.builder()
				.firstName("Mohit")
				.email("mohit@gmail.com")
				.lastName("Narkhede")
				.userName("Mohit123").build();
		
		String valueAsString = mapper.writeValueAsString(employee);
		EmployeeDto readValue = mapper.readValue(valueAsString, EmployeeDto.class);
		String jsonValue = mapper.writeValueAsString(readValue);
		assertThat(valueAsString).isEqualTo(jsonValue);
		
		
	}
	

}
