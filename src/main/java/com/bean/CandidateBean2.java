package com.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.CellEditEvent;

import com.core.Candidate;
import com.service.CandidateService;
import com.ui.CandidateField;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
public class CandidateBean2 implements Serializable {
	
	@Getter
	private String idField = CandidateField.ID.getValue();
	@Getter
	private String updatedDateField = CandidateField.UPDATED_DATE.getValue();
	@Getter
	private String fullNameField = CandidateField.FULLNAME.getValue();
	@Getter
	private String yobField = CandidateField.YOB.getValue();
	
	@Getter @Setter
	private List<Candidate> candidates;
	
	@EJB
	CandidateService candidateService;
	
	@PostConstruct
	public void initScreen() {
		this.candidates = this.candidateService.findAll();
	}
	
	public void doUpdateCandidate(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		
		if(Objects.nonNull(newValue) && !Objects.equals(oldValue, newValue)) {
			String field = event.getColumn().getField();
			Integer candidateId = Integer.valueOf(event.getRowKey());
			
			this.candidateService.updateField(candidateId, field, newValue);
		}
	}
	
	public void doAddNewRow() {
		Candidate candidate = new Candidate("Full Name", 0);
		this.candidateService.add(candidate);
		this.initScreen();
	}
	
}
