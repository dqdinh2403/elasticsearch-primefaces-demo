package com.util;

import java.util.Map;

import com.elastic.CandidateIndexData;
import com.elastic.CandidateIndexName;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Mapper {
	
	public static CandidateIndexData convertMaptoCandidateIndexData(Map<String, Object> rawData) {
		
		CandidateIndexData candidateIndexData = new CandidateIndexData();
		
		String fullName = rawData.get(CandidateIndexName.FULL_NAME.getValue()).toString();
		candidateIndexData.setFullName(fullName);
		
		Integer yearOfBirth = (Integer) rawData.get(CandidateIndexName.YEAR_OF_BIRTH.getValue());
		candidateIndexData.setYearOfBirth(yearOfBirth);
		
		String emails = rawData.get(CandidateIndexName.EMAILS.getValue()).toString();
		candidateIndexData.setEmails(emails);
		
		String phones = rawData.get(CandidateIndexName.PHONES.getValue()).toString();
		candidateIndexData.setPhones(phones);
		
		String skype = rawData.get(CandidateIndexName.SKYPE.getValue()).toString();
		candidateIndexData.setSkype(skype);
		
		return candidateIndexData;
	}
	
}
