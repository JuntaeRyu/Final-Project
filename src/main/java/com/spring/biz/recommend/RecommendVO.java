package com.spring.biz.recommend;

public class RecommendVO {
	private int recommendNum; // 추천 번호 (고유 식별자)를 저장하는 멤버 변수
    private String memberID; // 추천한 회원의 아이디 (Member ID)를 저장하는 멤버 변수
    private int commonNum; // 게시글의 PK (게시글의 고유 번호)를 저장하는 멤버 변수

    private String searchCondition;
    
	// 추천 번호를 반환하는 메서드
	public int getRecommendNum() {
		return recommendNum;
	}

	// 추천 번호를 설정하는 메서드
	public void setRecommendNum(int recommendNum) {
		this.recommendNum = recommendNum;
	}

	// 추천한 회원의 아이디를 반환하는 메서드
	public String getMemberID() {
		return memberID;
	}

	// 추천한 회원의 아이디를 설정하는 메서드
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	// 게시글의 PK를 반환하는 메서드
	public int getCommonNum() {
		return commonNum;
	}

	// 게시글의 PK를 설정하는 메서드
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
