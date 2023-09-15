package com.spring.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Admin {

	@RequestMapping(value = "/memberListPage.do")
	public String memberListPage() {
		System.out.println("로그: Admin: memberListPage() ");
		
		return "memberListPage.jsp";
	}
	
	@RequestMapping(value = "/adminListPage.do")
	public String adminListPage() {
		System.out.println("로그: Admin: adminLisPage() ");
		
		return "adminListPage.jsp";
	}
	
}
