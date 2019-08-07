package com.mifinity.creditcardapp.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;
/**
 * User Model is used to define structure of user table
 * @author Akarsh Jain
 * @version 1.0
 *
 */
@Entity
@Table(name="user")
public class User implements Serializable {

	private static final long serialVersionUID = -7855465834895962664L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NaturalId
	private String username;
	private String password;
	
	@Transient
	private String passwordConfirm;
	
	@ManyToMany(fetch = FetchType.EAGER)
   	private Set<Role> roles = new HashSet<>();
	
	//Constructors 
	public User() {
		super();
	}
	
	
	public User(String username, String password, Role role) {
		super();
		this.username = username;
		this.password = password;
		addRole(role);
	}


	//Getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public void addRole(Role role) {
		this.roles.add(role);
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", roles=" + roles + "]";
	}
	
	
}
