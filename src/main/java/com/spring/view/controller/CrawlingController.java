package com.spring.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.advertisement.AdvertisementService;
import com.spring.biz.advertisement.AdvertisementVO;
import com.spring.biz.advertisement.Crawring;

@Controller
public class CrawlingController {

	@Autowired
	private AdvertisementService advertisementService;
	
	@RequestMapping(value = "/crawring.do")
	public String crawring () {
		
		List<AdvertisementVO> hdex = Crawring.crawlingHdex();
		List<AdvertisementVO> zerotohero = Crawring.crawlingZerotohero();
		
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
		
		return "main.do";
	}
	
}
