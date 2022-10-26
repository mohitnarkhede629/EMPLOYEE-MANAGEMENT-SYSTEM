package com.technoelevate.employeemanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

	private int doorNumber;
	private String city;
	private String streetNumber;
	private String buildingName;
	private String state;
	private long pincode;
}
