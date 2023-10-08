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
public class BoardController {

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


	@RequestMapping(value = "/boardListPage.do")
	public String boardListPage(BoardVO bVO, CommentsVO cVO, PageVO pageVO, Model model) {
		System.out.println("로그: Board: boardListPage() ");

		if (pageVO.getCurrentPage() > 0) {
			pageVO.setCurrentPage(pageVO.getCurrentPage());
		} else {
			pageVO.setCurrentPage(1);
		}

		// "전체커뮤니티"에 해당하는 모든 게시글 리스트를 가져옵니다.
		bVO.setSearchCondition("totalCommunity");

		List<BoardVO> bdatas = boardService.selectAll(bVO);

		if(!(bdatas == null || bdatas.isEmpty())) {
			for(int i = 0; i < bdatas.size(); i++) {
				cVO.setBoardNum(bdatas.get(i).getBoardNum());

				bdatas.get(i).setBoardCommentsCnt(commentsService.selectAll(cVO).size());
			}
		}

		pageVO.setTotalPosts(bdatas.size());

		// 현재 페이지에 보여줄 게시글 범위를 계산합니다.
		int startIdx = (pageVO.getCurrentPage() - 1) * pageVO.getPostPerPage();
		int endIdx = Math.min(pageVO.getCurrentPage() * pageVO.getPostPerPage(), pageVO.getTotalPosts());

		// 현재 페이지에 해당하는 게시글 리스트를 추출합니다.
		for (int i = startIdx; i < endIdx; i++) {
			//			currentPageBoards.add(bdatas.get(i));
			pageVO.getCurrentPageBoards().add(bdatas.get(i));
		}
		pageVO.setCurrentPageBoards(pageVO.getCurrentPageBoards());

		model.addAttribute("pagedata", pageVO);

		return "boardListPage.jsp";
	}

	@RequestMapping(value = "/infoListPage.do")
	public String infoListPage(BoardVO bVO, CommentsVO cVO, PageVO pageVO, Model model) {
		System.out.println("로그: Board: infoListPage() ");

		if (pageVO.getCurrentPage()> 0) {
			pageVO.setCurrentPage(pageVO.getCurrentPage());
		} else {
			pageVO.setCurrentPage(1);
		}

		// "커뮤니티"에 해당하는 카테고리 1에 속하는 게시글 리스트를 가져옵니다.
		bVO.setSearchCondition("community");
		bVO.setCategory(1);

		List<BoardVO> bdatas = boardService.selectAll(bVO);

		if(!(bdatas == null || bdatas.isEmpty())) {
			for(int i = 0; i < bdatas.size(); i++) {
				cVO.setBoardNum(bdatas.get(i).getBoardNum());

				bdatas.get(i).setBoardCommentsCnt(commentsService.selectAll(cVO).size());
			}
		}

		pageVO.setTotalPosts(bdatas.size());

		int startIdx = (pageVO.getCurrentPage() - 1) * pageVO.getPostPerPage();
		int endIdx = Math.min(pageVO.getCurrentPage() * pageVO.getPostPerPage(), pageVO.getTotalPosts());

		for (int i = startIdx; i < endIdx; i++) {
			pageVO.getCurrentPageBoards().add(bdatas.get(i));
		}
		pageVO.setCurrentPageBoards(pageVO.getCurrentPageBoards());

		model.addAttribute("pagedata", pageVO);

		return "infoListPage.jsp";
	}

	@RequestMapping(value = "/chatListPage.do")
	public String chatListPage(BoardVO bVO, CommentsVO cVO, PageVO pageVO, Model model) {
		System.out.println("로그: Board: chatListPage() ");

		if (pageVO.getCurrentPage()> 0) {
			pageVO.setCurrentPage(pageVO.getCurrentPage());
		} else {
			pageVO.setCurrentPage(1);
		}

		// "커뮤니티"에 해당하는 카테고리 1에 속하는 게시글 리스트를 가져옵니다.
		bVO.setSearchCondition("community");
		bVO.setCategory(2);

		List<BoardVO> bdatas = boardService.selectAll(bVO);

		if(!(bdatas == null || bdatas.isEmpty())) {
			for(int i = 0; i < bdatas.size(); i++) {
				cVO.setBoardNum(bdatas.get(i).getBoardNum());

				bdatas.get(i).setBoardCommentsCnt(commentsService.selectAll(cVO).size());
			}
		}

		pageVO.setTotalPosts(bdatas.size());

		int startIdx = (pageVO.getCurrentPage() - 1) * pageVO.getPostPerPage();
		int endIdx = Math.min(pageVO.getCurrentPage() * pageVO.getPostPerPage(), pageVO.getTotalPosts());

		for (int i = startIdx; i < endIdx; i++) {
			pageVO.getCurrentPageBoards().add(bdatas.get(i));
		}
		pageVO.setCurrentPageBoards(pageVO.getCurrentPageBoards());

		model.addAttribute("pagedata", pageVO);

		return "chatListPage.jsp";
	}

	@RequestMapping(value = "/boardDetailPage.do")
	public String boardDetailPage(BoardVO bVO, RecommendVO rcVO, ProhibitVO pVO, 
			CommentsVO cVO, ReplyVO rVO, HttpSession session, HttpServletRequest request) {
		System.out.println("로그: Board: boardDetailPage() ");

		rcVO.setMemberID((String)session.getAttribute("memberID"));
		rcVO.setCommonNum(Integer.parseInt(request.getParameter("boardNum")));

		pVO.setMemberID((String)session.getAttribute("memberID")); 
		pVO.setCommonNum(Integer.parseInt(request.getParameter("boardNum")));

		// RecommendDAO를 통해 해당 게시글에 대한 사용자의 추천 여부를 확인합니다.
		rcVO = recommendService.selectOne(rcVO);

		// ProhibitDAO를 통해 해당 게시글에 대한 사용자의 신고 여부를 확인합니다.
		pVO = prohibitService.selectOne(pVO);

		// BoardDAO를 통해 해당 게시글의 상세 정보를 가져옵니다.
		bVO = boardService.selectOne(bVO);

		// 추천 여부에 따라 request에 "recommend" 속성을 설정합니다.
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

		// 해당 게시글의 상세 정보를 request에 저장하고, boardPage.jsp로 이동합니다.
		if (bVO != null) {
			if(bVO.getBoardImg()!=null) {

				String[] boardImgs = bVO.getBoardImg().split(",");
				System.out.println(boardImgs);
				for(String boardImg : boardImgs) {
					bVO.getBoardImgList().add(boardImg);
				}
				bVO.setBoardImgList(bVO.getBoardImgList());
			}

			request.setAttribute("bdata", bVO);
			request.setAttribute("cdatas", comments);
			request.setAttribute("rdatas", replies);

			////////////// 사이드바 - 해당게시물 작성자의 또다른 글 3개 ///////////////////
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

			return "boardDetailPage.jsp";
		}
		// 해당 게시글이 없는 경우에는 메시지와 함께 경고 페이지를 보여줍니다.
		else {
			request.setAttribute("title", "잘못된 접근입니다.");
			request.setAttribute("text", "다시 한번 확인해주세요.");
			request.setAttribute("icon", "warning");

			return "goback.jsp";
		}
	}

	@RequestMapping(value = "/insertBoardPage.do")
	public String insertBoardPage(HttpSession session, Model model) {
		System.out.println("로그: Board: insertBoardPage() ");

		if(session.getAttribute("memberID") == null || (Integer)session.getAttribute("role") == 9) {
			model.addAttribute("title", "잘못된 접근입니다.");
			model.addAttribute("text", "다시 한번 확인해주세요.");
			model.addAttribute("icon", "warning");

			return "goback.jsp";
		}

		return "insertBoardPage.jsp";
	}

	@RequestMapping(value = "/insertBoard.do", method = RequestMethod.POST)
	public String insertBoard(BoardVO bVO, HttpSession session, Model model) {
		System.out.println("로그: Board: insertBoard() ");

		if (bVO.getContent() == null || bVO.getContent().isEmpty() || bVO.getContent().isBlank()) {
			model.addAttribute("title", "게시글 작성실패!" );
			model.addAttribute("text", "게시글 내용이 없습니다!" );
			model.addAttribute("icon", "warning" );

			return "goback.jsp";
		}
		
		if(bVO.getBoardImg() != null) {

			String imgUrl=bVO.getBoardImg();

			System.out.println("img "+imgUrl);

			// 첫 번째 쉼표(,)까지의 부분을 잘라냄
			int indexOfComma = imgUrl.indexOf(",");
			
			bVO.setBoardImg(imgUrl.substring(indexOfComma + 1));
		}

		boolean flag = boardService.insert(bVO);

		if(flag) {
			model.addAttribute("title", "글 작성 성공!");
			model.addAttribute("icon", "success");
			model.addAttribute("url", "boardListPage.do");

			return "SweetAlert2.jsp";
		}
		else {
			// 게시글 등록이 실패했을 경우, 경고 메시지를 설정하고 이전 페이지로 돌아갑니다.
			model.addAttribute("title", "게시글작성실패.." );
			model.addAttribute("text", "다시한번 확인해주세요.." );
			model.addAttribute("icon", "warning" );

			return "goback.jsp";
		}
	}

	@RequestMapping(value = "/updateBoardPage.do", method = RequestMethod.POST)
	public String updateBoardPage(BoardVO bVO, Model model) {
		System.out.println("로그: Board: updateBoardPage() ");

		bVO = boardService.selectOne(bVO);

		if (bVO != null) {
			model.addAttribute("bdata", bVO);

			return "updateBoardPage.jsp";
		}
		else {
			model.addAttribute("title", "잘못된 접근입니다." );
			model.addAttribute("text", "다시한번 확인해주세요.." );
			model.addAttribute("icon", "warning" );

			return "goback.jsp";
		}
	}

	@RequestMapping(value = "/updateBoard.do", method = RequestMethod.POST)
	public String updateBoard(BoardVO bVO, Model model) {
		System.out.println("로그: Board: updateBoard() ");

		if (bVO.getContent() == null || bVO.getContent().isEmpty() || bVO.getContent().isBlank()) {
			model.addAttribute("title", "게시글 수정 실패!" );
			model.addAttribute("text", "내용이 없습니다!" );
			model.addAttribute("icon", "warning" );

			return "goback.jsp";
		}
		
		bVO.setSearchCondition("updateBoard");

		boolean flag = boardService.update(bVO);

		if(flag) {
			model.addAttribute("title", "게시글 수정 성공!");
			model.addAttribute("icon", "success");
			model.addAttribute("url", "boardDetailPage.do?boardNum="+bVO.getBoardNum());

			return "SweetAlert2.jsp";
		}
		else {
			model.addAttribute("title", "게시글 수정 실패..");
			model.addAttribute("text", "다시 한 번 확인해주세요..");
			model.addAttribute("icon", "warning");

			return "goback.jsp";
		}
	}

	@RequestMapping(value = "/deleteBoard.do", method = RequestMethod.POST)
	public String deleteBoard(BoardVO bVO, RecommendVO rcVO, ProhibitVO pVO,CommentsVO cVO,ReplyVO rVO, HttpSession session, Model model) {
		System.out.println("로그: Board: deleteBoard() ");

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
			model.addAttribute("title", "게시글 삭제 성공!");
			model.addAttribute("icon", "success");
			model.addAttribute("url", "boardListPage.do");
			
			rcVO.setCommonNum(bVO.getBoardNum());
			
			pVO.setCommonNum(bVO.getBoardNum());
			
			recommendService.delete(rcVO);
			
			prohibitService.delete(pVO);
			

			return "SweetAlert2.jsp";
		}
		else {
			model.addAttribute("title", "게시글 삭제 실패..");
			model.addAttribute("text", "다시 한번 확인해주세요");
			model.addAttribute("icon", "warning");

			return "goback.jsp";
		}

	}









































}