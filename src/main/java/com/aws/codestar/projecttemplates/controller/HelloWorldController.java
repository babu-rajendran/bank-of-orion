package com.aws.codestar.projecttemplates.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Basic Spring MVC controller that handles all GET requests.
 */
@Controller
@RequestMapping("/")
public class HelloWorldController {

	private final String siteName;

	public HelloWorldController(final String siteName) {
		this.siteName = siteName;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("siteName", this.siteName);
		return mav;
	}

	@RequestMapping(value = "/signInPage", method = RequestMethod.GET)
	public ModelAndView signInPage() {
		ModelAndView mav = new ModelAndView("signIn");
		return mav;
	}

	@RequestMapping(value = "/signUpPage", method = RequestMethod.GET)
	public ModelAndView signUpPage() {
		ModelAndView mav = new ModelAndView("signUp");
		return mav;
	}

}
