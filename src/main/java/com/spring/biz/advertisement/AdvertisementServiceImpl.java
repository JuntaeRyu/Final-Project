package com.spring.biz.advertisement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("AdvertisementService")
public class AdvertisementServiceImpl implements AdvertisementService {

	@Autowired
	private AdvertisementDAO2 aDAO;
	
	@Override
	public boolean insert(AdvertisementVO aVO) {
		return aDAO.insert(aVO);
	}

	@Override
	public List<AdvertisementVO> selectAll(AdvertisementVO aVO) {
		return aDAO.selectAll(aVO);
	}

	@Override
	public AdvertisementVO selectOne(AdvertisementVO aVO) {
		return aDAO.selectOne(aVO);
	}

	@Override
	public boolean update(AdvertisementVO aVO) {
		return aDAO.update(aVO);
	}

	@Override
	public boolean delete(AdvertisementVO aVO) {
		return aDAO.delete(aVO);
	}

}
