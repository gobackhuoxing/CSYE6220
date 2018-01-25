package edu.neu.csye6220.web.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import edu.neu.csye6220.web.validation.ValidEmail;

@Entity
@Table(name="users")
public class User {
	
	@NotBlank(message="come!")
	@Size(min=4, max=40,message="are you sure?")
	@Pattern(regexp="^\\w{8,}$", message ="oops")
	@Id
	private String username;
	
	@NotBlank(message="good day")
	@Pattern(regexp="^\\S+$", message="no no no")
	@Size(min=8,max=15, message="too bad ")
	private String password;
	
	@ValidEmail(message="try again")
	private String email;
	
	@NotBlank(message="good night")
	private String name;
	
	@NotBlank
	private String gender;
	
	private boolean enabled= false;
	private String authority;
	
	public User(){}
	public User(String username, String name, String password,String email, String gender, boolean enabled, String authority) {
		this.username = username;
		this.name=name;
		this.password = password;
		this.email = email;
		this.gender= gender;
		this.enabled = enabled;
		this.authority = authority;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
}
