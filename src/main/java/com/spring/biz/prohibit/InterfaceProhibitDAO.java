package com.spring.biz.prohibit;

import java.util.List;

public interface InterfaceProhibitDAO {

	public ProhibitVO selectOne(ProhibitVO pVO);
	public List<ProhibitVO> selectAll(ProhibitVO pVO);
	
	public boolean insert(ProhibitVO pVO);
	public boolean update(ProhibitVO pVO);
	public boolean delete(ProhibitVO pVO);
}
