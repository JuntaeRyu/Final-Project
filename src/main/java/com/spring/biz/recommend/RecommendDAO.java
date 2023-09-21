package com.spring.biz.recommend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.spring.biz.common.JDBCUtil;

public class RecommendDAO implements InterfaceRecommendDAO{
	private final String sql_INSERT ="INSERT INTO RECOMMEND (MEMBERID,COMMONNUM) VALUES ( ? , ? )";
//	private final String sql_SELECTALL ="SELECT RECOMMENDNUM,MEMBERID,COMMONNUM FROM RECOMMEND WHERE COMMONNUM=?";
	private final String sql_SELECTONE ="SELECT RECOMMENDNUM,MEMBERID,COMMONNUM FROM RECOMMEND WHERE MEMBERID=? AND COMMONNUM=?";
	private final String sql_DELETE ="DELETE FROM RECOMMEND WHERE RECOMMENDNUM=?";
	
	// JDBC(자바 데이터베이스 커넥트) 도구
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public boolean insert(RecommendVO rcVO) { // DB에 객체정보 저장
		try { // 의도하지 않은 프로그램종료 예방
			conn = JDBCUtil.connect();
			// SQL 쿼리문을 입력하는 저장공간 생성
			pstmt = conn.prepareStatement(sql_INSERT);
			// SQL 쿼리문 수정
			pstmt.setString(1, rcVO.getMemberID());
			pstmt.setInt(2, rcVO.getCommonNum());
			// SQL 쿼리문 실행 및 결과 저장
			int result = pstmt.executeUpdate();

			// 사용한 도구 정리
			JDBCUtil.disconnect(pstmt, conn);

			// 검색 결과 리턴
			if (result >= 1) {
				return true;
			}

		} catch (SQLException e) { // 의도하지 않은 오류가 발생했을 경우
			e.printStackTrace(); // 예외정보 출력
			return false;
		}
		return false; // 저장 실패
	} // insert

	public ArrayList<RecommendVO> selectAll(RecommendVO rcVO) { // 목록 검색
//		ArrayList<RecommendVO> rcdatas = new ArrayList<RecommendVO>(); // 정보들을 저장할 배열
//		try { // 예외처리
//			conn = JDBCUtil.connect();
//			// SQL 쿼리문을 입력하는 저장공간 생성
//			pstmt = conn.prepareStatement(sql_SELECTALL);
//			pstmt.setInt(1, rcVO.getRecommendNum());
//			// SQL 쿼리문 실행
//			rs = pstmt.executeQuery();
//			// 가져온 정보 저장용 객체
//			while (rs.next()) {
//				// 가져온 정보 저장한 객체 생성
//				RecommendVO rcdata = new RecommendVO();
//				rcdata.setRecommendNum(rs.getInt("RECOMMENDNUM"));
//				rcdata.setMemberID(rs.getString("MEMBERID"));
//				rcdata.setCommonNum(rs.getInt("COMMONNUM"));
//				// 생성한 객체를 배열에 저장
//				rcdatas.add(rcdata);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		JDBCUtil.disconnect(rs, pstmt, conn);
//		return rcdatas;
		return null;
	}

	public RecommendVO selectOne(RecommendVO rcVO) { // 하나의 객체 정보 검색
		RecommendVO rcdata = null;
		try { // 예외방지
			conn = JDBCUtil.connect();
			pstmt = conn.prepareStatement(sql_SELECTONE);
			// SQL 쿼리문 수정
			pstmt.setString(1, rcVO.getMemberID());
			pstmt.setInt(2, rcVO.getCommonNum());
			// SQL 쿼리문 실행
			rs = pstmt.executeQuery();

			// 가져온 정보 저장
			if (rs.next()) {
				rcdata=new RecommendVO();
				rcdata.setRecommendNum(rs.getInt("RECOMMENDNUM"));
				rcdata.setMemberID(rs.getString("MEMBERID"));
				rcdata.setCommonNum(rs.getInt("COMMONNUM"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(rs, pstmt, conn);
		return rcdata;
	}

	public boolean update(RecommendVO rcVO) {
		return false;
	}

	public boolean delete(RecommendVO rcVO) {

		try { // 의도하지 않은 프로그램종료 예방
			conn = JDBCUtil.connect();
			// SQL 쿼리문 입력
			pstmt = conn.prepareStatement(sql_DELETE);
			// SQL 쿼리문 수정
			pstmt.setInt(1, rcVO.getRecommendNum());
			// SQL 쿼리문 실행 및 결과 저장
			int result = pstmt.executeUpdate();
			// 사용한 도구 정리
			JDBCUtil.disconnect(pstmt, conn);

			// 성공 리턴
			if (result >= 1) {
				return true;
			}
		} catch (SQLException e) { // 의도하지 않은 오류가 발생했을 경우
			e.printStackTrace(); // 예외정보 출력
			return false;
		}		
		return false;
	}
}
