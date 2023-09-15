package com.spring.biz.email;

public class EmailVO {
	
	private String email;	// 이메일 주소를 저장하는 멤버 변수
	private String name;	// 이름을 저장하는 멤버 변수
	
	// 이메일 주소를 반환하는 메서드
	public String getEmail() {
		return email;
	}
	
	// 이메일 주소를 설정하는 메서드
	public void setEmail(String email) {
		this.email = email;
	}
	
	// 이름을 반환하는 메서드
	public String getName() {
		return name;
	}
	
	// 이름을 설정하는 메서드
	public void setName(String name) {
		this.name = name;
	}
}

