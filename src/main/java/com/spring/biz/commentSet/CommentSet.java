package com.spring.biz.commentSet;

import java.util.List;

import com.spring.biz.comments.CommentsVO;
import com.spring.biz.reply.ReplyVO;

public class CommentSet {
	
	// 댓글(1개) 정보를 저장하는 객체
	private CommentsVO comment;
	// 대댓글들(N개)을 저장하는 ArrayList
	private List<ReplyVO> rdatas;
	
	// 댓글(1개) 정보를 반환하는 메서드
    public CommentsVO getComment() {
        return comment;
    }
    
    // 댓글(1개) 정보를 설정하는 메서드
    public void setComment(CommentsVO comment) {
        this.comment = comment;
    }
    
    // 대댓글들(N개)을 반환하는 메서드
    public List<ReplyVO> getRdatas() {
        return rdatas;
    }
    
    // 대댓글들(N개)을 설정하는 메서드
    public void setRdatas(List<ReplyVO> rdatas) {
        this.rdatas = rdatas;
    }

	public void addReply(ReplyVO rdata) {
		this.rdatas.add(rdata);
	}
	
}
