package com.spring.view.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.board.BoardService;
import com.spring.biz.board.BoardVO;
import com.spring.biz.comments.CommentsService;
import com.spring.biz.comments.CommentsVO;
import com.spring.biz.memberProfile.MemberProfileService;
import com.spring.biz.memberProfile.MemberProfileVO;

@Controller
public class MainController {

	@Autowired
	private MemberProfileService memberProfileService;

	@Autowired
	private BoardService boardService;

	@Autowired
	private CommentsService commentsService;

	@RequestMapping(value = "/main.do")
	public String main(MemberProfileVO mpVO, BoardVO bVO,CommentsVO cVO, Random random, Model model) {
		System.out.println("로그: MainController: main() ");
		
		mpVO.setSearchCondition("mainProfile");
		
		List<MemberProfileVO> mpdatas = memberProfileService.selectAll(mpVO);
		System.out.println("mpdatas:" + mpdatas);

		if(mpdatas.isEmpty() || mpdatas.size()==0) {
			System.out.println("멤버 프로필 data가 비어있음");
		}

		bVO.setSearchCondition("recommendRank"); 

		List<BoardVO> bdatas = boardService.selectAll(bVO); // 1 2 3 4 5등 나눠서 저장시키기

		if(!(bdatas == null || bdatas.isEmpty())) {
			for(int i = 0; i < bdatas.size(); i++) {
				cVO.setBoardNum(bdatas.get(i).getBoardNum());

				bdatas.get(i).setBoardCommentsCnt(commentsService.selectAll(cVO).size());
			}

			bVO = bdatas.get(0);
			bdatas.remove(0);


		}
		model.addAttribute("mempdatas", mpdatas);
		model.addAttribute("firstBdata", bVO);
		model.addAttribute("bdatas", bdatas);
		
		return "main.jsp";
	}
}