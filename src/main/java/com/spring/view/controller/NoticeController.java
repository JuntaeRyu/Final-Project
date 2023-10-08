package com.spring.view.controller;

import java.util.ArrayList;
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
import com.spring.biz.page.PageVO;
import com.spring.biz.prohibit.ProhibitService;
import com.spring.biz.prohibit.ProhibitVO;
import com.spring.biz.recommend.RecommendService;
import com.spring.biz.recommend.RecommendVO;
import com.spring.biz.reply.ReplyService;
import com.spring.biz.reply.ReplyVO;

@Controller
public class NoticeController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private CommentsService commentsService;

	@Autowired
	private ReplyService replyService;

	@Autowired
	private RecommendService recommendService;

	@Autowired
	private ProhibitService prohibitService;

	@RequestMapping(value = "/noticeListPage.do")
	public String notcieListPage(BoardVO  bVO, PageVO pageVO, Model model) {
		System.out.println("로그: Notice: noticeListPage() ");

		if (pageVO.getCurrentPage() > 0) {
			pageVO.setCurrentPage(pageVO.getCurrentPage());
		} else {
			pageVO.setCurrentPage(1);
		}

		// 공지사항 목록을 조회하기 위해 bVO에 설정합니다.
		bVO.setSearchCondition("community");
		bVO.setCategory(0);

		// 전체 공지사항 목록을 조회합니다.
		List<BoardVO> bdatas = new ArrayList<BoardVO>();

		bdatas = boardService.selectAll(bVO);

		// 전체 공지사항 개수를 구합니다.
		pageVO.setTotalPosts(bdatas.size());

		// 한 페이지에 보여줄 공지사항 개수를 설정합니다. (10개씩 표시)

		// 현재 페이지에 해당하는 공지사항의 시작 인덱스와 끝 인덱스를 계산합니다.
		int startIdx = (pageVO.getCurrentPage() - 1) * pageVO.getPostPerPage();
		int endIdx = Math.min(pageVO.getCurrentPage() * pageVO.getPostPerPage(), pageVO.getTotalPosts());

		// 현재 페이지에 해당하는 공지사항만 currentPageNotices에 추가합니다.
		for (int i = startIdx; i < endIdx; i++) {
			pageVO.getCurrentPageBoards().add(bdatas.get(i));
		} 	
		pageVO.setCurrentPageBoards(pageVO.getCurrentPageBoards());

		model.addAttribute("pagedata", pageVO);

		// forward 객체를 설정하여 noticeListPage.jsp로 포워딩합니다.
		return "noticeListPage.jsp";
	}

	@RequestMapping(value = "/noticeDetailPage.do")
	public String noticeDetailPage(BoardVO bVO, CommentsVO cVO, RecommendVO rcVO, ReplyVO rVO, ProhibitVO pVO, HttpSession session, HttpServletRequest request) {
		System.out.println("로그: Notice: noticeDetailPage() ");

		rcVO.setMemberID((String)session.getAttribute("memberID"));

		rcVO = recommendService.selectOne(rcVO);

		if(rcVO != null) {
			request.setAttribute("recommend", 1);
		}
		else {
			request.setAttribute("recommend", 0);
		}

		rVO.setSearchCondition("totalReply");

		List<CommentsVO> cdatas = commentsService.selectAll(cVO);

		List<CommentsVO> comments = new ArrayList<CommentsVO>();

		List<ReplyVO> rdatas = replyService.selectAll(rVO);

		List<ReplyVO> replies = new ArrayList<ReplyVO>();

		bVO = boardService.selectOne(bVO);

		for(int i = 0; i < cdatas.size(); i++) {
			if(bVO.getBoardNum() == cdatas.get(i).getBoardNum()) {
				comments.add(cdatas.get(i));

				for(int j = 0; j < rdatas.size(); j++) {
					if(cdatas.get(i).getCommentsNum() == rdatas.get(j).getCommentsNum()) {
						replies.add(rdatas.get(j));
					}
				}
			}
		}

		for(int i = 0; i < comments.size(); i++) {
			comments.get(i).setCheck(0);

			pVO = new ProhibitVO();

			pVO.setMemberID((String)session.getAttribute("memberID"));
			pVO.setCommonNum(comments.get(i).getCommentsNum());

			pVO = prohibitService.selectOne(pVO);

			if(pVO != null) {
				if(pVO.getCommonNum() == comments.get(i).getCommentsNum()) {
					comments.get(i).setCheck(1);
				}
			}
		}

		for(int i = 0; i < replies.size(); i++) {
			replies.get(i).setCheck(0);

			pVO = new ProhibitVO();

			pVO.setMemberID((String)session.getAttribute("memberID"));
			pVO.setCommonNum(replies.get(i).getReplyNum());

			pVO = prohibitService.selectOne(pVO);

			if(pVO != null) {
				if(pVO.getCommonNum() == replies.get(i).getReplyNum()) {
					replies.get(i).setCheck(1);
				}
			}
		}

		// 게시글 데이터가 조회되었을 경우, 게시글 정보를 JSP 페이지에서 사용할 수 있도록 request에 저장합니다.
		if(bVO != null) {
			request.setAttribute("bdata", bVO);
			request.setAttribute("cdatas", comments);
			request.setAttribute("rdatas", replies);

			//////////////사이드바 - 해당게시물 작성자의 또다른 글 3개 ///////////////////
			bVO.setSearchCondition("writerBoard");

			List<BoardVO> writerBoards = boardService.selectAll(bVO);

			request.setAttribute("writerbdatas", writerBoards);
			///////////////////////////////////////

			bVO.setSearchCondition("viewCnt");

			boardService.update(bVO);

			////////////// 사이드바 - 이 달의 1등 게시물 /////////////////////////////
			bVO.setSearchCondition("topBoard");

			bVO = boardService.selectOne(bVO);

			request.setAttribute("topbdata", bVO);
			/////////////////////////////////////////////////////////////////

			return "noticeDetailPage.jsp";
		}
		else {
			request.setAttribute("title", "요청실패..");
			request.setAttribute("text", "다시한번 확인해주세요.." );
			request.setAttribute("icon", "warning" );

			return "goback.jsp";
		}
	}

	@RequestMapping(value = "/insertNoticePage.do")
	public String insertNoticePage(HttpSession session, Model model) {
		System.out.println("로그: Notice: insertNoticePage() ");

		if((Integer)session.getAttribute("role") != 2) {
			model.addAttribute("title", "잘못된 요청입니다.." );
			model.addAttribute("text", "다시한번 확인해주세요.." );
			model.addAttribute("icon", "warning" );

			return "goback.jsp";
		}

		return "redirect:insertNoticePage.jsp";
	}

	@RequestMapping(value = "/insertNotice.do", method = RequestMethod.POST)
	public String insertNotice(BoardVO bVO, HttpSession session, Model model) {
		System.out.println("로그: Notice: insertNotice() ");

		if (bVO.getContent() == null || bVO.getContent().isEmpty() || bVO.getContent().isBlank()) {
			model.addAttribute("title", "공지사항 작성실패!" );
			model.addAttribute("text", "공지사항 내용이 없습니다!" );
			model.addAttribute("icon", "warning" );

			return "goback.jsp";
		}
		
		bVO.setMemberID((String)session.getAttribute("memberID"));

		boolean flag = boardService.insert(bVO);

		if(flag) {
			model.addAttribute("title", "공지사항 작성 성공!");
			model.addAttribute("icon", "success");
			model.addAttribute("url", "noticeListPage.do");

			return "SweetAlert2.jsp";
		} else {
			model.addAttribute("title", "공지사항작성실패.." );
			model.addAttribute("text", "다시한번 확인해주세요.." );
			model.addAttribute("icon", "warning" );

			return "goback.jsp";
		}
	}

	@RequestMapping(value = "/updateNoticePage.do", method = RequestMethod.POST)
	public String updateNoticePage(BoardVO bVO, HttpSession session, Model model) {
		System.out.println("로그: Notice: updateNoticePage() ");

		if((Integer)session.getAttribute("role") != 2) {
			model.addAttribute("title", "잘못된 요청입니다.." );
			model.addAttribute("text", "다시한번 확인해주세요.." );
			model.addAttribute("icon", "warning" );

			return "goback.jsp";
		}

		bVO = boardService.selectOne(bVO);

		if (bVO != null) {
			model.addAttribute("bdata", bVO);

			return "updateNoticePage.jsp";
		}
		else {
			model.addAttribute("title", "요청실패.." );
			model.addAttribute("text", "다시한번 확인해주세요.." );
			model.addAttribute("icon", "warning" );

			return "goback.jsp";
		}

	}

	@RequestMapping(value = "/updateNotice.do", method = RequestMethod.POST)
	public String updateNotice(BoardVO bVO, Model model) {
		System.out.println("로그: Notice: updateNotice() ");

		if (bVO.getContent() == null || bVO.getContent().isEmpty() || bVO.getContent().isBlank()) {
			model.addAttribute("title", "공지사항 수정실패!" );
			model.addAttribute("text", "내용이 없습니다!" );
			model.addAttribute("icon", "warning" );

			return "goback.jsp";
		}
		
		bVO.setSearchCondition("updateBoard");

		boolean flag = boardService.update(bVO);

		if (flag) {
			model.addAttribute("title", "공지사항 수정 성공!");
			model.addAttribute("icon", "success");
			model.addAttribute("url", "noticeDetailPage.do?boardNum="+bVO.getBoardNum());

			return "SweetAlert2.jsp";
		} else {
			model.addAttribute("title", "공지사항 수정 실패..");
			model.addAttribute("text", "다시한번 확인해주세요..");
			model.addAttribute("icon", "warning");

			return "goback.jsp";
		}
	}

	@RequestMapping(value = "/deleteNotice.do", method = RequestMethod.POST)
	public String deleteNotice(BoardVO bVO, RecommendVO rcVO, ProhibitVO pVO,CommentsVO cVO,ReplyVO rVO, Model model) {
		System.out.println("로그: Notice: deleteNotice() ");

		bVO.setSearchCondition("boardNum");
		rcVO.setSearchCondition("commonNum");
		pVO.setSearchCondition("commonNum");

		cVO.setBoardNum(bVO.getBoardNum());
		List<CommentsVO> cdatas = commentsService.selectAll(cVO);
		for(int i=0;i<cdatas.size();i++) {

			rVO.setCommentsNum(cdatas.get(i).getCommentsNum());
			rVO.setSearchCondition("commentsReplyNum");
			List<ReplyVO> rdatas= replyService.selectAll(rVO);

			for(int j=0; j < rdatas.size(); j++) {
				pVO.setCommonNum(rdatas.get(j).getReplyNum());

				prohibitService.delete(pVO);
			}

			pVO.setCommonNum(cdatas.get(i).getCommentsNum());

			prohibitService.delete(pVO);
		}

		boolean flag = boardService.delete(bVO);

		if (flag) {
			model.addAttribute("title", "공지사항 삭제 성공!");
			model.addAttribute("icon", "success");
			model.addAttribute("url", "noticeListPage.do");

			rcVO.setCommonNum(bVO.getBoardNum());

			pVO.setCommonNum(bVO.getBoardNum());

			recommendService.delete(rcVO);

			prohibitService.delete(pVO);

			return "SweetAlert2.jsp";
		}
		else {
			// 삭제 실패 메시지를 설정하기 위해 필요한 데이터를 request에 저장합니다.
			model.addAttribute("title", "공지사항삭제실패..");
			model.addAttribute("text", "다시 한번 확인해주세요..");
			model.addAttribute("icon", "warning");

			return "goback.jsp";
		}
	}
}
