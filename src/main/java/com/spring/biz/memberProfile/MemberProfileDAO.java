package com.spring.biz.memberProfile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.spring.biz.common.JDBCUtil;

public class MemberProfileDAO implements InterfaceMemberProfileDAO {
	
	// SQL 쿼리문
	private final String sql_INSERT = "INSERT INTO MEMBERPROFILE (PROFILENUM, MEMBERID, PROFILEIMG, SHORTINTRO, INTRO, PROHIBITCNT) VALUES ((SELECT NVL(MAX(PROFILENUM),0)+1 FROM MEMBERPROFILE), ?, ?, ?, ?, ?)";
	private final String sql_SELECTONE = "SELECT MP.PROFILENUM, M.MEMBERID, MP.SHORTINTRO, MP.INTRO, MP.PROHIBITCNT, M.NICKNAME, (SELECT COUNT(CASE WHEN COMMONNUM = B.BOARDNUM THEN 1 END) FROM RECOMMEND) AS RECOMMENDCNT FROM MEMBERPROFILE MP JOIN MEMBER M ON MP.MEMBERID=M.MEMBERID WHERE PROFILENUM=?";
	private final String sql_SELECTALL = "SELECT MP.PROFILENUM, M.MEMBERID, MP.SHORTINTRO, MP.INTRO, MP.PROHIBITCNT, M.NICKNAME, (SELECT COUNT(CASE WHEN COMMONNUM = B.BOARDNUM THEN 1 END) FROM RECOMMEND) AS RECOMMENDCNT FROM MEMBERPROFILE MP JOIN MEMBER M ON MP.MEMBERID=M.MEMBERID";
	private final String sql_SELECTALL_PROHIBITCNT = "SELECT MP.PROFILENUM, M.MEMBERID, MP.SHORTINTRO, MP.INTRO, MP.PROHIBITCNT, M.NICKNAME, (SELECT COUNT(CASE WHEN COMMONNUM = B.BOARDNUM THEN 1 END) FROM RECOMMEND) AS RECOMMENDCNTFROM MEMBERPROFILE MP JOIN MEMBER M ON MP.MEMBERID=M.MEMBERID WHERE MP.PROHIBITCNT >= 3";
	private final String sql_UPDATE_PROHIBIT = "UPDATE MEMBERPROFILE SET PROHIBIT=(SELECT COUNT(CASE WHEN COMMONNUM=? THEN 1 END) FROM PROHIBIT) WHERE PROFILENUM=?";
	private final String sql_UPDATE_PROFILESHORTINTRO = "UPDATE MEMBERPROFILE SET SHORTINTRO=? WHERE PROFILENUM=?";
	private final String sql_UPDATE_PROFILEINTRO = "UPDATE MEMBERPROFILE SET INTRO=? WHERE PROFILENUM=?";
	private final String sql_UPDATE_PROFILEIMG = "UPDATE MEMBERPROFILE SET PROFILEIMG=? WHERE PROFILENUM=?";
	private final String sql_UPDATE_PROFILERESET = "UPDATE MEMBERPROFILE SET PROFILEIMG=?,SHORTIMG=?,INTRO=?,PROFILEIMG=?,PROHIBITCNT=0 WHERE PROFILENUM=?";
	// private final String sql_DELETE = "DELETE FROM MEMBER WHERE MEMBERID=?";

	// JDBC(자바 데이터베이스 커넥트) 도구
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public boolean insert(MemberProfileVO mpVO) { // DB에 객체정보 저장
		try { // 의도하지 않은 프로그램종료 예방
				// 데이터베이스 로그인 == 자바와 DB를 연결하는 객체 생성
			conn = JDBCUtil.connect();
			// SQL 쿼리문을 입력하는 저장공간 생성
			pstmt = conn.prepareStatement(sql_INSERT);

			// SQL 쿼리문 수정
			pstmt.setString(1, mpVO.getMemberID());
			pstmt.setString(2, mpVO.getProfileImg());
			pstmt.setString(3, mpVO.getShortIntro());
			pstmt.setString(4, mpVO.getIntro());
			pstmt.setInt(5, mpVO.getProhibitCnt());

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

	public ArrayList<MemberProfileVO> selectAll(MemberProfileVO mpVO) {
		ArrayList<MemberProfileVO> mdatas = new ArrayList<MemberProfileVO>(); // 저장할 공간
		try { // 예외방지
				// 데이터베이스 로그인 == 자바와 DB를 연결하는 객체 생성
			conn = JDBCUtil.connect();
			
			if (mpVO.getSearchCondition() == null) {
				// SQL 쿼리문 입력
				pstmt = conn.prepareStatement(sql_SELECTALL);
			}
			else if (mpVO.getSearchCondition().equals("prohibitProfile")) {
				// SQL 쿼리문 입력
				pstmt = conn.prepareStatement(sql_SELECTALL_PROHIBITCNT);
			}
			// SQL 쿼리문 실행
			rs = pstmt.executeQuery();

			// 가져온 정보 저장
			while (rs.next()) {
				MemberProfileVO mdata = new MemberProfileVO();
				
				mdata.setProfileNum(rs.getInt("PROFILENUM"));
				mdata.setMemberID(rs.getString("MEMBERID"));
				mdata.setProfileImg(rs.getString("PROFILEIMG"));
				mdata.setShortIntro(rs.getString("SHORTINTRO"));
				mdata.setIntro(rs.getString("INTRO"));
				mdata.setProhibitCnt(rs.getInt("PROHIBITCNT"));
				mdata.setNickName(rs.getString("NICKNAME"));
				
				mdatas.add(mdata);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		JDBCUtil.disconnect(rs, pstmt, conn);
		// 검색 결과 리턴
		return mdatas;
	}

	public MemberProfileVO selectOne(MemberProfileVO mpVO) {
		MemberProfileVO mpdata = null; // 저장할 공간
		try { // 예외방지
				// 데이터베이스 로그인 == 자바와 DB를 연결하는 객체 생성
			conn = JDBCUtil.connect();
			// SQL 쿼리문 입력
				pstmt = conn.prepareStatement(sql_SELECTONE);
				pstmt.setString(1, mpVO.getMemberID());
			// SQL 쿼리문 실행
			rs = pstmt.executeQuery();

			// 가져온 정보 저장
			if (rs.next()) {
				mpdata = new MemberProfileVO();
				
				mpdata.setProfileNum(rs.getInt("PROFILENUM"));
				mpdata.setMemberID(rs.getString("MEMBERID"));
				mpdata.setProfileImg(rs.getString("PROFILEIMG"));
				mpdata.setShortIntro(rs.getString("SHORTINTRO"));
				mpdata.setIntro(rs.getString("INTRO"));
				mpdata.setProhibitCnt(rs.getInt("PROHIBITCNT"));
				mpdata.setNickName(rs.getString("NICKNAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		JDBCUtil.disconnect(rs, pstmt, conn);
		// 검색 결과 리턴
		return mpdata;
	}

	public boolean update(MemberProfileVO mpVO) {
		try {
			conn = JDBCUtil.connect();
			
			if(mpVO.getSearchCondition() == null) {
				return false;
			}
			else if(mpVO.getSearchCondition().equals("updateProhibit")) {
				// SQL 쿼리문 입력
				pstmt = conn.prepareStatement(sql_UPDATE_PROHIBIT);
				// SQL 쿼리문 수정
				pstmt.setString(1, mpVO.getShortIntro());
				pstmt.setInt(2, mpVO.getProfileNum());
			}
			else if(mpVO.getSearchCondition().equals("updateShortIntro")) {
				// SQL 쿼리문 입력
				pstmt = conn.prepareStatement(sql_UPDATE_PROFILESHORTINTRO);
				// SQL 쿼리문 수정
				pstmt.setString(1, mpVO.getShortIntro());
				pstmt.setInt(2, mpVO.getProfileNum());
			}
			else if(mpVO.getSearchCondition().equals("updateIntro")) {
				// SQL 쿼리문 입력
				pstmt = conn.prepareStatement(sql_UPDATE_PROFILEINTRO);
				// SQL 쿼리문 수정
				pstmt.setString(1, mpVO.getIntro());
				pstmt.setInt(2, mpVO.getProfileNum());
			}
			else if(mpVO.getSearchCondition().equals("updateProfileImg")) {
				// SQL 쿼리문 입력
				pstmt = conn.prepareStatement(sql_UPDATE_PROFILEIMG);
				// SQL 쿼리문 수정
				pstmt.setString(1, mpVO.getProfileImg());
				pstmt.setInt(2, mpVO.getProfileNum());
			}
			else if(mpVO.getSearchCondition().equals("profileReset")) {
				// SQL 쿼리문 입력
				pstmt = conn.prepareStatement(sql_UPDATE_PROFILERESET);
				// SQL 쿼리문 수정
				pstmt.setInt(1, mpVO.getProfileNum());
			}
			
			// SQL 쿼리문 실행 및 결과 저장
			int result = pstmt.executeUpdate();
			// 사용한 도구 정리
			JDBCUtil.disconnect(pstmt, conn);

			// 성공 리턴1
			if (result >= 1) {
				return true;
			}
		} catch (SQLException e) { // 의도하지 않은 오류가 발생했을 경우
			e.printStackTrace(); // 예외정보 출력
			return false;
		}
		return false;
	}

	public boolean delete(MemberProfileVO mpVO) {
//		try {
//			conn = JDBCUtil.connect();
//			// SQL 쿼리문 입력
//			pstmt = conn.prepareStatement(sql_DELETE);
//			// SQL 쿼리문 수정
//			pstmt.setString(1, mVO.getMemberID());
//
//			// SQL 쿼리문 실행 및 결과 저장
//			int result = pstmt.executeUpdate();
//			// 사용한 도구 정리
//			JDBCUtil.disconnect(pstmt, conn);
//
//			// 성공 리턴
//			if (result >= 1) {
//				return true;
//			}
//		} catch (SQLException e) { // 의도하지 않은 오류가 발생했을 경우
//			e.printStackTrace(); // 예외정보 출력
//			return false;
//		}
		return false;
	}
}
