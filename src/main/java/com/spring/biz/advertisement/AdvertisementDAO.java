package com.spring.biz.advertisement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.spring.biz.common.JDBCUtil;

//import org.springframework.stereotype.Repository;

//@Repository("AdvertisementDAO")
public class AdvertisementDAO implements InterfaceAdvertisementDAO {

	// 광고 저장
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
//	private final String SELECTONE = "";
//	private final String UPDATE = "";
//	private final String DELETE = "";
	private final String CREATE = "CREATE TABLE ADVERTISEMENT ( "
			+ "ADVERTISEMENTNUM NUMBER PRIMARY KEY, "
			+ "SITE NUMBER, "
			+ "SITEURL VARCHAR2(100) NOT NULL, "
			+ "ITEM VARCHAR2(100) NOT NULL, "
			+ "ITEMIMG VARCHAR2(100) NOT NULL, "
			+ "ITEMPAY VARCHAR2(15) NOT NULL )";
	private final String DROP = "DROP TABLE ADVERTISEMENT";
	
	// JDBC(자바 데이터베이스 커넥트) 도구
		private Connection conn;
		private PreparedStatement pstmt;
		private ResultSet rs;
	
	@Override
	public boolean insert(AdvertisementVO aVO) {
		try {
			conn = JDBCUtil.connect();
			pstmt = conn.prepareStatement(INSERT);
			
			pstmt.setInt(1, aVO.getSite());
			pstmt.setString(2, aVO.getSiteUrl());
			pstmt.setString(3, aVO.getItem());
			pstmt.setString(4, aVO.getItemImg());
			pstmt.setString(5, aVO.getItemPay());
			
			int result = pstmt.executeUpdate();
			
			JDBCUtil.disconnect(pstmt, conn);
			
			if (result >= 1) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public List<AdvertisementVO> selectAll(AdvertisementVO aVO) {
		List<AdvertisementVO> adatas = new  ArrayList<AdvertisementVO>();
		try {
			conn = JDBCUtil.connect();
			pstmt = conn.prepareStatement(SELECTALL);
			
//			pstmt.setInt(1, aVO.getSite());
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				AdvertisementVO adata = new AdvertisementVO();
				
				adata.setSite(rs.getInt("SITE"));
				adata.setSiteUrl(rs.getString("SITEURL"));
				adata.setItem(rs.getString("ITEM"));
				adata.setItemImg(rs.getString("ITEMIMG"));
				adata.setItemPay(rs.getString("ITEMPAY"));
				
				adatas.add(adata);
			}
			
			JDBCUtil.disconnect(rs, pstmt, conn);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return adatas;
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
			conn = JDBCUtil.connect();
			pstmt = conn.prepareStatement(DROP);
			
			pstmt.executeUpdate();
			
			JDBCUtil.disconnect(pstmt, conn);
			
		} catch (SQLException e) {
			System.out.println("로그: 테이블 삭제 실패");
			System.out.println("로그: 삭제할 테이블이 없어서일걸요?");
		}

		try {
			conn = JDBCUtil.connect();
			pstmt = conn.prepareStatement(CREATE);
			
			pstmt.executeUpdate();
			
			JDBCUtil.disconnect(pstmt, conn);
			
		} catch (SQLException e) {
			System.out.println("로그: 테이블 생성 실패");
			System.out.println("로그: 이건 문제가 있는데?");
			System.out.println("로그: 만약 삭제,생성 둘다 실패라면 KDW한테 문의해주세요");
			return false;
		}

		return true;
	}
	
}
