package com.spring.biz.recommend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class RecommendDAO2 implements InterfaceRecommendDAO{
	// 추천 추가
	private final String sql_INSERT = "INSERT INTO RECOMMEND (RECOMMENDNUM, MEMBERID,COMMONNUM) VALUES ((SELECT NVL(MAX(RECOMMENDNUM),0)+1 FROM RECOMMEND), ? , ? )";
//	private final String sql_SELECTALL = "SELECT RECOMMENDNUM,MEMBERID,COMMONNUM FROM RECOMMEND WHERE COMMONNUM=?";
//	// 추천 정보 출력
	private final String sql_SELECTONE = "SELECT RECOMMENDNUM,MEMBERID,COMMONNUM FROM RECOMMEND WHERE MEMBERID=? AND COMMONNUM=?";
//	 추천 취소(삭제)
	private final String sql_DELETE = "DELETE FROM RECOMMEND WHERE RECOMMENDNUM=?";
	// 게시물 또는 댓글 대댓글 삭제로인한 추천수 삭제
	private final String sql_DELETE_COMMONNUM = "DELETE FROM RECOMMEND WHERE COMMONNUM=?";

	// JDBC(자바 데이터베이스 커넥트) 도구
	@Autowired
	JdbcTemplate jdbcTemplate;

////////////////////// insert ////////////////////////////////////////////////////
	public boolean insert(RecommendVO rcVO) { // DB에 객체정보 저장

		// 쿼리문 수정 및 실행 후 결과를 저장
		int result = jdbcTemplate.update(sql_INSERT, rcVO.getMemberID(), rcVO.getCommonNum());

		// 검색 결과 리턴
		if (result <= 0) {
			return false; // 저장 실패
		}
		return true;

	} // insert

////////////////////// selectAll ////////////////////////////////////////////////////
	public List<RecommendVO> selectAll(RecommendVO rcVO) { // 목록 검색

		// 쿼리문 수정할 정보 저장
//		Object[] args = { rcVO.getRecommendNum() };

		// 쿼리문 수정 및 실행 후 결과 리턴
//		return jdbcTemplate.query(sql_SELECTALL, args, new RecommendRowMapper());
		return null;
	}

////////////////////// selectOne ////////////////////////////////////////////////////
	public RecommendVO selectOne(RecommendVO rcVO) { // 하나의 객체 정보 검색

		try { // 의도하지 않은 프로그램종료 예방
			// 쿼리문 수정할 정보 저장

			Object[] args = { rcVO.getMemberID(), rcVO.getCommonNum() };

			// 쿼리문 수정 및 실행 후 결과 리턴
			return jdbcTemplate.queryForObject(sql_SELECTONE, args, new RecommendRowMapper());
		}
		catch(EmptyResultDataAccessException e) {
			System.out.println("해결~");
			return null;
		}
		
	}

////////////////////// update ////////////////////////////////////////////////////
	public boolean update(RecommendVO rcVO) {
		return false;
	}

////////////////////// delete ////////////////////////////////////////////////////
	public boolean delete(RecommendVO rcVO) {

		// 쿼리문 수정 및 실행 후 결과를 저장
		
		int result = 0;
		if(rcVO.getSearchCondition()== null) {
			
		}else if(rcVO.getSearchCondition().equals("recommendCancel")) {
			
			jdbcTemplate.update(sql_DELETE, rcVO.getRecommendNum());
		}else if(rcVO.getSearchCondition().equals("commonNum")) {
			
			jdbcTemplate.update(sql_DELETE_COMMONNUM, rcVO.getCommonNum());
		}

		// 성공 리턴
		if (result <=0) {
			return false;
		}
		return true;
	}
}

////////////////////// rowMapper ////////////////////////////////////////////////////
class RecommendRowMapper implements RowMapper<RecommendVO> {

	@Override
	public RecommendVO mapRow(ResultSet rs, int rowNum) throws SQLException {

		RecommendVO rcdata = new RecommendVO();
		rcdata.setRecommendNum(rs.getInt("RECOMMENDNUM"));
		rcdata.setMemberID(rs.getString("MEMBERID"));
		rcdata.setCommonNum(rs.getInt("COMMONNUM"));

		return rcdata;
	}

}
