package com.technoelevate.employeemanagementsystem.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private Integer employeeId;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private Long phoneNumber;
	private int age;
	private double salary;
	private String experience;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id")
	private List<Address> address;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id")
	private List<Department> department;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id")
	private List<BankAccount> account;

}
