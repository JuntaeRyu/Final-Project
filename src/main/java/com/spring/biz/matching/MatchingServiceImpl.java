package com.spring.biz.matching;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MatchingService")
public class MatchingServiceImpl implements MatchingService {

	@Autowired
	private MatchingDAO2 mcDAO;
	
	@Override
	public boolean insert(MatchingVO mcVO) {
		return mcDAO.insert(mcVO);
	}

	@Override
	public List<MatchingVO> selectAll(MatchingVO mcVO) {
		return mcDAO.selectAll(mcVO);
	}

	@Override
	public MatchingVO selectOne(MatchingVO mcVO) {
		return mcDAO.selectOne(mcVO);
	}

	@Override
	public boolean update(MatchingVO mcVO) {
		return mcDAO.update(mcVO);
	}

	@Override
	public boolean delete(MatchingVO mcVO) {
		return mcDAO.delete(mcVO);
	}

}
