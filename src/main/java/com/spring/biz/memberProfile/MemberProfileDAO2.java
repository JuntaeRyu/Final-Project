package com.spring.biz.memberProfile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class MemberProfileDAO2 implements InterfaceMemberProfileDAO{

	// 회원 프로필 작성
	private final String sql_INSERT = "INSERT INTO MEMBERPROFILE (PROFILENUM, MEMBERID, PROFILEIMG, SHORTINTRO, INTRO) VALUES ((SELECT NVL(MAX(PROFILENUM),0)+1 FROM MEMBERPROFILE), ?, ?, ?, ?)";
	// 회원 프로필 상세페이지
	private final String sql_SELECTONE_MEMBERPROFILE = "SELECT MP.PROFILENUM, MP.MEMBERID, MP.PROFILEIMG, MP.SHORTINTRO, MP.INTRO, MP.PROHIBITCNT, M.NICKNAME, M.ADDRESS, (SELECT COUNT(CASE WHEN COMMONNUM = MP.PROFILENUM THEN 1 END) FROM RECOMMEND) AS RECOMMENDCNT FROM MEMBERPROFILE MP JOIN MEMBER M ON MP.MEMBERID=M.MEMBERID WHERE PROFILENUM=?";
	// 회원 프로필 아이디로 프로필 출력
	private final String sql_SELECTONE_MYPROFILE = "SELECT MP.PROFILENUM, MP.MEMBERID, MP.PROFILEIMG, MP.SHORTINTRO, MP.INTRO, MP.PROHIBITCNT, M.NICKNAME, (SELECT COUNT(CASE WHEN COMMONNUM = MP.PROFILENUM THEN 1 END) FROM RECOMMEND) AS RECOMMENDCNT FROM MEMBERPROFILE MP JOIN MEMBER M ON MP.MEMBERID=M.MEMBERID WHERE MP.MEMBERID=?";
	// 회원 프로필 전체 출력
	private final String sql_SELECTALL_HELCELL = "SELECT MP.PROFILENUM, MP.MEMBERID, MP.PROFILEIMG, MP.SHORTINTRO, MP.INTRO, MP.PROHIBITCNT, M.NICKNAME, (SELECT COUNT(CASE WHEN COMMONNUM = MP.PROFILENUM THEN 1 END) FROM RECOMMEND) AS RECOMMENDCNT FROM MEMBERPROFILE MP JOIN MEMBER M ON MP.MEMBERID=M.MEMBERID WHERE M.ROLE = 3 AND MP.MEMBERID != ? AND MP.PROHIBITCNT <= 2 ORDER BY DBMS_RANDOM.VALUE";
	// 메인페이지 회원프로필 4개 출력
	private final String sql_SELECTALL_MAINPROFILE = "SELECT PROFILENUM, MEMBERID, PROFILEIMG, SHORTINTRO, INTRO, PROHIBITCNT, NICKNAME, RECOMMENDCNT "
			+ " FROM ("
			+ "SELECT MP.PROFILENUM, MP.MEMBERID, MP.PROFILEIMG, MP.SHORTINTRO, MP.INTRO, MP.PROHIBITCNT, M.NICKNAME,"
			+ "(SELECT COUNT(CASE WHEN COMMONNUM = MP.PROFILENUM THEN 1 END) FROM RECOMMEND) AS RECOMMENDCNT "
			+ "FROM MEMBERPROFILE MP "
			+ "JOIN MEMBER M ON MP.MEMBERID = M.MEMBERID "
			+ "WHERE M.ROLE = 3 AND MP.PROHIBITCNT <= 2 "
			+ "ORDER BY DBMS_RANDOM.VALUE"
			+ ")"
			+ "WHERE ROWNUM <= 4";
	// 회원 신고 3개이산인 프로필 출력
	private final String sql_SELECTALL_PROHIBITCNT = "SELECT MP.PROFILENUM, MP.MEMBERID, MP.PROFILEIMG, MP.SHORTINTRO, MP.INTRO, MP.PROHIBITCNT, M.NICKNAME, (SELECT COUNT(CASE WHEN COMMONNUM = MP.PROFILENUM THEN 1 END) FROM RECOMMEND) AS RECOMMENDCNT FROM MEMBERPROFILE MP JOIN MEMBER M ON MP.MEMBERID=M.MEMBERID WHERE MP.PROHIBITCNT >= 3 AND M.ROLE = 3";
	// 회원프로필 신고수 갱신
	private final String sql_UPDATE_PROHIBIT = "UPDATE MEMBERPROFILE SET PROHIBIT=(SELECT COUNT(CASE WHEN COMMONNUM=? THEN 1 END) FROM PROHIBIT) WHERE PROFILENUM=?";
	// 회원프로필 짧은 소개글 변경
	// 게시물 조회수+1
	private final String sql_UPDATE_PROFILEPROHIBIT = "UPDATE MEMBERPROFILE SET "
			+ "PROHIBITCNT=(SELECT COUNT(CASE WHEN COMMONNUM=? THEN 1 END ) FROM PROHIBIT) WHERE PROFILENUM=?";
	// 게시물 조회수+1
	private final String sql_UPDATE_PROFILERECOMMEND = "UPDATE MEMBERPROFILE SET "
			+ "RECOMMENDCNT=(SELECT COUNT(CASE WHEN COMMONNUM=? THEN 1 END ) FROM RECOMMEND) WHERE PROFILENUM=?";
	private final String sql_UPDATE_PROFILESHORTINTRO = "UPDATE MEMBERPROFILE SET SHORTINTRO=? WHERE PROFILENUM=?";
	// 회원프로필 정식 소개글 변경
	private final String sql_UPDATE_PROFILEINTRO = "UPDATE MEMBERPROFILE SET INTRO=? WHERE PROFILENUM=?";
	// 회원프로필 이미지 변경
	private final String sql_UPDATE_PROFILEIMG = "UPDATE MEMBERPROFILE SET PROFILEIMG=? WHERE PROFILENUM=?";
	// 회원프로필 초기화
	private final String sql_UPDATE_PROFILERESET = "UPDATE MEMBERPROFILE SET PROFILEIMG=NULL,SHORTINTRO=NULL,INTRO=NULL,PROHIBITCNT=0,RECOMMENDCNT=0 WHERE PROFILENUM=?";
	//	private final String sql_DELETE = "DELETE FROM MEMBER WHERE MEMBERID=?";
	// JDBC(자바 데이터베이스 커넥트) 도구
	// 중복검사에 쿼리문 굳이 다필요한지 확인


	@Autowired
	private JdbcTemplate jdbcTemplate;

	/////////////////// insert ///////////////////////////////////////////////
	public boolean insert(MemberProfileVO mpVO) { // DB에 객체정보 저장

		// 쿼리문 수정 및 실행 후 결과를 저장
		int result = jdbcTemplate.update(sql_INSERT, mpVO.getMemberID(),mpVO.getProfileImg(), mpVO.getShortIntro(), mpVO.getIntro());

		// 검색 결과 리턴
		if (result<=0) {
			return false; // 저장 실패
		}
		return true;
	} // insert

	/////////////////// selectAll ///////////////////////////////////////////////
	public List<MemberProfileVO> selectAll(MemberProfileVO mpVO) {
		if(mpVO.getSearchCondition()==null) {
			System.out.println("memberProfileDAO2 selectAll 서치컨디션 null");
		}
		// 회원 프로필 전체출력
		else if(mpVO.getSearchCondition().equals("totalMemberProfile")) {
			Object[] args = { mpVO.getMemberID() };
			return jdbcTemplate.query(sql_SELECTALL_HELCELL, args, new MemberProfileRowMapper());
		}
		// 메인페이지에서 회원 프로필 4개 출력
		else if(mpVO.getSearchCondition().equals("mainProfile")) {
			return jdbcTemplate.query(sql_SELECTALL_MAINPROFILE, new MemberProfileRowMapper());
		}
		// 신고수 3개이상인 회원 출력
		else if(mpVO.getSearchCondition().equals("prohibitProfile")) {
			return jdbcTemplate.query(sql_SELECTALL_PROHIBITCNT, new MemberProfileRowMapper());
		}
		return null;
	}

	/////////////////// selectOne ///////////////////////////////////////////////
	public MemberProfileVO selectOne(MemberProfileVO mpVO) {

		try {

			if(mpVO.getSearchCondition()==null) {
				System.out.println("memberProfileDAO2 selectOne 서치컨디션 null");
			}
			else if(mpVO.getSearchCondition().equals("memberProfile")) {
				Object[] args = { mpVO.getProfileNum() };
				return jdbcTemplate.queryForObject(sql_SELECTONE_MEMBERPROFILE, args, new MemberProfileRowMapper2());
			}
			else if(mpVO.getSearchCondition().equals("myProfile")) {
				Object[] args = { mpVO.getMemberID() };
				return jdbcTemplate.queryForObject(sql_SELECTONE_MYPROFILE, args, new MemberProfileRowMapper());
			}
		}
		catch(EmptyResultDataAccessException e) {
			System.out.println("MemberProfileDAO2 selectOne 데이터 비어있음");
			return null;
		}
		// 검색 실패라면
		return null;
	}

	/////////////////// update ///////////////////////////////////////////////
	public boolean update(MemberProfileVO mpVO) {
		// 쿼리문 수정 및 실행 후 결과를 저장
		int result=0;
		if(mpVO.getSearchCondition() == null) {
			System.out.println("memberProfileDAO2 update 서치컨디션 null");
		}
		// 프로필 신고수 갱신
		else if(mpVO.getSearchCondition().equals("updateProhibit")) {
			result = jdbcTemplate.update(sql_UPDATE_PROHIBIT, mpVO.getShortIntro(), mpVO.getProfileNum());
		}
		// 프로필 짧은 소개글 변경
		else if(mpVO.getSearchCondition().equals("updateShortIntro")) {
			result = jdbcTemplate.update(sql_UPDATE_PROFILESHORTINTRO, mpVO.getShortIntro(), mpVO.getProfileNum());
		}
		// 프로필 정식 소개글 변경
		else if(mpVO.getSearchCondition().equals("updateIntro")) {
			result = jdbcTemplate.update(sql_UPDATE_PROFILEINTRO, mpVO.getIntro(), mpVO.getProfileNum());
		}
		// 프로필 이미지 변경
		else if(mpVO.getSearchCondition().equals("updateProfileImg")) {
			result = jdbcTemplate.update(sql_UPDATE_PROFILEIMG, mpVO.getProfileImg(), mpVO.getProfileNum());
		}
		// 회원 프로필 초기화
		else if(mpVO.getSearchCondition().equals("profileReset")) {
			result = jdbcTemplate.update(sql_UPDATE_PROFILERESET, mpVO.getProfileNum());
		}
		else if(mpVO.getSearchCondition().equals("prohibit")) {
			result = jdbcTemplate.update(sql_UPDATE_PROFILEPROHIBIT, mpVO.getProfileNum(), mpVO.getProfileNum());
		}
		else if(mpVO.getSearchCondition().equals("recommend")) {
			result = jdbcTemplate.update(sql_UPDATE_PROFILERECOMMEND, mpVO.getProfileNum(), mpVO.getProfileNum());
		}
		// 성공 리턴
		if (result > 0) {
			return true;
		}
		return false;
	}

	/////////////////// delete ///////////////////////////////////////////////
	public boolean delete(MemberProfileVO mpVO) {
		//
		//		// 쿼리문 수정 및 실행 후 결과를 저장
		//		int result = jdbcTemplate.update(sql_DELETE, mpVO.getMemberID());
		//
		//		// 성공 리턴
		//		if (result <=0) {
		//		}
		//		return true;
		return false;
	}
}

/////////////////// rowMapper ///////////////////////////////////////////////
class MemberProfileRowMapper implements RowMapper<MemberProfileVO> {

	@Override
	public MemberProfileVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberProfileVO mpdata = new MemberProfileVO();

		mpdata.setProfileNum(rs.getInt("PROFILENUM"));
		mpdata.setMemberID(rs.getString("MEMBERID"));
		mpdata.setProfileImg(rs.getString("PROFILEIMG"));
		mpdata.setShortIntro(rs.getString("SHORTINTRO"));
		mpdata.setIntro(rs.getString("INTRO"));
		mpdata.setProhibitCnt(rs.getInt("PROHIBITCNT"));
		mpdata.setRecommendCnt(rs.getInt("RECOMMENDCNT"));
		mpdata.setNickName(rs.getString("NICKNAME"));

		return mpdata;
	}

}

class MemberProfileRowMapper2 implements RowMapper<MemberProfileVO> {

	@Override
	public MemberProfileVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberProfileVO mpdata = new MemberProfileVO();

		mpdata.setProfileNum(rs.getInt("PROFILENUM"));
		mpdata.setMemberID(rs.getString("MEMBERID"));
		mpdata.setProfileImg(rs.getString("PROFILEIMG"));
		mpdata.setShortIntro(rs.getString("SHORTINTRO"));
		mpdata.setIntro(rs.getString("INTRO"));
		mpdata.setProhibitCnt(rs.getInt("PROHIBITCNT"));
		mpdata.setRecommendCnt(rs.getInt("RECOMMENDCNT"));
		mpdata.setNickName(rs.getString("NICKNAME"));
		mpdata.setAddress(rs.getString("ADDRESS"));

		return mpdata;
	}

}
