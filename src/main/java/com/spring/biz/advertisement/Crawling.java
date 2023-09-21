package com.spring.biz.advertisement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//멤버변수없이 메서드만 존재하는 클래스
//: 메서드 실행이 목적
public class Crawling {

	// hdex 상품 크롤링
	public static List<AdvertisementVO> crawlingHdex() {

		// 1. 웹 페이지 주소 저장
		final String url = "https://m.hdex.co.kr/hd/best.html";

		// a) 스트림(연결통로) 만들기
		Connection conn = Jsoup.connect(url);

		// b) get()를 통해 해당 페이지의 코드를 받아오기
		Document doc = null;
		try {
			doc = conn.get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 2. 페이지 스크랩핑 완료 확인
//		System.out.println(doc); // 확인 완료

		// 3. 페이지 코드 분석 <- 이건 크롬에서 F12를 통해 하는게 유리함 (1차 가공)
		Elements elemsImg = doc.select("div.thumbnail > a > img"); // 상품 이미지
		Elements elemsTitle = doc.select("strong > a"); // 상품명
		Elements elemsPay = doc.select("li.price"); // 상품가격

		// 1차 가공 완료 확인
//		System.out.println(elemsImg);
//		System.out.println(elemsTitle);
//		System.out.println(elemsPay);

		// 4. 긁어와서 1차가공한 정보를 각각 분류
		// a) 각각 분류
		Iterator<Element> itrImg = elemsImg.iterator();
		Iterator<Element> itrTitle = elemsTitle.iterator();
		Iterator<Element> itrPay = elemsPay.iterator();

		// b) 가공
		List<AdvertisementVO> adatas = new ArrayList<AdvertisementVO>();

		// b) 가공
		while (true) {
			// 가공준비
			String strImg;
			String strTitle;
			String strPay;

			// 예외 처리 - 입력할 값이 더이상 없을 경우
			try {
				strImg = itrImg.next().toString();
				strTitle = itrTitle.next().toString();
				strPay = itrPay.next().toString();
				itrPay.next().toString();
			} catch (Exception e) {
				break; // 종료
			}

			// 가공
			// 1차 가공용 범위지정 (앞에 지우기)
			int indexImg = strImg.indexOf("src");
			int indexTitle = strTitle.indexOf(">");
			int indexPay = strPay.indexOf(">");

			// 1차 가공
			strImg = strImg.substring(indexImg + 5);
			strTitle = strTitle.substring(indexTitle + 1);
			strPay = strPay.substring(indexPay + 1);

			// 2차 가공용 범위 지정 (뒤에 지우기)
			indexImg = strImg.indexOf(" ");
			indexTitle = strTitle.indexOf("<");
			indexPay = strPay.indexOf("<");

			// 2차 가공
			strImg = strImg.substring(0, indexImg - 1);
			strTitle = strTitle.substring(0, indexTitle);
			strPay = strPay.substring(0, indexPay - 1);

			// 완성된 정보 출력
//			System.out.println("상품이미지: " + strImg);
//			System.out.println("상품명: " + strTitle);
//			System.out.println("가격: " + strPay);

			AdvertisementVO adata = new AdvertisementVO();

			adata.setSite(2);
			adata.setSiteUrl(url);
			adata.setItemImg(strImg);
			adata.setItem(strTitle);
			adata.setItemPay(strPay);

			adatas.add(adata);

		} // while
//		System.out.println(adatas.size());
		// 목록 반환
		return adatas;
	}

	// 제로투히어로 상품 크롤링
	public static List<AdvertisementVO> crawlingZerotohero() {

		// 1. 웹 페이지 주소 저장
		final String url = "https://zerotohero.co.kr/shop";

		// a) 스트림(연결통로) 만들기
		Connection conn = Jsoup.connect(url);

		// b) get()를 통해 해당 페이지의 코드를 받아오기
		Document doc = null;
		try {
			doc = conn.get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 2. 페이지 스크랩핑 완료 확인
//		System.out.println(doc); // 확인 완료

		// 3. 페이지 코드 분석 <- 이건 크롬에서 F12를 통해 하는게 유리함 (1차 가공)
		Elements elemsImg = doc.select("a > img._org_img"); // 상품 이미지
		Elements elemsTitle = doc.select("h2 > a"); // 상품명
		Elements elemsPay = doc.select("div.item-pay-detail > p.pay"); // 상품가격

		// 1차 가공 완료 확인
//		System.out.println(elemsImg);
//		System.out.println(elemsTitle);
//		System.out.println(elemsPay);

		// 4. 긁어와서 1차가공한 정보를 각각 분류
		// a) 각각 분류
		Iterator<Element> itrImg = elemsImg.iterator();
		Iterator<Element> itrTitle = elemsTitle.iterator();
		Iterator<Element> itrPay = elemsPay.iterator();

		// 저장할 공간 생성
		List<AdvertisementVO> adatas = new ArrayList<AdvertisementVO>();

		// b) 가공
		while (true) {
			// 가공준비
			String strImg;
			String strTitle;
			String strPay;

			// 예외 처리 - 입력할 값이 더이상 없을 경우
			try {
				strImg = itrImg.next().toString();
				strTitle = itrTitle.next().toString();
				strPay = itrPay.next().toString();
			} catch (Exception e) {
				break; // 종료
			}

			// 가공
			// 1차 가공용 범위지정 (앞에 지우기)
			int indexImg = strImg.indexOf("original");
			int indexTitle = strTitle.indexOf(">");
			int indexPay = strPay.indexOf(">");

			// 1차 가공
			strImg = strImg.substring(indexImg + 10);
			strTitle = strTitle.substring(indexTitle + 1);
			strPay = strPay.substring(indexPay + 1);

			// 2차 가공용 범위 지정 (뒤에 지우기)
			indexImg = strImg.indexOf(" ");
			indexTitle = strTitle.indexOf("<");
			indexPay = strPay.indexOf("<");

			// 2차 가공
			strImg = strImg.substring(0, indexImg - 1);
			strTitle = strTitle.substring(0, indexTitle);
			strPay = strPay.substring(0, indexPay);

			strTitle = strTitle.replace("&amp;", " ");
			
			// 완성된 정보 출력
//			System.out.println("상품이미지: " + strImg);
//			System.out.println("상품명: " + strTitle);
//			System.out.println("가격: " + strPay);

			// 저장할 객체 생성
			AdvertisementVO adata = new AdvertisementVO();

			// 객체에 정보 저장
			adata.setSite(1);
			adata.setSiteUrl(url);
			adata.setItemImg(strImg);
			adata.setItem(strTitle);
			adata.setItemPay(strPay);

			// 완성한 객체를 목록에 추가
			adatas.add(adata);

		} // while
			// 목록 반환
		return adatas;
	}
}
