package com.mifinity.creditcardapp.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
/**
 * CreditCardDetails Model is used to define structure of user table
 * @author Akarsh Jain
 * @version 1.0
 *
 */
@Entity
@Table(name = "card_details")
public class CreditCardDetails implements Serializable {

	private static final long serialVersionUID = -2926999841258330324L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String cardholderName;
	
	@NaturalId
	private String cardNumber;
	
	private String expiryDate;
	
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "username", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	//Constructors 
	public CreditCardDetails() {
		super();
	}
	
	

	public CreditCardDetails(String cardholderName, String cardNumber, String expiryDate) {
		super();
		this.cardholderName = cardholderName;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
	}


	//Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardholderName() {
		return cardholderName;
	}

	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "CreditCardDetails [id=" + id + ", cardholderName=" + cardholderName + ", cardNumber=" + cardNumber
				+ ", expiryDate=" + expiryDate + "]";
	}
	
	
}
