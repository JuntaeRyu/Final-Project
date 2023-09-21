package com.spring.biz.board;

import java.util.List;

public interface InterfaceBoardDAO {
	public BoardVO selectOne(BoardVO bVO);
	public List<BoardVO> selectAll(BoardVO bVO);
	public boolean insert(BoardVO bVO);
	public boolean update(BoardVO bVO);
	public boolean delete(BoardVO bVO);
}
