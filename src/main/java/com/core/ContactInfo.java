package com.core;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contact_info")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "The contact type should be not null")
	@Enumerated(EnumType.STRING)
	private ContactType contactType;
	
	@NotNull(message = "The contact value should be not null")
	private String contactValue;
	
//	@ManyToOne
//	private Candidate candidate;
	
}
