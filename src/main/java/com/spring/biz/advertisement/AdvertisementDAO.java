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
public class AdvertisementDAO {

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
	
	// JDBC(자바 데이터베이스 커넥트) 도구
		private Connection conn;
		private PreparedStatement pstmt;
		private ResultSet rs;
	
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
