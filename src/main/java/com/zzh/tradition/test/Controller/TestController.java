package com.zzh.tradition.test.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TestController {
	@RequestMapping("/test")
	public String test(HttpServletRequest request,ModelMap modelMap){
		
		return "tests";
	}
	
	@RequestMapping("/testfm")
	public String testfm(HttpServletRequest request,ModelMap modelMap){
		
		return "test";
	}
}
