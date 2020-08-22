package com.ee.example.relationship;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cd")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CD {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private Float price;
	
	@ManyToMany(mappedBy = "appearsOnCDs")
	private List<Artist> createdByArtists;

}
