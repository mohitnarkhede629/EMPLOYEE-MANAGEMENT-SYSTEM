package com.technoelevate.employeemanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.technoelevate.employeemanagementsystem.dto.EmployeeDto;
import com.technoelevate.employeemanagementsystem.entity.Employee;
import com.technoelevate.employeemanagementsystem.response.Response;
import com.technoelevate.employeemanagementsystem.services.EmployeeServices;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeServices employeeServices;

	@PostMapping(path = "/addemployee")
	public ResponseEntity<Response> addEnployee(@RequestBody EmployeeDto employeeDto) {

		Employee addEmployee = employeeServices.addEmployee(employeeDto);
		return new ResponseEntity<>(new Response(false, "Data Saved Successfully", addEmployee), HttpStatus.OK);

	}

	@GetMapping(path = "/getemployee")
	public ResponseEntity<Response> getEmployee(@RequestParam Long id) {
		Employee employee = employeeServices.getEmployee(id);
		return new ResponseEntity<>(new Response(false, "Data Displayed In Response", employee), HttpStatus.OK);

	}

	@GetMapping(path = "/getallemp")
	public ResponseEntity<Response> getAllEmployee() {

		List<Employee> allEmp = employeeServices.getAllEmp();
		return new ResponseEntity<>(new Response(false, "Data Displayed", allEmp), HttpStatus.OK);

	}

	@DeleteMapping(path = "/deleterecord")
	public ResponseEntity<Response> deleteRecord(@RequestParam Long id) {
		Employee deleteRecord = employeeServices.deleteRecord(id);
		return new ResponseEntity<>(new Response(false, "Data Deleted Successfully", deleteRecord), HttpStatus.OK);
	}

	@PutMapping(path = "/update/{id}")
	public ResponseEntity<Response> updateData(@PathVariable("id") Long id, @RequestBody EmployeeDto dto) {
		Employee updateRecord = employeeServices.updateRecord(id, dto);
		return new ResponseEntity<>(new Response(false, " Data Updated Successfully", updateRecord), HttpStatus.OK);

	}

}