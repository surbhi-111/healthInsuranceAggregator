package com.syneHealthInsurance.Aggregator.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NegativeOrZero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Aggregator {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private long regiNumber;
	
	private String CompanyName;

	private String apiURL;
	private String responseType;
	private String responseFormat;
	
}
