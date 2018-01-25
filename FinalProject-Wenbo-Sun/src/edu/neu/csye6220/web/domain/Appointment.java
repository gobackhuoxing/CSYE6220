package edu.neu.csye6220.web.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="appointments")
public class Appointment {
	
	@Id
	@GeneratedValue
	private int id;

	@NotBlank
	private String detail;
	
	@NotBlank
	private String time;
	
	private String confirm;
	
	@ManyToOne
	@JoinColumn(name="username")
	private User user;

	public Appointment() {
		this.user = new User();
	}

	public Appointment(User user, String detail, String time, String confirm) {
		this.confirm=confirm;
		this.user = user;
		this.detail = detail;
		this.time = time;
	}

	public Appointment(int id, User user, String detail, String time, String confirm) {
		this.id = id;
		this.user = user;
		this.detail = detail;
		this.time = time;
		this.confirm=confirm;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername(){
		return user.getUsername();
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}	
	
}
