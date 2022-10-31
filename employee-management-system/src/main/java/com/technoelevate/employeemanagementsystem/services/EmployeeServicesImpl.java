package com.technoelevate.employeemanagementsystem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.technoelevate.employeemanagementsystem.customexceptions.EmployeeNotFoundException;
import com.technoelevate.employeemanagementsystem.dao.EmployeeDao;
import com.technoelevate.employeemanagementsystem.dto.EmployeeDto;
import com.technoelevate.employeemanagementsystem.entity.Employee;

import lombok.AllArgsConstructor;

@Component
@Service
@AllArgsConstructor
public class EmployeeServicesImpl implements EmployeeServices {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Employee addEmployee(EmployeeDto employeeDto) {

		try {
			Employee employee = new Employee();
			BeanUtils.copyProperties(employeeDto, employee);

			return employeeDao.save(employee);

		} catch (Exception e) {
			throw e;

		}
	}

	@Override
	public Employee getEmployee(int id) {

		try {
			Optional<Employee> findById = employeeDao.findById(id);
			if (findById.isPresent()) {
				Employee employee2 = findById.get();
				return employee2;
			} else {
				throw new EmployeeNotFoundException("No Record With Emp Id " + id + " Was Found");
			}
		} catch (EmployeeNotFoundException e) {
			throw e;
		}
	}

	@Override
	public List<Employee> getAllEmp() {

		try {
			List<Employee> findAll = employeeDao.findAll();

			if (findAll.isEmpty()) {
				throw new EmployeeNotFoundException("No Data Is Present");
			} else {
				return findAll;
			}
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public String deleteRecord(int id) {

		try {
			Optional<Employee> findById = employeeDao.findById(id);
			if (findById.isPresent()) {
				employeeDao.deleteById(id);
				return "Record Deleted Successfuly";
			} else {
				throw new EmployeeNotFoundException("Record with employee id " + id + " was not found");
			}
		} catch (RuntimeException e) {
			throw e;
		}

	}

	@Override
	public Employee updateRecord(int id, EmployeeDto dto) {

		try {
			if (employeeDao.findById(id).isPresent()) {

				Optional<Employee> findById = employeeDao.findById(id);
				Employee employee2 = findById.get();
				BeanUtils.copyProperties(dto, employee2);
				employeeDao.save(employee2);

				return employee2;
			} else {
				throw new EmployeeNotFoundException("Record With Employee Id " + id + " Was Not Found");
			}
		} catch (RuntimeException   e) {
			throw e;
		}
	}

}
