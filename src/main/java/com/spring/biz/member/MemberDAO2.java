package com.spring.biz.member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO2 implements InterfaceMemberDAO{

	// SQL 쿼리문

	// 회원가입
	private final String sql_INSERT = "INSERT INTO MEMBER (MEMBERID, MEMBERPW, NAME, NICKNAME, EMAIL, PHONENUM, GENDER, ADDRESS, DETAILADDRESS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	// 아이디(default)검색으로 하는 회원 및 관리자 목록
	private final String sql_SELECTALL_USERLIST = "SELECT M.MEMBERID, M.MEMBERPW,M.NAME, M.NICKNAME, M.EMAIL, M.PHONENUM, M.GENDER, M.ADDRESS, M.DETAILADDRESS, M.ROLE, "
			+ "(SELECT COUNT(*) FROM WARN W WHERE W.MEMBERID = M.MEMBERID) AS WARNCNT "
			+ "FROM MEMBER M WHERE M.ROLE = ? AND M.MEMBERID LIKE '%' || ? || '%' ORDER BY M.MEMBERID ASC";
	// 닉네임 검색으로 하는 회원 및 관리자 목록
	private final String sql_SELECTALL_USERLIST_NICKNAME = "SELECT M.MEMBERID, M.MEMBERPW,M.NAME, M.NICKNAME, M.EMAIL, M.PHONENUM, M.GENDER, M.ADDRESS, M.DETAILADDRESS, M.ROLE, "
			+ "(SELECT COUNT(*) FROM WARN W WHERE W.MEMBERID = M.MEMBERID) AS WARNCNT "
			+ "FROM MEMBER M WHERE M.ROLE = ? AND M.NICKNAME LIKE '%' || ? || '%' ORDER BY M.NICKNAME ASC";
	// 아이디(default)검색으로 회원 전체 목록
	private final String sql_SELECTALL_TOTALUSERLIST = "SELECT M.MEMBERID, M.MEMBERPW,M.NAME, M.NICKNAME, M.EMAIL, M.PHONENUM, M.GENDER, M.ADDRESS, M.DETAILADDRESS, M.ROLE, "
			+ "(SELECT COUNT(*) FROM WARN W WHERE W.MEMBERID = M.MEMBERID) AS WARNCNT "
			+ "FROM MEMBER M WHERE M.ROLE IN(2,3) AND M.MEMBERID LIKE '%' || ? || '%' ORDER BY M.MEMBERID ASC";
	// 닉네임 검색으로 하는 회원 전체 목록
	private final String sql_SELECTALL_TOTALUSERLIST_NICKNAME = "SELECT M.MEMBERID, M.MEMBERPW,M.NAME, M.NICKNAME, M.EMAIL, M.PHONENUM, M.GENDER, M.ADDRESS, M.DETAILADDRESS, M.ROLE, "
			+ "(SELECT COUNT(*) FROM WARN W WHERE W.MEMBERID = M.MEMBERID) AS WARNCNT "
			+ "FROM MEMBER M WHERE M.ROLE IN(2,3) AND M.NICKNAME LIKE '%' || ? || '%' ORDER BY M.NICKNAME ASC";
	// 회원 중복검사및 아이디로 정보 추출
	private final String sql_SELECTONE_DUPLICATE = "SELECT M.MEMBERID, M.MEMBERPW, M.NAME, M.NICKNAME, M.EMAIL, M.PHONENUM, M.GENDER, M.ADDRESS, M.DETAILADDRESS, M.ROLE, "
			+ "(SELECT COUNT(*) FROM WARN W WHERE W.MEMBERID = M.MEMBERID) AS WARNCNT "
			+ "FROM MEMBER M WHERE MEMBERID=?";
	// 회원 닉네임 중복 검사
	private final String sql_SELECTONE_DUPLICATE_NICKNAME = "SELECT NICKNAME FROM MEMBER WHERE NICKNAME=?";
	// 회원 이메일 중복 검사
	private final String sql_SELECTONE_DUPLICATE_EMAIL = "SELECT EMAIL FROM MEMBER WHERE EMAIL=?";
	// 회원 로그인
	private final String sql_SELECTONE_LOGIN = "SELECT M.MEMBERID, M.MEMBERPW, M.NAME, M.NICKNAME, M.EMAIL, M.PHONENUM, M.GENDER, M.ADDRESS, M.DETAILADDRESS, M.ROLE, "
			+ "(SELECT COUNT(*) FROM WARN W WHERE W.MEMBERID = M.MEMBERID) AS WARNCNT "
			+ "FROM MEMBER M WHERE MEMBERID=? AND MEMBERPW=?"; // 로그인
	// 회원 주소 변경
	private final String sql_UPDATE_ADDRESS = "UPDATE MEMBER SET ADDRESS=? AND SET DETAILADDRESS=? WHERE MEMBERID=?";
	// 회원 이메일 변경
	private final String sql_UPDATE_EMAIL = "UPDATE MEMBER SET EMAIL=? WHERE MEMBERID=?";
	// 회원 닉네임 변경
	private final String sql_UPDATE_NICKNAME = "UPDATE MEMBER SET NICKNAME=? WHERE MEMBERID=?";
	// 회원 비밀번호 변경
	private final String sql_UPDATE_MEMBERPW = "UPDATE MEMBER SET MEMBERPW=? WHERE MEMBERID=?";
	// 관리자로 권한 변경
	private final String sql_UPDATE_ADMIN = "UPDATE MEMBER SET ROLE=2 WHERE MEMBERID=?";
	// 회원으로 권한 변경
	private final String sql_UPDATE_USER = "UPDATE MEMBER SET ROLE=3 WHERE MEMBERID=?";
	// 낙오자로 권한 변경
	private final String sql_UPDATE_STOPID = "UPDATE MEMBER SET ROLE=9 WHERE MEMBERID=?";
	// 회원 탈퇴 및 삭제
	private final String sql_DELETE = "DELETE FROM MEMBER WHERE MEMBERID=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	////////////////////// insert ////////////////////////////////////////////////////
	public boolean insert(MemberVO mVO) { // DB에 객체정보 저장

		// 쿼리문 수정 및 실행 후 결과를 저장
		int result = jdbcTemplate.update(sql_INSERT, mVO.getMemberID(), mVO.getMemberPW(), mVO.getName(), mVO.getNickName(), mVO.getEmail(), mVO.getPhoneNum(), mVO.getGender(), mVO.getAddress(), mVO.getDetailAddress());

		// 검색 결과 리턴
		if (result<=0) {
			return false; // 저장 실패
		}
		return true;
	}

	///////////////////// selectAll ///////////////////////////////////////////////////
	public List<MemberVO> selectAll(MemberVO mVO) {

		if(mVO.getSearchCondition()!=null) {
			if (mVO.getSearchCondition().equals("userList")) {
				if(mVO.getSearchType()== null) {
					System.out.println("MemberDAO2 selectAll 서치컨디션 != null,  서치타입 null");
				}
				else if(mVO.getSearchType().equals("memberID")) {
					Object[] args = { mVO.getRole(), mVO.getSearchText()};
					return jdbcTemplate.query(sql_SELECTALL_USERLIST, args, new MemberListRowMapper());
				}
				else if(mVO.getSearchType().equals("nickName")) {
					Object[] args = { mVO.getRole(), mVO.getSearchText()};
					return jdbcTemplate.query(sql_SELECTALL_USERLIST_NICKNAME, args, new MemberListRowMapper());
				}
			}
			else if (mVO.getSearchCondition().equals("totalUserList")) {
				if(mVO.getSearchType()== null) {
					System.out.println("MemberDAO2 selectAll 서치컨디션 != null,  서치타입 null");
				}
				else if(mVO.getSearchType().equals("memberID")) {

					Object[] args = { mVO.getSearchText()};
					return jdbcTemplate.query(sql_SELECTALL_TOTALUSERLIST, args, new MemberListRowMapper());
				}
				else if(mVO.getSearchType().equals("nickName")) {
				
					Object[] args = { mVO.getSearchText()};
					return jdbcTemplate.query(sql_SELECTALL_TOTALUSERLIST_NICKNAME, args, new MemberListRowMapper());
				}
			}
		}
		else if (mVO.getSearchCondition() == null) {
			System.out.println("boardDAO2 selectAll 서치컨디션 null");
		}
		return null;
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
			result = jdbcTemplate.update(sql_UPDATE_ADDRESS, mVO.getAddress(),mVO.getDetailAddress(), mVO.getMemberID());
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
		else if(mVO.getSearchCondition().equals("upgradeAdmin")) {
			result = jdbcTemplate.update(sql_UPDATE_ADMIN, mVO.getMemberID());
		}
		else if(mVO.getSearchCondition().equals("downgradeUser")) {
			result = jdbcTemplate.update(sql_UPDATE_USER, mVO.getMemberID());
		}
		else if(mVO.getSearchCondition().equals("stopID")) {
			result = jdbcTemplate.update(sql_UPDATE_STOPID, mVO.getMemberID());
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
		mdata.setDetailAddress(rs.getString("DETAILADDRESS"));
		mdata.setRole(rs.getInt("ROLE"));
		mdata.setWarnCnt(rs.getInt("WARNCNT"));


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
		mdata.setDetailAddress(rs.getString("DETAILADDRESS"));
		mdata.setRole(rs.getInt("ROLE"));
		mdata.setWarnCnt(rs.getInt("ROLE"));


		return mdata;
	}

}
// 아이디 중복 검사 출력 rowmapper
class DuplicateIDRowMapper implements RowMapper<MemberVO> {

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
		mdata.setDetailAddress(rs.getString("DETAILADDRESS"));
		mdata.setRole(rs.getInt("ROLE"));
		mdata.setWarnCnt(rs.getInt("WARNCNT"));

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