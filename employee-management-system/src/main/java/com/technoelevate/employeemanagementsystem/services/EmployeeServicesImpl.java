package com.technoelevate.employeemanagementsystem.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.technoelevate.employeemanagementsystem.customexceptions.EmployeeNotFoundException;
import com.technoelevate.employeemanagementsystem.dao.EmployeeDao;
import com.technoelevate.employeemanagementsystem.dto.EmployeeDto;
import com.technoelevate.employeemanagementsystem.entity.Employee;
import com.technoelevate.employeemanagementsystem.helper.JwtUtil;

@Component
@Service
public class EmployeeServicesImpl implements EmployeeServices, UserDetailsService {

	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtUtil jwtUtil;


	@Override
	public Employee addEmployee(EmployeeDto employeeDto) {

		try {
			Employee employee = new Employee();
			BeanUtils.copyProperties(employeeDto, employee);
			
			employee.setPassword(passwordEncoder.encode(employee.getPassword()));
			Employee emp = employeeDao.save(employee);
			
			UserDetails userDetails = loadUserByUsername(employeeDto.getUserName());
			String token = this.jwtUtil.generateToken(userDetails);
			System.out.println(token);
			
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			
			simpleMailMessage.setFrom("mohitnarkhede629@gmail.com");
			simpleMailMessage.setTo(emp.getEmail());
			simpleMailMessage.setText("Registration Sussessful.\n your Login Credentials are as follows\n "
					+ "username : " + emp.getUserName() + " \n Password : " + emp.getPassword());
			simpleMailMessage.setSubject("Registration successful.This Email was sent from java");
			mailSender.send(simpleMailMessage);
			return emp;

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
	public Employee deleteRecord(int id) {

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
		} catch (EmployeeNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee user = employeeDao.findByUserName(username);
		return new User(user.getUserName(), user.getPassword(), new ArrayList<>());
	}
}
