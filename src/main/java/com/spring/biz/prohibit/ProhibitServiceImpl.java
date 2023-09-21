package com.spring.biz.prohibit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("prohibitService")
public class ProhibitServiceImpl implements ProhibitService{

	@Autowired
	private InterfaceProhibitDAO pDAO;
	
	@Override
	public ProhibitVO selectOne(ProhibitVO pVO) {
		return pDAO.selectOne(pVO);
	}

	@Override
	public List<ProhibitVO> selectAll(ProhibitVO pVO) {
		return pDAO.selectAll(pVO);
	}

	@Override
	public boolean insert(ProhibitVO pVO) {
		return pDAO.insert(pVO);
	}

	@Override
	public boolean update(ProhibitVO pVO) {
		return pDAO.update(pVO);
	}

	@Override
	public boolean delete(ProhibitVO pVO) {
		return pDAO.delete(pVO);
	}

}
