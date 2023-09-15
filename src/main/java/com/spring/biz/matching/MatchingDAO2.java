package com.spring.biz.matching;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("MatchingDAO")
public class MatchingDAO2 {

	// 매칭 신청
	private String sql_INSERT = "INSERT INTO MATCHING (MATCHINGNUM, SENDERID, RECEIVERID) VALUES ((SELECT NVL(MAX(MATCHINGNUM),0)+1 FROM MATCHING),?, ?)";
	// 매칭 목록
	private String sql_SELECTALL = "SELECT MATCHINGNUM, SENDERID, RECEIVERID, ACCEPT FROM MATCHING WHERE SENDERID = ? OR RECEIVERID = ?";
//	// 매칭 상세페이지?
//	private String sql_SELECTONE = "SELECT MATCHINGNUM, SENDERID, RECEIVERID, ACCEPT FROM MATCHING WHERE MATCHINGNUM = ?";
	// 매칭 수락하면 변경해주는 확인 여부
	private String sql_UPDATE = "UPDATE MATCHING SET ACCEPT = 1 WHERE MATCHINGNUM = ?";
	// 매칭 거절로 인한 매칭데이터 삭제
	private String sql_DELETE = "DELETE FROM MATCHING WHERE MATCHINGNUM = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
/////////////////// insert ///////////////////////////////////////////////
	public boolean insert(MatchingVO mcVO) {

		// 쿼리문 수정 및 실행 후 결과를 저장
		int result = jdbcTemplate.update(sql_INSERT, mcVO.getSenderID(), mcVO.getReceiverID());
		
		// 검색 결과 리턴
		if (result > 0) {
			return true;
		}
		return false;
	}
	
/////////////////// selectAll ///////////////////////////////////////////////
	public List<MatchingVO> selectAll(MatchingVO mcVO) {
		
		// 쿼리문 수정할 정보 저장
		Object[] args = { mcVO.getSenderID(), mcVO.getReceiverID() };

		// 쿼리문 수정 및 실행 후 결과 리턴
		return jdbcTemplate.query(sql_SELECTALL, args, new MatchingRowMapper());
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
