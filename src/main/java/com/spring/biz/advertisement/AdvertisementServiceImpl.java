package com.spring.biz.advertisement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdvertisementService")
public class AdvertisementServiceImpl implements AdvertisementService {

	@Autowired
	private InterfaceAdvertisementDAO aDAO;
	
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
	
	// 프로그램 실행할 때마다 크롤링이 추가되서 데이터가 중복되는게 불편하다는 팀원의 문의가 있었습니다
	// 해당 문의를 해결하기 위해 테이블을 지우고 다시 만드는 기능을 추가합니다(특수)
	@Override
	public boolean reset(AdvertisementVO aVO) {
		return aDAO.reset(aVO);
	}

}
