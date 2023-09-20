package com.spring.view.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.biz.board.BoardService;
import com.spring.biz.board.BoardVO;
import com.spring.biz.comments.CommentsService;
import com.spring.biz.comments.CommentsVO;
import com.spring.biz.matching.MatchingService;
import com.spring.biz.matching.MatchingVO;
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
	
	@Autowired
	private MatchingService matchingService;

	@RequestMapping(value = "/mypage.do")
	public String mypage(MemberVO mVO, MemberProfileVO mpVO, HttpSession session, Model model) {
		System.out.println("로그: Mypage: mypage()");

		mVO.setMemberID((String)session.getAttribute("memberID"));
		mVO.setSearchCondition("duplicateID");

		mpVO.setMemberID((String)session.getAttribute("memberID"));
		mpVO.setSearchCondition("myProfile");

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

	@RequestMapping(value = "/ownMatchPage.do")
	public String ownMatchPage(MatchingVO mcVO, HttpSession session, Model model) {
		System.out.println("로그: Mypage: ownMatchPage()");

		//////////////////////////////////// 현재 로그인한 회원이 보낸거
		mcVO.setSenderID((String)session.getAttribute("memberID"));
		mcVO.setSearchCondition("sent");

		List<MatchingVO> senderdatas = matchingService.selectAll(mcVO);

		model.addAttribute("senderdatas", senderdatas);
		//////////////////////////////////// 현재 로그인한 회원이 받은거
		mcVO.setReceiverID((String)session.getAttribute("memberID"));
		mcVO.setSearchCondition("received");

		List<MatchingVO> receiverdatas = matchingService.selectAll(mcVO);

		model.addAttribute("receiverdatas", receiverdatas);

		return "ownMatchPage.jsp";
	}

	@RequestMapping(value = "/ownBoardListPage.do")
	public String ownBoardListPage(BoardVO bVO, CommentsVO cVO, HttpSession session, Model model) {
		System.out.println("로그: Mypage: ownBaordListPage() ");

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

	@RequestMapping(value = "/deleteOwnBoardList.do")
	public String deleteOwnBoard(@RequestParam("number") List<String> boardNums, BoardVO bVO, CommentsVO cVO, HttpSession session, Model model) {
		System.out.println("로그: Mypage: deleteOwnBoard() ");

		System.out.println("boardNums: " + boardNums);

		bVO.setMemberID((String)session.getAttribute("memberID"));
		bVO.setSearchCondition("ownBoard");

		List<BoardVO> bdatas = boardService.selectAll(bVO);

		for(int i = 0; i < bdatas.size(); i++) {
			for (String boardNum : boardNums) {
				if(bdatas.get(i).getBoardNum() == Integer.parseInt(boardNum)) {

					bVO = bdatas.get(i);

					bVO.setSearchCondition("boardNum");

					boardService.delete(bVO);
				}
			}
		}

		return "ownBoardListPage.do";

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

	@RequestMapping(value = "updateProfileImg.do")
	public String updateProfileImg(MemberVO mVO, MemberProfileVO mpVO, HttpSession session, Model model) throws IllegalStateException, IOException {
		System.out.println("로그: Mypage: updateProfileImg() ");

		MultipartFile profileImgUpload = mpVO.getProfileImgUpload();

		if(!profileImgUpload.isEmpty()) {

			mpVO.setProfileImg(profileImgUpload.getOriginalFilename());

			profileImgUpload.transferTo(new File("C:\\KANG\\stsworkspace\\healthDuo\\src\\main\\webapp\\images\\profileImg\\"+ mpVO.getProfileImg()));
		}
		else {
			mpVO.setSearchCondition("memberProfile");

			mpVO = memberProfileService.selectOne(mpVO);

			mpVO.setProfileImg(mpVO.getProfileImg());
		}

		mpVO.setSearchCondition("updateProfileImg");

		System.out.println("update 전");
		System.out.println(mpVO.getProfileNum());
		System.out.println(mpVO.getProfileImg());

		boolean flag = memberProfileService.update(mpVO);

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(flag) {
			return "updateProfilePage.do";
		}
		else {
			model.addAttribute("title", "프로필 이미지 변경 실패..");
			model.addAttribute("text", "다시한번 확인해주세요.." );
			model.addAttribute("icon", "warning" );

			return "goback.jsp";
		}
	}

	@RequestMapping(value = "/updateShortIntro.do")
	public String updateShortIntro(MemberProfileVO mpVO, Model model) {
		System.out.println("로그: Mypage: updateShortIntro() ");

		System.out.println("profileNum : " + mpVO.getProfileNum());
		System.out.println("shortIntro : " + mpVO.getShortIntro());

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

		System.out.println("profileNum : " + mpVO.getProfileNum());
		System.out.println("intro : " + mpVO.getIntro());

		mpVO.setSearchCondition("updateIntro");

		boolean flag = memberProfileService.update(mpVO);

		if(flag) {
			return "updateProfilePage.do";
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
