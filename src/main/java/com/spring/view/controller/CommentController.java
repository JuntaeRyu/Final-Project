package com.spring.view.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.biz.board.BoardService;
import com.spring.biz.board.BoardVO;
import com.spring.biz.comments.CommentsService;
import com.spring.biz.comments.CommentsVO;
import com.spring.biz.reply.ReplyService;
import com.spring.biz.reply.ReplyVO;

@Controller
public class CommentController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private CommentsService commentsService;
	
	@Autowired
	private ReplyService replyService;

	@RequestMapping(value = "/insertComment.do", method = RequestMethod.POST)
	public String insertComment(BoardVO bVO,CommentsVO cVO, HttpSession session, Model model) {
		System.out.println("로그: Comment: insertComment() ");

		cVO.setMemberID((String)session.getAttribute("memberID"));
		
		boolean flag = commentsService.insert(cVO);

		if(flag) {
			
			bVO = boardService.selectOne(bVO);
			
			if(bVO.getCategory() == 0) {
				model.addAttribute("title", "댓글작성 성공!");
				model.addAttribute("icon", "success");
				model.addAttribute("url", "noticeDetailPage.do?boardNum=" + cVO.getBoardNum());
			}
			else {
				model.addAttribute("title", "댓글작성 성공!");
				model.addAttribute("icon", "success");
				model.addAttribute("url", "boardDetailPage.do?boardNum=" + cVO.getBoardNum());
			}
			
			return "SweetAlert2.jsp";
		}
		else {
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

		if(flag) {
			model.addAttribute("title", "댓글수정 성공!");
			model.addAttribute("icon", "success");
			model.addAttribute("url", "boardDetailPage.do?boardNum="+cVO.getBoardNum());
			
			return "SweetAlert2.jsp";
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

		System.out.println("rVO의 commentsNum"+rVO.getCommentsNum());
		rVO.setSearchCondition("commentsReplyNum");
		
		List<ReplyVO> rdatas=replyService.selectAll(rVO);
		System.out.println("rdatas"+rdatas);
	
		boolean flag = false;
		
		if (!(rdatas.isEmpty())) {
			cVO.setSearchCondition("updateComments");
			cVO.setComments(null);

			flag = commentsService.update(cVO);

			model.addAttribute("text", "대댓글이 있어서 삭제된 댓글입니다로 수정됩니다");
		} else {
			flag = commentsService.delete(cVO);
		}
		
		if (flag) {
			model.addAttribute("title", "댓글삭제 성공!");
			model.addAttribute("icon", "success");
			model.addAttribute("url", "boardDetailPage.do?boardNum="+cVO.getBoardNum());

			return "SweetAlert2.jsp";
		}
		else {
			model.addAttribute("title", "댓글 삭제 실패..");
			model.addAttribute("text", "다시한번 확인해주세요..");
			model.addAttribute("icon", "warning");

			return "goback.jsp";
		}
		
	}

	@RequestMapping(value = "/insertReply.do", method = RequestMethod.POST)
	public String insertReply(BoardVO bVO, CommentsVO cVO, ReplyVO rVO, HttpSession session, HttpServletRequest request, Model model) {
		System.out.println("로그: Comment: insertReply() ");

		rVO.setMemberID((String)session.getAttribute("memberID"));
		
		boolean flag = replyService.insert(rVO);
		
		if (flag) {
			
			bVO = boardService.selectOne(bVO);
			
			if(bVO.getCategory() == 0) {
				model.addAttribute("title", "대댓글 작성 성공!");
				model.addAttribute("icon", "success");
				model.addAttribute("url", "noticeDetailPage.do?boardNum=" + cVO.getBoardNum());
			}
			else {
				model.addAttribute("title", "대댓글 작성 성공!");
				model.addAttribute("icon", "success");
				model.addAttribute("url", "boardDetailPage.do?boardNum="+cVO.getBoardNum());
			}
			
			return "SweetAlert2.jsp";
		} 
		else {
			request.setAttribute("title", "대댓글 작성실패.." );
			request.setAttribute("text", "다시한번 확인해주세요.." );
			request.setAttribute("icon", "warning" );

			return "goback.jsp";
		}
	}

	@RequestMapping(value = "/updateReply.do", method = RequestMethod.POST)
	public String updateReply(CommentsVO cVO, ReplyVO rVO, Model model, HttpServletRequest request) {
		System.out.println("로그: Comment: updateReply() ");

        rVO.setSearchCondition("updateReply");

        boolean flag = replyService.update(rVO);
        
        if (flag) {
        	model.addAttribute("title", "대댓글 수정 성공!");
			model.addAttribute("icon", "success");
			model.addAttribute("url", "boardDetailPage.do?boardNum="+cVO.getBoardNum());
			
			return "SweetAlert2.jsp";
        } else {
        	model.addAttribute("title", "대댓글 수정실패.." );
			model.addAttribute("text", "다시한번 확인해주세요.." );
			model.addAttribute("icon", "warning" );

			return "goback.jsp";
        }
	}

	@RequestMapping(value = "/deleteReply.do", method = RequestMethod.POST)
	public String deleteReply(CommentsVO cVO, ReplyVO rVO, HttpServletRequest request, Model model) {
		System.out.println("로그: Comment: deleteReply() ");
        
		rVO.setSearchCondition("replyNum");
        boolean flag = replyService.delete(rVO);
        
        if (flag) {
        	model.addAttribute("title", "대댓글 삭제 성공!");
			model.addAttribute("icon", "success");
			model.addAttribute("url", "boardDetailPage.do?boardNum="+cVO.getBoardNum());
			
			return "SweetAlert2.jsp";
        }
        else {
            request.setAttribute("title", "대댓글 삭제실패..");
            request.setAttribute("text", "다시 한번 확인해주세요..");
            request.setAttribute("icon", "warning");
            
            return "goback.jsp";
        }
        
	}
}
