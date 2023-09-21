package com.spring.biz.footer;

import java.util.List;

public interface InterfaceFooterDAO {

	public boolean insert(FooterVO fVO);
	public List<FooterVO> selectAll(FooterVO fVO);
	public FooterVO selectOne(FooterVO fVO);
	public boolean update(FooterVO fVO);
	public boolean delete(FooterVO fVO);
	
}
