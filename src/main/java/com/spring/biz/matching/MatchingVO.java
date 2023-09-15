package com.spring.biz.matching;

public class MatchingVO {

	private int matchingNum;	// 매칭 번호
	private String senderID;	// 신청한 회원 아이디
	private String receiverID;	// 받는 회원 아이디
	private int accept;			// 수락여부
	
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
}
