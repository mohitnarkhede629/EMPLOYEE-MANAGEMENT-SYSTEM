package com.technoelevate.employeemanagementsystem.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technoelevate.employeemanagementsystem.entity.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Serializable> {

}
