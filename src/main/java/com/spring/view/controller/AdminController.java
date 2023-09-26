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
import com.spring.biz.page.PageVO;
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

	@ModelAttribute("searchMap")
	public Map<String,String> searchMap(){
		Map<String,String> map = new HashMap<String, String>();
		
		map.put("아이디", "memberID");
		map.put("닉네임", "nickName");
		
		return map;
	}
	
	@RequestMapping(value = "/adminPage.do", method = RequestMethod.GET)
	public String admingPage(MemberVO mVO, HttpSession session, Model model) {
		System.out.println("로그: Admin: admingPage() ");
		
		if((Integer)session.getAttribute("role") != 2) {
			model.addAttribute("title", "잘못된 접근입니다.");
			model.addAttribute("text", "다시 한번 확인해주세요.");
			model.addAttribute("icon", "warning");
			
			return "goback.jsp";
		}

		if(mVO.getRole() ==	0) {
			mVO.setSearchCondition("totalUserList");
		}
		else if(mVO.getRole() == 2 || mVO.getRole() == 3) {
			mVO.setSearchCondition("userList");
		}
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

	@RequestMapping(value = "/prohibitListPage.do", method = RequestMethod.GET)
	public String prohibitListPage(BoardVO bVO, CommentsVO cVO, PageVO pageVO, Model model) {
		System.out.println("로그: Mypage: prohibitListPage()");

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
	public String deleteProhibitList(@RequestParam("number") List<String> boardNums, BoardVO bVO, WarnVO wVO) {
		System.out.println("로그: Mypage: deleteProhibitList() ");
		
		bVO.setSearchCondition("prohibitBoard");

		List<BoardVO> phbdatas = boardService.selectAll(bVO);
		
		for(int i = 0; i < phbdatas.size(); i++) {
			for (String boardNum : boardNums) {
				if(phbdatas.get(i).getBoardNum() == Integer.parseInt(boardNum)) {

					bVO = phbdatas.get(i);

					bVO.setSearchCondition("boardNum");

					boardService.delete(bVO);
					
					wVO.setSearchCondition("boardWarn");
					wVO.setMemberID(bVO.getMemberID());
					
					boolean flag = warnService.insert(wVO);
					
					if(!flag) {
						System.out.println("adminController deleteProhibitList 경고누적 실패");
					}
					
				}
			}
		}
		
		return "redirect:prohibitListPage.do";
		
	}

}
