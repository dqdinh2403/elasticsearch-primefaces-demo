package com.ee.example.relationship;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post2")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post2 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String content;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "post2_join_comment2",
			joinColumns = @JoinColumn(name = "post2_fk"),
			inverseJoinColumns = @JoinColumn(name = "comment2_fk"))
	@OrderColumn(name = "posted_index")
	private List<Comment2> comment2s;
}
