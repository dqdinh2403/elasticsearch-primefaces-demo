package com.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;

import com.core.Candidate;
import com.ui.CandidateField;

@Stateless
public class CandidateService {
	
	@PersistenceContext
	EntityManager em;
	
	public void add(Candidate candidate) {
		em.persist(candidate);
	}
	
	public Candidate findById(Integer candidateId) {
		return this.em.createNamedQuery(Candidate.FIND_BY_ID, Candidate.class)
					.setParameter("id", candidateId)
					.getSingleResult();
	}
	
	public List<Candidate> findAll(){
		return em.createNamedQuery(Candidate.FIND_ALL, Candidate.class).getResultList();
	}

	public void update(Candidate newCandidate) {
		Integer candidateId = newCandidate.getId();
		Candidate oldCandidate = this.em.createNamedQuery(Candidate.FIND_BY_ID, Candidate.class)
									.setParameter("id", candidateId)
									.getSingleResult();
		
		oldCandidate.setUpdatedDate(new Date());
		oldCandidate.setFullName(newCandidate.getFullName());
		oldCandidate.setYearOfBirth(newCandidate.getYearOfBirth());
		
		this.em.merge(oldCandidate);
	}
	
	public void updateField(Integer candidateId, String fieldUpdate, Object value) {
		Candidate candidateInDb = this.em.createNamedQuery(Candidate.FIND_BY_ID, Candidate.class)
				.setParameter("id", candidateId)
				.getSingleResult();
		
		if(StringUtils.equals(CandidateField.FULLNAME.getValue(), fieldUpdate)) {
			candidateInDb.setFullName(String.valueOf(value));
		}
		else if(StringUtils.equals(CandidateField.YOB.getValue(), fieldUpdate)) {
			candidateInDb.setYearOfBirth(Integer.valueOf(String.valueOf(value)));
		}
		
		candidateInDb.setUpdatedDate(new Date());
		this.em.merge(candidateInDb);
	}
	
}
