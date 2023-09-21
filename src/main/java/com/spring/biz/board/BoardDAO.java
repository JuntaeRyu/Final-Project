package com.spring.biz.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.spring.biz.common.JDBCUtil;

public class BoardDAO implements InterfaceBoardDAO{
	// SQL 쿼리문
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
			+ "FROM BOARD B JOIN MEMBER M ON B.MEMBERID=M.MEMBERID " + "WHERE B.CATEGORY=? ORDER BY BOARDNUM DESC";
	// 내가 작성한 게시물 목록
	private final String sql_SELECTALL_MYBOARD = "SELECT B.BOARDNUM, B.TITLE, B.RECOMMENDCNT, B.CATEGORY, B.VIEWCNT, M.NICKNAME, "
			+ "TO_CHAR(B.BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, "
			+ "(SELECT COUNT(CASE WHEN BOARDNUM = B.BOARDNUM THEN 1 END ) FROM COMMENTS) AS COMMENTSCNT "
			+ "FROM BOARD B JOIN MEMBER M ON B.MEMBERID=M.MEMBERID " + "WHERE B.MEMBERID=? ORDER BY BOARDNUM DESC";
	// 신고 3개이상인 게시물 목록
	private final String sql_SELECTALL_PROHIBITCNT = "SELECT B.BOARDNUM, B.TITLE, B.MEMBERID, B.PROHIBITCNT, B.RECOMMENDCNT, B.CATEGORY, B.VIEWCNT, M.NICKNAME, "
			+ "TO_CHAR(B.BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE "
			+ "FROM BOARD B JOIN MEMBER M ON B.MEMBERID=M.MEMBERID "
			+ "WHERE B.PROHIBITCNT >= 3 ORDER BY BOARDNUM DESC";
	// 추천수 top5 게시물 리스트
	private final String sql_SELECTALL_RANK = "SELECT BOARDNUM, TITLE, CONTENT, PROHIBITCNT, RECOMMENDCNT, CATEGORY, BOARDIMG, VIEWCNT, NICKNAME, BOARDDATE, COMMENTSCNT "
			+ "FROM (SELECT B.BOARDNUM, B.TITLE, B.CONTENT, B.PROHIBITCNT, B.RECOMMENDCNT,  B.CATEGORY, B.BOARDIMG, B.VIEWCNT, M.NICKNAME "
			+ "TO_CHAR(B.BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, "
			+ "(SELECT COUNT(CASE WHEN BOARDNUM = B.BOARDNUM THEN 1 END ) FROM COMMENTS) AS COMMENTSCNT "
			+ "FROM BOARD B JOIN MEMBER M ON B.MEMBERID=M.MEMBERID "
			+ "WHERE B.CATEGORY IN (1,2) ORDER BY RECOMMENDCNT DESC, VIEWCNT DESC) WHERE ROWNUM <= 5";
	// 게시물 상세페이지
	private final String sql_SELECTONE = "SELECT B.BOARDNUM, B.TITLE, B.CONTENT, B.MEMBERID, B.PROHIBITCNT, B.RECOMMENDCNT, B.CATEGORY, B.BOARDIMG, B.VIEWCNT, M.NICKNAME, "
			+ "TO_CHAR(B.BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE "
			+ "FROM BOARD B JOIN MEMBER M ON B.MEMBERID=M.MEMBERID WHERE B.BOARDNUM=?";
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

	// JDBC(자바 데이터베이스 커넥트) 도구
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public BoardDAO() {
	}

	public boolean insert(BoardVO bVO) { // DB에 객체정보 저장
		try { // 예외처리
			conn = JDBCUtil.connect(); // 데이터베이스 로그인 == 자바와 DB를 연결하는 객체 생성
			pstmt = conn.prepareStatement(sql_INSERT); // SQL 쿼리문을 입력하는 저장공간 생성

			pstmt.setString(1, bVO.getTitle());
			pstmt.setString(2, bVO.getContent());
			pstmt.setString(3, bVO.getMemberID());
			pstmt.setInt(4, bVO.getCategory());
			pstmt.setString(5, bVO.getBoardImg());

			int result = pstmt.executeUpdate(); // SQL 쿼리문 실행 및 결과 저장

			JDBCUtil.disconnect(pstmt, conn); // 사용한 도구 정리

			if (result >= 1) {
				return true;
			}

		} catch (SQLException e) { // 예외발생시
			e.printStackTrace(); // 예외정보 출력
			return false;
		}

		return false; // 저장 실패
	} // insert

	public ArrayList<BoardVO> selectAll(BoardVO bVO) { // 목록 검색
		ArrayList<BoardVO> bdatas = new ArrayList<BoardVO>(); // 정보들을 저장할 배열

		try { // 예외처리
			conn = JDBCUtil.connect();
			if (bVO.getSearchCondition() != null) {
				if (bVO.getSearchCondition().equals("totalCommunity")) { // 이름 검색일 경우
					pstmt = conn.prepareStatement(sql_SELECTALL_COMMUNITY);
					
					rs = pstmt.executeQuery(); // SQL 쿼리문 실행
					
					while (rs.next()) { // 가져온 정보 저장용 객체
						BoardVO bdata = new BoardVO(); // 가져온 정보 저장한 객체 생성
						
						bdata.setBoardNum(rs.getInt("BOARDNUM"));
						bdata.setTitle(rs.getString("TITLE"));
						bdata.setRecommendCnt(rs.getInt("RECOMMENDCNT"));
						bdata.setCategory(rs.getInt("CATEGORY"));
						bdata.setBoardDate(rs.getString("BOARDDATE"));
						bdata.setViewCnt(rs.getInt("VIEWCNT"));
						bdata.setNickName(rs.getString("NICKNAME"));
						bdata.setBoardCommentsCnt(rs.getInt("COMMENTSCNT"));
						
						bdatas.add(bdata); // 생성한 객체를 배열에 저장
					}
				}
				else if (bVO.getSearchCondition().equals("community")) { // 이름 검색일 경우
					pstmt = conn.prepareStatement(sql_SELECTALL_CATEGORY);
					pstmt.setInt(1, bVO.getCategory());
					
					rs = pstmt.executeQuery(); // SQL 쿼리문 실행
					
					while (rs.next()) { // 가져온 정보 저장용 객체
						BoardVO bdata = new BoardVO(); // 가져온 정보 저장한 객체 생성
						
						bdata.setBoardNum(rs.getInt("BOARDNUM"));
						bdata.setTitle(rs.getString("TITLE"));
						bdata.setRecommendCnt(rs.getInt("RECOMMENDCNT"));
						bdata.setCategory(rs.getInt("CATEGORY"));
						bdata.setBoardDate(rs.getString("BOARDDATE"));
						bdata.setViewCnt(rs.getInt("VIEWCNT"));
						bdata.setNickName(rs.getString("NICKNAME"));
						bdata.setBoardCommentsCnt(rs.getInt("COMMENTSCNT"));
						
						bdatas.add(bdata); // 생성한 객체를 배열에 저장
					}
				}
				else if (bVO.getSearchCondition().equals("prohibitBoard")) { // 라인 검색일 경우
					pstmt = conn.prepareStatement(sql_SELECTALL_PROHIBITCNT);
					
					rs = pstmt.executeQuery(); // SQL 쿼리문 실행
					
					while (rs.next()) { // 가져온 정보 저장용 객체
						BoardVO bdata = new BoardVO(); // 가져온 정보 저장한 객체 생성
						
						bdata.setBoardNum(rs.getInt("BOARDNUM"));
						bdata.setTitle(rs.getString("TITLE"));
						bdata.setMemberID(rs.getString("MEMBERID"));
						bdata.setProhibitCnt(rs.getInt("PROHIBITCNT"));
						bdata.setRecommendCnt(rs.getInt("RECOMMENDCNT"));
						bdata.setCategory(rs.getInt("CATEGORY"));
						bdata.setBoardDate(rs.getString("BOARDDATE"));
						bdata.setViewCnt(rs.getInt("VIEWCNT"));
						bdata.setNickName(rs.getString("NICKNAME"));
						
						bdatas.add(bdata); // 생성한 객체를 배열에 저장
					}
				}
				else if (bVO.getSearchCondition().equals("ownBoard")) { // 라인 검색일 경우
					pstmt = conn.prepareStatement(sql_SELECTALL_MYBOARD);
					pstmt.setString(1, bVO.getMemberID());
					
					rs = pstmt.executeQuery(); // SQL 쿼리문 실행
					
					while (rs.next()) { // 가져온 정보 저장용 객체
						BoardVO bdata = new BoardVO(); // 가져온 정보 저장한 객체 생성
						
						bdata.setBoardNum(rs.getInt("BOARDNUM"));
						bdata.setTitle(rs.getString("TITLE"));
						bdata.setRecommendCnt(rs.getInt("RECOMMENDCNT"));
						bdata.setCategory(rs.getInt("CATEGORY"));
						bdata.setBoardDate(rs.getString("BOARDDATE"));
						bdata.setViewCnt(rs.getInt("VIEWCNT"));
						bdata.setNickName(rs.getString("NICKNAME"));
						bdata.setBoardCommentsCnt(rs.getInt("COMMENTSCNT"));
						
						bdatas.add(bdata); // 생성한 객체를 배열에 저장
					}
				}
				else if (bVO.getSearchCondition().equals("recommendRank")) { // 상위 랭크일 경우
					pstmt = conn.prepareStatement(sql_SELECTALL_RANK);
					
					rs = pstmt.executeQuery(); // SQL 쿼리문 실행
					
					while (rs.next()) { // 가져온 정보 저장용 객체
						BoardVO bdata = new BoardVO(); // 가져온 정보 저장한 객체 생성
						
						bdata.setBoardNum(rs.getInt("BOARDNUM"));
						bdata.setTitle(rs.getString("TITLE"));
						bdata.setContent(rs.getString("CONTENT"));
						bdata.setProhibitCnt(rs.getInt("PROHIBITCNT"));
						bdata.setRecommendCnt(rs.getInt("RECOMMENDCNT"));
						bdata.setCategory(rs.getInt("CATEGORY"));
						bdata.setBoardDate(rs.getString("BOARDDATE"));
						bdata.setBoardImg(rs.getString("BOARDIMG"));
						bdata.setViewCnt(rs.getInt("VIEWCNT"));
						bdata.setNickName(rs.getString("NICKNAME"));
						bdata.setBoardCommentsCnt(rs.getInt("COMMENTSCNT"));
						
						bdatas.add(bdata); // 생성한 객체를 배열에 저장
					}
				}
			}


		} catch (SQLException e) { // 예외발생시
			e.printStackTrace();
		}

		JDBCUtil.disconnect(rs, pstmt, conn); // 사용한 도구 정리

		return bdatas; // 검색 결과 리턴
	}

	public BoardVO selectOne(BoardVO bVO) { // 하나의 객체 정보 검색
		BoardVO bdata = null;
		try { // 예외방지
			conn = JDBCUtil.connect();
			pstmt = conn.prepareStatement(sql_SELECTONE); // SQL 쿼리문 입력
			pstmt.setInt(1, bVO.getBoardNum()); // SQL 쿼리문 수정
			rs = pstmt.executeQuery(); // SQL 쿼리문 실행

			if (rs.next()) { // 가져온 정보 저장
				bdata = new BoardVO();
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JDBCUtil.disconnect(rs, pstmt, conn); // 사용한 도구 정리

		return bdata; // 검색 결과 리턴
	}

	// 글 수정, 신고 증가, 신고 감소
	public boolean update(BoardVO bVO) {
		try { // 예외처리
			conn = JDBCUtil.connect(); // 데이터베이스 로그인 == 자바와 DB를 연결하는 객체 생성

			if (bVO.getSearchCondition().equals("updateBoard")) {
				pstmt = conn.prepareStatement(sql_UPDATE_BOARD);
				pstmt.setString(1, bVO.getTitle());
				pstmt.setString(2, bVO.getContent());
				pstmt.setString(3, bVO.getBoardImg());
				pstmt.setInt(4, bVO.getBoardNum());
			}
			else if (bVO.getSearchCondition().equals("prohibit")) {
				pstmt = conn.prepareStatement(sql_UPDATE_PROHIBIT);
				pstmt.setInt(1, bVO.getBoardNum());
				pstmt.setInt(2, bVO.getBoardNum());
			}
			else if (bVO.getSearchCondition().equals("recommend")) {
				pstmt = conn.prepareStatement(sql_UPDATE_RECOMMEND);
				pstmt.setInt(1, bVO.getBoardNum());
				pstmt.setInt(2, bVO.getBoardNum());
			}
			else if (bVO.getSearchCondition().equals("viewCnt")) {
				pstmt = conn.prepareStatement(sql_UPDATE_VIEWCNT);
				pstmt.setInt(1, bVO.getBoardNum());
				pstmt.setInt(2, bVO.getBoardNum());
			}

			int result = pstmt.executeUpdate(); // SQL 쿼리문 실행 및 결과 저장

			JDBCUtil.disconnect(pstmt, conn); // 사용한 도구 정리

			if (result >= 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return false;
	}

	public boolean delete(BoardVO bVO) {
		try { // 예외처리
			conn = JDBCUtil.connect();

			if (bVO.getSearchCondition().equals("boardNum")) {
				pstmt = conn.prepareStatement(sql_DELETE); // SQL 쿼리문 입력
				pstmt.setInt(1, bVO.getBoardNum()); // SQL 쿼리문 수정
			}
			else if (bVO.getSearchCondition().equals("memberID")) {
				pstmt = conn.prepareStatement(sql_DELETE_MEMBERID); // SQL 쿼리문 입력
				pstmt.setString(1, bVO.getMemberID()); // SQL 쿼리문 수정
			}
			int result = pstmt.executeUpdate(); // SQL 쿼리문 실행 및 결과 저장

			JDBCUtil.disconnect(pstmt, conn); // 사용한 도구 정리

			if (result >= 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return false;
	}
}

//SELECT B.BOARDNUM, B.TITLE, B.CONTENT, B.MID, B.PROHIBITCNT, B.CATEGORY, B.DATE,
//M.NICKNAME, (SELECT COUNT(CASE WHEN NUM = B.BOARDNUM THEN 1 END) FROM RECOMMEND) AS RCNT
//FROM BOARD B JOIN MEMBER M ON B.MID=M.MID WHERE B.CATEGORY=? ORDER BY BOARDNUM DESC
