package com.spring.biz.warn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("warnService")
public class WarnServiceImpl implements WarnService {
	
	@Autowired
	private InterfaceWarnDAO wDAO;

	@Override
	public boolean insert(WarnVO wVO) {
		return wDAO.insert(wVO);
	}

	@Override
	public List<WarnVO> selectAll(WarnVO wVO) {
		return wDAO.selectAll(wVO);
	}

	@Override
	public WarnVO selectOne(WarnVO wVO) {
		return wDAO.selectOne(wVO);
	}

	@Override
	public boolean update(WarnVO wVO) {
		return wDAO.update(wVO);
	}

	@Override
	public boolean delete(WarnVO wVO) {
		return wDAO.delete(wVO);
	}
}
