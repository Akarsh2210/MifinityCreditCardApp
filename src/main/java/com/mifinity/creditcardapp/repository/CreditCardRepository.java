package com.mifinity.creditcardapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.mifinity.creditcardapp.model.CreditCardDetails;
/**
 * CreditCardRepository interface is the repository that extends the JPA repository.
 * It is connected to "card_details" table in the database 
 * 
 * @author Akarsh Jain
 * @version 1.0
 */

public interface CreditCardRepository extends JpaRepository<CreditCardDetails, Long> {

	@Query(value = "select * from card_details cd WHERE cd.user_id = ?1 AND cd.card_number like %?2%", nativeQuery = true)
	List<CreditCardDetails> findCreditCards(String loggedUser, String creditCardNumber);

	@Modifying
	@Transactional
	@Query(value = "update card_details set expiry_date = ?3 where user_id = ?1 AND card_number = ?2", nativeQuery = true)
	int updateExistingCard(String loggedUser, String cardNumber, String expiryDate);

	@Query(value ="SELECT cd from CreditCardDetails cd WHERE cd.cardNumber like %?1%")
	List<CreditCardDetails> findAllCards(String creditCardNumber);

	@Modifying
	@Transactional
	@Query(value ="UPDATE CreditCardDetails cd SET cd.expiryDate = ?2 where cd.cardNumber = ?1")
	int updateExistingCardAdmin(String cardNumber, String expiryDate);

	CreditCardDetails findByCardNumber(String cardNumber);
	
	
	
	
}
