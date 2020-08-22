package com.ee.example.news;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@IdClass(NewsId2.class)
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class News2 {
	
	@Id
	private String title;
	
	@Id
	private String language;
	
	private String content;
	
	
}
