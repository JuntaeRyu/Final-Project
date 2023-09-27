package com.spring.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(MemberVO mVO, HttpSession session, Model model) {
		System.out.println("로그: LogController: login() ");

		mVO.setSearchCondition("login");

		mVO = memberService.selectOne(mVO);

		if(mVO != null) {
			session.setAttribute("memberID", mVO.getMemberID());
			session.setAttribute("nickName", mVO.getNickName());
			session.setAttribute("role", mVO.getRole());

			if(mVO.getRole()==2) {
				return "redirect:goAdminLoginSuccess.jsp";
			}
			return "redirect:main.do";
		}
		else {
			model.addAttribute("title", "로그인 실패!");
			model.addAttribute("text", "아이디와 비밀번호를 확인해주세요!");
			model.addAttribute("icon", "warning");

			return "goback.jsp";
		}

	}

	@RequestMapping(value = "/searchIDPhone.do", method = RequestMethod.POST)
	@ResponseBody
	public String searchIDPhone(MemberVO mVO) {

		mVO.setSearchCondition("searchIDPhoneNum");

		mVO = memberService.selectOne(mVO);

		if(mVO == null) {
			return null;
		}

		return mVO.getMemberID();
	}

	@RequestMapping(value = "/searchIDEmail.do", method = RequestMethod.POST)
	@ResponseBody
	public String searchIDEmail(MemberVO mVO) {

		mVO.setSearchCondition("searchIDEmail");

		mVO = memberService.selectOne(mVO);

		if(mVO == null) {
			return null;
		}
		System.out.println("로그: ID = " + mVO.getMemberID());
		return mVO.getMemberID();
	}

	//////////
	@RequestMapping(value = "/checkMemberPhoneNum.do", method = RequestMethod.POST)
	@ResponseBody
	public String checkMemberPhoneNum(MemberVO mVO) {

		mVO.setSearchCondition("checkMemberPhoneNum");

		mVO = memberService.selectOne(mVO);

		if(mVO == null) {
			return null;
		}

		return mVO.getMemberID();
	}

	///////////////////
	@RequestMapping(value = "/checkMemberEmail.do", method = RequestMethod.POST)
	@ResponseBody
	public String checkMemberEmail(MemberVO mVO) {

		mVO.setSearchCondition("checkMemberEmail");

		mVO = memberService.selectOne(mVO);

		if(mVO == null) {
			return null;
		}

		return mVO.getMemberID();
	}

	////////////////
	@RequestMapping(value = "/updateMemberPWLogin.do", method = RequestMethod.POST)
	@ResponseBody
	public String updateMemberPWLogin(MemberVO mVO) {

		mVO.setSearchCondition("updateMemberPW");

		if(memberService.update(mVO)) {
			return "성공!";
		}
		return null;
	}

	@RequestMapping(value = "/signupPage.do")
	public String signupPage() {
		System.out.println("로그: LogController: signupPage() ");

		return "redirect:signupPage.jsp";
	}

	@RequestMapping(value = "/signup.do", method = RequestMethod.POST)
	public String signup(MemberVO mVO, MemberProfileVO mpVO, HttpSession session, Model model) {
		System.out.println("로그: LogController: signup() ");

//		mVO.setSearchCondition("duplicateID");
//
//		MemberVO mdata = memberService.selectOne(mVO);
//
//		if (mdata != null) {
//			model.addAttribute("title", "중복된 아이디입니다..");
//			model.addAttribute("text", "다른 아이디를 입력해주세요..");
//			model.addAttribute("icon", "warning");
//
//			return "goback.jsp";
//		}
//
//		mVO.setSearchCondition("duplicateNickName");
//
//		mdata = memberService.selectOne(mVO);
//
//		// 닉네임이 중복된 경우
//		if (mdata != null) {
//			model.addAttribute("title", "중복된 닉네임입니다..");
//			model.addAttribute("text", "다른 닉네임을 입력해주세요..");
//			model.addAttribute("icon", "warning");
//
//			return "goback.jsp";
//		}
//
//		mVO.setSearchCondition("duplicateEmail");
//
//		mdata = memberService.selectOne(mVO);
//
//		if (mdata != null) {
//			model.addAttribute("title", "중복된 이메일입니다..");
//			model.addAttribute("text", "다시한번 확인해주세요..");
//			model.addAttribute("icon", "warning");
//
//			return "goback.jsp";
//		}

		boolean flag = memberService.insert(mVO);

		if (flag) {
//			model.addAttribute("name", mVO.getName());
//			model.addAttribute("email", mVO.getEmail());

			mpVO.setMemberID(mVO.getMemberID());
			mpVO.setProfileImg(null);
			mpVO.setShortIntro(null);
			mpVO.setIntro(null);

			memberProfileService.insert(mpVO);

			return "signupSuccess.do?name="+mVO.getName()+"&email="+mVO.getEmail();
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
		session.removeAttribute("nickName");
		session.removeAttribute("role");

		return "redirect:main.do";
	}

	@ResponseBody
	@RequestMapping(value = "/duplicateID.do", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	public String duplicateID(MemberVO mVO, HttpSession session, HttpServletRequest request) {
		System.out.println("로그: LogController: duplicateID() ");

		mVO.setSearchCondition("duplicateID");

		MemberVO mdata = memberService.selectOne(mVO);

		if (mdata != null) {
			return "0";
		}
		return "1";
	}

	@ResponseBody
	@RequestMapping(value = "/duplicateNickName.do", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	public String duplicateNickName(MemberVO mVO, HttpSession session, HttpServletRequest request) {
		System.out.println("로그: LogController: duplicateNickName() ");

		mVO.setSearchCondition("duplicateNickName");

		MemberVO mdata = memberService.selectOne(mVO);

		if (mdata != null) {
			return "0";
		}
		return "1";
	}

	@ResponseBody
	@RequestMapping(value = "/duplicateEmail.do", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	public String duplicateEmail(MemberVO mVO, HttpSession session, HttpServletRequest request) {
		System.out.println("로그: LogController: duplicateEmail() ");

		mVO.setSearchCondition("duplicateEmail");

		MemberVO mdata = memberService.selectOne(mVO);

		if (mdata != null) {
			return "0";
		}
		else if(mVO.getEmail() == null || mVO.getEmail().isEmpty()) {
			return "2";
		}
		return "1";
	}
}

