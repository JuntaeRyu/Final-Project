package com.spring.biz.reply;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDAO2 implements InterfaceReplyDAO{
	////////////////////// 쿼리문 ////////////////////////////////////////////////////	
	// 대댓글 작성
	private final String sql_INSERT = "INSERT INTO REPLY (REPLYNUM, REPLY, COMMENTSNUM, MEMBERID, REPLYDATE) VALUES ((SELECT NVL(MAX(REPLYNUM),29999)+1 FROM REPLY), ?, ?, ?, SYSTIMESTAMP)";
	// 대댓글 전체 출력
	private final String sql_SELECTALL = "SELECT R.REPLYNUM, R.REPLY, R.COMMENTSNUM, R.MEMBERID, TO_CHAR(R.REPLYDATE, 'YYYY-MM-DD') AS REPLYDATE, (SELECT COUNT(CASE WHEN COMMONNUM=REPLYNUM THEN 1 END ) FROM PROHIBIT) AS PROHIBITCNT, M.NICKNAME FROM REPLY R LEFT JOIN MEMBER M ON R.MEMBERID=M.MEMBERID ORDER BY R.REPLYNUM ASC";
	// 선택된 댓글의 대댓글 전체출력
	private final String sql_SELECTALL_COMMENTSNUM = "SELECT R.REPLYNUM, R.REPLY, R.COMMENTSNUM, R.MEMBERID, TO_CHAR(R.REPLYDATE, 'YYYY-MM-DD') AS REPLYDATE, (SELECT COUNT(CASE WHEN COMMONNUM=REPLYNUM THEN 1 END ) FROM PROHIBIT) AS PROHIBITCNT, M.NICKNAME FROM REPLY R LEFT JOIN MEMBER M ON R.MEMBERID=M.MEMBERID WHERE COMMENTSNUM=?";
	// 대댓글 선택 출력
	private final String sql_SELECTONE = "SELECT R.REPLYNUM, R.REPLY, R.COMMENTSNUM, R.MEMBERID, R.PROHIBITCNT, TO_CHAR(R.REPLYDATE, 'YYYY-MM-DD') AS REPLYDATE, M.NICKNAME FROM REPLY R LEFT JOIN MEMBER M ON R.MEMBERID=M.MEMBERID WHERE R.REPLYNUM=?";
	// 대댓글 수정
	private final String sql_UPDATE_REPLY = "UPDATE REPLY SET REPLY=? WHERE REPLYNUM=?";
	// 대댓글 신고횟수 갱신
	private final String sql_UPDATE_PROHIBIT = "UPDATE REPLY SET PROHIBITCNT=(SELECT COUNT(CASE WHEN COMMONNUM=? THEN 1 END ) FROM PROHIBIT) WHERE REPLYNUM=?";
	// 대댓글 삭제
	private final String sql_DELETE = "DELETE FROM REPLY WHERE REPLYNUM=?";
	// 회원 정지 및 탈퇴시 대댓글 삭제
	private final String sql_DELETE_MEMBERID = "DELETE FROM REPLY WHERE MEMBERID=?";

	// JDBC(자바 데이터베이스 커넥트) 도구
	@Autowired
	JdbcTemplate jdbcTemplate;

	////////////////////// insert ////////////////////////////////////////////////////
	public boolean insert(ReplyVO rVO) { // DB에 객체정보 저장

		// 쿼리문 수정 및 실행 후 결과를 저장
		int result = jdbcTemplate.update(sql_INSERT, rVO.getReply(), rVO.getCommentsNum(), rVO.getMemberID());

		// 검색 결과 리턴
		if (result <=0) {
			return false;
		}
		return true;
	} // insert

	////////////////////// selectAll ////////////////////////////////////////////////////
	public List<ReplyVO> selectAll(ReplyVO rVO) { // 목록 검색

		// SQL 쿼리문 입력
		if (rVO.getSearchCondition()==null) {
			System.out.println("replyDAO2 selectAll 서치컨디션 null");
		}
		else if (rVO.getSearchCondition().equals("totalReply")) {
			// 쿼리문 수정 및 실행 후 결과 리턴
			return jdbcTemplate.query(sql_SELECTALL, new ReplyRowMapper());
		} else if (rVO.getSearchCondition().equals("commentsReplyNum")) {
			// 쿼리문 수정할 정보 저장
			Object[] args = { rVO.getCommentsNum() };

			// 쿼리문 수정 및 실행 후 결과 리턴
			return jdbcTemplate.query(sql_SELECTALL_COMMENTSNUM, args, new ReplyRowMapper());
		}
		return null;
	}

	////////////////////// selectOne ////////////////////////////////////////////////////
	public ReplyVO selectOne(ReplyVO rVO) { // 하나의 객체 정보 검색

		try { // 의도치 않은 프로그램종료 예방
			// 쿼리문 수정할 정보 저장
			Object[] args = { rVO.getReplyNum() };

			// 쿼리문 수정 및 실행 후 결과 리턴
			return jdbcTemplate.queryForObject(sql_SELECTONE, args, new ReplyRowMapper());
		}
		catch(EmptyResultDataAccessException e) {
			System.out.println("해결~");
			return null;
		}
	}

	////////////////////// update ////////////////////////////////////////////////////
	public boolean update(ReplyVO rVO) {

		int result = 0;

		// SQL 쿼리문 입력

		if (rVO.getSearchCondition()==null) {
			System.out.println("replyDAO2 update 서치컨디션 null");
		}
		else if (rVO.getSearchCondition().equals("updateReply")) {
			// 쿼리문 수정 및 실행 후 결과를 저장
			result = jdbcTemplate.update(sql_UPDATE_REPLY, rVO.getReply(), rVO.getReplyNum());
		} else if (rVO.getSearchCondition().equals("prohibit")) {
			// 쿼리문 수정 및 실행 후 결과를 저장
			result = jdbcTemplate.update(sql_UPDATE_PROHIBIT, rVO.getReplyNum(), rVO.getReplyNum());
		}

		// 검색 결과 리턴
		if (result <=0) {
			return false;
		}
		return true;
	}

	////////////////////// delete ////////////////////////////////////////////////////
	public boolean delete(ReplyVO rVO) {

		// 쿼리문 수정 및 실행 후 결과를 저장
		int result = 0;
		if (rVO.getSearchCondition().equals("replyNum")) {
			result = jdbcTemplate.update(sql_DELETE, rVO.getReplyNum());
		}
		else if (rVO.getSearchCondition().equals("memberID")) {
			result = jdbcTemplate.update(sql_DELETE_MEMBERID, rVO.getMemberID());
		}

		// 성공 리턴
		if (result <=0) {
			return false;
		}
		return true;
	}
}

////////////////////// rowMapper ////////////////////////////////////////////////////
class ReplyRowMapper implements RowMapper<ReplyVO> {

	@Override
	public ReplyVO mapRow(ResultSet rs, int rowNum) throws SQLException {

		ReplyVO rdata = new ReplyVO();

		rdata.setReplyNum(rs.getInt("REPLYNUM"));
		rdata.setReply(rs.getString("REPLY"));
		rdata.setCommentsNum(rs.getInt("COMMENTSNUM"));
		rdata.setMemberID(rs.getString("MEMBERID"));
		rdata.setProhibitCnt(rs.getInt("PROHIBITCNT"));
		rdata.setReplyDate(rs.getString("REPLYDATE"));
		rdata.setNickName(rs.getString("NICKNAME"));

		return rdata;
	}
}
