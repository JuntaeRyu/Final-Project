package com.spring.biz.warn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("WarnDAO")
public class WarnDAO2 {
////////////////////// 쿼리문 ////////////////////////////////////////////////////
	// 경고 추가
	private final String sql_INSERT = "INSERT INTO WARN (WARNNUM, MEMBERID, WARNTYPE, WARNDATE) VALUES ((SELECT NVL(MAX(WARNNUM),0)+1 FROM WARN),?, ?, SYSTIMESTAMP)";
	// 경고 누적 목록
	private final String sql_SELECTALL = "SELECT WARNNUM, MEMBERID, WARNTYPE, TO_CHAR(WARNDATE, 'YYYY-MM-DD') AS WARNDATE FROM WARN WHERE MEMBERID = ?";
//	private final String sql_SELECTONE = "";
//	private final String sql_UPDATE = "";
//	private final String sql_DELETE = "";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
////////////////////// insert ////////////////////////////////////////////////////
	public boolean insert(WarnVO wVO) {

		// 1- 게시글 신고누적, 2-프로필 신고누적
		int result = jdbcTemplate.update(sql_INSERT, wVO.getMenberID(), wVO.getWarnType());

		// 검색 결과 리턴
		if (result > 0) {
			return true;
		}
		return false;
	}

////////////////////// selectAll ////////////////////////////////////////////////////
	public List<WarnVO> selectAll(WarnVO wVO) {

		// 쿼리문 수정할 정보 저장
		Object[] args = { wVO.getMenberID() };

		// 쿼리문 수정 및 실행 후 결과 리턴
		return jdbcTemplate.query(sql_SELECTALL, args, new WarnRowMapper());
	}

////////////////////// selectOne ////////////////////////////////////////////////////
	public WarnVO selectOne(WarnVO wVO) {
		
		return null;
	}

////////////////////// update ////////////////////////////////////////////////////
	public boolean update(WarnVO wVO) {
		
		return false;
	}

////////////////////// delete ////////////////////////////////////////////////////
	public boolean delete(WarnVO wVO) {
		
		return false;
	}
}

////////////////////// rowMapper ////////////////////////////////////////////////////
class WarnRowMapper implements RowMapper<WarnVO> {

	@Override
	public WarnVO mapRow(ResultSet rs, int rowNum) throws SQLException {

		WarnVO wdata = new WarnVO();
		
		wdata.setWarnNum(rs.getInt("WARNNUM"));
		wdata.setMenberID(rs.getString("MEMBERID"));
		wdata.setWarnType(rs.getInt("WARNTYPE"));
		wdata.setWarnDate(rs.getString("WARNDATE"));

		return wdata;
	}
}
