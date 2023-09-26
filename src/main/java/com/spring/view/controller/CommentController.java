package com.spring.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.biz.comments.CommentsService;
import com.spring.biz.comments.CommentsVO;
import com.spring.biz.reply.ReplyService;
import com.spring.biz.reply.ReplyVO;

@Controller
public class CommentController {

	@Autowired
	private CommentsService commentsService;
	
	@Autowired
	private ReplyService replyService;

	@RequestMapping(value = "/insertComment.do", method = RequestMethod.POST)
	public String insertComment(CommentsVO cVO, HttpSession session, Model model) {
		System.out.println("로그: Comment: insertComment() ");

		cVO.setMemberID((String)session.getAttribute("memberID"));
		
		boolean flag = commentsService.insert(cVO);

		if(flag) {
			model.addAttribute("boardNum", cVO.getBoardNum());
//			session.setAttribute("boardNum", cVO.getBoardNum());
			
			return "boardDetailPage.do";
		} else {
			model.addAttribute("title", "댓글작성실패.." );
			model.addAttribute("text", "다시한번 확인해주세요.." );
			model.addAttribute("icon", "warning" );

			return "goback.jsp";
		}

	}

	@RequestMapping(value = "/updateComment.do", method = RequestMethod.POST)
	public String updateComment(CommentsVO cVO, Model model) {
		System.out.println("로그: Comment: updateComment() ");

		cVO.setSearchCondition("updateComments");

		boolean flag = commentsService.update(cVO);

		cVO = commentsService.selectOne(cVO);

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

	@RequestMapping(value = "/deleteComment.do", method = RequestMethod.POST)
	public String deleteComment(CommentsVO cVO, ReplyVO rVO, Model model) {
		System.out.println("로그: Comment: deleteComment() ");

		rVO.setSearchCondition("commentsReplyNum");

		if (replyService.selectAll(rVO) != null) {
			cVO.setSearchCondition("updateComments");
			cVO.setComments(null);

			commentsService.update(cVO);
		} else {
			commentsService.delete(cVO);
		}

		model.addAttribute("boardNum", cVO.getBoardNum());
		
		return "boardDetailPage.do";
	}

	@RequestMapping(value = "/insertReply.do", method = RequestMethod.POST)
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

	@RequestMapping(value = "/updateReply.do", method = RequestMethod.POST)
	public String updateReply(CommentsVO cVO, ReplyVO rVO, Model model) {
		System.out.println("로그: Comment: updateReply() ");

        rVO.setSearchCondition("updateReply");

        boolean flag = replyService.update(rVO);

        if (flag) {
            rVO = replyService.selectOne(rVO);
            
            cVO.setCommentsNum(rVO.getCommentsNum());
            
            cVO = commentsService.selectOne(cVO);
            
            model.addAttribute("boardNum", cVO.getBoardNum());

            return "boardDetailPage.do";
        } else {
        	model.addAttribute("title", "대댓글 수정실패.." );
			model.addAttribute("text", "다시한번 확인해주세요.." );
			model.addAttribute("icon", "warning" );

			return "goback.jsp";
        }
	}

	@RequestMapping(value = "/deleteReply.do", method = RequestMethod.POST)
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
