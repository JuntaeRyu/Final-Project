package com.spring.biz.advertisement;

import java.util.List;

public interface AdvertisementService {

	boolean insert (AdvertisementVO aVO);
	List<AdvertisementVO> selectAll (AdvertisementVO aVO);
	AdvertisementVO selectOne (AdvertisementVO aVO);
	boolean update (AdvertisementVO aVO);
	boolean delete (AdvertisementVO aVO);
	
	
}
