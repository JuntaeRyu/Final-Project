package com.spring.biz.comments;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CommentsDAO2 implements InterfaceCommentsDAO{

	// 댓글 작성
	private final String sql_INSERT = "INSERT INTO COMMENTS (COMMENTSNUM, COMMENTS, BOARDNUM, MEMBERID, COMMENTSDATE) VALUES ((SELECT NVL(MAX(COMMENTSNUM),19999)+1 FROM COMMENTS), ?, ?, ?, SYSTIMESTAMP)";
	// 선택한 게시물의 댓글 전체 출력
	private final String sql_SELECTALL = "SELECT C.COMMENTSNUM, C.COMMENTS, C.BOARDNUM, C.MEMBERID, C.PROHIBITCNT, TO_CHAR(C.COMMENTSDATE, 'YYYY-MM-DD') AS COMMENTSDATE, M.NICKNAME FROM COMMENTS C LEFT JOIN MEMBER M ON C.MEMBERID=M.MEMBERID WHERE C.BOARDNUM=? ORDER BY C.COMMENTSNUM DESC";
	// 댓글 선택(?)
	private final String sql_SELECTONE = "SELECT C.COMMENTSNUM, C.COMMENTS, C.BOARDNUM, C.MEMBERID, C.PROHIBITCNT, TO_CHAR(C.COMMENTSDATE, 'YYYY-MM-DD') AS COMMENTSDATE, M.NICKNAME FROM COMMENTS C LEFT JOIN MEMBER M ON C.MEMBERID=M.MEMBERID WHERE C.COMMENTSNUM=?";
	// 댓글 수정
	private final String sql_UPDATE_COMMENTS = "UPDATE COMMENTS SET COMMENTS=? WHERE COMMENTSNUM=?";
	// 댓글의 신고수 갱신
	private final String sql_UPDATE_PROHIBIT = "UPDATE COMMENTS SET PROHIBITCNT=(SELECT COUNT(CASE WHEN COMMONNUM=? THEN 1 END ) FROM PROHIBIT) WHERE COMMENTSNUM=?";
	// 댓글 삭제
	private final String sql_DELETE = "DELETE FROM COMMENTS WHERE COMMENTSNUM=?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

/////////////////// insert ///////////////////////////////////////////////
	public boolean insert(CommentsVO cVO) { 

		System.out.println("CommentDAO2 로그 insert 메서드");
		
		int rs = jdbcTemplate.update(sql_INSERT, cVO.getComments(), cVO.getBoardNum(), cVO.getMemberID());
		if (rs <= 0) {
			return false;
		}
		return true;
	}

/////////////////// selectAll ///////////////////////////////////////////////
	public List<CommentsVO> selectAll(CommentsVO cVO) { // 목록 검색

		System.out.println("CommentDAO2 로그 selectAll 메서드");
		
		Object[] args = { cVO.getBoardNum() };
		
		return jdbcTemplate.query(sql_SELECTALL, args, new CommentsRowMapper());
	}

/////////////////// selectOne ///////////////////////////////////////////////
	public CommentsVO selectOne(CommentsVO cVO) {

		System.out.println("CommentDAO2 로그 selectOne 메서드");

		try { // 의도하지 않은 프로그램종료 예방
			Object[] args= {cVO.getCommentsNum()};

			return jdbcTemplate.queryForObject(sql_SELECTONE, args, new CommentsRowMapper());
		}
		catch(EmptyResultDataAccessException e) {
			System.out.println("commentsDAO2 selectOne 데이터 비어있음");
			return null;
		}
	}

/////////////////// update ///////////////////////////////////////////////
	public boolean update(CommentsVO cVO) {
		
		System.out.println("CommentDAO2 로그 update 메서드");
		int rs=0;
		if(cVO.getSearchCondition()==null) {
			System.out.println("commentsDAO2 update 서치컨디션 null");
		}
		// 댓글 수정
		else if(cVO.getSearchCondition().equals("updateComments")) {
			rs=jdbcTemplate.update(sql_UPDATE_COMMENTS, cVO.getComments(), cVO.getCommentsNum());

		}
		// 댓글의 신고수 갱신
		else if(cVO.getSearchCondition().equals("prohibit")) {
			rs=jdbcTemplate.update(sql_UPDATE_PROHIBIT, cVO.getCommentsNum(), cVO.getCommentsNum());
		}
		if (rs > 0) {
			return true;
		}
		return false;
	}

/////////////////// delete ///////////////////////////////////////////////
	public boolean delete(CommentsVO cVO) {
		System.out.println("CommentDAO2 로그 delete 메서드");
		int rs=jdbcTemplate.update(sql_DELETE, cVO.getCommentsNum());
		if (rs <= 0) {
			return false;
		}
		return true;
	}
}

/////////////////// rowMapper ///////////////////////////////////////////////
class CommentsRowMapper implements RowMapper<CommentsVO>{

	@Override
	public CommentsVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CommentsVO cdata=new CommentsVO();

		cdata.setCommentsNum(rs.getInt("COMMENTSNUM"));
		cdata.setComments(rs.getString("COMMENTS"));
		cdata.setBoardNum(rs.getInt("BOARDNUM"));
		cdata.setMemberID(rs.getString("MEMBERID"));
		cdata.setProhibitCnt(rs.getInt("PROHIBITCNT"));
		cdata.setCommentsDate(rs.getString("COMMENTSDATE"));
		cdata.setNickName(rs.getString("NICKNAME"));
		
		return cdata;
	}


}
