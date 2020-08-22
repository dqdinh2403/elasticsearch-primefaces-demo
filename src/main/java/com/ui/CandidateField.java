package com.ui;

import lombok.Getter;

public enum CandidateField {
	ID("ID"),
	UPDATED_DATE("Updated Date"),
	FULLNAME("Full Name"),
	YOB("YOB");
	
	@Getter
	private String value;
	
	private CandidateField(String value) {
		this.value = value;
	}
}
