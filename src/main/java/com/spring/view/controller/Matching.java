package com.spring.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Matching {

	@RequestMapping(value = "/matchingPage.do")
	public String matchingPage() {
		System.out.println("로그: Matching: matchingPage() ");
		
		
		return "matchingPage.jsp";
	}
	
	@RequestMapping(value = "/profileDetailPage.do")
	public String profileListPage() {
		System.out.println("로그: Matching: profileDetailPage() ");
	
		return "profileDetailPage.jsp";
	}
}
