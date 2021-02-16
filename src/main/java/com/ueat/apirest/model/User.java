package com.ueat.apirest.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table(name="USER")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	
	@Column(name="PASSWORD", length = 100)
	private String password;
	
	@Column(name="EMAIL", length = 100)
	private String email;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="USER_ROLE",joinColumns = @JoinColumn(name="USER"),inverseJoinColumns = @JoinColumn(name="ROLE"))
	private List<Role> roles;
	
	
	

	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
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




	public List<Role> getRoles() {
		return roles;
	}




	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}




	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", email=" + email + ", roles=" + roles + "]";
	}
	
	

}
