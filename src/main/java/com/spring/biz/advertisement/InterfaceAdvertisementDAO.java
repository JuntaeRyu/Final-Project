package com.spring.biz.advertisement;

import java.util.List;

public interface InterfaceAdvertisementDAO {

	boolean insert (AdvertisementVO aVO);
	List<AdvertisementVO> selectAll (AdvertisementVO aVO);
	AdvertisementVO selectOne (AdvertisementVO aVO);
	boolean update (AdvertisementVO aVO);
	boolean delete (AdvertisementVO aVO);
	// 프로그램 실행할 때마다 크롤링이 추가되서 데이터가 중복되는게 불편하다는 팀원의 문의가 있었습니다
	// 해당 문의를 해결하기 위해 테이블을 지우고 다시 만드는 기능을 추가합니다(특수)
	boolean reset (AdvertisementVO aVO);
	
}
