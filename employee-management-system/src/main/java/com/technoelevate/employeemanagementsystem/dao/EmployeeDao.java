package com.technoelevate.employeemanagementsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technoelevate.employeemanagementsystem.entity.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {

	Employee findByUserName(String username);
	Employee findByPassword(String password);

}
