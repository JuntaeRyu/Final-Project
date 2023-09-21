package com.spring.biz.matching;

import java.util.List;

public interface InterfaceMatchingDAO {

	public boolean insert(MatchingVO mcVO);
	public List<MatchingVO> selectAll(MatchingVO mcVO);
	public MatchingVO selectOne(MatchingVO mcVO);
	public boolean update(MatchingVO mcVO);
	public boolean delete(MatchingVO mcVO);
	
}
