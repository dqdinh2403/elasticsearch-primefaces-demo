package com.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public enum ContactType {
	
	EMAIL, PHONE, SKYPE;

//	EMAIL("Email"),
//	PHONE("Phone"),
//	SKYPE("Skype");
//	
//	@Getter
//	private String value;
//	
//	private ContactType(String value) {
//		this.value = value;
//	}
	
}
