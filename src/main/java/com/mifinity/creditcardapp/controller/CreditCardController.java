package com.mifinity.creditcardapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mifinity.creditcardapp.ApplicationUtil;
import com.mifinity.creditcardapp.model.CreditCardDetails;
import com.mifinity.creditcardapp.service.CreditCardService;
import com.mifinity.creditcardapp.validate.CardValidator;
/**
 * The CreditCardController class contains methods 
 * which  handles the HTTP request and response related to functionality of application.
 * @author Akarsh Jain
 * @version 1.0
 */
@Controller
public class CreditCardController {

	@Autowired
	private CardValidator cardValidator;

	@Autowired
	private CreditCardService creditCardService;

	/**
	 * This method displays the credit card options 
	 * 
	 * @return creditCardFunctions page
	 */
	@GetMapping({ "/creditCardFunctions" })
	public String creditCardFunction(Model model) {
		return "creditCardFunctions";
	}

	/**
	 * This method displays the form to create a new credit card
	 * 
	 * @return createCreditCard page
	 */
	@GetMapping("/createCard")
	public String createNewCard(Model model) {
		model.addAttribute("cardDetailsForm", new CreditCardDetails());
		return "createCreditCard";
	}

	/**
	 * This method stores the form that contain details to create a new credit card
	 * @param loggedUser contains loggged user name
	 * @cardDetailsForm is CreditCardDetails object that contains new card details 
	 * @return createCreditCard page with suitable message after validations
	 */
	@PostMapping("/createCard")
	public ModelAndView createNewCard(@RequestParam("username") String loggedUser,
			@ModelAttribute("cardDetailsForm") CreditCardDetails cardDetailsForm, BindingResult bindingResult) {
		cardValidator.validateNew(cardDetailsForm, bindingResult);
		ModelAndView mv;
		if (bindingResult.hasErrors()) {
			mv = new ModelAndView("createCreditCard");
			return mv;
		}

		boolean flag = creditCardService.addNewCard(loggedUser, cardDetailsForm);
		mv = new ModelAndView("createCreditCard");
		mv.addObject("flag", flag);
		return mv;
	}

	/**
	 * This method displays the form to search an existing credit card
	 * 
	 * @return searchCreditCard page
	 */
	@GetMapping("/searchCard")
	public String searchCreditCard() {
		return "searchCreditCard";
	}

	/**
	 * This method search card in database of entered credit card number
	 * @param loggedUser contains logged user name
	 * @param creditCardNumber contains the searched card number
	 * @param model
	 * @return updateCreditCard page after validations
	 */
	@PostMapping("/searchCard")
	public String searchCreditCard(@RequestParam("username") String loggedUser,
			@RequestParam("creditCardNumber") String creditCardNumber, Model model) {
		if (!StringUtils.isEmpty(loggedUser) && !StringUtils.isEmpty(creditCardNumber)) {
			List<CreditCardDetails> creditCardList = creditCardService.searchCreditCard(loggedUser, creditCardNumber);
			if (creditCardList != null && !creditCardList.isEmpty()) {
				model.addAttribute("creditCardList", creditCardList);
				model.addAttribute("updateForm", new CreditCardDetails());
				return "updateCreditCard";
			}
		}
		return "error";
	}
	/**
	 * This method update card details of entered credit card number
	 * @param loggedUser contains logged user name
	 * @param updateForm contains object of CreditCardDetails
	 * 
	 * @return creditCardFunctions page after validations
	 */
	@PostMapping("/updateCard")
	public ModelAndView updateExistingCard(@RequestParam("username") String loggedUser,
			@ModelAttribute("updateForm") CreditCardDetails updateForm, BindingResult bindingResult) {

		ModelAndView mv;
		String role;
		cardValidator.validateUpdate(updateForm, bindingResult);

		if (bindingResult.hasErrors()) {
			mv = new ModelAndView("updateCreditCard");
		} else {
			role = creditCardService.updateExistingCard(loggedUser, updateForm);
			if (ApplicationUtil.ROLE_USER.equalsIgnoreCase(role)) {
				mv = new ModelAndView("creditCardFunctions");
			} else if (ApplicationUtil.ROLE_ADMIN.equalsIgnoreCase(role)) {
				mv = new ModelAndView("searchCreditCard");
			} else {
				mv = new ModelAndView("error");
				mv.addObject("msg", "Error cannot update the card details");
			}
		}  

		return mv;
	}


}
