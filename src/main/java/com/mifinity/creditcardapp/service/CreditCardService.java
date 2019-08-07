package com.mifinity.creditcardapp.service;

import java.util.List;

import com.mifinity.creditcardapp.model.CreditCardDetails;
/**
 * CreditCardService is an interface in which all the method declaration are defined 
 * to perform the fuctionality present in an application.
 * @author Akarsh Jain
 * @version 1.0
 */
public interface CreditCardService {

	public boolean addNewCard(String loggedUser, CreditCardDetails creditCardDetails);
	public List<CreditCardDetails> searchCreditCard(String loggedUser, String creditCardNumber);
	//public boolean updateExistingCard(String loggedUser, CreditCardDetails creditCardDetails);
	public String updateExistingCard(String loggedUser, CreditCardDetails creditCardDetails);
	public CreditCardDetails findByCardNumber(String cardNumber);
}
