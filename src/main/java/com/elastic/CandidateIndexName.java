package com.elastic;

import lombok.Getter;

public enum CandidateIndexName {
	ID("id"),
	FULL_NAME("fullName"),
	YEAR_OF_BIRTH("yearOfBirth"),
	EMAILS("emails"),
	PHONES("phones"),
	SKYPE("skype");
	
	@Getter
	private String value;
	
	private CandidateIndexName(String value) {
		this.value = value;
	}
}
