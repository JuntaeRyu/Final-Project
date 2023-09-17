package com.spring.view.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.biz.member.MemberService;
import com.spring.biz.member.MemberVO;
import com.spring.biz.memberProfile.MemberProfileService;
import com.spring.biz.memberProfile.MemberProfileVO;

@Controller
public class LogController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberProfileService memberProfileService;

	@RequestMapping(value = "/loginPage.do")
	public String loginPage() {
		System.out.println("로그: LogController: loginPage() ");
		
		return "redirect:loginPage.jsp";
	}

	@RequestMapping(value = "/login.do")
	public String login(MemberVO mVO, HttpSession session) {
		System.out.println("로그: LogController: login() ");
		
		mVO.setSearchCondition("login");
		
		mVO = memberService.selectOne(mVO);

		if(mVO != null) {
			session.setAttribute("memberID", mVO.getMemberID());
			session.setAttribute("nickName", mVO.getNickName());
			session.setAttribute("role", mVO.getRole());
			
			return "main.do";
		}
		else {
			return "loginPage.do";
		}

	}

	@RequestMapping(value = "/signupPage.do")
	public String signupPage() {
		System.out.println("로그: LogController: signupPage() ");
		
		return "redirect:signupPage.jsp";
	}

	@RequestMapping(value = "/signup.do", method = RequestMethod.POST)
	public String signup(MemberVO mVO, MemberProfileVO mpVO, HttpSession session, Model model) {
		System.out.println("로그: LogController: signup() ");

		mVO.setSearchCondition("duplicateID");

		MemberVO mdata = memberService.selectOne(mVO);

		if (mdata != null) {
			model.addAttribute("title", "중복된 아이디입니다..");
			model.addAttribute("text", "다른 아이디를 입력해주세요..");
			model.addAttribute("icon", "warning");

			return "goback.jsp";
		}

		mVO.setSearchCondition("duplicateNickName");

		mdata = memberService.selectOne(mVO);

		// 닉네임이 중복된 경우
		if (mdata != null) {
			model.addAttribute("title", "중복된 닉네임입니다..");
			model.addAttribute("text", "다른 닉네임을 입력해주세요..");
			model.addAttribute("icon", "warning");

			return "goback.jsp";
		}

		mVO.setSearchCondition("duplicateEmail");

		mdata = memberService.selectOne(mVO);

		if (mdata != null) {
			model.addAttribute("title", "중복된 이메일입니다..");
			model.addAttribute("text", "다시한번 확인해주세요..");
			model.addAttribute("icon", "warning");

			return "goback.jsp";
		}

		boolean flag = memberService.insert(mVO);

		if (flag) {
			model.addAttribute("name", mVO.getName());
			model.addAttribute("email", mVO.getEmail());

			mpVO.setMemberID(mVO.getMemberID());
			mpVO.setProfileImg(null);
			mpVO.setShortIntro(null);
			mpVO.setIntro(null);
			mpVO.setProhibitCnt(0);
			
			memberProfileService.insert(mpVO);
			
			return "signupSuccess.do";
		}
		else {
			model.addAttribute("title", "회원가입실패..");
			model.addAttribute("text", "다시한번 확인해주세요");
			model.addAttribute("icon", "warning");
			
			return "goback.jsp";
		}
	}

	@RequestMapping(value = "/logout.do")
	public String logout(HttpSession session) {
		System.out.println("로그: LogController: logout() ");

		session.removeAttribute("memberID");

		return "main.do";
	}
}

