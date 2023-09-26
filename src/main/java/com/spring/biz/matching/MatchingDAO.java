package com.spring.biz.matching;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.spring.biz.common.JDBCUtil;

//@Repository("MatchingDAO")
public class MatchingDAO {

	private String sql_INSERT = "INSERT INTO MATCHING (MATCHINGNUM, SENDERID, RECEIVERID) VALUES ((SELECT NVL(MAX(MATCHINGNUM),0)+1 FROM MATCHING),?, ?)";
	private String sql_SELECTALL = "SELECT MATCHINGNUM, SENDERID, RECEIVERID, ACCEPT FROM MATCHING WHERE SENDERID = ? OR RECEIVERID = ?";
	private String sql_SELECTONE = "SELECT MATCHINGNUM, SENDERID, RECEIVERID, ACCEPT FROM MATCHING WHERE MATCHINGNUM = ?";
	private String sql_UPDATE = "UPDATE MATCHING SET ACCEPT = ? WHERE MATCHINGNUM = ?";
	private String sql_DELETE = "DELETE FROM MATCHING WHERE MATCHINGNUM = ?";
	
	// JDBC(자바 데이터베이스 커넥트) 도구
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public boolean insert(MatchingVO mcVO) {
		
		try { // 의도하지 않은 프로그램종료 예방
			// 도구 연결
			conn = JDBCUtil.connect();
			
			// SQL 쿼리문을 입력하는 저장공간 생성
			pstmt = conn.prepareStatement(sql_INSERT);

			// SQL 쿼리문 수정
			pstmt.setString(1, mcVO.getSenderID());
			pstmt.setString(2, mcVO.getReceiverID());

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
		return false;
	}

	public List<MatchingVO> selectAll(MatchingVO mcVO) {
		List<MatchingVO> mcdatas = new ArrayList<MatchingVO>(); // 정보들을 저장할 배열

		try { // 의도하지 않은 프로그램종료 예방
			// 도구 연결
			conn = JDBCUtil.connect();
			
			// SQL 쿼리문 입력
			pstmt = conn.prepareStatement(sql_SELECTALL);
			pstmt.setString(1, mcVO.getSenderID());
			pstmt.setString(1, mcVO.getReceiverID());
			
			// SQL 쿼리문 실행
			rs = pstmt.executeQuery();
			
			// 가져온 정보 저장용 객체
			while (rs.next()) {
				MatchingVO mcdata = new MatchingVO();
				
				mcdata.setMatchingNum(rs.getInt("MATCHINGNUM"));
				mcdata.setSenderID(rs.getString("SENDERID"));
				mcdata.setReceiverID(rs.getString("RECEIVERID"));
				mcdata.setAccept(rs.getInt("ACCEPT"));
				
				mcdatas.add(mcdata);
			}
			
			// 사용한 도구 정리
			JDBCUtil.disconnect(rs, pstmt, conn);
			
			// 검색 결과 리턴
			return mcdatas;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public MatchingVO selectOne(MatchingVO mcVO) {
		MatchingVO mcdata = null; // 저장할 공간
		
		try { // 의도하지 않은 프로그램종료 예방
			// 도구 연결
			conn = JDBCUtil.connect();
			
			// SQL 쿼리문 입력
			pstmt = conn.prepareStatement(sql_SELECTONE);
			
			// SQL 쿼리문 수정
			pstmt.setInt(1, mcVO.getMatchingNum());

			// SQL 쿼리문 실행
			rs = pstmt.executeQuery();

			// 가져온 정보 저장
			if (rs.next()) {
				mcdata = new MatchingVO();
				
				mcdata.setMatchingNum(rs.getInt("MATCHINGNUM"));
				mcdata.setSenderID(rs.getString("SENDERID"));
				mcdata.setReceiverID(rs.getString("RECEIVERID"));
				mcdata.setAccept(rs.getInt("ACCEPT"));
			}
			
			// 사용한 도구 정리
			JDBCUtil.disconnect(rs, pstmt, conn);

			return mcdata;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean update(MatchingVO mcVO) {
		
		try { // 의도하지 않은 프로그램종료 예방
			// 도구 연결
			conn = JDBCUtil.connect();
			
			// SQL 쿼리문 입력
			pstmt = conn.prepareStatement(sql_UPDATE);
			pstmt.setInt(1, mcVO.getAccept());
			pstmt.setInt(2, mcVO.getMatchingNum());
			
			// SQL 쿼리문 실행 및 결과 저장
			int result = pstmt.executeUpdate();
			
			// 사용한 도구 정리
			JDBCUtil.disconnect(pstmt, conn);
			
			// 검색 결과 리턴
			if (result >= 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(MatchingVO mcVO) {
		
		try { // 의도하지 않은 프로그램종료 예방
			// 도구 연결
			conn = JDBCUtil.connect();
			
			// SQL 쿼리문 입력
			pstmt = conn.prepareStatement(sql_DELETE);
			
			// SQL 쿼리문 수정
			pstmt.setInt(1, mcVO.getMatchingNum());

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
