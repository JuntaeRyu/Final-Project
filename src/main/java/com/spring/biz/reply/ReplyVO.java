package com.spring.biz.reply;

public class ReplyVO {
    
    private int replyNum;			// 대댓글 번호
    private String reply;			// 대댓글 내용
    private int commentsNum;		// 대댓글이 속한 댓글 그룹(댓글의 원글)의 번호
    private String memberID;		// 대댓글 작성자 아이디
    private int prohibitCnt;		// 대댓글의 신고 횟수
    private String replyDate;		// 대댓글 작성 날짜
    // 대댓글에 대한 작업 선택 조건 (서치 컨디션)
    private String searchCondition;
    private String nickName;		// 대댓글 작성자 닉네임
    private int check;
    
    public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}

	// 대댓글 번호를 반환하는 메서드
    public int getReplyNum() {
        return replyNum;
    }
    
    // 대댓글 번호를 설정하는 메서드
    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }
    
    // 대댓글 내용을 반환하는 메서드
    public String getReply() {
        return reply;
    }
    
    // 대댓글 내용을 설정하는 메서드
    public void setReply(String reply) {
        this.reply = reply;
    }
    
    // 대댓글이 속한 댓글 그룹(대댓글의 원글)의 번호를 반환하는 메서드
    public int getCommentsNum() {
        return commentsNum;
    }
    
    // 대댓글이 속한 댓글 그룹(대댓글의 원글)의 번호를 설정하는 메서드
    public void setCommentsNum(int commentsNum) {
        this.commentsNum = commentsNum;
    }
    
    // 대댓글 작성자 아이디를 반환하는 메서드
    public String getMemberID() {
        return memberID;
    }
    
    // 대댓글 작성자 아이디를 설정하는 메서드
    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }
    
    // 대댓글의 신고 횟수를 반환하는 메서드
    public int getProhibitCnt() {
        return prohibitCnt;
    }
    
    // 대댓글의 신고 횟수를 설정하는 메서드
    public void setProhibitCnt(int prohibitCnt) {
        this.prohibitCnt = prohibitCnt;
    }
    
    // 대댓글 작성 날짜를 반환하는 메서드
    public String getReplyDate() {
		return replyDate;
	}

    // 대댓글 작성 날짜를 설정하는 메서드
	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}

	// 대댓글에 대한 작업 선택 조건(서치 컨디션)을 반환하는 메서드
    public String getSearchCondition() {
        return searchCondition;
    }
    
    // 대댓글에 대한 작업 선택 조건(서치 컨디션)을 설정하는 메서드
    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }
    
    // 대댓글 작성자 닉네임을 반환하는 메서드
    public String getNickName() {
        return nickName;
    }
    
    // 대댓글 작성자 닉네임을 설정하는 메서드
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}

