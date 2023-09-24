package com.spring.view.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.matching.MatchingService;
import com.spring.biz.matching.MatchingVO;
import com.spring.biz.memberProfile.MemberProfileService;
import com.spring.biz.memberProfile.MemberProfileVO;
import com.spring.biz.prohibit.ProhibitService;
import com.spring.biz.prohibit.ProhibitVO;
import com.spring.biz.recommend.RecommendService;
import com.spring.biz.recommend.RecommendVO;

@Controller
public class MatchingController {

	@Autowired
	private MemberProfileService memberProfileService;

	@Autowired
	private RecommendService recommendService;

	@Autowired
	private ProhibitService prohibitService;

	@Autowired
	private MatchingService matchingService;

	@RequestMapping(value = "/matchingPage.do")
	public String matchingPage(MemberProfileVO mpVO, Random random, Model model) {
		System.out.println("로그: Matching: matchingPage() ");

		mpVO.setSearchCondition("totalMemberProfile");

		List<MemberProfileVO> mpdatas = memberProfileService.selectAll(mpVO);

		model.addAttribute("mpdatas", mpdatas);

		return "matchingPage.jsp";
	}

	@RequestMapping(value = "/profileDetailPage.do")
	public String profileListPage(MemberProfileVO mpVO, RecommendVO rcVO, ProhibitVO pVO, 
			HttpSession session, HttpServletRequest request, Model model) {
		System.out.println("로그: Matching: profileDetailPage() ");
		
		if(mpVO.getMemberID() == null) {
			mpVO.setSearchCondition("memberProfile"); // 회원 프로필 번호로 가져오는거
		}
		else {
			mpVO.setSearchCondition("myProfile"); // 회원 아이디 번호로 가져오는거
		}
		
		mpVO = memberProfileService.selectOne(mpVO);

		rcVO.setMemberID((String)session.getAttribute("memberID"));
		rcVO.setCommonNum(mpVO.getProfileNum());
//		rcVO.setCommonNum(Integer.parseInt(request.getParameter("profileNum")));
		
		pVO.setMemberID((String)session.getAttribute("memberID"));
		pVO.setCommonNum(mpVO.getProfileNum());
//		pVO.setCommonNum(Integer.parseInt(request.getParameter("profileNum")));
		
		// RecommendDAO를 통해 해당 게시글에 대한 사용자의 추천 여부를 확인합니다.
		rcVO = recommendService.selectOne(rcVO);

		// ProhibitDAO를 통해 해당 게시글에 대한 사용자의 신고 여부를 확인합니다.
		pVO = prohibitService.selectOne(pVO);

		if (rcVO != null) {
			request.setAttribute("recommend", 1);
		} else {
			request.setAttribute("recommend", 0);
		}

		// 신고 여부에 따라 request에 "prohibit" 속성을 설정합니다.
		if (pVO != null) {
			request.setAttribute("prohibit", 1);
		} else {
			request.setAttribute("prohibit", 0);
		}

		model.addAttribute("mpdata", mpVO);

		return "profileDetailPage.jsp";
	}

	@RequestMapping(value = "/applyMatching.do")
	public String applyMatching(MatchingVO mcVO, HttpSession session, Model model) {
		System.out.println("로그: Matching: applyMatching() ");

		mcVO.setSenderID((String)session.getAttribute("memberID"));

		boolean flag = matchingService.insert(mcVO);

		if(flag) {
			return "ownMatchPage.do";
		}
		else {
			model.addAttribute("title", "매칭 중복 신청 ..");
			model.addAttribute("text", "나의 매칭목록 확인해주세요.." );
			model.addAttribute("icon", "warning" );

			return "goback.jsp";
		}
	}

	@RequestMapping(value = "/acceptMatching.do")
	public String acceptMatching(MatchingVO mcVO, Model model) {
		System.out.println("로그: Matching: acceptMatching() ");

		boolean flag = matchingService.update(mcVO);

		if(flag) {
			return "ownMatchPage.do";
		}
		else {
			model.addAttribute("title", "매칭신청 수락 실패..");
			model.addAttribute("text", "다시한번 확인해주세요.." );
			model.addAttribute("icon", "warning" );

			return "goback.jsp";
		}
	}

	@RequestMapping(value = "/deleteMatching.do")
	public String deleteMatching(MatchingVO mcVO, Model model) {
		System.out.println("로그: Matching: deleteMatching() ");

		boolean flag = matchingService.delete(mcVO);

		if(flag) {
			return "ownMatchPage.do";
		}
		else {
			model.addAttribute("title", "매칭신청 수락 실패..");
			model.addAttribute("text", "다시한번 확인해주세요.." );
			model.addAttribute("icon", "warning" );

			return "goback.jsp";
		}
	}
}
