package com.ee.example;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import com.ee.example.news.News;
import com.ee.example.news.News2;
import com.ee.example.news.NewsId;
import com.ee.example.news.NewsId2;
import com.ee.example.relationship.Address;
import com.ee.example.relationship.Artist;
import com.ee.example.relationship.CD;
import com.ee.example.relationship.Comment;
import com.ee.example.relationship.Comment2;
import com.ee.example.relationship.Customer;
import com.ee.example.relationship.Order;
import com.ee.example.relationship.OrderLine;
import com.ee.example.relationship.Post;
import com.ee.example.relationship.Post2;

@ManagedBean
@ViewScoped
public class TestingBean implements Serializable {
	
	private Logger logger = Logger.getLogger(TestingBean.class);
	
	@EJB
	TestingService testingService;
	
	public void doAddNews1() {
		for (int i = 1; i <= 10; i++) {
			NewsId pk = new NewsId("title " + i, "eng " + i);
			News news = new News(pk, "content " + i);
			this.testingService.addNews1(news);
		}
	}
	
	public void doFindNews1() {
		NewsId pk = new NewsId("title 1", "eng 1");
		News news = this.testingService.findNews1(pk);
		logger.info("Title = " + news.getId().getTitle() + "; Languge = " + news.getId().getLanguage()
			+ "; Content = " + news.getContent());
	}
	
	public void doAddNews2() {
		for (int i = 1; i <= 10; i++) {
			News2 news2 = new News2("title " + i, "eng " + i, "content " + i);
			this.testingService.addNews2(news2);
		}
	}
	
	public void doFindNews2() {
		NewsId2 pk = new NewsId2("title 5", "eng 5");
		News2 news2 = this.testingService.findNews2(pk);
		logger.info("Title = " + news2.getTitle() + "; Languge = " + news2.getLanguage()
				+ "; Content = " + news2.getContent());
	}
	
	
	// For testing JPA relationship @OneToOne unidirectional
	public void doCreateCustomer() {
		Address address = Address.builder().country("Viet Nam").build();
		Customer customer = Customer.builder().fullName("Dao Quang Dinh").address(address).build();
		this.testingService.createCustomer(customer);
	}
	
	public void doFindCustomer() {
		Customer customer = this.testingService.findCustomer(1l);
		Address address = customer.getAddress();
		logger.info("Customer: " + customer.getId() + " - " + customer.getFullName()
				+ "; Address: " + address.getId() + " - " + address.getCountry());
	}
	
	
	// For testing JPA relationship @OneToMany unidirectional
	public void doCreateOrder() {
		OrderLine orderLine1 = OrderLine.builder().item("Item 1").quantity(5).build();
		OrderLine orderLine2 = OrderLine.builder().item("Item 2").quantity(10).build();
		List<OrderLine> orderLines = Arrays.asList(orderLine1, orderLine2);
		
		Order order = Order.builder().creationDate(new Date()).orderLines(orderLines).build();
		this.testingService.createOrder(order);
	}
	
	public void doFindOrder() {
		Order order = this.testingService.findOrder(1l);
		List<OrderLine> orderLines = order.getOrderLines();
		
		logger.info("Order: " + order.getId() + " - " + order.getCreationDate());
		for (OrderLine orderLine : orderLines) {
			logger.info("OrderLine: " + orderLine.getId() + " - " + orderLine.getItem() + " - " + orderLine.getQuantity());
		}
	}
	
	
	// For testing JPA relationship @ManyToMany bidirectional
	public void doCreateArtist() {
		CD cd1 = CD.builder().title("CD number 1").price(1000f).build();
		CD cd2 = CD.builder().title("CD number 2").price(2000f).build();
		CD cd3 = CD.builder().title("CD number 3").price(3000f).build();
		CD cd4 = CD.builder().title("CD number 4").price(4000f).build();
		
		Artist dinh = Artist.builder().fullName("Dinh")
				.appearsOnCDs(Arrays.asList(cd1, cd2)).build();
		Artist thai = Artist.builder().fullName("Thai")
				.appearsOnCDs(Arrays.asList(cd3, cd4)).build();
		
		this.testingService.createArtist(dinh);
		this.testingService.createArtist(thai);
	}
	
	public void doFindArtist() {
		Artist dinh = this.testingService.findArtist(1l);
		List<CD> dinhCDs = dinh.getAppearsOnCDs();
		
		Artist thai = this.testingService.findArtist(2l);
		List<CD> thaiCDs = thai.getAppearsOnCDs();
		
		logger.info("Artist: " + dinh.getId() + " - " + dinh.getFullName());
		for (CD cd : dinhCDs) {
			logger.info("CD: " + cd.getId() + " - " + cd.getTitle() + " - " + cd.getPrice());
		}
		
		logger.info("Artist: " + thai.getId() + " - " + thai.getFullName());
		for (CD cd : thaiCDs) {
			logger.info("CD: " + cd.getId() + " - " + cd.getTitle() + " - " + cd.getPrice());
		}
	}
	
	public void doFindCD() {
		CD cd1 = this.testingService.findCD(1l);
		List<Artist> CD1artists = cd1.getCreatedByArtists();
		
		CD cd2 = this.testingService.findCD(2l);
		List<Artist> CD2artists = cd2.getCreatedByArtists();
		
		CD cd3 = this.testingService.findCD(3l);
		List<Artist> CD3artists = cd3.getCreatedByArtists();
		
		CD cd4 = this.testingService.findCD(4l);
		List<Artist> CD4artists = cd4.getCreatedByArtists();
		
		logger.info("CD: " + cd1.getId() + " - " + cd1.getTitle() + " - " + cd1.getPrice());
		for (Artist artist : CD1artists) {
			logger.info("Artist: " + artist.getId() + " - " + artist.getFullName());
		}
		
		logger.info("CD: " + cd2.getId() + " - " + cd2.getTitle() + " - " + cd2.getPrice());
		for (Artist artist : CD2artists) {
			logger.info("Artist: " + artist.getId() + " - " + artist.getFullName());
		}
		
		logger.info("CD: " + cd3.getId() + " - " + cd3.getTitle() + " - " + cd3.getPrice());
		for (Artist artist : CD3artists) {
			logger.info("Artist: " + artist.getId() + " - " + artist.getFullName());
		}
		
		logger.info("CD: " + cd4.getId() + " - " + cd4.getTitle() + " - " + cd4.getPrice());
		for (Artist artist : CD4artists) {
			logger.info("Artist: " + artist.getId() + " - " + artist.getFullName());
		}
	}
	
	
	// For testing JPA relationship @OrderBy
	public void doCreatePost() {
		Comment firstComment = Comment.builder()
				.nickname("Dinh")
				.content("This is dinh's comment")
				.note(1)
				.postedDate(new Date())
				.build();
		Comment secondComment = Comment.builder()
				.nickname("Nhat")
				.content("This is nhat's comment")
				.note(2)
				.postedDate(new Date())
				.build();
		Comment thirdComment = Comment.builder()
				.nickname("Thai")
				.content("This is thai's comment")
				.note(3)
				.postedDate(new Date())
				.build();
		
		Post post = Post.builder()
				.content("This is post")
				.comments(Arrays.asList(firstComment, secondComment, thirdComment))
				.build();
		
		this.testingService.createPost(post);
	}
	
	public void doFindPost() {
		Post post = this.testingService.findPost(1l);
		List<Comment> comments = post.getComments();
		
		logger.info("Post: " + post.getId() + " - " + post.getContent());
		for (Comment comment : comments) {
			logger.info("Comment: " + comment.getId() + " - " + comment.getNickname()
					+ " - " + comment.getContent() + " - " + comment.getNote()
					+ " - " + comment.getPostedDate());
		}
	}

	
	// For testing JPA relationship @OrderColumn
	public void doCreatePost2() {
		Comment2 firstComment = Comment2.builder()
				.nickname("Dinh")
				.content("This is dinh's comment")
				.note(1)
				.build();
		Comment2 secondComment = Comment2.builder()
				.nickname("Nhat")
				.content("This is nhat's comment")
				.note(2)
				.build();
		Comment2 thirdComment = Comment2.builder()
				.nickname("Thai")
				.content("This is thai's comment")
				.note(3)
				.build();
		
		Post2 post = Post2.builder()
				.content("This is post")
				.comment2s(Arrays.asList(firstComment, secondComment, thirdComment))
				.build();
		
		this.testingService.createPost2(post);
	}
	
	public void doFindPost2() {
		Post2 post = this.testingService.findPost2(1l);
		List<Comment2> comments = post.getComment2s();
		
		logger.info("Post: " + post.getId() + " - " + post.getContent());
		for (Comment2 comment : comments) {
			logger.info("Comment: " + comment.getId() + " - " + comment.getNickname()
					+ " - " + comment.getContent() + " - " + comment.getNote());
		}
	}
	
}
