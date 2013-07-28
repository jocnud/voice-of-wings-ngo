package com.ngo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ngo.bean.EmailBean;
import com.ngo.domain.Email;
import com.ngo.service.EmailService;

/**
 * 
 * @author shabby
 * 
 */
@Controller
@RequestMapping("/email")
@SessionAttributes("emailFormBean")
public class EmailController {

	@Autowired(required = true)
	private EmailService emailService;

	/**
	 * 
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "createEmail", method = RequestMethod.GET)
	public String home(final HttpServletRequest request,
			final HttpSession session, final ModelMap model) {

		System.out.println("entering controller");
		model.addAttribute("emailFormBean", new EmailBean());
		return "createEmail";
	}

	@RequestMapping(value = "saveEmail", method = RequestMethod.POST, params = { "action" })
	public String saveExpense(@RequestParam("action") final String action,
			@ModelAttribute("expenseFormBean") EmailBean emailFormBean,
			final ModelMap model, final HttpServletRequest request,
			final HttpSession session) {

		Email email = new Email();

		DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
		Date date = new Date();
		System.out.println(dateFormat.format(date));



		email.setToAddr(emailFormBean.getToAddr());
		email.setSubject(emailFormBean.getSubject());
		email.setBody(emailFormBean.getBody());
		email.setEmailStatus("PTS");

		emailService.save(email);

		/*
		 * Blog blog = new Blog(); blog.setBlg_name("testing");
		 * blogService.save(blog);
		 */

		model.addAttribute("expenseFormBean", new EmailBean());

		model.addAttribute("success", true);

		return "createEmail";
	}

}
