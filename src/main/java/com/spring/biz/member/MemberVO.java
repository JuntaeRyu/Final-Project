package com.spring.biz.member;

public class MemberVO {
	private String memberID;    // 회원 아이디를 저장하는 멤버 변수
    private String memberPW;    // 회원 비밀번호를 저장하는 멤버 변수
    private String name;      	// 회원 이름을 저장하는 멤버 변수
    private String nickName;  	// 회원 닉네임을 저장하는 멤버 변수
    private String email;     	// 회원 이메일을 저장하는 멤버 변수
    private String phoneNum;   	// 회원의 전화보호를 저장하는 멤버변수
    private int	gender;   		// 회원의 성별을 저장하는 멤버변수
    private String address;   	// 회원의 주소를 저장하는 멤버변수
    private String detailAddress;   // 회원의 상세주소를 저장하는 멤버변수
    private int warnCnt;    	// 경고횟수를 저장하는 멤버 변수
    private int role;   		// 회원의 권한을 저장하는 멤버변수
    private String suspensionMember;
    // 서치컨디션
    private String searchCondition;    	// 검색 조건을 저장하는 멤버 변수
	private String searchType;		// 검색종류를 저장하는 멤버변수
	private String searchText;		// 검색단어를 저장하는 멤버변수
	
    
    // 검색 조건을 반환하는 메서드
	public String getSearchCondition() {
		return searchCondition;
	}
	
	// 검색 조건을 설정하는 메서드
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	
	// 회원 아이디를 반환하는 메서드
	public String getMemberID() {
		return memberID;
	}
	
	// 회원 아이디를 설정하는 메서드
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	
	// 회원 비밀번호를 반환하는 메서드
	public String getMemberPW() {
		return memberPW;
	}
	
	// 회원 비밀번호를 설정하는 메서드
	public void setMemberPW(String memberPW) {
		this.memberPW = memberPW;
	}
	
	//
	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	// 회원 이름을 반환하는 메서드
	public String getName() {
		return name;
	}
	
	// 회원 이름을 설정하는 메서드
	public void setName(String name) {
		this.name = name;
	}
	
	// 회원 닉네임을 반환하는 메서드
	public String getNickName() {
		return nickName;
	}
	
	// 회원 닉네임을 설정하는 메서드
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	// 회원 이메일을 반환하는 메서드
	public String getEmail() {
		return email;
	}
	
	// 회원 이메일을 설정하는 메서드
	public void setEmail(String email) {
		this.email = email;
	}

	// 회원 성별을 반환하는 메서드
	public int getGender() {
		return gender;
	}

	// 회원 성별을 설정하는 메서드
	public void setGender(int gender) {
		this.gender = gender;
	}

	// 회원 전화번호를 반환하는 메서드
	public String getPhoneNum() {
		return phoneNum;
	}

	// 회원 전화번호를 설정하는 메서드
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	// 회원 주소를 반환하는 메서드
	public String getAddress() {
		return address;
	}

	// 회원 주소를 설정하는 메서드
	public void setAddress(String address) {
		this.address = address;
	}
	
	// 회원의 상세주소를 반환하는 메서드
	public String getDetailAddress() {
		return detailAddress;
	}

	// 회원의 상세주소를 설정하는 메서드
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	// 회원 권한을 반환하는 메서드
	public int getRole() {
		return role;
	}

	// 회원 권한을 설정하는 메서드
	public void setRole(int role) {
		this.role = role;
	}

	// 회원 경고횟수를 반환하는 메서드
	public int getWarnCnt() {
		return warnCnt;
	}

	// 회원 경고 회수를 설정하는 메서드
	public void setWarnCnt(int warnCnt) {
		this.warnCnt = warnCnt;
	}

	public String getSuspensionMember() {
		return suspensionMember;
	}

	public void setSuspensionMember(String suspensionMember) {
		this.suspensionMember = suspensionMember;
	}
	
	
	
}
