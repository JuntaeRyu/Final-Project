package com.spring.biz.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.spring.biz.common.JDBCUtil;

public class MemberDAO implements InterfaceMemberDAO{
	// SQL 쿼리문
	// 회원가입
	private final String sql_INSERT = "INSERT INTO MEMBER (MEMBERID, MEMBERPW, NAME, NICKNAME, EMAIL, PHONENUM, GENDER, ADDRESS) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	// 회원 및 관리자 목록
	private final String sql_SELECTALL_MEMBERLIST = "SELECT MEMBERID,NAME,NICKNAME,PHONENUM,GENDER,ADDRESS,ROLE FROM MEMBER WHERE ROLE = ?";
//	// 회원(!관리자) 목록
//	private final String sql_SELECTALL_HELCELL = "SELECT MEMBERID,NAME,NICKNAME,PHONENUM,GENDER,ADDRESS,ROLE FROM MEMBER WHERE ROLE = 3";
//	// 관리자 목록
//	private final String sql_SELECTALL_HELLS = "SELECT MEMBERID,NAME,NICKNAME,PHONENUM,GENDER,ADDRESS,ROLE FROM MEMBER WHERE ROLE = 1";
	// 회원 중복검사및 아이디로 정보 추출
	private final String sql_SELECTONE_DUPLICATE = "SELECT MEMBERID,MEMBERPW,NAME,NICKNAME,EMAIL,PHONENUM,GENDER,ADDRESS,ROLE FROM MEMBER WHERE MEMBERID=?";
	// 회원 닉네임 중복 검사
	private final String sql_SELECTONE_DUPLICATE_NICKNAME = "SELECT NICKNAME FROM MEMBER WHERE NICKNAME=?";
	// 회원 이메일 중복 검사
	private final String sql_SELECTONE_DUPLICATE_EMAIL = "SELECT EMAIL FROM MEMBER WHERE EMAIL=?";
	// 회원 로그인
	private final String sql_SELECTONE_LOGIN = "SELECT MEMBERID,MEMBERPW,NAME,NICKNAME,EMAIL,PHONENUM,GENDER,ADDRESS,ROLE FROM MEMBER WHERE MEMBERID=? AND MEMBERPW=?"; // 로그인
	// 회원 주소 변경
	private final String sql_UPDATE_ADDRESS = "UPDATE MEMBER SET ADDRESS=? WHERE MEMBERID=?";
	// 회원 이메일 변경
	private final String sql_UPDATE_EMAIL = "UPDATE MEMBER SET EMAIL=? WHERE MEMBERID=?";
	// 회원 닉네임 변경
	private final String sql_UPDATE_NICKNAME = "UPDATE MEMBER SET NICKNAME=? WHERE MEMBERID=?";
	// 회원 비밀번호 변경
	private final String sql_UPDATE_MEMBERPW = "UPDATE MEMBER SET MEMBERPW=? WHERE MEMBERID=?";
	// 회원 탈퇴 및 삭제
	private final String sql_DELETE = "DELETE FROM MEMBER WHERE MEMBERID=?";
	
	// JDBC(자바 데이터베이스 커넥트) 도구
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public boolean insert(MemberVO mVO) { // DB에 객체정보 저장
		try { // 의도하지 않은 프로그램종료 예방
			// 데이터베이스 로그인 == 자바와 DB를 연결하는 객체 생성
			conn = JDBCUtil.connect();
			// SQL 쿼리문을 입력하는 저장공간 생성
			pstmt = conn.prepareStatement(sql_INSERT);

			// SQL 쿼리문 수정
			pstmt.setString(1, mVO.getMemberID());
			pstmt.setString(2, mVO.getMemberPW());
			pstmt.setString(3, mVO.getName());
			pstmt.setString(4, mVO.getNickName());
			pstmt.setString(5, mVO.getEmail());
			pstmt.setString(6, mVO.getPhoneNum());
			pstmt.setInt(7, mVO.getGender());
			pstmt.setString(8, mVO.getAddress());

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

	public ArrayList<MemberVO> selectAll(MemberVO mVO) { 
		ArrayList<MemberVO> mdatas = new ArrayList<MemberVO>(); // 저장할 공간
		
		try { // 예외방지
			// 데이터베이스 로그인 == 자바와 DB를 연결하는 객체 생성
			conn = JDBCUtil.connect();
			
			// SQL 쿼리문 입력
			pstmt = conn.prepareStatement(sql_SELECTALL_MEMBERLIST);
			pstmt.setInt(1, mVO.getRole());
			
			// SQL 쿼리문 실행
			rs = pstmt.executeQuery();

			// 가져온 정보 저장
			while (rs.next()) {
				MemberVO mdata = new MemberVO();
				
				mdata.setMemberID(rs.getString("MEMBERID"));
				mdata.setName(rs.getString("NAME"));
				mdata.setNickName(rs.getString("NICKNAME"));
				mdata.setGender(rs.getInt("GENDER"));
				mdata.setPhoneNum(rs.getString("PHONENUM"));
				mdata.setAddress(rs.getString("ADDRESS"));
				mdata.setRole(rs.getInt("ROLE"));
				
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

	public MemberVO selectOne(MemberVO mVO) { 
		MemberVO mdata = null; // 저장할 공간
		try { // 예외방지
			// 데이터베이스 로그인 == 자바와 DB를 연결하는 객체 생성
			conn = JDBCUtil.connect();
			
			
			// NULLPOINT 유효성 검사
			if(mVO.getSearchCondition()==null) {
				System.out.println("memberDAO2 selectOne 서치컨디션 null");
			}
			// 아이디 중복검사
			else if (mVO.getSearchCondition().equals("duplicateID")) {
				pstmt = conn.prepareStatement(sql_SELECTONE_DUPLICATE);
				pstmt.setString(1, mVO.getMemberID());
				// SQL 쿼리문 실행
				rs = pstmt.executeQuery();
				
				// 가져온 정보 저장
				if (rs.next()) {
					mdata = new MemberVO();
					
					mdata.setMemberID(rs.getString("MEMBERID"));
					mdata.setName(rs.getString("NAME"));
					mdata.setNickName(rs.getString("NICKNAME"));
					mdata.setGender(rs.getInt("GENDER"));
					mdata.setPhoneNum(rs.getString("PHONENUM"));
					mdata.setAddress(rs.getString("ADDRESS"));
					mdata.setRole(rs.getInt("ROLE"));
				}
			}
			// 닉네임 중복검사
			else if (mVO.getSearchCondition().equals("duplicateNickName")) {
				pstmt = conn.prepareStatement(sql_SELECTONE_DUPLICATE_NICKNAME);
				pstmt.setString(1, mVO.getNickName());
				// SQL 쿼리문 실행
				rs = pstmt.executeQuery();
				
				// 가져온 정보 저장
				if (rs.next()) {
					mdata = new MemberVO();
					
					mdata.setNickName(rs.getString("NICKNAME"));
				}
			}
			// 이메일 중복검사
			else if (mVO.getSearchCondition().equals("duplicateEmail")) {
				pstmt = conn.prepareStatement(sql_SELECTONE_DUPLICATE_EMAIL);
				pstmt.setString(1, mVO.getEmail());
				// SQL 쿼리문 실행
				rs = pstmt.executeQuery();
				
				// 가져온 정보 저장
				if (rs.next()) {
					mdata = new MemberVO();
					
					mdata.setEmail(rs.getString("EMAIL"));
				}
			}
			// 로그인
			else if (mVO.getSearchCondition().equals("login")) {
				pstmt = conn.prepareStatement(sql_SELECTONE_LOGIN);
				pstmt.setString(1, mVO.getMemberID());
				pstmt.setString(2, mVO.getMemberPW());
				// SQL 쿼리문 실행
				rs = pstmt.executeQuery();
				
				// 가져온 정보 저장
				if (rs.next()) {
					mdata = new MemberVO();
					
					mdata.setMemberID(rs.getString("MEMBERID"));
					mdata.setMemberPW(rs.getString("MEMBERPW"));
					mdata.setName(rs.getString("NAME"));
					mdata.setNickName(rs.getString("NICKNAME"));
					mdata.setEmail(rs.getString("EMAIL"));
					mdata.setGender(rs.getInt("GENDER"));
					mdata.setPhoneNum(rs.getString("PHONENUM"));
					mdata.setAddress(rs.getString("ADDRESS"));
					mdata.setRole(rs.getInt("ROLE"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		JDBCUtil.disconnect(rs, pstmt, conn);
		// 검색 결과 리턴
		return mdata;
	}

	public boolean update(MemberVO mVO) {
		try { 
			conn = JDBCUtil.connect();
			
			
			// NULLPOINT 유효성 검사
			if(mVO.getSearchCondition()==null) {
				System.out.println("memberDAO2 update 서치컨디션 null");
			}
			// 주소 변경
			else if(mVO.getSearchCondition().equals("updateAddress")) {
				// SQL 쿼리문 입력
				pstmt = conn.prepareStatement(sql_UPDATE_ADDRESS);
				// SQL 쿼리문 수정
				pstmt.setString(1, mVO.getMemberPW());
				pstmt.setString(2, mVO.getMemberID());
			}
			// 이메일 변경
			else if(mVO.getSearchCondition().equals("updateEmail")) {
				// SQL 쿼리문 입력
				pstmt = conn.prepareStatement(sql_UPDATE_EMAIL);
				// SQL 쿼리문 수정
				pstmt.setString(1, mVO.getMemberPW());
				pstmt.setString(2, mVO.getMemberID());
			}
			// 닉네임 변경
			else if(mVO.getSearchCondition().equals("updateNickName")) {
				// SQL 쿼리문 입력
				pstmt = conn.prepareStatement(sql_UPDATE_NICKNAME);
				// SQL 쿼리문 수정
				pstmt.setString(1, mVO.getMemberPW());
				pstmt.setString(2, mVO.getMemberID());
			}
			// 비밀번호 변경
			else if(mVO.getSearchCondition().equals("updateMemberPW")) {
				// SQL 쿼리문 입력
				pstmt = conn.prepareStatement(sql_UPDATE_MEMBERPW);
				// SQL 쿼리문 수정
				pstmt.setString(1, mVO.getMemberPW());
				pstmt.setString(2, mVO.getMemberID());
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

	public boolean delete(MemberVO mVO) {
		try { 
			conn = JDBCUtil.connect();
			// SQL 쿼리문 입력
			pstmt = conn.prepareStatement(sql_DELETE);
			// SQL 쿼리문 수정
			pstmt.setString(1, mVO.getMemberID());

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
