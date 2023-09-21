package com.spring.biz.advertisement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("AdvertisementDAO")
public class AdvertisementDAO2 implements InterfaceAdvertisementDAO {

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
	private final String CREATE = "CREATE TABLE ADVERTISEMENT ( "
			+ "ADVERTISEMENTNUM NUMBER PRIMARY KEY, "
			+ "SITE NUMBER, "
			+ "SITEURL VARCHAR2(100) NOT NULL, "
			+ "ITEM VARCHAR2(100) NOT NULL, "
			+ "ITEMIMG VARCHAR2(100) NOT NULL, "
			+ "ITEMPAY VARCHAR2(15) NOT NULL )";
	private final String DROP = "DROP TABLE ADVERTISEMENT";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean insert(AdvertisementVO aVO) {

		Object[] args = { aVO.getSite(), aVO.getSiteUrl(), aVO.getItem(), aVO.getItemImg(), aVO.getItemPay() };

		int result = jdbcTemplate.update(INSERT, args);

		if (result >= 1) {
			return true;
		}

		return false;
	}

	@Override
	public List<AdvertisementVO> selectAll(AdvertisementVO aVO) {
		
		return jdbcTemplate.query(SELECTALL, new BeanPropertyRowMapper<AdvertisementVO>(AdvertisementVO.class));
	}
	
	@Override
	public AdvertisementVO selectOne(AdvertisementVO aVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(AdvertisementVO aVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(AdvertisementVO aVO) {
		// TODO Auto-generated method stub
		return false;
	}
	
	// 프로그램 실행할 때마다 크롤링이 추가되서 데이터가 중복되는게 불편하다는 팀원의 문의가 있었습니다
	// 해당 문의를 해결하기 위해 테이블을 지우고 다시 만드는 기능을 추가합니다(특수)
	@Override
	public boolean reset(AdvertisementVO aVO) {

		try {
			jdbcTemplate.update(DROP);
		} catch (DataAccessException e) {
			System.out.println("로그: 테이블 삭제 실패");
			System.out.println("로그: 삭제할 테이블이 없어서일걸요?");
		}

		try {
			jdbcTemplate.update(CREATE);
		} catch (DataAccessException e) {
			System.out.println("로그: 테이블 생성 실패");
			System.out.println("로그: 이건 문제가 있는데?");
			System.out.println("로그: 만약 삭제,생성 둘다 실패라면 KDW한테 문의해주세요");
			return false;
		}
		
		return true;
	}

}
