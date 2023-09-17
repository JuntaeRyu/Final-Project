package com.spring.view.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.board.BoardService;
import com.spring.biz.board.BoardVO;
import com.spring.biz.comments.CommentsService;
import com.spring.biz.comments.CommentsVO;
import com.spring.biz.page.PageVO;
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
	public String noticeDetailPage(BoardVO bVO, CommentsVO cVO, RecommendVO rcVO, ReplyVO rVO, HttpSession session, HttpServletRequest request) {
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
		
		bVO = boardService.selectOne(bVO);

		// 게시글 데이터가 조회되었을 경우, 게시글 정보를 JSP 페이지에서 사용할 수 있도록 request에 저장합니다.
		if(bVO != null) {
			request.setAttribute("bdata", bVO);
			request.setAttribute("cdatas", comments);
			request.setAttribute("rdatas", replies);

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
	public String insertNoticePage() {
		System.out.println("로그: Notice: insertNoticePage() ");

		return "redirect:insertNoticePage.jsp";
	}

	@RequestMapping(value = "/insertNotice.do")
	public String insertNotice(BoardVO bVO, HttpSession session, Model model) {
		System.out.println("로그: Notice: insertNotice() ");

		bVO.setMemberID((String)session.getAttribute("memberID"));

		boolean flag = boardService.insert(bVO);

		if(flag) {
			return "noticeListPage.do";
		} else {
			model.addAttribute("title", "공지사항작성실패.." );
			model.addAttribute("text", "다시한번 확인해주세요.." );
			model.addAttribute("icon", "warning" );

			return "goback.jsp";
		}
	}

	@RequestMapping(value = "/updateNoticePage.do")
	public String updateNoticePage(BoardVO bVO, Model model) {
		System.out.println("로그: Notice: updateNoticePage() ");

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

	@RequestMapping(value = "/updateNotice.do")
	public String updateNotice(BoardVO bVO, Model model) {
		System.out.println("로그: Notice: updateNotice() ");

		bVO.setSearchCondition("updateBoard");

		boolean flag = boardService.update(bVO);

		if (flag) {
			model.addAttribute("boardNum", bVO.getBoardNum());

			return "noticeDetailPage.do";
		} else {
			model.addAttribute("title", "공지사항 수정 실패..");
			model.addAttribute("text", "다시한번 확인해주세요..");
			model.addAttribute("icon", "warning");

			return "goback.jsp";
		}
	}

	@RequestMapping(value = "/deleteNotice.do")
	public String deleteNotice(BoardVO bVO, Model model) {
		System.out.println("로그: Notice: deleteNotice() ");

		boolean flag = boardService.delete(bVO);

		if (flag) {
			return "noticeListPage.do";
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
