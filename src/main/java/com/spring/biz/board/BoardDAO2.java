package com.spring.biz.board;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO2 implements InterfaceBoardDAO{
	// 게시글 작성
	private final String sql_INSERT = "INSERT INTO BOARD "
			+ "(BOARDNUM, TITLE, CONTENT, MEMBERID, CATEGORY, BOARDIMG, BOARDDATE) "
			+ "VALUES((SELECT NVL(MAX(BOARDNUM),9999)+1 FROM BOARD), ?, ?, ?, ?, ?, SYSTIMESTAMP)";
	// 전체 커뮤니티 게시물 목록
	private final String sql_SELECTALL_COMMUNITY = "SELECT B.BOARDNUM, B.TITLE, B.RECOMMENDCNT, B.CATEGORY, B.VIEWCNT, M.NICKNAME, "
			+ "TO_CHAR(B.BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, "
			+ "(SELECT COUNT(CASE WHEN BOARDNUM = B.BOARDNUM THEN 1 END ) FROM COMMENTS) AS COMMENTSCNT "
			+ "FROM BOARD B JOIN MEMBER M ON B.MEMBERID = M.MEMBERID "
			+ "WHERE B.CATEGORY IN (1,2) ORDER BY BOARDNUM DESC";
	// 카테고리별 커뮤니티 게시물 목록
	private final String sql_SELECTALL_CATEGORY = "SELECT B.BOARDNUM, B.TITLE, B.RECOMMENDCNT, B.CATEGORY, B.VIEWCNT, M.NICKNAME, "
			+ "B.CATEGORY, TO_CHAR(B.BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, "
			+ "(SELECT COUNT(CASE WHEN BOARDNUM = B.BOARDNUM THEN 1 END ) FROM COMMENTS) AS COMMENTSCNT "
			+ "FROM BOARD B JOIN MEMBER M ON B.MEMBERID=M.MEMBERID "
			+ "WHERE B.CATEGORY=? ORDER BY BOARDNUM DESC";
	// 내가 작성한 게시물 목록
	private final String sql_SELECTALL_MYBOARD = "SELECT B.BOARDNUM, B.TITLE, B.RECOMMENDCNT, B.CATEGORY, B.VIEWCNT, M.NICKNAME, "
			+ "TO_CHAR(B.BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, "
			+ "(SELECT COUNT(CASE WHEN BOARDNUM = B.BOARDNUM THEN 1 END ) FROM COMMENTS) AS COMMENTSCNT "
			+ "FROM BOARD B JOIN MEMBER M ON B.MEMBERID=M.MEMBERID "
			+ "WHERE B.MEMBERID=? ORDER BY BOARDNUM DESC";
	// 해당 작성자가 작성한 게시물 목록 3개
	private final String sql_SELECTALL_WRITERBOARD = "SELECT BOARDNUM, TITLE, RECOMMENDCNT, CATEGORY, VIEWCNT, NICKNAME, BOARDDATE, COMMENTSCNT "
			+ "FROM "
			+ 		"(SELECT B.BOARDNUM, B.TITLE, B.RECOMMENDCNT, B.CATEGORY, B.VIEWCNT, M.NICKNAME, TO_CHAR(B.BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, "
			+ 		"(SELECT COUNT(CASE WHEN C.BOARDNUM = B.BOARDNUM THEN 1 END) FROM COMMENTS C) AS COMMENTSCNT "
			+ 		"FROM BOARD B JOIN MEMBER M ON B.MEMBERID = M.MEMBERID "
			+ 		"WHERE B.MEMBERID = ? "
			+ 		"ORDER BY B.BOARDDATE DESC) "
			+ "WHERE ROWNUM <= 3";	// 정렬한 후에 가져오는 쿼리문임
	// 신고 3개이상인 게시물 목록
	private final String sql_SELECTALL_PROHIBITCNT = "SELECT B.BOARDNUM, B.TITLE, B.MEMBERID, B.PROHIBITCNT, B.RECOMMENDCNT, B.CATEGORY, B.VIEWCNT, M.NICKNAME, "
			+ "TO_CHAR(B.BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE "
			+ "FROM BOARD B JOIN MEMBER M ON B.MEMBERID=M.MEMBERID "
			+ "WHERE B.PROHIBITCNT >= 3 ORDER BY BOARDNUM DESC";
	// 추천수 top5 게시물 리스트
	private final String sql_SELECTALL_RANK = "SELECT BOARDNUM, TITLE, CONTENT, MEMBERID, PROHIBITCNT, RECOMMENDCNT, CATEGORY, BOARDIMG, VIEWCNT, NICKNAME, BOARDDATE, COMMENTSCNT "
			+ "FROM "
			+ 		"(SELECT B.BOARDNUM, B.TITLE, B.CONTENT, M.MEMBERID, B.PROHIBITCNT, B.RECOMMENDCNT,  B.CATEGORY, B.BOARDIMG, B.VIEWCNT, M.NICKNAME, "
			+ 		"TO_CHAR(B.BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, "
			+ 		"(SELECT COUNT(CASE WHEN BOARDNUM = B.BOARDNUM THEN 1 END ) FROM COMMENTS) AS COMMENTSCNT "
			+ 		"FROM BOARD B JOIN MEMBER M ON B.MEMBERID=M.MEMBERID "
			+ 		"WHERE B.CATEGORY IN (1,2) ORDER BY RECOMMENDCNT DESC, VIEWCNT DESC) "
			+ "WHERE ROWNUM <= 5";
	// 게시물 상세페이지
	private final String sql_SELECTONE = "SELECT B.BOARDNUM, B.TITLE, B.CONTENT, B.MEMBERID, B.PROHIBITCNT, B.RECOMMENDCNT, B.CATEGORY, B.BOARDIMG, B.VIEWCNT, M.NICKNAME, "
			+ "TO_CHAR(B.BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE "
			+ "FROM BOARD B JOIN MEMBER M ON B.MEMBERID=M.MEMBERID WHERE B.BOARDNUM=?";
	// 추천수 1등 게시물
	private final String sql_SELECTONE_TOPBOARD = "SELECT BOARDNUM, TITLE, CONTENT, MEMBERID, PROHIBITCNT, RECOMMENDCNT, CATEGORY, BOARDIMG, VIEWCNT, NICKNAME, BOARDDATE, COMMENTSCNT "
			+ "FROM ( "
			+ "    SELECT B.BOARDNUM, B.TITLE, B.CONTENT, M.MEMBERID, B.PROHIBITCNT, B.RECOMMENDCNT,  B.CATEGORY, B.BOARDIMG, B.VIEWCNT, M.NICKNAME, "
			+ "    TO_CHAR(B.BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, "
			+ "    (SELECT COUNT(CASE WHEN BOARDNUM = B.BOARDNUM THEN 1 END ) FROM COMMENTS) AS COMMENTSCNT, "
			+ "    ROW_NUMBER() OVER (ORDER BY RECOMMENDCNT DESC, VIEWCNT DESC) AS ranked "
			+ "    FROM BOARD B "
			+ "    JOIN MEMBER M ON B.MEMBERID=M.MEMBERID "
			+ "    WHERE B.CATEGORY IN (1,2)) "
			+ "WHERE ranked = 1";
	// 게시물 수정
	private final String sql_UPDATE_BOARD = "UPDATE BOARD SET TITLE=?, CONTENT=?, BOARDIMG=? WHERE BOARDNUM=?";
	// 게시물 신고수 갱신
	private final String sql_UPDATE_PROHIBIT = "UPDATE BOARD SET "
			+ "PROHIBITCNT=(SELECT COUNT(CASE WHEN COMMONNUM=? THEN 1 END ) FROM PROHIBIT) WHERE BOARDNUM=?";
	// 게시물 조회수+1
	private final String sql_UPDATE_RECOMMEND = "UPDATE BOARD SET "
			+ "RECOMMENDCNT=(SELECT COUNT(CASE WHEN COMMONNUM=? THEN 1 END ) FROM RECOMMEND) WHERE BOARDNUM=?";
	// 게시물 조회수 갱신
	private final String sql_UPDATE_VIEWCNT = "UPDATE BOARD SET "
			+ "VIEWCNT=(SELECT VIEWCNT FROM BOARD WHERE BOARDNUM=?)+1 WHERE BOARDNUM=?";
	// 게시물 삭제
	private final String sql_DELETE = "DELETE FROM BOARD WHERE BOARDNUM=?";
	// 회원 정지로 인한 게시물 삭제
	private final String sql_DELETE_MEMBERID = "DELETE FROM BOARD WHERE MEMBERID=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

/////////////////// insert ///////////////////////////////////////////////
	public boolean insert(BoardVO bVO) {
		System.out.println("BoardDAO2 로그 insert 메서드");
		int result= jdbcTemplate.update(sql_INSERT,bVO.getTitle(),bVO.getContent(),bVO.getMemberID(),bVO.getCategory(),bVO.getBoardImg());
		if (result <=0 ) {
			return false;
		}
		return true;
	}

/////////////////// selectAll ///////////////////////////////////////////////
	public List<BoardVO> selectAll(BoardVO bVO) {
		System.out.println("BoardDAO2 로그 selectAll 메서드");
		if (bVO.getSearchCondition() != null) {
			if (bVO.getSearchCondition().equals("totalCommunity")) {

				return jdbcTemplate.query(sql_SELECTALL_COMMUNITY, new BoardListRowMapper());
			}
			else if (bVO.getSearchCondition().equals("community")) {
				Object[] args= {bVO.getCategory()};
				return jdbcTemplate.query(sql_SELECTALL_CATEGORY, args, new BoardListRowMapper());
			}
			else if (bVO.getSearchCondition().equals("prohibitBoard")) {
				return jdbcTemplate.query(sql_SELECTALL_PROHIBITCNT, new BoardProhibitRowMapper());
			}
			else if (bVO.getSearchCondition().equals("ownBoard")) {
				Object[] args= {bVO.getMemberID()};
				return jdbcTemplate.query(sql_SELECTALL_MYBOARD, args, new BoardListRowMapper());
			}
			else if (bVO.getSearchCondition().equals("writerBoard")) {
				Object[] args= {bVO.getMemberID()};
				return jdbcTemplate.query(sql_SELECTALL_WRITERBOARD, args, new BoardListRowMapper());
			}
			else if (bVO.getSearchCondition().equals("recommendRank")) {
				return jdbcTemplate.query(sql_SELECTALL_RANK, new BoardRankRowMapper());
			}
		}
		else if (bVO.getSearchCondition() == null) {
			System.out.println("boardDAO2 selectAll 서치컨디션 null");
		}
		return null;
	}

/////////////////// selectOne ///////////////////////////////////////////////
	public BoardVO selectOne(BoardVO bVO) { 
		System.out.println("BoardDAO2 로그 selectOne 메서드");
		try {
			if(bVO.getSearchCondition()==null) {
				System.out.println("BoardDAO2 selectOne 서치컨디션 null");

				Object[] args= {bVO.getBoardNum()};
				
				return jdbcTemplate.queryForObject(sql_SELECTONE, args, new BoardRowMapper());
			}
			else if(bVO.getSearchCondition().equals("topBoard")) {
				
				return jdbcTemplate.queryForObject(sql_SELECTONE_TOPBOARD, new BoardRankRowMapper());
			}
		}
		catch(EmptyResultDataAccessException e) {
			System.out.println("boardDAO2 selectOne 데이터 비어있음");
			return null;
		}
		return null;
	}

/////////////////// update ///////////////////////////////////////////////
	public boolean update(BoardVO bVO) {
		System.out.println("BoardDAO2 로그 update 메서드");
		int result=0;
		if (bVO.getSearchCondition()==null) {
			System.out.println("boardDAO2 selectOne 서치컨디션 null");
		}
		else if (bVO.getSearchCondition().equals("updateBoard")) {
			result=jdbcTemplate.update(sql_UPDATE_BOARD,bVO.getTitle(),bVO.getContent(),bVO.getBoardImg(),bVO.getBoardNum());
		}
		else if (bVO.getSearchCondition().equals("prohibit")) {
			result=jdbcTemplate.update(sql_UPDATE_PROHIBIT,bVO.getBoardNum(),bVO.getBoardNum());
		}
		else if (bVO.getSearchCondition().equals("recommend")) {
			result=jdbcTemplate.update(sql_UPDATE_RECOMMEND,bVO.getBoardNum(),bVO.getBoardNum());
		}
		else if (bVO.getSearchCondition().equals("viewCnt")) {
			result=jdbcTemplate.update(sql_UPDATE_VIEWCNT,bVO.getBoardNum(),bVO.getBoardNum());
		}

		if (result > 0) {
			return true;
		}
		return false;
	}

/////////////////// delete ///////////////////////////////////////////////
	public boolean delete(BoardVO bVO) {
		System.out.println("BoardDAO2 로그 delete 메서드");
		int result=0;

		if (bVO.getSearchCondition()==null) {
			System.out.println("boardDAO2 selectOne 서치컨디션 null");
		}
		else if (bVO.getSearchCondition().equals("boardNum")) {
			result=jdbcTemplate.update(sql_DELETE,bVO.getBoardNum());
		}
		else if (bVO.getSearchCondition().equals("memberID")) {
			result=jdbcTemplate.update(sql_DELETE_MEMBERID,bVO.getMemberID());
		} 

		if (result <= 0) {
			return false;
		}
		return true;
	}
}

/////////////////// rowMapper ///////////////////////////////////////////////
class BoardRowMapper implements RowMapper<BoardVO>{

	@Override
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardVO bdata= new BoardVO();
		
		bdata.setBoardNum(rs.getInt("BOARDNUM"));
		bdata.setTitle(rs.getString("TITLE"));
		bdata.setContent(rs.getString("CONTENT"));
		bdata.setMemberID(rs.getString("MEMBERID"));
		bdata.setProhibitCnt(rs.getInt("PROHIBITCNT"));
		bdata.setRecommendCnt(rs.getInt("RECOMMENDCNT"));
		bdata.setCategory(rs.getInt("CATEGORY"));
		bdata.setBoardDate(rs.getString("BOARDDATE"));
		bdata.setBoardImg(rs.getString("BOARDIMG"));
		bdata.setViewCnt(rs.getInt("VIEWCNT"));
		bdata.setNickName(rs.getString("NICKNAME"));
		
		return bdata;
	}
}

class BoardListRowMapper implements RowMapper<BoardVO>{
	
	@Override
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardVO bdata= new BoardVO();
		
		bdata.setBoardNum(rs.getInt("BOARDNUM"));
		bdata.setTitle(rs.getString("TITLE"));
		bdata.setRecommendCnt(rs.getInt("RECOMMENDCNT"));
		bdata.setCategory(rs.getInt("CATEGORY"));
		bdata.setBoardDate(rs.getString("BOARDDATE"));
		bdata.setViewCnt(rs.getInt("VIEWCNT"));
		bdata.setNickName(rs.getString("NICKNAME"));
		bdata.setBoardCommentsCnt(rs.getInt("COMMENTSCNT"));
		
		return bdata;
	}
}

class BoardProhibitRowMapper implements RowMapper<BoardVO>{
	
	@Override
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardVO bdata= new BoardVO();
		
		bdata.setBoardNum(rs.getInt("BOARDNUM"));
		bdata.setTitle(rs.getString("TITLE"));
		bdata.setMemberID(rs.getString("MEMBERID"));
		bdata.setProhibitCnt(rs.getInt("PROHIBITCNT"));
		bdata.setRecommendCnt(rs.getInt("RECOMMENDCNT"));
		bdata.setCategory(rs.getInt("CATEGORY"));
		bdata.setBoardDate(rs.getString("BOARDDATE"));
		bdata.setViewCnt(rs.getInt("VIEWCNT"));
		bdata.setNickName(rs.getString("NICKNAME"));
		
		return bdata;
	}
}

class BoardRankRowMapper implements RowMapper<BoardVO>{
	
	@Override
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardVO bdata= new BoardVO();
		
		bdata.setBoardNum(rs.getInt("BOARDNUM"));
		bdata.setTitle(rs.getString("TITLE"));
		bdata.setContent(rs.getString("CONTENT"));
		bdata.setMemberID(rs.getString("MEMBERID"));
		bdata.setProhibitCnt(rs.getInt("PROHIBITCNT"));
		bdata.setRecommendCnt(rs.getInt("RECOMMENDCNT"));
		bdata.setCategory(rs.getInt("CATEGORY"));
		bdata.setBoardDate(rs.getString("BOARDDATE"));
		bdata.setBoardImg(rs.getString("BOARDIMG"));
		bdata.setViewCnt(rs.getInt("VIEWCNT"));
		bdata.setNickName(rs.getString("NICKNAME"));
		bdata.setBoardCommentsCnt(rs.getInt("COMMENTSCNT"));
		
		return bdata;
	}
}
