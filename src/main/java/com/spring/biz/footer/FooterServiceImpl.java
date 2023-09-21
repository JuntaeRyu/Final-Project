package com.spring.biz.footer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("FooterService")
public class FooterServiceImpl implements FooterService {

	@Autowired
	private InterfaceFooterDAO fDAO;
	
	@Override
	public boolean insert(FooterVO fVO) {
		return fDAO.insert(fVO);
	}

	@Override
	public List<FooterVO> selectAll(FooterVO fVO) {
		return fDAO.selectAll(fVO);
	}

	@Override
	public FooterVO selectOne(FooterVO fVO) {
		return fDAO.selectOne(fVO);
	}

	@Override
	public boolean update(FooterVO fVO) {
		return fDAO.update(fVO);
	}

	@Override
	public boolean delete(FooterVO fVO) {
		return fDAO.delete(fVO);
	}

}
