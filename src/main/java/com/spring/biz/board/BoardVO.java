package com.spring.biz.board;

import java.util.ArrayList;
import java.util.List;

public class BoardVO {
    
    private int boardNum;			// 게시글 번호
    private String title; 			// 게시글 제목
    private String content; 		// 게시글 내용
    private String memberID; 		// 작성자 아이디
    private int prohibitCnt; 		// 게시글의 금지 횟수
    private int recommendCnt; 		// 게시글의 추천 횟수
    private int category; 			// 게시글의 카테고리
    private String boardDate;	 	// 게시글 작성일
    private String boardImg;	 	// 게시글 이미지
    private int viewCnt;			// 게시글 조회수

    // 서치 컨디션 (검색 조건)
    private String searchCondition;	// 서치컨디션
    private String nickName; 		// 작성자 닉네임
    private int boardCommentsCnt;	// 해당 게시글의 댓글수
    private List<String> boardImgList=new ArrayList<String>();	// 게시물 사진 리스트
    
    // 게시글 번호를 반환하는 메서드
    public int getBoardNum() {
        return boardNum;
    }
    
    // 게시글 번호를 설정하는 메서드
    public void setBoardNum(int boardNum) {
        this.boardNum = boardNum;
    }
    
    // 게시글 제목을 반환하는 메서드
    public String getTitle() {
        return title;
    }
    
    // 게시글 제목을 설정하는 메서드
    public void setTitle(String title) {
        this.title = title;
    }
    
    // 게시글 내용을 반환하는 메서드
    public String getContent() {
        return content;
    }
    
    // 게시글 내용을 설정하는 메서드
    public void setContent(String content) {
        this.content = content;
    }
   
    // 작성자 아이디를 반환하는 메서드
    public String getMemberID() {
        return memberID;
    }
    
    // 작성자 아이디를 설정하는 메서드
    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }
    
    // 게시글의 추천 횟수를 반환하는 메서드
    public int getRecommendCnt() {
        return recommendCnt;
    }
    
    // 게시글의 추천 횟수를 설정하는 메서드
    public void setRecommendCnt(int recommendCnt) {
        this.recommendCnt = recommendCnt;
    }
    
    // 게시글의 금지 횟수를 반환하는 메서드
    public int getProhibitCnt() {
        return prohibitCnt;
    }
    
    // 게시글의 금지 횟수를 설정하는 메서드
    public void setProhibitCnt(int prohibitCnt) {
        this.prohibitCnt = prohibitCnt;
    }
    
    // 게시글의 카테고리를 반환하는 메서드
    public int getCategory() {
        return category;
    }
    
    // 게시글의 카테고리를 설정하는 메서드
    public void setCategory(int category) {
        this.category = category;
    }
    
    // 게시글 작성일을 반환하는 메서드
        public String getBoardDate() {
		return boardDate;
	}

    // 게시글 작성일을 설정하는 메서드
    	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}
    	
    // 서치 컨디션(검색 조건)을 반환하는 메서드
    	public String getSearchCondition() {
		return searchCondition;
	}
    	
    // 서치 컨디션(검색 조건)을 설정하는 메서드
    	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
    	
    // 작성자 닉네임을 반환하는 메서드
    public String getNickName() {
        return nickName;
    }
    
    // 게시글 이미지를 반환하는 메서드
	public String getBoardImg() {
		return boardImg;
	}

	// 게시글 이미지를 설정하는 메서드
	public void setBoardImg(String boardImg) {
		this.boardImg = boardImg;
	}

	// 작성자 닉네임을 설정하는 메서드
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    // 조회수를 반환하는 메서드
	public int getViewCnt() {
		return viewCnt;
	}

	// 조회수를 설정하는 메서드
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	// 해당 게시글의 댓글수를 반환하는 메서드
	public int getBoardCommentsCnt() {
		return boardCommentsCnt;
	}

	// 해당 게시글의 댓글수를 설정하는 메서드
	public void setBoardCommentsCnt(int boardCommentsCnt) {
		this.boardCommentsCnt = boardCommentsCnt;
	}
	
	// 게시물 이미지 리스트를 반환하는 메서드
	public List<String> getBoardImgList() {
		return boardImgList;
	}

	// 게시물 이미지 리스트를 설정하는 메서드
	public void setBoardImgList(List<String> boardImgList) {
		this.boardImgList = boardImgList;
	}
	
	
	   
}
