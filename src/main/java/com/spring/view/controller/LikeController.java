package com.spring.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.biz.board.BoardService;
import com.spring.biz.board.BoardVO;
import com.spring.biz.prohibit.ProhibitService;
import com.spring.biz.prohibit.ProhibitVO;
import com.spring.biz.recommend.RecommendService;
import com.spring.biz.recommend.RecommendVO;

@Controller
public class LikeController {

	@Autowired
	private RecommendService recommendService;
	
	@Autowired
	private ProhibitService prohibitService;
	
	@Autowired
	private BoardService boardService;
	
	@ResponseBody
	@RequestMapping(value = "/RcServlet.do", produces = "application/json; charset=utf-8")
	public String recommend(RecommendVO rcVO, HttpSession session, HttpServletRequest request) {
		System.out.println("로그: LikeController: Recommend() ");
		
		// AJAX를 통해 전송된 값을 가져와서 서버 콘솔에 출력합니다.
        System.out.println("ajax 로그 : " + request.getParameter("rcresult"));
        
        rcVO.setMemberID((String)session.getAttribute("memberID"));
        rcVO.setCommonNum(Integer.parseInt(request.getParameter("boardNum")));
        
        // AJAX를 통해 받은 값(사용자 추천: 0 또는 1)을 확인합니다.
        if (request.getParameter("rcresult").equals("0")) {
            // 추천을 데이터베이스에 추가합니다.
            recommendService.insert(rcVO);

            // 클라이언트에게 성공 응답을 보냅니다. (1은 성공을 나타냄)
            return "1";
        } 
        else if (request.getParameter("rcresult").equals("1")) {
            // 사용자가 이미 추천했을 경우, 데이터베이스에서 해당 추천을 삭제합니다.
            rcVO = recommendService.selectOne(rcVO);
            
            recommendService.delete(rcVO);

            // 클라이언트에게 성공 응답을 보냅니다. (0은 성공을 나타냄)
            return "0";
        }
        request.setAttribute("title", "잘못된 요청입니다..");
        request.setAttribute("text", "다시한번 확인해주세요..");
        request.setAttribute("icon", "warning");
		
		return "goback.jsp";
	}
	
	@ResponseBody
	@RequestMapping(value = "/PhServlet.do", produces = "application/json; charset=utf-8")
	public String prohibit(ProhibitVO pVO, BoardVO bVO, HttpSession session, HttpServletRequest request) {
		System.out.println("로그: LikeController:  prohibit() ");
		
        System.out.println("ajax 로그 : " + request.getParameter("phresult"));

        bVO.setSearchCondition("prohibit");
        
        pVO.setMemberID((String)session.getAttribute("memberID"));
        pVO.setCommonNum(Integer.parseInt(request.getParameter("boardNum")));

        // AJAX 요청으로부터 받은 "phresult" 값을 확인합니다.
        if (request.getParameter("phresult").equals("0")) {
            // "phresult" 값이 "0"이면 글을 신고하고, 해당 글의 신고 상태를 업데이트합니다.
            prohibitService.insert(pVO);
            
            boardService.update(bVO);
            
            return "1"; // 클라이언트에게 성공 응답을 보냅니다. (1은 성공을 나타냄)
        } 
        else if (request.getParameter("phresult").equals("1")) {
            // "phresult" 값이 "1"이면 글 신고를 취소하고, 해당 글의 신고 상태를 업데이트합니다.
            pVO = prohibitService.selectOne(pVO);
            
            prohibitService.delete(pVO);
            
            boardService.update(bVO);
            
            return "0"; // 클라이언트에게 성공 응답을 보냅니다. (0은 성공을 나타냄)
        }
        request.setAttribute("title", "잘못된 요청입니다..");
        request.setAttribute("text", "다시한번 확인해주세요..");
        request.setAttribute("icon", "warning");
		
		return "goback.jsp";
	}
}