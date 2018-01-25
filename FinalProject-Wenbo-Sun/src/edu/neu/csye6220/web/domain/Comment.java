package edu.neu.csye6220.web.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="comments")
public class Comment {

	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank
	private String subject;
	
	@NotBlank
	private String comment;
	
	@ManyToOne
	@JoinColumn(name="username")
	private User user;

	public Comment() {
		this.user=new User();
	}
	
	public Comment(String subject, String comment, User user) {
		this.subject = subject;
		this.comment = comment;
		this.user = user;
	}

	public Comment(int id, String subject, String comment, User user) {
		this.subject = subject;
		this.id = id;
		this.comment = comment;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
