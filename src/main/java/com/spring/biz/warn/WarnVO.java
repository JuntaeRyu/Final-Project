package com.spring.biz.warn;

public class WarnVO {

	private int warnNum;		// 경고 번호
	private String memberID;	// 경고받은 회원 아이디
	private int warnType;		// 경고 타입(이유)
	private String warnDate;	// 경고 날짜
	
	// 서치컨디션
	private String SearchCondition;
	
	
	// 경고 번호를 반환하는 메서드
	public int getWarnNum() {
		return warnNum;
	}
	
	// 경고 번호를 설정하는 메서드
	public void setWarnNum(int warnNum) {
		this.warnNum = warnNum;
	}
	
	// 경고받은 회원 아이디를 반환하는 메서드
	public String getMemberID() {
		return memberID;
	}
	
	// 경고받은 회원 아이디를 설정하는 메서드
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	
	// 경고 타입(이유)를 반환하는 메서드
	public int getWarnType() {
		return warnType;
	}
	
	// 경고 타입(이유)를 설정하는 메서드
	public void setWarnType(int warnType) {
		this.warnType = warnType;
	}
	
	// 경고 날짜를 반환하는 메서드
	public String getWarnDate() {
		return warnDate;
	}
	
	// 경고 날짜를 설정하는 메서드
	public void setWarnDate(String warnDate) {
		this.warnDate = warnDate;
	}

	// 서치컨디션을 반환하는 메서드
	public String getSearchCondition() {
		return SearchCondition;
	}
	
	// 서치컨디션을 설정하는 메서드
	public void setSearchCondition(String searchCondition) {
		SearchCondition = searchCondition;
	}
	
}
