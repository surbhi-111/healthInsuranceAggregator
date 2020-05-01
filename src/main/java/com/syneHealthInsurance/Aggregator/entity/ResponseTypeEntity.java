package com.syneHealthInsurance.Aggregator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTypeEntity {

	private String planNumber;
	private String planName;
	private String lifeCover;
}
