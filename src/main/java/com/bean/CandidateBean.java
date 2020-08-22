package com.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.core.Candidate;
import com.service.CandidateService;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
public class CandidateBean implements Serializable {
	
	@Getter @Setter
	private String fullName;
	
	@Getter @Setter
	private Integer yearOfBirth;
	
	@Getter @Setter
	private List<Candidate> candidates;
	
	@Getter @Setter
	private Candidate selectedCandidate;
	
	@EJB
	CandidateService candidateService;
	
	@PostConstruct
	public void initScreen() {
		this.candidates = candidateService.findAll();
	}
	
	public void doAddCandidate() {
		Candidate candidate = Candidate.builder()
				.updatedDate(new Date())
				.fullName(this.fullName)
				.yearOfBirth(this.yearOfBirth)
				.build();
		
		this.candidateService.add(candidate);
		this.resetAddData();
		this.initScreen();
	}
	
	private void resetAddData() {
		this.fullName = null;
		this.yearOfBirth = null;
	}
	
	public void doUpdateCandidate() {
		this.candidateService.update(this.selectedCandidate);
		this.resetUpdateData();
		this.initScreen();
	}
	
	private void resetUpdateData() {
		this.selectedCandidate = null;
	}
	
}
