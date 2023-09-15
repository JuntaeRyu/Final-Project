package com.spring.biz.page;
import java.util.ArrayList;

import com.spring.biz.board.BoardVO;

public class PageVO {

	private static int postPerPage=10;

	private int currentPage;
	private int totalPosts;
	
	private ArrayList<BoardVO> currentPageBoards=new ArrayList<BoardVO>();
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPosts() {
		return totalPosts;
	}
	public void setTotalPosts(int totalPosts) {
		this.totalPosts = totalPosts;
	}
	public int getPostPerPage() {
		return postPerPage;
	}
	public void setPostPerPage(int postPerPage) {
		PageVO.postPerPage = postPerPage;
	}
	public ArrayList<BoardVO> getCurrentPageBoards() {
		return currentPageBoards;
	}
	public void setCurrentPageBoards(ArrayList<BoardVO> currentPageBoards) {
		this.currentPageBoards = currentPageBoards;
	}
}