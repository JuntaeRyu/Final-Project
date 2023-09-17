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
import com.spring.biz.memberProfile.MemberProfileService;
import com.spring.biz.memberProfile.MemberProfileVO;

@Controller
public class MypageController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberProfileService memberProfileService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private CommentsService commentsService;

	@RequestMapping(value = "/mypage.do")
	public String mypage(MemberVO mVO, MemberProfileVO mpVO, HttpSession session, Model model) {
		System.out.println("로그: Mypage: mypage()");

		mVO.setMemberID((String)session.getAttribute("memberID"));
		mVO.setSearchCondition("duplicateID");
		
		mpVO.setMemberID((String)session.getAttribute("memberID"));
		mpVO.setSearchCondition("memberProfile");
		
		mVO = memberService.selectOne(mVO);
		
		mpVO = memberProfileService.selectOne(mpVO);

		if(mVO != null & mpVO != null) {
			model.addAttribute("mdata", mVO);
			model.addAttribute("mpdata", mpVO);

			return "mypage.jsp";
		}
		else {
			model.addAttribute("title", "요청실패..");
			model.addAttribute("text", "다시한번 확인해주세요.." );
			model.addAttribute("icon", "warning" );
			
			return "goback.jsp";
		}
	}

	@RequestMapping(value = "/ownBoardListPage.do")
	public String ownBoardListPage(BoardVO bVO, CommentsVO cVO, HttpSession session, Model model) {
		System.out.println("로그: Mypage: ownBaordListPage()");

		bVO.setMemberID((String)session.getAttribute("memberID"));
		bVO.setSearchCondition("ownBoard");

		List<BoardVO> bdatas = boardService.selectAll(bVO);
		
		if(!(bdatas == null || bdatas.isEmpty())) {
			for(int i = 0; i < bdatas.size(); i++) {
				cVO.setBoardNum(bdatas.get(i).getBoardNum());
				
				bdatas.get(i).setBoardCommentsCnt(commentsService.selectAll(cVO).size());
			}
		}

		model.addAttribute("bdatas", bdatas);

		return "ownBoardListPage.jsp";
	}
	
	@RequestMapping(value = "/updateProfilePage.do")
	public String updateProfilePage(MemberVO mVO, MemberProfileVO mpVO, HttpSession session, Model model) {
		System.out.println("로그: Mypage: updateProfilePage()");
		
		mVO.setMemberID((String)session.getAttribute("memberID"));
		mVO.setSearchCondition("duplicateID");
		
		mpVO.setMemberID((String)session.getAttribute("memberID"));
		mpVO.setSearchCondition("myProfile");
		
		mVO = memberService.selectOne(mVO);
		
		mpVO = memberProfileService.selectOne(mpVO);
		
		if(mpVO != null & mVO != null) {
			model.addAttribute("mpdata", mpVO);
			model.addAttribute("mdata", mVO);
		}
		return "updateProfilePage.jsp";
	}
	
	@RequestMapping(value = "/updateShortIntro.do")
	public String updateShortIntro(MemberProfileVO mpVO, Model model) {
		System.out.println("로그: Mypage: updateShortIntro() ");
		
		mpVO.setSearchCondition("updateShortIntro");
		
		boolean flag = memberProfileService.update(mpVO);
		
		if(flag) {
			return "updateProfilePage.do";
		}
		else {
			model.addAttribute("title", "한줄 소개글 수정 실패..");
			model.addAttribute("text", "다시 한 번 확인해주세요..");
			model.addAttribute("icon", "warning");

			return "goback.jsp";
		}
	}
	
	@RequestMapping(value = "/updateIntro.do")
	public String updateIntro(MemberProfileVO mpVO, Model model) {
		System.out.println("로그: Mypage: updateIntro() ");

		mpVO.setSearchCondition("updateIntro");
		
		boolean flag = memberProfileService.update(mpVO);
		
		if(flag) {
			return "updatProfilePage.do";
		}
		else {
			model.addAttribute("title", "소개글 수정 실패..");
			model.addAttribute("text", "다시 한 번 확인해주세요..");
			model.addAttribute("icon", "warning");
			
			return "goback.jsp";
		}
	}

	@RequestMapping(value = "/updateInfoPage.do")
	public String updateMpwPage(MemberVO mVO, HttpSession session, Model model) {
		System.out.println("로그: Mypage: updateInfoPage()");

		mVO.setMemberID((String)session.getAttribute("memberID"));
		mVO.setSearchCondition("duplicateID");
		
		mVO = memberService.selectOne(mVO);
		
		model.addAttribute("mdata", mVO);
		
		return "updateInfoPage.jsp";
	}

	@RequestMapping(value = "/updateMemberPW.do")
	public String updateMpw(MemberVO mVO, HttpSession session, Model model) {
		System.out.println("로그: Mypage: updateMpw()");

		mVO.setMemberID((String)session.getAttribute("memberID"));
		mVO.setSearchCondition("updateMemberPW");
		
		boolean flag = memberService.update(mVO);

		if(flag) {
			return "logout.do";
		} 
		else {
			model.addAttribute("title", "비밀번호 변경 실패..");
			model.addAttribute("text", "다시한번 확인해주세요..");
			model.addAttribute("icon", "warning");
			
			return "goback.jsp";
		}
	}
	
	@RequestMapping(value = "/updateNickName.do")
	public String updateNickName(MemberVO mVO, HttpSession session, Model model) {
		
		mVO.setMemberID((String)session.getAttribute("memberID"));
		mVO.setSearchCondition("updateNickName");
		
		boolean flag = memberService.update(mVO);
		
		if(flag) {
			return "updateInfoPage.do";
		}
		else {
			model.addAttribute("title", "닉네임 변경 실패..");
			model.addAttribute("text", "다시한번 확인해주세요..");
			model.addAttribute("icon", "warning");
			
			return "goback.jsp";
		}
	}
	
	@RequestMapping(value = "/updatePhoneNum.do")
	public String updatePhoneNum(MemberVO mVO, HttpSession session, Model model) {
		
		mVO.setMemberID((String)session.getAttribute("memberID"));
		mVO.setSearchCondition("updatePhoneNum");
		
		boolean flag = memberService.update(mVO);
		
		if(flag) {
			return "updateInfoPage.do";
		}
		else {
			model.addAttribute("title", "핸드폰 번호 변경 실패..");
			model.addAttribute("text", "다시한번 확인해주세요..");
			model.addAttribute("icon", "warning");
			
			return "goback.jsp";
		}
	}
	
	@RequestMapping(value = "/updateEmail.do")
	public String updateEmail(MemberVO mVO, HttpSession session, Model model) {
		
		mVO.setMemberID((String)session.getAttribute("memberID"));
		mVO.setSearchCondition("updateEmail");
		
		boolean flag = memberService.update(mVO);
		
		if(flag) {
			return "updateInfoPage.do";
		}
		else {
			model.addAttribute("title", "이메일 변경 실패..");
			model.addAttribute("text", "다시한번 확인해주세요..");
			model.addAttribute("icon", "warning");
			
			return "goback.jsp";
		}
	}
	
	@RequestMapping(value = "/updateAddress.do")
	public String updateAddress(MemberVO mVO, HttpSession session, Model model) {
		
		mVO.setMemberID((String)session.getAttribute("memberID"));
		mVO.setSearchCondition("updateAddress");
		
		boolean flag = memberService.update(mVO);
		
		if(flag) {
			return "updateInfoPage.do";
		}
		else {
			model.addAttribute("title", "주소 변경 실패..");
			model.addAttribute("text", "다시한번 확인해주세요..");
			model.addAttribute("icon", "warning");
			
			return "goback.jsp";
		}
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
