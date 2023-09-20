package com.spring.biz.memberProfile;

import org.springframework.web.multipart.MultipartFile;

public class MemberProfileVO {
	
	private int profileNum;		// 프로필 PK
	private String memberID;	// 회원 아이디 FK
	private String profileImg;	// 프로필 이미지
	private String shortIntro;	// 짧은 소개글
	private String intro;		// 정식 소개글
	private int prohibitCnt;		// 신고당한 횟수
	private int recommendCnt;		// 신고당한 횟수
	
	private String searchCondition;	// 서치컨디션
	private String nickName;	// 닉네임
	private String address;		// 주소
	
	private MultipartFile profileImgUpload;
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	// 회원프로필 PK를 반환하는 메서드
	public int getProfileNum() {
		return profileNum;
	}
	// 회원프로필 PK를 설정하는 메서드
	public void setProfileNum(int profileNum) {
		this.profileNum = profileNum;
	}

	// 회원 아이디를 반환하는 메서드
	public String getMemberID() {
		return memberID;
	}
	// 회원아이디를 설정하는 메서드
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	
	// 프로필 이미지를 반환하는 메서드
	public String getProfileImg() {
		return profileImg;
	}
	// 프로필 이미지를 설정하는 메서드
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	
	// 짧은 소개글을 반환하는 메서드
	public String getShortIntro() {
		return shortIntro;
	}
	// 짧은 소개글을 설정하는 메서드
	public void setShortIntro(String shortIntro) {
		this.shortIntro = shortIntro;
	}

	// 정식 소개글을 반환하는 메서드
	public String getIntro() {
		return intro;
	}
	// 정식 소개글을 설정하는 메서드
	public void setIntro(String intro) {
		this.intro = intro;
	}

	// 신고횟수를 반환하는 메서드
	public int getProhibitCnt() {
		return prohibitCnt;
	}
	// 신고횟수를 설정하는 메서드
	public void setProhibitCnt(int prohibitCnt) {
		this.prohibitCnt = prohibitCnt;
	}
	
	// 서치컨디션을 반환하는 메서드
	public String getSearchCondition() {
		return searchCondition;
	}
	// 서치컨디션을 설정하는 메서드
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	
	// 닉네임을 반환하는 메서드
	public String getNickName() {
		return nickName;
	}
	// 닉네임을 설정하는 메서드
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	// 추천횟수를 반환하는 메서드
	public int getRecommendCnt() {
		return recommendCnt;
	}
	// 추천횟수를 설정하는 메서드
	public void setRecommendCnt(int recommendCnt) {
		this.recommendCnt = recommendCnt;
	}

	public MultipartFile getProfileImgUpload() {
		return profileImgUpload;
	}

	public void setProfileImgUpload(MultipartFile profileImgUpload) {
		this.profileImgUpload = profileImgUpload;
	}
	
	
	
	
	
	
	
}