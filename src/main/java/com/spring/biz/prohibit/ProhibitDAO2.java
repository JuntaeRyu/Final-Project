package com.spring.biz.prohibit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProhibitDAO2 implements InterfaceProhibitDAO{

	// 신고 추가
	private final String sql_INSERT = "INSERT INTO PROHIBIT (PROHIBITNUM,MEMBERID,COMMONNUM) VALUES ((SELECT NVL(MAX(PROHIBITNUM),0)+1 FROM PROHIBIT), ? , ? )";
//	// 신고 전체출력
//	private final String sql_SELECTALL = "SELECT PROHIBITNUM,MEMBERID,COMMONNUM FROM PROHIBIT WHERE COMMONNUM=?";
//	// 신고 정보 출력
	private final String sql_SELECTONE = "SELECT PROHIBITNUM,MEMBERID,COMMONNUM FROM PROHIBIT WHERE MEMBERID=? AND COMMONNUM=?";
	// 신고 취소(삭제)
	private final String sql_DELETE = "DELETE FROM PROHIBIT WHERE PROHIBITNUM=?";
	// 게시물 또는 댓글 대댓글 삭제
	private final String sql_DELETE_COMMONNUM = "DELETE FROM PROHIBIT WHERE COMMONNUM=?";

	// JDBC(자바 데이터베이스 커넥트) 도구
	@Autowired
	private JdbcTemplate jdbcTemplate;

////////////////////// insert ////////////////////////////////////////////////////	
	public boolean insert(ProhibitVO pVO) { // DB에 객체정보 저장

		// 쿼리문 수정 및 실행 후 결과를 저장
		int result = jdbcTemplate.update(sql_INSERT, pVO.getMemberID(), pVO.getCommonNum());

		// 검색 결과 리턴
		if (result <= 0) {
			return false; // 저장 실패
		}
		return true;
	} // insert

////////////////////// selectAll ////////////////////////////////////////////////////
	public List<ProhibitVO> selectAll(ProhibitVO pVO) { // 목록 검색

//		// 쿼리문 수정할 정보 저장
//		Object[] args = { pVO.getCommonNum() };
//
//		// 쿼리문 수정 및 실행 후 결과 리턴
//		return jdbcTemplate.query(sql_SELECTALL, args, new ProhibitRowMapper());
		return null;
	}

////////////////////// selectOne ////////////////////////////////////////////////////
	public ProhibitVO selectOne(ProhibitVO pVO) { // 하나의 객체 정보 검색
		try { // 의도하지 않은 프로그램종료 예방
			// 쿼리문 수정할 정보 저장
			Object[] args = { pVO.getMemberID(), pVO.getCommonNum() };

			// 쿼리문 수정 및 실행 후 결과 리턴
			return jdbcTemplate.queryForObject(sql_SELECTONE, args, new ProhibitRowMapper());
		}
		catch(EmptyResultDataAccessException e) {
			return null;
		}
	}

////////////////////// update ////////////////////////////////////////////////////
	public boolean update(ProhibitVO pVO) {
		return false;
	}

	public boolean delete(ProhibitVO pVO) {

		// 쿼리문 수정 및 실행 후 결과를 저장
		int result = 0;
		if(pVO.getSearchCondition()== null) {
			
		}else if(pVO.getSearchCondition().equals("prohibitCancel")) {
			
			jdbcTemplate.update(sql_DELETE, pVO.getProhibitNum());
		}else if(pVO.getSearchCondition().equals("commonNum")) {
			
			jdbcTemplate.update(sql_DELETE_COMMONNUM, pVO.getCommonNum());
		}

		// 성공 리턴
		if (result <= 0) {
			return false;
		}
		return true;
	}
}

////////////////////// rowMapper ////////////////////////////////////////////////////
class ProhibitRowMapper implements RowMapper<ProhibitVO> {

	@Override
	public ProhibitVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProhibitVO pdata = new ProhibitVO();
		
		pdata.setProhibitNum(rs.getInt("PROHIBITNUM"));
		pdata.setMemberID(rs.getString("MEMBERID"));
		pdata.setCommonNum(rs.getInt("COMMONNUM"));
		
		return pdata;
	}

}
