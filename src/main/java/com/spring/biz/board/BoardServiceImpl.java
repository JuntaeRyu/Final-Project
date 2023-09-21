package com.spring.biz.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("boardService")
public class BoardServiceImpl implements BoardService{

	@Autowired
	private InterfaceBoardDAO bDAO;
	
	@Override
	public BoardVO selectOne(BoardVO bVO) {
		return bDAO.selectOne(bVO);
	}

	@Override
	public List<BoardVO> selectAll(BoardVO bVO) {
		return bDAO.selectAll(bVO);
	}

	@Override
	public boolean insert(BoardVO bVO) {
		return bDAO.insert(bVO);
	}

	@Override
	public boolean update(BoardVO bVO) {
		return bDAO.update(bVO);
	}

	@Override
	public boolean delete(BoardVO bVO) {
		return bDAO.delete(bVO);
	}

}
