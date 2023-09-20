package com.spring.biz.advertisement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("AdvertisementDAO")
public class AdvertisementDAO2 {

	// 광고 자징
	private final String INSERT = "INSERT INTO ADVERTISEMENT "
			+ "(ADVERTISEMENTNUM, SITE, SITEURL, ITEM, ITEMIMG, ITEMPAY) "
			+ "VALUES((SELECT NVL(MAX(ADVERTISEMENTNUM),0)+1 FROM ADVERTISEMENT), ?, ?, ?, ?, ?)";
	// 사이트별 광고 리스트 출력
//	private final String SELECTALL = "SELECT SITE, SITEURL, ITEM, ITEMIMG, ITEMPAY "
//			+ "FROM ADVERTISEMENT WHERE SITE = ?";
	private final String SELECTALL = "SELECT SITE, SITEURL, ITEM, ITEMIMG, ITEMPAY "
			+ "FROM ( SELECT SITE, SITEURL, ITEM, ITEMIMG, ITEMPAY "
			+ "FROM ADVERTISEMENT "
			+ "ORDER BY DBMS_RANDOM.VALUE ) "
			+ "WHERE ROWNUM <= 3";
//		private final String SELECTONE = "";
//		private final String UPDATE = "";
//		private final String DELETE = "";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean insert(AdvertisementVO aVO) {

		Object[] args = { aVO.getSite(), aVO.getSiteUrl(), aVO.getItem(), aVO.getItemImg(), aVO.getItemPay() };

		int result = jdbcTemplate.update(INSERT, args);

		if (result >= 1) {
			return true;
		}

		return false;
	}

	public List<AdvertisementVO> selectAll(AdvertisementVO aVO) {
		
		return jdbcTemplate.query(SELECTALL, new BeanPropertyRowMapper<AdvertisementVO>(AdvertisementVO.class));
	}
	
	public AdvertisementVO selectOne(AdvertisementVO aVO) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean update(AdvertisementVO aVO) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(AdvertisementVO aVO) {
		// TODO Auto-generated method stub
		return false;
	}

}
