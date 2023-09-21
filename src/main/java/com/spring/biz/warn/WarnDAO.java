package com.spring.biz.warn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.spring.biz.common.JDBCUtil;

// @Repository("warnDAO")
public class WarnDAO implements InterfaceWarnDAO{

	private final String sql_INSERT = "INSERT INTO WARN (WARNNUM, MEMBERID, WARNTYPE, WARNDATE) VALUES ((SELECT NVL(MAX(WARNNUM),0)+1 FROM WARN),?, ?, SYSTIMESTAMP)";
	private final String sql_SELECTALL = "SELECT WARNNUM, MEMBERID, WARNTYPE, TO_CHAR(WARNDATE, 'YYYY-MM-DD') AS WARNDATE FROM WARN WHERE MEMBERID = ?";
//	private final String sql_SELECTONE = "";
//	private final String sql_UPDATE = "";
//	private final String sql_DELETE = "";
	
	// JDBC(자바 데이터베이스 커넥트) 도구
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public boolean insert(WarnVO wVO) {
		try { // 의도하지 않은 프로그램종료 예방
			conn = JDBCUtil.connect();
			// SQL 쿼리문을 입력하는 저장공간 생성
			pstmt = conn.prepareStatement(sql_INSERT);

			// SQL 쿼리문 수정
			pstmt.setString(1, wVO.getMemberID());
			pstmt.setInt(2, wVO.getWarnType());

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

	public List<WarnVO> selectAll(WarnVO wVO) {
		List<WarnVO> wdatas = new ArrayList<WarnVO>(); // 정보들을 저장할 배열

		try { // 예외처리
			conn = JDBCUtil.connect();
			// SQL 쿼리문 입력
			pstmt = conn.prepareStatement(sql_SELECTALL);
			pstmt.setString(1, wVO.getMemberID());

			// SQL 쿼리문 실행
			rs = pstmt.executeQuery();
			// 가져온 정보 저장용 객체
			while (rs.next()) {
				//"SELECT REPLYNUM,REPLY,COMMENTNUM,MID,PROHIBITCNT FROM REPLY";
				WarnVO wdata = new WarnVO();
				wdata.setWarnNum(rs.getInt("WARNNUM"));
				wdata.setMemberID(sql_INSERT);
				wdata.setWarnType(rs.getInt("WARNTYPE"));
				wdata.setWarnDate(rs.getString("WARNDATE"));
				wdatas.add(wdata);
			}
			// 사용한 도구 정리
			JDBCUtil.disconnect(rs, pstmt, conn);
			// 검색 결과 리턴
			return wdatas;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public WarnVO selectOne(WarnVO wVO) {
		
		return null;
	}

	public boolean update(WarnVO wVO) {
		
		return false;
	}

	public boolean delete(WarnVO wVO) {
		
		return false;
	}
}
