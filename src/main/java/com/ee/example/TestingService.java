package com.ee.example;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ee.example.news.News;
import com.ee.example.news.News2;
import com.ee.example.news.NewsId;
import com.ee.example.news.NewsId2;
import com.ee.example.relationship.Artist;
import com.ee.example.relationship.CD;
import com.ee.example.relationship.Customer;
import com.ee.example.relationship.Order;
import com.ee.example.relationship.Post;
import com.ee.example.relationship.Post2;

@Stateless
public class TestingService {
	
	@PersistenceContext
	EntityManager em;

	public void addNews1(News news) {
		this.em.persist(news);
	}

	public News findNews1(NewsId pk) {
		return this.em.find(News.class, pk);
	}

	public void addNews2(News2 news2) {
		this.em.persist(news2);
	}

	public News2 findNews2(NewsId2 pk) {
		return this.em.find(News2.class, pk);
	}

	
	// For testing JPA relationship @OneToOne unidirectional
	public void createCustomer(Customer customer) {
		this.em.persist(customer);
	}

	public Customer findCustomer(Long id) {
		return this.em.find(Customer.class, id);
	}
	
	
	// For testing JPA relationship @OneToMany unidirectional
	public void createOrder(Order order) {
		this.em.persist(order);
	}
	
	public Order findOrder(Long id) {
		return this.em.find(Order.class, id);
	}
	
	
	// For testing JPA relationship @ManyToMany bidirectional
	public void createArtist(Artist artist) {
		this.em.persist(artist);
	}
	
	public Artist findArtist(Long id) {
		return this.em.find(Artist.class, id);
	}
	
	public CD findCD(Long id) {
		return this.em.find(CD.class, id);
	}
	
	
	// For testing JPA relationship @OrderBy
	public void createPost(Post post) {
		this.em.persist(post);
	}
	
	public Post findPost(Long id) {
		return this.em.find(Post.class, id);
	}
	
	// For testing JPA relationship @OrderColumn
	public void createPost2(Post2 post) {
		this.em.persist(post);
	}
	
	public Post2 findPost2(Long id) {
		return this.em.find(Post2.class, id);
	}
	
}
