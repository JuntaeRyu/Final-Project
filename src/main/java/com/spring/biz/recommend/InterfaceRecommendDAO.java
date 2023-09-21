package com.spring.biz.recommend;

import java.util.List;

public interface InterfaceRecommendDAO {

	public RecommendVO selectOne(RecommendVO rcVO);
	public List<RecommendVO> selectAll(RecommendVO rcVO);
	
	public boolean insert(RecommendVO rcVO);
	public boolean update(RecommendVO rcVO);
	public boolean delete(RecommendVO rcVO);
}
