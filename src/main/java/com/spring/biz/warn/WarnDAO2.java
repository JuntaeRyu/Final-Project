package com.spring.biz.warn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class WarnDAO2 implements InterfaceWarnDAO{
	////////////////////// 쿼리문 ////////////////////////////////////////////////////
	// 게시글로 인한 경고 추가
	private final String sql_INSERT_BOARDWARN = "INSERT INTO WARN (WARNNUM, MEMBERID, WARNTYPE, WARNDATE) VALUES ((SELECT NVL(MAX(WARNNUM),0)+1 FROM WARN),?, 1, SYSTIMESTAMP)";
	// 프로필로 인한 경고 추가
	private final String sql_INSERT_PROFILEWARN = "INSERT INTO WARN (WARNNUM, MEMBERID, WARNTYPE, WARNDATE) VALUES ((SELECT NVL(MAX(WARNNUM),0)+1 FROM WARN),?, 2, SYSTIMESTAMP)";
	// 경고 누적 목록
	private final String sql_SELECTALL = "SELECT WARNNUM, MEMBERID, WARNTYPE, TO_CHAR(WARNDATE, 'YYYY-MM-DD') AS WARNDATE FROM WARN WHERE MEMBERID = ?";
	//	private final String sql_SELECTONE = "";
	//	private final String sql_UPDATE = "";
	private final String sql_DELETE = "DELETE FROM WARN WHERE MEMBERID=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	////////////////////// insert ////////////////////////////////////////////////////
	public boolean insert(WarnVO wVO) {

		// 1- 게시글 신고누적, 2-프로필 신고누적
		int result=0;
		if(wVO.getSearchCondition()==null) {
			System.out.println("WarnDAO2 insert 서치컨디션 null");
		}
		else if(wVO.getSearchCondition().equals("boardWarn")) {
			result=jdbcTemplate.update(sql_INSERT_BOARDWARN, wVO.getMemberID());
		}
		else if(wVO.getSearchCondition().equals("profileWarn")) {
			result=jdbcTemplate.update(sql_INSERT_PROFILEWARN, wVO.getMemberID());
		}

		// 검색 결과 리턴
		if (result > 0) {
			return true;
		}
		return false;
	}

	////////////////////// selectAll ////////////////////////////////////////////////////
	public List<WarnVO> selectAll(WarnVO wVO) {

		// 쿼리문 수정할 정보 저장
		Object[] args = { wVO.getMemberID() };

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

		int result= jdbcTemplate.update(sql_DELETE, wVO.getMemberID());
		// 검색 결과 리턴
		if (result > 0) {
			return true;
		}
		return false;
	}
}

////////////////////// rowMapper ////////////////////////////////////////////////////
class WarnRowMapper implements RowMapper<WarnVO> {

	@Override
	public WarnVO mapRow(ResultSet rs, int rowNum) throws SQLException {

		WarnVO wdata = new WarnVO();

		wdata.setWarnNum(rs.getInt("WARNNUM"));
		wdata.setMemberID(rs.getString("MEMBERID"));
		wdata.setWarnType(rs.getInt("WARNTYPE"));
		wdata.setWarnDate(rs.getString("WARNDATE"));

		return wdata;
	}
}
