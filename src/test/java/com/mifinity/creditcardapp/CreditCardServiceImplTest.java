package com.mifinity.creditcardapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.mifinity.creditcardapp.model.CreditCardDetails;
import com.mifinity.creditcardapp.model.Role;
import com.mifinity.creditcardapp.model.User;
import com.mifinity.creditcardapp.repository.CreditCardRepository;
import com.mifinity.creditcardapp.repository.UserRepository;
import com.mifinity.creditcardapp.service.impl.CreditCardServiceImpl;
/**
 * The CreditCardServiceImplTest class test functionality related to  application.
 * @author Akarsh Jain
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class CreditCardServiceImplTest {

	private static CreditCardDetails creditCardDetails, savedCreditCardDetails, creditCardDetails2, creditCardDetails3;
	private static User user, user2, adminUser;
	private static Role userRole;
	private static Role adminRole;

	@InjectMocks
	private CreditCardServiceImpl creditCardServiceImpl;

	@Mock
	private CreditCardRepository creditCardRepository;

	@Mock
	private UserRepository userRepository;

	@BeforeClass
	public static void setUp() {
		//make roles
		userRole = new Role(ApplicationUtil.ROLE_USER);
		adminRole = new Role(ApplicationUtil.ROLE_ADMIN);

		//make users
		user = new User("akarsh", "pass1", userRole);
		user2 = new User("joy", "pass2", userRole);
		adminUser = new User("admin", "password", adminRole);

		//make cards
		creditCardDetails = new CreditCardDetails("akarsh jain", "1111222233334444", "12/22");
		creditCardDetails.setUser(user);
		savedCreditCardDetails = new CreditCardDetails("akarsh jain", "1111222233334444", "12/22");
		savedCreditCardDetails.setUser(user);
		creditCardDetails2 = new CreditCardDetails("akar jainesh", "1234567890123456", "04/32");
		creditCardDetails2.setUser(user);
		creditCardDetails3 = new CreditCardDetails("joy mukerjee", "5555666677778888", "08/47");
		creditCardDetails3.setUser(user2);
	}

	@Test
	public void addNewCardSuccessTest() {
		when(userRepository.findByUsername(Mockito.anyString())).thenReturn(user);
		when(creditCardRepository.save(creditCardDetails)).thenReturn(savedCreditCardDetails);
		assertTrue(creditCardServiceImpl.addNewCard("akarsh", creditCardDetails));
		verify(userRepository, times(1)).findByUsername(Mockito.anyString());
		verify(creditCardRepository, times(1)).save(creditCardDetails);
	}

	@Test
	public void addNewCardUserDoNotExistFailTest() {
		when(userRepository.findByUsername(Mockito.anyString())).thenReturn(null);
		assertFalse(creditCardServiceImpl.addNewCard("akarsh", creditCardDetails));
		verify(userRepository, times(1)).findByUsername(Mockito.anyString());
	}

	@Test
	public void searchCreditCardUserSuccessTest() {
		List<CreditCardDetails> creditCardList, creditCardList2;
		creditCardList = new ArrayList<CreditCardDetails>() {
			{
				add(creditCardDetails);
				add(creditCardDetails2);
			}
		};
		when(userRepository.findByUsername(Mockito.anyString())).thenReturn(user);
		when(creditCardRepository.findCreditCards(Mockito.anyString(), Mockito.anyString())).thenReturn(creditCardList);
		creditCardList2 = creditCardServiceImpl.searchCreditCard("akarsh", "23");
		assertEquals(creditCardList, creditCardList2);
		verify(userRepository, times(1)).findByUsername(Mockito.anyString());
		verify(creditCardRepository, times(1)).findCreditCards(Mockito.anyString(), Mockito.anyString());
	}
	
	@Test
	public void searchCreditCardAdminSuccessTest() {
		List<CreditCardDetails> creditCardList, creditCardList2;
		creditCardList = new ArrayList<CreditCardDetails>() {
			{
				add(creditCardDetails);
				add(creditCardDetails2);
				add(creditCardDetails3);
			}
		};
		when(userRepository.findByUsername(Mockito.anyString())).thenReturn(adminUser);
		when(creditCardRepository.findAllCards(Mockito.anyString())).thenReturn(creditCardList);
		creditCardList2 = creditCardServiceImpl.searchCreditCard("admin", "23");
		assertEquals(creditCardList, creditCardList2);
		verify(userRepository, times(1)).findByUsername(Mockito.anyString());
		verify(creditCardRepository, times(1)).findAllCards(Mockito.anyString());
	}
	
	@Test
	public void updateExistingCardUserSuccessTest() {
		when(userRepository.findByUsername(Mockito.anyString())).thenReturn(user);
		when(creditCardRepository.updateExistingCard(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(1);
		assertEquals(ApplicationUtil.ROLE_USER, creditCardServiceImpl.updateExistingCard("akarsh", creditCardDetails));
		verify(userRepository, times(1)).findByUsername(Mockito.anyString());
		verify(creditCardRepository, times(1)).updateExistingCard(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
	}
	
	@Test
	public void updateExistingCardAdminSuccessTest() {
		when(userRepository.findByUsername(Mockito.anyString())).thenReturn(adminUser);
		when(creditCardRepository.updateExistingCardAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(1);
		assertEquals(ApplicationUtil.ROLE_ADMIN, creditCardServiceImpl.updateExistingCard("admin", creditCardDetails));
		verify(userRepository, times(1)).findByUsername(Mockito.anyString());
		verify(creditCardRepository, times(1)).updateExistingCardAdmin(Mockito.anyString(), Mockito.anyString());
	}

}
