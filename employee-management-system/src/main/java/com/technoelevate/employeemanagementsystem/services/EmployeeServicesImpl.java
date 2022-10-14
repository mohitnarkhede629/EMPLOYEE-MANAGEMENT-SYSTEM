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

@Component
@Service
public class EmployeeServicesImpl implements EmployeeServices {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Employee addEmployee(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDto, employee);
		Employee emp = employeeDao.save(employee);
		return emp;
	}

	@Override
	public Employee getEmployee(Long id) {

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
			if (findAll == null) {
				throw new EmployeeNotFoundException("No Data Is Present");
			} else {
				return findAll;
			}
		} catch (EmployeeNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public Employee deleteRecord(Long id) {

		try {
			Optional<Employee> findById = employeeDao.findById(id);
			Employee delete = findById.get();
			if (findById.isPresent()) {
				employeeDao.delete(delete);

				return null;

			} else {
				throw new EmployeeNotFoundException("Record with employee id " + id + " was not found");
			}
		} catch (EmployeeNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public Employee updateRecord(Long id, EmployeeDto dto) {

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
		} catch (EmployeeNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}
}
