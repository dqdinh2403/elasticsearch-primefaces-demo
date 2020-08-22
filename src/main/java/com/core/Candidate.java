package com.core;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "candidate")
@NamedQueries({
	@NamedQuery(name = "findAllCandidate", query = "SELECT c FROM Candidate c"),
	@NamedQuery(name = "findCandidateById", query = "SELECT c FROM Candidate c WHERE c.id = :id")
})
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Candidate implements Serializable {
	
	public static final String FIND_ALL = "findAllCandidate";
	public static final String FIND_BY_ID = "findCandidateById";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	@NotNull(message = "The full name should be not null")
	@Column(name = "full_name")
	private String fullName;
	
	@NotNull(message = "The year of birth should be not null")
	@Column(name = "year_of_birth")
	private Integer yearOfBirth;
	
//	@OneToMany
//	private List<ContactInfo> contactInfos;
	
//	@ManyToMany(mappedBy = "candidate_skill")
//	private List<Skill> skills;
	
	@Transient
	private String noneSave;

	public Candidate(String fullName, Integer yearOfBirth) {
		this.updatedDate = new Date();
		this.fullName = fullName;
		this.yearOfBirth = yearOfBirth;
	}
	
}
