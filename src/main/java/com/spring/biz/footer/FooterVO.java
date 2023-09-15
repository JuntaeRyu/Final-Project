package com.spring.biz.footer;

public class FooterVO {

	private String companyName;		// 회사명
	private String companyCEO;		// 대표자
	private String companyEmail;	// 회사 이메일
	private String companyPhoneNum;	// 회사 전화
	private String companyInfo;		// 회사 소개글
	
	// 회사명을 반환하는 메서드
	public String getCompanyName() {
		return companyName;
	}
	
	// 회사명을 설정하는 메서드
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	// 대표자를 반환하는 메서드
	public String getCompanyCEO() {
		return companyCEO;
	}
	
	// 대표자를 설정하는 메서드
	public void setCompanyCEO(String companyCEO) {
		this.companyCEO = companyCEO;
	}
	
	// 회사 이메일을 반환하는 메서드
	public String getCompanyEmail() {
		return companyEmail;
	}
	
	// 회사 이메일을 설정하는 메서드
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}
	
	// 회사 전화를 반환하는 메서드
	public String getCompanyPhoneNum() {
		return companyPhoneNum;
	}
	
	// 회사 전화를 설정하는 메서드
	public void setCompanyPhoneNum(String companyPhoneNum) {
		this.companyPhoneNum = companyPhoneNum;
	}
	
	// 회사 소개글을 반환하는 메서드
	public String getCompanyInfo() {
		return companyInfo;
	}
	
	// 회사 소개글을 설정하는 메서드
	public void setCompanyInfo(String companyInfo) {
		this.companyInfo = companyInfo;
	}
}
