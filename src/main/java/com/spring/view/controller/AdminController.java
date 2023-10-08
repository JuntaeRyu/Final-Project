package com.spring.view.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.biz.board.BoardService;
import com.spring.biz.board.BoardVO;
import com.spring.biz.comments.CommentsService;
import com.spring.biz.comments.CommentsVO;
import com.spring.biz.member.MemberService;
import com.spring.biz.member.MemberVO;
import com.spring.biz.memberProfile.MemberProfileService;
import com.spring.biz.memberProfile.MemberProfileVO;
import com.spring.biz.page.PageVO;
import com.spring.biz.prohibit.ProhibitService;
import com.spring.biz.prohibit.ProhibitVO;
import com.spring.biz.recommend.RecommendService;
import com.spring.biz.recommend.RecommendVO;
import com.spring.biz.reply.ReplyService;
import com.spring.biz.reply.ReplyVO;
import com.spring.biz.warn.WarnService;
import com.spring.biz.warn.WarnVO;

@Controller
public class AdminController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private BoardService boardService;

	@Autowired
	private CommentsService commentsService;

	@Autowired
	private WarnService warnService;

	@Autowired
	private MemberProfileService memberProfileService;

	@Autowired
	private ReplyService replyService;

	@Autowired
	private RecommendService recommendService;

	@Autowired
	private ProhibitService prohibitService;

	@ModelAttribute("searchMap")
	public Map<String,String> searchMap(){
		Map<String,String> map = new HashMap<String, String>();

		map.put("아이디", "memberID");
		map.put("닉네임", "nickName");

		return map;
	}

	@RequestMapping(value = "/adminPage.do")
	public String admingPage(MemberVO mVO, HttpSession session, Model model) {
		System.out.println("로그: Admin: admingPage() ");

		if((Integer)session.getAttribute("role") == null || (Integer)session.getAttribute("role") != 2) {
			model.addAttribute("title", "잘못된 접근입니다.");
			model.addAttribute("text", "다시 한번 확인해주세요.");
			model.addAttribute("icon", "warning");

			return "goback.jsp";
		}

		if(mVO.getRole() ==	0) {
			mVO.setRole(2);
		}
		mVO.setSearchCondition("userList");

		if(mVO.getSearchType() == null) {
			mVO.setSearchType("memberID");
		}

		if(mVO.getSearchText() == null) { 
			mVO.setSearchText("");
		}

		List<MemberVO> mdatas = memberService.selectAll(mVO);

		model.addAttribute("searchRole", mVO.getRole());
		model.addAttribute("mdatas", mdatas);

		return "adminPage.jsp";

	}

	@RequestMapping(value = "/prohibitListPage.do")
	public String prohibitListPage(BoardVO bVO, CommentsVO cVO, PageVO pageVO, HttpSession session, Model model) {
		System.out.println("로그: Admin: prohibitListPage()");

		if((Integer)session.getAttribute("role") == null || (Integer)session.getAttribute("role") != 2) {
			model.addAttribute("title", "잘못된 접근입니다.");
			model.addAttribute("text", "다시 한번 확인해주세요.");
			model.addAttribute("icon", "warning");

			return "goback.jsp";
		}

		if (pageVO.getCurrentPage() > 0) {
			pageVO.setCurrentPage(pageVO.getCurrentPage());
		} else {
			pageVO.setCurrentPage(1);
		}

		bVO.setSearchCondition("prohibitBoard");

		List<BoardVO> phbdatas = boardService.selectAll(bVO);

		if(!(phbdatas == null || phbdatas.isEmpty())) {
			for(int i = 0; i < phbdatas.size(); i++) {
				cVO.setBoardNum(phbdatas.get(i).getBoardNum());

				phbdatas.get(i).setBoardCommentsCnt(commentsService.selectAll(cVO).size());
			}
		}

		pageVO.setTotalPosts(phbdatas.size());

		int startIdx = (pageVO.getCurrentPage() - 1) * pageVO.getPostPerPage();
		int endIdx = Math.min(pageVO.getCurrentPage() * pageVO.getPostPerPage(), pageVO.getTotalPosts());

		for (int i = startIdx; i < endIdx; i++) {
			pageVO.getCurrentPageBoards().add(phbdatas.get(i));
		}
		pageVO.setCurrentPageBoards(pageVO.getCurrentPageBoards());

		model.addAttribute("pagedata", pageVO);

		return "prohibitListPage.jsp";
	}

	@RequestMapping(value = "/deleteProhibitList.do", method = RequestMethod.POST)
	public String deleteProhibitList(@RequestParam("number") List<Integer> boardNums,CommentsVO cVO, BoardVO bVO, WarnVO wVO,RecommendVO rcVO,ProhibitVO pVO,ReplyVO rVO, Model model) {
		System.out.println("로그: Admin: deleteProhibitList() ");

		boolean totalFlag = false;

		rcVO.setSearchCondition("commonNum");
		pVO.setSearchCondition("commonNum");
		wVO.setSearchCondition("boardWarn");

		bVO.setSearchCondition("prohibitBoard");

		List<BoardVO> phbdatas = boardService.selectAll(bVO);

		for(int i = 0; i < phbdatas.size(); i++) {
			for (Integer boardNum : boardNums) {

				cVO.setBoardNum(boardNum);
				List<CommentsVO> cdatas = commentsService.selectAll(cVO);
				for(int j=0;j<cdatas.size();j++) {

					rVO.setCommentsNum(cdatas.get(j).getCommentsNum());
					rVO.setSearchCondition("commentsReplyNum");
					List<ReplyVO> rdatas= replyService.selectAll(rVO);

					for(int k=0; k < rdatas.size(); k++) {
						pVO.setCommonNum(rdatas.get(k).getReplyNum());

						prohibitService.delete(pVO);
					}

					pVO.setCommonNum(cdatas.get(j).getCommentsNum());

					prohibitService.delete(pVO);
				}

				bVO.setBoardNum(boardNum);

				bVO.setSearchCondition("boardNum");

				if (boardService.delete(bVO)) {
					rcVO.setCommonNum(bVO.getBoardNum());

					pVO.setCommonNum(bVO.getBoardNum());

					recommendService.delete(rcVO);

					prohibitService.delete(pVO);

					wVO.setMemberID(phbdatas.get(i).getMemberID());

					boolean flag = warnService.insert(wVO);

					if(!flag) {
						System.out.println("adminController deleteProhibitList 경고누적 실패");
					}
				}
				else {
					System.out.println("total: 안됨");
					totalFlag = true;
				}
			}
		}

		if (totalFlag) {
			model.addAttribute("title", "일부 신고글 삭제 실패!");
			model.addAttribute("text", "다시 시도해주세요");
			model.addAttribute("icon", "warning");
		}
		else {
			model.addAttribute("title", "신고글 삭제 성공!");
			model.addAttribute("icon", "success");
		}

		model.addAttribute("url", "prohibitListPage.do");

		return "SweetAlert2.jsp";
	}

	@RequestMapping(value = "/prohibitMemberListPage.do")
	public String prohibitMemberListPage(MemberProfileVO mpVO, Model model) {
		System.out.println("로그: Admin: prohibitMemberListPage() ");

		mpVO.setSearchCondition("prohibitProfile");

		List<MemberProfileVO> mpdatas = memberProfileService.selectAll(mpVO);

		model.addAttribute("mpdatas", mpdatas);

		return "prohibitMemberListPage.jsp";
	}

	@RequestMapping(value = "/deleteMemberProhibitList.do", method = RequestMethod.POST)
	public String deleteMemberProhibitList(@RequestParam("number") List<String> profileNums, MemberProfileVO mpVO, WarnVO wVO, Model model) {
		System.out.println("로그: Admin: deleteMemberProhibitList() ");

		mpVO.setSearchCondition("prohibitProfile");

		List<MemberProfileVO> phmdatas = memberProfileService.selectAll(mpVO);

		boolean totalFlag = false;

		for(int i = 0; i < phmdatas.size(); i++) {
			for (String profileNum : profileNums) {
				if(phmdatas.get(i).getProfileNum() == Integer.parseInt(profileNum)) {

					mpVO = phmdatas.get(i);

					mpVO.setSearchCondition("profileReset");

					if (!memberProfileService.update(mpVO)) {
						totalFlag = true;
					}

					wVO.setSearchCondition("profileWarn");
					wVO.setMemberID(mpVO.getMemberID());

					boolean flag = warnService.insert(wVO);

					if(!flag) {
						System.out.println("adminController deleteMemberProhibitList 경고누적 실패");
					}

				}
			}
		}
		if (totalFlag) {
			model.addAttribute("title", "프로필 리셋 일부 실패!");
			model.addAttribute("text", "다시 시도해주세요");
			model.addAttribute("icon", "warning");
		}
		else {
			model.addAttribute("title", "프로필 리셋 성공!");
			model.addAttribute("icon", "success");
		}

		model.addAttribute("url", "prohibitMemberListPage.do");

		return "SweetAlert2.jsp";
	}

	@RequestMapping(value = "/updateMemberRole.do", method = RequestMethod.POST)
	public String updateMemberRole(@RequestParam("mid") List<String> mids, MemberVO mVO, WarnVO wVO, Model model) {
		System.out.println("로그: AdminController: updateMemberRole() ");

		boolean flag = false;

		for(String mid : mids) {
			mVO.setSearchCondition("duplicateID");
			mVO.setMemberID(mid);

			mVO = memberService.selectOne(mVO);
			System.out.println("mVO.getRole= "+ mVO.getRole());

			if(mVO.getRole() == 2 || mVO.getRole() == 9) {
				mVO.setSearchCondition("downGradeUser");
			}
			else if(mVO.getRole() == 3) {
				mVO.setSearchCondition("upgradeAdmin");
			}
			flag = memberService.update(mVO);

			wVO.setMemberID(mid);

			warnService.delete(wVO);
		}

		if (flag) {
			model.addAttribute("title", "등급 변경 성공!");
			model.addAttribute("icon", "success");
		}
		else {
			model.addAttribute("title", "등급 변경 실패!");
			model.addAttribute("text", "다시 시도해주세요");
			model.addAttribute("icon", "warning");
		}

		model.addAttribute("url", "adminPage.do");

		return "SweetAlert2.jsp";
	}

}
