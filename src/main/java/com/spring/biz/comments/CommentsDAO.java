package com.spring.biz.comments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.spring.biz.common.JDBCUtil;


public class CommentsDAO implements InterfaceCommentsDAO{
	// SQL 쿼리문
	private final String sql_INSERT = "INSERT INTO COMMENTS (COMMENTSNUM, COMMENTS, BOARDNUM, MEMBERID, COMMENTSDATE) VALUES ((SELECT NVL(MAX(COMMENTSNUM),19999)+1 FROM COMMENTS), ?, ?, ?, SYSTIMESTAMP)";
	private final String sql_SELECTALL = "SELECT C.COMMENTSNUM, C.COMMENTS, C.BOARDNUM, C.MEMBERID, C.PROHIBITCNT, TO_CHAR(C.COMMENTSDATE, 'YYYY-MM-DD') AS COMMENTSDATE, M.NICKNAME, FROM COMMENTS C LEFT JOIN MEMBER M ON C.MEMBERID=M.MEMBERID WHERE C.BOARDNUM=? ORDER BY C.COMMENTSNUM DESC";
	private final String sql_SELECTONE = "SELECT C.COMMENTSNUM, C.COMMENTS, C.BOARDNUM, C.MEMBERID, C.PROHIBITCNT, TO_CHAR(C.COMMENTSDATE, 'YYYY-MM-DD') AS COMMENTSDATE, M.NICKNAME, FROM COMMENTS C LEFT JOIN MEMBER M ON C.MEMBERID=M.MEMBERID WHERE C.COMMENTSNUM=?";
	private final String sql_UPDATE_COMMENTS = "UPDATE COMMENTS SET COMMENTS=? WHERE COMMENTSNUM=?";
	private final String sql_UPDATE_PROHIBIT = "UPDATE COMMENTS SET PROHIBITCNT=(SELECT COUNT(CASE WHEN COMMONNUM=? THEN 1 END ) FROM PROHIBIT) WHERE COMMENTSNUM=?";
	private final String sql_DELETE = "DELETE FROM COMMENTS WHERE COMMENTSNUM=?";
	
   // JDBC(자바 데이터베이스 커넥트) 도구
   Connection conn;
   PreparedStatement pstmt;
   ResultSet rs;
   
   public CommentsDAO() {

   }
   public boolean insert(CommentsVO cVO) { // DB에 객체정보 저장
      try { // 의도하지 않은 프로그램종료 예방
         conn = JDBCUtil.connect();
         // SQL 쿼리문을 입력하는 저장공간 생성
         pstmt = conn.prepareStatement(sql_INSERT);
         // SQL 쿼리문 수정
         pstmt.setString(1, cVO.getComments());
         pstmt.setInt(2, cVO.getBoardNum());
         pstmt.setString(3, cVO.getMemberID());
         // SQL 쿼리문 실행 및 결과 저장
         int result = pstmt.executeUpdate();
         // 사용한 도구 정리
         JDBCUtil.disconnect(pstmt, conn);
         // 검색 결과 리턴
         if (result <=0) {
        	 return false;
         }
      } catch (SQLException e) { // 의도하지 않은 오류가 발생했을 경우
         e.printStackTrace(); // 예외정보 출력
         return false;
      }
      return true;
   } // insert

   public ArrayList<CommentsVO> selectAll(CommentsVO cVO) { // 목록 검색
      ArrayList<CommentsVO> cdatas = new ArrayList<CommentsVO>(); // 정보들을 저장할 배열

      try { // 예외처리
         conn = JDBCUtil.connect();
         pstmt = conn.prepareStatement(sql_SELECTALL);
         pstmt.setInt(1, cVO.getBoardNum());
         rs = pstmt.executeQuery();
         
         while (rs.next()) {
            CommentsVO cdata=new CommentsVO();
            
            cdata.setCommentsNum(rs.getInt("COMMENTSNUM"));
            cdata.setComments(rs.getString("COMMENTS"));
            cdata.setBoardNum(rs.getInt("BOARDNUM"));
            cdata.setMemberID(rs.getString("MEMBERID"));
            cdata.setProhibitCnt(rs.getInt("PROHIBITCNT"));
            cdata.setCommentsDate(rs.getString("COMMENTSDATE"));
            cdata.setNickName(rs.getString("NICKNAME"));
            
            cdatas.add(cdata);
         }
         
         // 사용한 도구 정리
         JDBCUtil.disconnect(rs, pstmt, conn);

         // 검색 결과 리턴
         return cdatas;

      } catch (SQLException e) {
         e.printStackTrace();
      }
      return null;

   }

   public CommentsVO selectOne(CommentsVO cVO) { // 하나의 객체 정보 검색
	   CommentsVO cdata = null; // 저장할 공간
		try { // 예외방지
			conn = JDBCUtil.connect();
			// SQL 쿼리문 입력
			pstmt = conn.prepareStatement(sql_SELECTONE);
			// SQL 쿼리문 수정
			pstmt.setInt(1, cVO.getCommentsNum());

			// SQL 쿼리문 실행
			rs = pstmt.executeQuery();
//			C.COMMENTNUM, C.COMMENT, C.BOARDNUM, C.MemberID, C.PROHIBITCNT, M.NICKNAME
			// 가져온 정보 저장
			if (rs.next()) {
				cdata=new CommentsVO();
				
				cdata.setCommentsNum(rs.getInt("COMMENTSNUM"));
				cdata.setComments(rs.getString("COMMENTS"));
				cdata.setBoardNum(rs.getInt("BOARDNUM"));
				cdata.setMemberID(rs.getString("MEMBERID"));
				cdata.setProhibitCnt(rs.getInt("PROHIBITCNT"));
				cdata.setCommentsDate(rs.getString("COMMENTSDATE"));
				cdata.setNickName(rs.getString("NICKNAME"));
			}
			// 사용한 도구 정리
			JDBCUtil.disconnect(rs, pstmt, conn);

			return cdata;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
   }

   public boolean update(CommentsVO cVO) {
      try { // 예외처리
         // 데이터베이스 로그인 == 자바와 DB를 연결하는 객체 생성
         conn = JDBCUtil.connect();

         // SQL 쿼리문 입력
         if(cVO.getSearchCondition().equals("updateComments")) {
            pstmt = conn.prepareStatement(sql_UPDATE_COMMENTS);
            pstmt.setString(1, cVO.getComments());
            pstmt.setInt(2, cVO.getCommentsNum());
         }
         else if(cVO.getSearchCondition().equals("prohibit")) {
            pstmt = conn.prepareStatement(sql_UPDATE_PROHIBIT);
            pstmt.setInt(1, cVO.getCommentsNum());
            pstmt.setInt(2, cVO.getCommentsNum());
         }
         // SQL 쿼리문 실행 및 결과 저장
         int result = pstmt.executeUpdate();
         // 사용한 도구 정리
         JDBCUtil.disconnect(pstmt, conn);
         // 검색 결과 리턴
         if (result >= 1) {
            return true;
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return false;
   }

   public boolean delete(CommentsVO cVO) {

      try { // 의도하지 않은 프로그램종료 예방
         // 데이터베이스 로그인 == 자바와 DB를 연결하는 객체 생성
         conn = JDBCUtil.connect();

         // SQL 쿼리문 입력
         pstmt = conn.prepareStatement(sql_DELETE);
         // SQL 쿼리문 수정
         pstmt.setInt(1, cVO.getCommentsNum());

         // SQL 쿼리문 실행 및 결과 저장
         int result = pstmt.executeUpdate();

         // 사용한 도구 정리
         JDBCUtil.disconnect(pstmt, conn);

         // 성공 리턴
         if (result >= 1) {
            return true;
         }
      } catch (SQLException e) { // 의도하지 않은 오류가 발생했을 경우
         e.printStackTrace(); // 예외정보 출력
         return false;
      }      
      return false;
   }
}

//SELECT C.COMMENTNUM, C.COMMENT, C.BOARDNUM, C.MEMBERID, C.PROHIBITCNT, M.NICKNAME 
//FROM COMMENT C LEFT JOIN MEMBER M ON C.MEMBERID=M.MEMBERID ORDER BY C.COMMENTNUM DESC

