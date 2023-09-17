package com.spring.view.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.board.BoardService;
import com.spring.biz.board.BoardVO;
import com.spring.biz.comments.CommentsService;
import com.spring.biz.comments.CommentsVO;
import com.spring.biz.member.MemberService;
import com.spring.biz.member.MemberVO;

@Controller
public class AdminController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private BoardService boardService;

	@Autowired
	private CommentsService commentsService;

	@RequestMapping(value = "/adminPage.do")
	public String admingPage(MemberVO mVO, HttpSession session, Model model) {
		System.out.println("로그: Admin: admingPage() ");

		mVO.setMemberID((String)session.getAttribute("memberID"));
		mVO.setSearchCondition("duplicateID");

		mVO = memberService.selectOne(mVO);

		if(mVO.getRole() == 2) {
			return "adminPage.jsp";
		}
		else {
			model.addAttribute("title", "잘못된 접근입니다.");
			model.addAttribute("text", "다시 한번 확인해주세요.");
			model.addAttribute("icon", "warning");

			return "goback.jsp";
		}

	}

	@RequestMapping(value = "/memberListPage.do")
	public String memberListPage(MemberVO mVO, Model model) {
		System.out.println("로그: Admin: memberListPage() ");

		mVO.setSearchCondition("회원");

		List<MemberVO> mdatas = memberService.selectAll(mVO);

		model.addAttribute("mdatas", mdatas);

		return "memberListPage.jsp";
	}

	@RequestMapping(value = "/adminListPage.do")
	public String adminListPage(MemberVO mVO, Model model) {
		System.out.println("로그: Admin: adminLisPage() ");

		mVO.setSearchCondition("관리자");

		List<MemberVO> mdatas = memberService.selectAll(mVO);

		model.addAttribute(mdatas);

		return "adminListPage.jsp";
	}

	@RequestMapping(value = "/prohibitListPage.do")
	public String prohibitListPage(BoardVO bVO, CommentsVO cVO, Model model) {
		System.out.println("로그: Mypage: prohibitListPage()");

		bVO.setSearchCondition("prohibitBoard");

		List<BoardVO> phbdatas = boardService.selectAll(bVO);

		if(!(phbdatas == null || phbdatas.isEmpty())) {
			for(int i = 0; i < phbdatas.size(); i++) {
				cVO.setBoardNum(phbdatas.get(i).getBoardNum());

				phbdatas.get(i).setBoardCommentsCnt(commentsService.selectAll(cVO).size());
			}
		}

		model.addAttribute("phdatas", phbdatas);

		return "prohibitListPage.jsp";
	}

}
