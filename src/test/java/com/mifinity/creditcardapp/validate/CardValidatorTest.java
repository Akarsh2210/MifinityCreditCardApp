package com.mifinity.creditcardapp.validate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import com.mifinity.creditcardapp.model.CreditCardDetails;
import com.mifinity.creditcardapp.service.CreditCardService;
/**
 * The CardValidatorTest class test validations related to  application.
 * @author Akarsh Jain
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class CardValidatorTest {
	
	private CreditCardDetails creditCardDetails;
	private BindingResult bindingResult;
	
	@InjectMocks
	private CardValidator cardValidator;
	
	@Mock
	private CreditCardService creditCardService;
	
	@Before
	public void setUp() {
		creditCardDetails = new CreditCardDetails();
		bindingResult = new BeanPropertyBindingResult(creditCardDetails, "creditCardDetails");
	}
	
	@Test
	public void validateNewSuccessTest() {
		creditCardDetails.setCardholderName("akarsh jain");
		creditCardDetails.setCardNumber("1234567890123456");
		creditCardDetails.setExpiryDate("12/22");
		cardValidator.validateNew(creditCardDetails, bindingResult);
		assertFalse(bindingResult.hasErrors());
	}
	
	@Test
	public void validateNewFieldsNullFailTest() {
		cardValidator.validateNew(creditCardDetails, bindingResult);
		assertTrue("Errors list size should not be null : ", bindingResult.getErrorCount() > 0);
		assertTrue(bindingResult.hasErrors());
		assertNotNull( bindingResult.getFieldError("cardholderName") );
		assertNotNull( bindingResult.getFieldError("cardNumber") );
		assertNotNull( bindingResult.getFieldError("expiryDate") );
	}
	
	@Test
	public void validateNewCardNoDuplicateFailTest() {
		creditCardDetails.setCardholderName("akarsh jain");
		creditCardDetails.setCardNumber("1234567890123456");
		creditCardDetails.setExpiryDate("12/22");
		when(creditCardService.findByCardNumber(Mockito.anyString())).thenReturn(new CreditCardDetails());
		cardValidator.validateNew(creditCardDetails, bindingResult);
		assertTrue("Errors list size should not be null : ", bindingResult.getErrorCount() > 0);
		assertTrue(bindingResult.hasErrors());
		assertNotNull( bindingResult.getFieldError("cardNumber") );
		verify(creditCardService, times(1)).findByCardNumber(Mockito.anyString());
	}
	
	@Test
	public void validateUpdateSuccessTest() {
		creditCardDetails.setCardNumber("1234567890123456");
		creditCardDetails.setExpiryDate("12/22");
		cardValidator.validateUpdate(creditCardDetails, bindingResult);
		assertFalse(bindingResult.hasErrors());
	}
	
	@Test
	public void validateUpdateFieldsNullFailTest() {
		cardValidator.validateNew(creditCardDetails, bindingResult);
		assertTrue("Errors list size should not be null : ", bindingResult.getErrorCount() > 0);
		assertTrue(bindingResult.hasErrors());
		assertNotNull( bindingResult.getFieldError("cardNumber") );
		assertNotNull( bindingResult.getFieldError("expiryDate") );
	}
	
}
