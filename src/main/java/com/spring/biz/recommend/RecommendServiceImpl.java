package com.spring.biz.recommend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("recommendService")
public class RecommendServiceImpl implements RecommendService{

	@Autowired
	private InterfaceRecommendDAO rcDAO;
	
	@Override
	public RecommendVO selectOne(RecommendVO rcVO) {
		return rcDAO.selectOne(rcVO);
	}

	@Override
	public List<RecommendVO> selectAll(RecommendVO rcVO) {
		return rcDAO.selectAll(rcVO);
	}

	@Override
	public boolean insert(RecommendVO rcVO) {
		return rcDAO.insert(rcVO);
	}

	@Override
	public boolean update(RecommendVO rcVO) {
		return rcDAO.update(rcVO);
	}

	@Override
	public boolean delete(RecommendVO rcVO) {
		return rcDAO.delete(rcVO);
	}

}
