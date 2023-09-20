package com.spring.biz.comments;


public class CommentsVO {
    
    private int commentsNum; 		// 댓글 번호를 저장하는 멤버 변수
    private String comments;  		// 댓글 내용을 저장하는 멤버 변수
    private int boardNum; 			// 댓글이 속한 게시글 번호를 저장하는 멤버 변수
    private String memberID;				// 댓글 작성자 아이디를 저장하는 멤버 변수
    private int prohibitCnt;		// 댓글에 대한 신고 횟수를 저장하는 멤버 변수
    private String commentsDate;		// 댓글에 대한 신고 횟수를 저장하는 멤버 변수
    
    // 서치 컨디션 (검색 조건)
    private String searchCondition;			// 검색 조건을 저장하는 멤버 변수
    private String nickName;		// 작성자 닉네임을 저장하는 멤버 변수
    private int check;
    
    public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}

	// 댓글 번호를 반환하는 메서드
    public int getCommentsNum() {
        return commentsNum;
    }
    
    // 댓글 번호를 설정하는 메서드
    public void setCommentsNum(int commentsNum) {
        this.commentsNum = commentsNum;
    }
    
    // 댓글 내용을 반환하는 메서드
    public String getComments() {
        return comments;
    }
    
    // 댓글 내용을 설정하는 메서드
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    // 댓글이 속한 게시글 번호를 반환하는 메서드
    public int getBoardNum() {
        return boardNum;
    }
    
    // 댓글이 속한 게시글 번호를 설정하는 메서드
    public void setBoardNum(int boardNum) {
        this.boardNum = boardNum;
    }
    
    // 댓글 작성자 아이디를 반환하는 메서드
    public String getMemberID() {
        return memberID;
    }
    
    // 댓글 작성자 아이디를 설정하는 메서드
    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }
    
    // 댓글에 대한 신고 횟수를 반환하는 메서드
    public int getProhibitCnt() {
        return prohibitCnt;
    }
    
    // 댓글에 대한 신고 횟수를 설정하는 메서드
    public void setProhibitCnt(int prohibitCnt) {
        this.prohibitCnt = prohibitCnt;
    }
    
    // 검색 조건을 반환하는 메서드
    public String getSearchCondition() {
        return searchCondition;
    }
    
    // 검색 조건을 설정하는 메서드
    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }
    
    // 작성자 닉네임을 반환하는 메서드
    public String getNickName() {
        return nickName;
    }
    
    // 작성자 닉네임을 설정하는 메서드
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    // 댓글 작성시간을 반환하는 메서드
	public String getCommentsDate() {
		return commentsDate;
	}

	// 댓글 작성시간을 설정하는 메서드
	public void setCommentsDate(String commentsDate) {
		this.commentsDate = commentsDate;
	}
    
    
}
