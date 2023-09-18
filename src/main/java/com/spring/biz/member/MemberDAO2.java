package com.spring.biz.member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("memberDAO")
public class MemberDAO2 {

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
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

////////////////////// insert ////////////////////////////////////////////////////
	public boolean insert(MemberVO mVO) { // DB에 객체정보 저장

		// 쿼리문 수정 및 실행 후 결과를 저장
		int result = jdbcTemplate.update(sql_INSERT, mVO.getMemberID(), mVO.getMemberPW(), mVO.getName(), mVO.getNickName(), mVO.getEmail(), mVO.getPhoneNum(), mVO.getGender(), mVO.getAddress());

		// 검색 결과 리턴
		if (result<=0) {
			return false; // 저장 실패
		}
		return true;
	}

///////////////////// selectAll ///////////////////////////////////////////////////
	public List<MemberVO> selectAll(MemberVO mVO) {
		Object[] args = { mVO.getRole() };
		return jdbcTemplate.query(sql_SELECTALL_MEMBERLIST, args, new MemberListRowMapper());
	}

///////////////////// selectOne ///////////////////////////////////////////////////
	public MemberVO selectOne(MemberVO mVO) {

		// 쿼리문 수정 및 실행 후 결과를 저장
		try {
			if(mVO.getSearchCondition()==null) {
				System.out.println("memberDAO2 selectOne 서치컨디션 null");
			}
			// 아이디 중복검사
			else if (mVO.getSearchCondition().equals("duplicateID")) {
				Object[] args = { mVO.getMemberID() };
				return jdbcTemplate.queryForObject(sql_SELECTONE_DUPLICATE, args, new DuplicateIDRowMapper());
			}
			// 닉네임 중복검사
			else if (mVO.getSearchCondition().equals("duplicateNickName")) {
				Object[] args = { mVO.getNickName() };
				return jdbcTemplate.queryForObject(sql_SELECTONE_DUPLICATE_NICKNAME, args, new DuplicateNickNameRowMapper());
			}
			// 이메일 중복검사
			else if (mVO.getSearchCondition().equals("duplicateEmail")) {
				Object[] args = { mVO.getEmail() };
				return jdbcTemplate.queryForObject(sql_SELECTONE_DUPLICATE_EMAIL, args, new DuplicateEmailRowMapper());
			}
			// 로그인
			else if (mVO.getSearchCondition().equals("login")) {
				Object[] args = { mVO.getMemberID(), mVO.getMemberPW() };
				return jdbcTemplate.queryForObject(sql_SELECTONE_LOGIN, args, new MemberRowMapper());
			}
		}
		catch(EmptyResultDataAccessException e) {
			System.out.println("memberDAO2 selectOne 데이터가 비어있음");
			return null;
		}

		// 검색 실패라면
		return null;
	}

//////////////////// update ///////////////////////////////////////////////////////////
	public boolean update(MemberVO mVO) {

		// 쿼리문 수정 및 실행 후 결과를 저장할 변수
		int result = 0;
		
		// NULLPOINT 유효성 검사
		if(mVO.getSearchCondition()==null) {
			System.out.println("memberDAO2 update 서치컨디션 null");
		}
		// 주소 변경
		else if(mVO.getSearchCondition().equals("updateAddress")) {
			result = jdbcTemplate.update(sql_UPDATE_ADDRESS, mVO.getAddress(), mVO.getMemberID());
		}
		// 이메일 변경
		else if(mVO.getSearchCondition().equals("updateEmail")) {
			result = jdbcTemplate.update(sql_UPDATE_EMAIL, mVO.getEmail(), mVO.getMemberID());
		}
		// 닉네임 변경
		else if(mVO.getSearchCondition().equals("updateNickName")) {
			result = jdbcTemplate.update(sql_UPDATE_NICKNAME, mVO.getNickName(), mVO.getMemberID());
		}
		// 비밀번호 변경
		else if(mVO.getSearchCondition().equals("updateMemberPW")) {
			result = jdbcTemplate.update(sql_UPDATE_MEMBERPW, mVO.getMemberPW(), mVO.getMemberID());
		}
		
		// 실패 리턴
		if (result <=0) {
			return false;
		}
		return true;
	}

/////////////////// delete ///////////////////////////////////////////////////////////////
	public boolean delete(MemberVO mVO) {

		// 쿼리문 수정 및 실행 후 결과를 저장
		int result = jdbcTemplate.update(sql_DELETE, mVO.getMemberID());

		// 실패 리턴
		if (result <=0) {
			return false;
		}
		return true;
	}
}

///////////////////ROWMAPPER///////////////////////////////////////////////////////////////
// 회원 출력 rowmapper
class MemberRowMapper implements RowMapper<MemberVO> {

	@Override
	public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		MemberVO mdata = new MemberVO();

		mdata.setMemberID(rs.getString("MEMBERID"));
		mdata.setMemberPW(rs.getString("MEMBERPW"));
		mdata.setName(rs.getString("NAME"));
		mdata.setNickName(rs.getString("NICKNAME"));
		mdata.setEmail(rs.getString("EMAIL"));
		mdata.setGender(rs.getInt("GENDER"));
		mdata.setPhoneNum(rs.getString("PHONENUM"));
		mdata.setAddress(rs.getString("ADDRESS"));
		mdata.setRole(rs.getInt("ROLE"));
		

		return mdata;
	}

}
// 회원목록 출력 rowmapper
class MemberListRowMapper implements RowMapper<MemberVO> {
	
	@Override
	public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		MemberVO mdata = new MemberVO();
		
		mdata.setMemberID(rs.getString("MEMBERID"));
		mdata.setName(rs.getString("NAME"));
		mdata.setNickName(rs.getString("NICKNAME"));
		mdata.setGender(rs.getInt("GENDER"));
		mdata.setPhoneNum(rs.getString("PHONENUM"));
		mdata.setAddress(rs.getString("ADDRESS"));
		mdata.setRole(rs.getInt("ROLE"));
		
		
		return mdata;
	}
	
}
// 아이디 중복 검사 출력 rowmapper
class DuplicateIDRowMapper implements RowMapper<MemberVO> {
	
	@Override
	public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		MemberVO mdata = new MemberVO();
		
		mdata.setMemberID(rs.getString("MEMBERID"));
		mdata.setName(rs.getString("NAME"));
		mdata.setNickName(rs.getString("NICKNAME"));
		mdata.setEmail(rs.getString("EMAIL"));
		mdata.setGender(rs.getInt("GENDER"));
		mdata.setPhoneNum(rs.getString("PHONENUM"));
		mdata.setAddress(rs.getString("ADDRESS"));
		mdata.setRole(rs.getInt("ROLE"));
		
		return mdata;
	}
}
// 이메일 중복 검사 출력 rowmapper
class DuplicateEmailRowMapper implements RowMapper<MemberVO> {
	
	@Override
	public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		MemberVO mdata = new MemberVO();
		
		mdata.setEmail(rs.getString("EMAIL"));
		
		return mdata;
	}
}
// 닉네임 중복 검사 출력 rowmapper
class DuplicateNickNameRowMapper implements RowMapper<MemberVO> {
	
	@Override
	public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		MemberVO mdata = new MemberVO();
		
		mdata.setNickName(rs.getString("NICKNAME"));
		
		return mdata;
	}
}