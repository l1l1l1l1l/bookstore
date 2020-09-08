package com.example.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class BookController {


	@Controller
	public class MessageController {

		@RequestMapping(value = "/index", method = RequestMethod.GET)
		public String greetingForm(Model model) {
			//model.addAttribute("message", new Message());
			return "index";
		}

		/*@RequestMapping(value = "/hello", method = RequestMethod.POST)
		public String greetingSubmit(@ModelAttribute Message msg, Model model) {
			model.addAttribute("message", msg);
			return "result";
		} */
	}

}
