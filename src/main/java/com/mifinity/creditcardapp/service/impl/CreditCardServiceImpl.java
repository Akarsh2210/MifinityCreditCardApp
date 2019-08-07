package com.mifinity.creditcardapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mifinity.creditcardapp.ApplicationUtil;
import com.mifinity.creditcardapp.model.CreditCardDetails;
import com.mifinity.creditcardapp.model.User;
import com.mifinity.creditcardapp.repository.CreditCardRepository;
import com.mifinity.creditcardapp.repository.UserRepository;
import com.mifinity.creditcardapp.service.CreditCardService;

/**
 * CreditCardServiceImpl is a class that extends the CreditCardService interface 
 * and overrides all the methods present in it. 
 * It contains the business logic required to execute to functionality.
 * @author Akarsh Jain
 * @version 1.0
 */
@Service
public class CreditCardServiceImpl implements CreditCardService {

	@Autowired
	private CreditCardRepository creditCardRepository;

	@Autowired
	private UserRepository userRepository;

	/*
	 * This method is used to add a new credit card.
	 * 
	 * @param loggedUser contains user name.
	 * @param creditCardDetails contains credit card details.
	 * 
	 * @return true if the card is added.
	 * 
	 */
	@Override
	public boolean addNewCard(String loggedUser, CreditCardDetails creditCardDetails) {
		User existingUser = userRepository.findByUsername(loggedUser);

		if (existingUser != null) {
			creditCardDetails.setUser(existingUser);
			creditCardRepository.save(creditCardDetails);
			return true;

		}
		return false;

	}

	/*
	 * This method is used to search an existng credit card.
	 * 
	 * @param loggedUser contains user name.
	 * @param creditCardNumber contains credit card number to be searched.
	 * 
	 * @return creditCardList list of card.
	 * 
	 */
	@Override
	public List<CreditCardDetails> searchCreditCard(String loggedUser, String creditCardNumber) {
		List<CreditCardDetails> creditCardList = null;
		
		if (userRepository.findByUsername(loggedUser).getRoles().stream().anyMatch(r -> r.getName().equals(ApplicationUtil.ROLE_USER))) {
			creditCardList = creditCardRepository.findCreditCards(loggedUser, creditCardNumber);
		} else {
			creditCardList = creditCardRepository.findAllCards(creditCardNumber);
		}
		return creditCardList;
	}


	/*
	 * This method is used to update an existing credit card.
	 * 
	 * @param loggedUser contains user name.
	 * @param creditCardDetails contains credit card details that is to be updated.
	 * 
	 * @return true if the card is updated.
	 * 
	 */
	@Override
	public String updateExistingCard(String loggedUser, CreditCardDetails creditCardDetails) {
		if (userRepository.findByUsername(loggedUser).getRoles().stream().anyMatch(r -> r.getName().equals(ApplicationUtil.ROLE_USER))) {
			if (creditCardRepository.updateExistingCard(loggedUser, creditCardDetails.getCardNumber(),
					creditCardDetails.getExpiryDate()) == 1) {
				return ApplicationUtil.ROLE_USER;
			}
		} else {
			if (creditCardRepository.updateExistingCardAdmin(creditCardDetails.getCardNumber(),
					creditCardDetails.getExpiryDate()) == 1) {
				return ApplicationUtil.ROLE_ADMIN;
			}
		}

		return "";
	}
	
	/*
	 * This method is used to search an existing credit card.
	 * 
	 * @param cardNumber contains card number.
	 * 
	 * @return card number.
	 * 
	 */
	@Override
	public CreditCardDetails findByCardNumber(String cardNumber) {
		return creditCardRepository.findByCardNumber(cardNumber);
	}

}
