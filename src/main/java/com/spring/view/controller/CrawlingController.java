package com.spring.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.advertisement.AdvertisementService;
import com.spring.biz.advertisement.AdvertisementVO;
import com.spring.biz.advertisement.Crawling;

@Controller
public class CrawlingController {

	@Autowired
	private AdvertisementService advertisementService;
	
	@RequestMapping(value = "/crawling.do")
	public String crawling (Model model) {
		
		List<AdvertisementVO> hdex = Crawling.crawlingHdex();
		List<AdvertisementVO> zerotohero = Crawling.crawlingZerotohero();
		
		boolean flag = false; // 오류 확인용
		
		for (AdvertisementVO data : hdex) {
			if (!advertisementService.insert(data)) {
				flag = true;
			}
		}
		
		for (AdvertisementVO data : zerotohero) {
			if(!advertisementService.insert(data)) {
				flag = true;
			}
		}
		
		if (flag) {
			model.addAttribute("title", "광고 저장 실패!");
			model.addAttribute("text", "프로그램을 중지하고 DB를 확인해주세요");
			model.addAttribute("icon", "error");
			model.addAttribute("url", "#");
		}
		else {
			model.addAttribute("title", "크롤링 성공!");
			model.addAttribute("text", "프로그램을 실행합니다");
			model.addAttribute("icon", "success");
			model.addAttribute("url", "main.do");
		}
		
		return "SweetAlert2.jsp";
	}
	
	@RequestMapping(value = "/reset.do")
	public String reset (Model model) {
		
		if (!advertisementService.reset(null)) {
			System.out.println("로그: 오류났으니 일단 프로그램 정지");
			
			model.addAttribute("title", "광고 테이블 리셋 실패!");
			model.addAttribute("text", "광고 테이블 생성에 실패했습니다<br>DB를 확인해주세요");
			model.addAttribute("icon", "error");
			model.addAttribute("url", "#");
			
			return "SweetAlert2.jsp";
		}
		
		return "redirect:crawling.do";
	}
	
}
