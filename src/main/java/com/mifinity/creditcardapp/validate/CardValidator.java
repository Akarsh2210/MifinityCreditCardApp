package com.mifinity.creditcardapp.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.mifinity.creditcardapp.model.CreditCardDetails;
import com.mifinity.creditcardapp.service.CreditCardService;

/**
 * CardValidator is a class that validates the card details.
 * 
 * @author Akarsh Jain
 * @version 1.0
 */

@Component
public class CardValidator {
	
	@Autowired
	CreditCardService creditCardService;

	public void validateNew(Object target, Errors errors) {

		CreditCardDetails creditCardDetails = (CreditCardDetails) target;

		if (creditCardDetails.getCardholderName() == null || "".equals(creditCardDetails.getCardholderName().trim())) {
			errors.rejectValue("cardholderName", "NotEmpty");
		} else if (creditCardDetails.getCardholderName().length() < 2
				|| creditCardDetails.getCardholderName().length() > 30) {
			errors.rejectValue("cardholderName", "Size.cardDetailsForm.cardholderName");
		}

		if (creditCardDetails.getCardNumber() == null || "".equals(creditCardDetails.getCardNumber().trim())) {
			errors.rejectValue("cardNumber", "NotEmpty");
		} else if (creditCardDetails.getCardNumber().length() != 16) {
			errors.rejectValue("cardNumber", "Size.cardDetailsForm.cardNumber");
		}

		if (creditCardDetails.getExpiryDate() == null || "".equals(creditCardDetails.getExpiryDate().trim())) {
			errors.rejectValue("expiryDate", "NotEmpty");
		} else if (!(creditCardDetails.getExpiryDate().matches("(?:0[1-9]|1[0-2])/[0-9]{2}"))) {
			errors.rejectValue("expiryDate", "Format.cardDetailsForm.expiryDate");
		}
		
		if (creditCardService.findByCardNumber(creditCardDetails.getCardNumber()) != null) {
			errors.rejectValue("cardNumber", "Duplicate.cardDetailsForm.cardNumber");
		}

	}
	
	public void validateUpdate(Object target, Errors errors) {

		CreditCardDetails creditCardDetails = (CreditCardDetails) target;

		if (creditCardDetails.getCardNumber() == null || "".equals(creditCardDetails.getCardNumber().trim())) {
			errors.rejectValue("cardNumber", "NotEmpty");
		} else if (creditCardDetails.getCardNumber().length() != 16) {
			errors.rejectValue("cardNumber", "Size.cardDetailsForm.cardNumber");
		}

		if (creditCardDetails.getExpiryDate() == null || "".equals(creditCardDetails.getExpiryDate().trim())) {
			errors.rejectValue("expiryDate", "NotEmpty");
		} else if (!(creditCardDetails.getExpiryDate().matches("(?:0[1-9]|1[0-2])/[0-9]{2}"))) {
			errors.rejectValue("expiryDate", "Format.cardDetailsForm.expiryDate");
		}

	}

}
