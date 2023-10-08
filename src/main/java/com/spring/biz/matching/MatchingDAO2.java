package com.spring.biz.matching;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("MatchingDAO")
public class MatchingDAO2 {

	// 매칭 신청
	private String sql_INSERT = "INSERT INTO MATCHING (MATCHINGNUM, SENDERID, RECEIVERID) VALUES ((SELECT NVL(MAX(MATCHINGNUM),0)+1 FROM MATCHING),?, ?)";
	// 내가 보낸 매칭 목록
	private String sql_SELECTALL_SENDED= "SELECT MC.MATCHINGNUM, MC.RECEIVERID, MC.ACCEPT, M.NICKNAME, MP.PROFILENUM, MP.PROFILEIMG, MP.SHORTINTRO "
			+ "FROM MATCHING MC "
			+ "INNER JOIN MEMBER M ON MC.RECEIVERID = M.MEMBERID "
			+ "INNER JOIN MEMBERPROFILE MP ON MC.RECEIVERID = MP.MEMBERID "
			+ "WHERE MC.SENDERID = ? AND MP.PROHIBITCNT <= 2 "
			+ "ORDER BY MC.ACCEPT DESC,M.NICKNAME ASC";
	// 내가 받은 매칭 목록
	private String sql_SELECTALL_RECEIVED= "SELECT MC.MATCHINGNUM, MC.SENDERID, MC.ACCEPT, M.NICKNAME, MP.PROFILENUM, MP.PROFILEIMG, MP.SHORTINTRO "
			+ "FROM MATCHING MC "
			+ "INNER JOIN MEMBER M ON MC.SENDERID = M.MEMBERID "
			+ "INNER JOIN MEMBERPROFILE MP ON MC.SENDERID = MP.MEMBERID "
			+ "WHERE MC.RECEIVERID = ? AND MP.PROHIBITCNT <= 2"
			+ "ORDER BY MC.ACCEPT DESC,M.NICKNAME ASC";
	//	// 매칭 상세페이지?
//		private String sql_SELECTONE = "SELECT MATCHINGNUM, SENDERID, RECEIVERID, ACCEPT FROM MATCHING WHERE MATCHINGNUM = ?";
	// 매칭 수락하면 변경해주는 확인 여부
	private String sql_UPDATE = "UPDATE MATCHING SET ACCEPT = 1 WHERE MATCHINGNUM = ?";
	// 매칭 거절로 인한 매칭데이터 삭제
	private String sql_DELETE = "DELETE FROM MATCHING WHERE MATCHINGNUM = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/////////////////// insert ///////////////////////////////////////////////
	public boolean insert(MatchingVO mcVO) {

		int result=0;
		// 쿼리문 수정 및 실행 후 결과를 저장
		try {
			result = jdbcTemplate.update(sql_INSERT, mcVO.getSenderID(), mcVO.getReceiverID());
		}catch(DuplicateKeyException e) {
			return false;
		}

		// 검색 결과 리턴
		if (result > 0) {
			return true;
		}
		return false; 
	}

	/////////////////// selectAll ///////////////////////////////////////////////
	public List<MatchingVO> selectAll(MatchingVO mcVO) {

		if(mcVO.getSearchCondition()==null) {
			System.out.println("MatchingDAO2 selectAll 서치컨디션 null");
		}
		else if(mcVO.getSearchCondition().equals("sent")) {
			Object[] args = { mcVO.getSenderID() };
			return jdbcTemplate.query(sql_SELECTALL_SENDED, args, new MatchingSenderRowMapper());
		}
		else if(mcVO.getSearchCondition().equals("received")) {
			Object[] args = { mcVO.getReceiverID() };
			return jdbcTemplate.query(sql_SELECTALL_RECEIVED, args, new MatchingReceiverRowMapper());
		}
		return null;
	}

	/////////////////// selectOne ///////////////////////////////////////////////
	public MatchingVO selectOne(MatchingVO mcVO) {

		//		// 쿼리문 수정할 정보 저장
		//		Object[] args = { mcVO.getMatchingNum() };
		//		
		//		// 쿼리문 수정 및 실행 후 결과 리턴
		//		return jdbcTemplate.queryForObject(sql_SELECTONE, args, new MatchingRowMapper());
		return null;
	}

	/////////////////// update ///////////////////////////////////////////////
	public boolean update(MatchingVO mcVO) {

		// 쿼리문 수정 및 실행 후 결과를 저장
		int result = jdbcTemplate.update(sql_UPDATE, mcVO.getMatchingNum());

		// 검색 결과 리턴
		if (result > 0) {
			return true;
		}
		return false;
	}

	/////////////////// delete ///////////////////////////////////////////////
	public boolean delete(MatchingVO mcVO) {

		// 쿼리문 수정 및 실행 후 결과를 저장
		int result = jdbcTemplate.update(sql_DELETE, mcVO.getMatchingNum());

		// 검색 결과 리턴
		if (result > 0) {
			return true;
		}
		return false;
	}
}

/////////////////// rowMapper ///////////////////////////////////////////////
// 매칭
class MatchingRowMapper implements RowMapper<MatchingVO> {

	@Override
	public MatchingVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MatchingVO mcdata = new MatchingVO();

		mcdata.setMatchingNum(rs.getInt("MATCHINGNUM"));
		mcdata.setSenderID(rs.getString("SENDERID"));
		mcdata.setReceiverID(rs.getString("RECEIVERID"));
		mcdata.setAccept(rs.getInt("ACCEPT"));

		return mcdata;
	}
}

class MatchingSenderRowMapper implements RowMapper<MatchingVO> {

	@Override
	public MatchingVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MatchingVO mcdata = new MatchingVO();

		mcdata.setMatchingNum(rs.getInt("MATCHINGNUM"));
		mcdata.setReceiverID(rs.getString("RECEIVERID"));
		mcdata.setAccept(rs.getInt("ACCEPT"));
		mcdata.setReceiverNickName(rs.getString("NICKNAME"));
		mcdata.setProfileNum(rs.getInt("PROFILENUM"));
		mcdata.setProfileImg(rs.getString("PROFILEIMG"));
		mcdata.setShortIntro(rs.getString("SHORTINTRO"));
		
		return mcdata;
	}
}

class MatchingReceiverRowMapper implements RowMapper<MatchingVO> {

	@Override
	public MatchingVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MatchingVO mcdata = new MatchingVO();

		mcdata.setMatchingNum(rs.getInt("MATCHINGNUM"));
		mcdata.setSenderID(rs.getString("SENDERID"));
		mcdata.setAccept(rs.getInt("ACCEPT"));
		mcdata.setSenderNickName(rs.getString("NICKNAME"));
		mcdata.setProfileNum(rs.getInt("PROFILENUM"));
		mcdata.setProfileImg(rs.getString("PROFILEIMG"));
		mcdata.setShortIntro(rs.getString("SHORTINTRO"));

		return mcdata;
	}
}





