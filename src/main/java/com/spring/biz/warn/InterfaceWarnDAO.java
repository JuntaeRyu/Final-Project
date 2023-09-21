package com.spring.biz.warn;

import java.util.List;

public interface InterfaceWarnDAO {

	public boolean insert(WarnVO wVO);
	public List<WarnVO> selectAll(WarnVO wVO);
	public WarnVO selectOne(WarnVO wVO);
	public boolean update(WarnVO wVO);
	public boolean delete(WarnVO wVO);
}
