package com.technoelevate.employeemanagementsystem.wrapper;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class LoginWrapper {
	private String userName;
	private String password;
}
