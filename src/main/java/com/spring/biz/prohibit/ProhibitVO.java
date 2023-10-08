package com.spring.biz.prohibit;

public class ProhibitVO {
	private int prohibitNum; // 금지어 데이터의 고유 번호를 저장하는 멤버 변수
	private String memberID; // 금지어를 등록한 회원의 아이디를 저장하는 멤버 변수
	private int commonNum; // 해당 금지어가 적용된 게시글, 댓글 또는 대댓글의 PK(Primary Key)를 저장하는 멤버 변수

	private String searchCondition;
	
	// 금지어 데이터의 고유 번호를 반환하는 메서드
	public int getProhibitNum() {
		return prohibitNum;
	}

	// 금지어 데이터의 고유 번호를 설정하는 메서드
	public void setProhibitNum(int prohibitNum) {
		this.prohibitNum = prohibitNum;
	}

	// 금지어를 등록한 회원의 아이디를 반환하는 메서드
	public String getMemberID() {
		return memberID;
	}

	// 금지어를 등록한 회원의 아이디를 설정하는 메서드
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public int getCommonNum() {
		return commonNum;
	}

	public void setCommonNum(int commonNum) {
		this.commonNum = commonNum;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	
	
}
