package com.spring.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.comments.CommentsService;
import com.spring.biz.comments.CommentsVO;
import com.spring.biz.reply.ReplyService;
import com.spring.biz.reply.ReplyVO;

@Controller
public class Comment {

	@Autowired
	private CommentsService commentService;
	
	@Autowired
	private ReplyService replyService;

	@RequestMapping(value = "/insertComment.do")
	public String insertComment(CommentsVO cVO, HttpSession session, Model model) {
		System.out.println("로그: Comment: insertComment() ");

		cVO.setMemberID((String)session.getAttribute("memberID"));
		
		boolean flag = commentService.insert(cVO);

		if(flag) {
			model.addAttribute("boardNum", cVO.getBoardNum());
			
			return "boardDetailPage.do";
		} else {
			model.addAttribute("title", "댓글작성실패.." );
			model.addAttribute("text", "다시한번 확인해주세요.." );
			model.addAttribute("icon", "warning" );

			return "goback.jsp";
		}

	}

	@RequestMapping(value = "/updateCommentPage.do")
	public String updateCommentPage(CommentsVO cVO, Model model) {
		System.out.println("로그: Comment: updateCommentPage() ");

		cVO = commentService.selectOne(cVO);

		// 조회된 댓글 정보가 있을 경우 수정 페이지로 이동합니다.
		if (cVO != null) {
			model.addAttribute("commentNum", cVO.getCommentsNum());

			return "updateCommentPage.jsp";
		}
		else {
			model.addAttribute("title", "잘못된 요청입니다.." );
			model.addAttribute("text", "다시한번 확인해주세요.." );
			model.addAttribute("icon", "warning" );

			return "goback.jsp";
		}
	}

	@RequestMapping(value = "/updateComment.do")
	public String updateComment(CommentsVO cVO, Model model) {
		System.out.println("로그: Comment: updateComment() ");

		cVO.setSearchCondition("updateComments");

		boolean flag = commentService.update(cVO);

		cVO = commentService.selectOne(cVO);

		if(flag) {
			model.addAttribute("boardNum", cVO.getBoardNum());
			
			return "boardDetailPage.do";
		}
		else {
			model.addAttribute("title", "댓글 수정 실패..");
			model.addAttribute("text", "다시한번 확인해주세요..");
			model.addAttribute("icon", "warning");

			return "goback.jsp";
		}

	}

	@RequestMapping(value = "/deleteComment.do")
	public String deleteComment(CommentsVO cVO, ReplyVO rVO, Model model) {
		System.out.println("로그: Comment: deleteComment() ");

		rVO.setSearchCondition("commentsReplyNum");

		if (replyService.selectAll(rVO) != null) {
			cVO.setSearchCondition("updateReply");
			cVO.setComments(null);

			commentService.update(cVO);
		} else {
			commentService.delete(cVO);
		}

		model.addAttribute("boardNum", cVO.getBoardNum());
		
		return "boardDetailPage.do";
	}

	@RequestMapping(value = "/insertReply.do")
	public String insertReply(ReplyVO rVO, HttpSession session, HttpServletRequest request, Model model) {
		System.out.println("로그: Comment: insertReply() ");

		rVO.setMemberID((String)session.getAttribute("memberID"));
		
		boolean flag = replyService.insert(rVO);

		String boardNum = request.getParameter("boardNum");

		System.out.println("boardNum: " + request.getParameter("boardNum"));

		if (flag) {
			model.addAttribute("boardNum", boardNum);
			
			return "boardDetailPage.do";
		} 
		else {
			request.setAttribute("title", "대댓글 작성실패.." );
			request.setAttribute("text", "다시한번 확인해주세요.." );
			request.setAttribute("icon", "warning" );

			return "goback.jsp";
		}
	}

	@RequestMapping(value = "/updateReplyPage.do")
	public String updateReplyPage(ReplyVO rVO, Model model) {
		System.out.println("로그: Comment: updateReplyPage() ");

		rVO = replyService.selectOne(rVO);

		if (rVO != null) {
			model.addAttribute("replyNum", rVO.getReplyNum());

			return "updateReplyPage.jsp";
		}
		else {
			model.addAttribute("title", "잘못된 요청입니다.." );
			model.addAttribute("text", "다시한번 확인해주세요.." );
			model.addAttribute("icon", "warning" );

			return "goback.jsp";
		}
	}

	@RequestMapping(value = "/updateReply.do")
	public String updateReply(CommentsVO cVO, ReplyVO rVO, Model model) {
		System.out.println("로그: Comment: updateReply() ");

        rVO.setSearchCondition("updateReply");

        boolean flag = replyService.update(rVO);

        if (flag) {
            rVO = replyService.selectOne(rVO);
            
            cVO.setCommentsNum(rVO.getCommentsNum());
            
            cVO = commentService.selectOne(cVO);
            
            model.addAttribute("boardNum", cVO.getBoardNum());

            return "boardDetailPage.do";
        } else {
        	model.addAttribute("title", "대댓글 수정실패.." );
			model.addAttribute("text", "다시한번 확인해주세요.." );
			model.addAttribute("icon", "warning" );

			return "goback.jsp";
        }
	}

	@RequestMapping(value = "/deleteReply.do")
	public String deleteReply(ReplyVO rVO, HttpServletRequest request, Model model) {
		System.out.println("로그: Comment: deleteReply() ");
        
        boolean flag = replyService.delete(rVO);
        
        if (flag) {
        	model.addAttribute("boardNum", Integer.parseInt(request.getParameter("boardNum")));
        	
            return "boardDetailPage.do";
        }
        else {
            request.setAttribute("title", "대댓글 삭제실패..");
            request.setAttribute("text", "다시 한번 확인해주세요..");
            request.setAttribute("icon", "warning");
            
            return "goback.jsp";
        }
        
	}
}
