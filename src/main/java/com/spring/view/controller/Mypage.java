package com.spring.view.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.board.BoardService;
import com.spring.biz.board.BoardVO;
import com.spring.biz.member.MemberService;
import com.spring.biz.member.MemberVO;

@Controller
public class Mypage {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/mypage.do")
	public String mypage(MemberVO mVO, HttpSession session, Model model) {
		System.out.println("로그: Mypage: mypage()");

		mVO.setMemberID((String)session.getAttribute("memberID"));
		mVO.setSearchCondition("duplicateID");
		
		mVO = memberService.selectOne(mVO);

		if(mVO != null) {
			model.addAttribute("mdata", mVO);

			return "mypage.jsp";
		}
		else {
			model.addAttribute("title", "요청실패..");
			model.addAttribute("text", "다시한번 확인해주세요.." );
			model.addAttribute("icon", "warning" );
			
			return "goback.jsp";
		}
	}

	@RequestMapping(value = "/prohibitListPage.do")
	public String prohibitListPage(BoardVO bVO, Model model) {
		System.out.println("로그: Mypage: prohibitListPage()");

		bVO.setSearchCondition("prohibitBoard");

		List<BoardVO> phbdatas = boardService.selectAll(bVO);

		model.addAttribute("phdatas", phbdatas);

		return "prohibitListPage.jsp";
	}

	@RequestMapping(value = "/ownBoardListPage.do")
	public String ownBoardListPage(BoardVO bVO, HttpSession session, Model model) {
		System.out.println("로그: Mypage: ownBaordListPage()");

		bVO.setMemberID((String)session.getAttribute("memberID"));
		bVO.setSearchCondition("ownBoard");

		List<BoardVO> bdatas = boardService.selectAll(bVO);

		model.addAttribute("bdatas", bdatas);

		return "ownBoardListPage.jsp";
	}
	
	@RequestMapping(value = "/updateProfilePage.do")
	public String updateProfilePage() {
		System.out.println("로그: Mypage: updateProfilePage()");
		
		return "redirect:updateProfilePage.jsp";
	}

	@RequestMapping(value = "/updateInfoPage.do")
	public String updateMpwPage() {
		System.out.println("로그: Mypage: updateInfoPage()");
		
		return "redirect:updateInfoPage.jsp";
	}

	@RequestMapping(value = "/updateMpw.do")
	public String updateMpw(MemberVO mVO, HttpSession session, Model model) {
		System.out.println("로그: Mypage: updateMpw()");

		mVO.setMemberID((String)session.getAttribute("memberID"));
		
		boolean flag = memberService.update(mVO);

		if (flag) {
			return "redirect:logout.do";
		} 
		else {
			model.addAttribute("title", "비밀번호 변경 실패..");
			model.addAttribute("text", "다시한번 확인해주세요..");
			model.addAttribute("icon", "warning");
			
			return "goback.jsp";
		}
	}
	
	@RequestMapping(value = "/deleteMemberPage.do")
	public String deleteMemberPage() {
		System.out.println("로그: Mypage: deleteMemberPage()");
		
		return "redirect:deleteMemberPage.jsp";
	}
	
	@RequestMapping(value = "/deleteMember.do")
	public String deleteMember(MemberVO mVO, HttpSession session, Model model) {
		System.out.println("로그: Mypage: deleteMember()");

		mVO.setMemberID((String)session.getAttribute("memberID"));
		
        boolean flag = memberService.delete(mVO);
        
        if (flag) {
            return "redirect:logout.do";
        }
        else {
            model.addAttribute("title", "탈퇴실패..");
            model.addAttribute("text", "다시 한번 확인해주세요..");
            model.addAttribute("icon", "warning");
            
            return "goback.jsp";
        }
        
	}
	
}
