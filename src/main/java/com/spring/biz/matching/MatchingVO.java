package com.spring.biz.matching;

public class MatchingVO {

	private int matchingNum;	// 매칭 번호
	private String senderID;	// 신청한 회원 아이디
	private String receiverID;	// 받는 회원 아이디
	private int accept;			// 수락여부
	
	// 서치컨디션(테이블에는 존재하지않는 칼럼이지만, JAVA 로직에서 사용하기위해 VO에 별도로 생성한 멤버변수)
	private String senderNickName;
	private String receiverNickName;
	private String profileImg;
	private String shortIntro;
	private String SearchCondition;
	private int profileNum;
	
	// 매칭 번호를 반환하는 메서드
	public int getMatchingNum() {
		return matchingNum;
	}
	
	// 매칭 번호를 설정하는 메서드
	public void setMatchingNum(int matchingNum) {
		this.matchingNum = matchingNum;
	}
	
	// 신청한 회원 아이디를 반환하는 메서드
	public String getSenderID() {
		return senderID;
	}
	
	// 신청한 회원 아이디를 설정하는 메서드
	public void setSenderID(String senderID) {
		this.senderID = senderID;
	}
	
	// 받는 회원 아이디를 반환하는 메서드
	public String getReceiverID() {
		return receiverID;
	}
	
	// 받는 회원 아이디를 설정하는 메서드
	public void setReceiverID(String receiverID) {
		this.receiverID = receiverID;
	}
	
	// 확인여부를 반환하는 메서드
	public int getAccept() {
		return accept;
	}
	
	// 확인여부를 설정하는 메서드
	public void setAccept(int accept) {
		this.accept = accept;
	}

	// 보낸 회원의 닉네임을 반환하는 메서드
	public String getSenderNickName() {
		return senderNickName;
	}

	// 보낸 회원의 닉네임을 설정하는 메서드
	public void setSenderNickName(String senderNickName) {
		this.senderNickName = senderNickName;
	}

	// 받은 회원의 닉네임을 반환하는 메서드
	public String getReceiverNickName() {
		return receiverNickName;
	}

	// 받은 회원의 닉네임을 설정하는 메서드
	public void setReceiverNickName(String receiverNickName) {
		this.receiverNickName = receiverNickName;
	}

	// 조건을 반환하는 메서드
	public String getSearchCondition() {
		return SearchCondition;
	}

	// 조건을 설정하는 메서드
	public void setSearchCondition(String searchCondition) {
		SearchCondition = searchCondition;
	}

	// 보낸 회원 혹은 받는 회원의 프로필이미지를 반환하는 메서드
	public String getProfileImg() {
		return profileImg;
	}

	// 보낸 회원 혹은 받는 회원의 프로필이미지를 설정하는 메서드
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	// 보낸 회원 혹은 받는 회원의 짧은 소개글을 반환하는 메서드
	public String getShortIntro() {
		return shortIntro;
	}

	// 보낸 회원 혹은 받는 회원의 짧은 소개글을 설정하는 메서드
	public void setShortIntro(String shortIntro) {
		this.shortIntro = shortIntro;
	}

	public int getProfileNum() {
		return profileNum;
	}

	public void setProfileNum(int profileNum) {
		this.profileNum = profileNum;
	}
	
	
	
	
	
}
