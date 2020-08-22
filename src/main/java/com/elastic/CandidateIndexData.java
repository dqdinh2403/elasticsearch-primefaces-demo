package com.elastic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CandidateIndexData {
	
	private Integer id;
	
	private String fullName;
	
	private Integer yearOfBirth;
	
	private String emails;
	
	private String phones;
	
	private String skype;
	
}
