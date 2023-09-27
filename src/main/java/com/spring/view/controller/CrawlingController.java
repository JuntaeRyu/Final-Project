package com.spring.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
			System.out.println("로그: 광고 저장 오류");
			System.out.println("로그: 프로그램을 종료하고 DB를 확인해주세요");
		}
		
//		List<AdvertisementVO> adatas = advertisementService.selectAll(null);
//		for (AdvertisementVO adata : adatas) {
//			System.out.println(adata.getSite());
//			System.out.println(adata.getItem());
//			System.out.println(adata.getSiteUrl());
//		}
		
		model.addAttribute("adatas", advertisementService.selectAll(null));
		
		return "main.do";
	}
	
	@RequestMapping(value = "/reset.do")
	public String reset () {
		
		if (!advertisementService.reset(null)) {
			System.out.println("로그: 오류났으니 일단 프로그램 정지");
			return "#";
		}
		
		return "redirect:crawling.do";
	}
	
}
