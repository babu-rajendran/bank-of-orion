package com.aws.codestar.projecttemplates.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView customerView() {
        ModelAndView mav = new ModelAndView("customer");
        //mav.addObject("siteName", this.siteName);
        return mav;
    }

}
