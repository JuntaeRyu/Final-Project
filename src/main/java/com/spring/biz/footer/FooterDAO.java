package com.spring.biz.footer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.spring.biz.common.JDBCUtil;

//@Repository("FooterDAO")
public class FooterDAO implements InterfaceFooterDAO{

	private String sql_INSERT = "INSERT INTO FOOTER (COMPANYNAME, COMPANYCEO, COMPANYEMAIL, COMPANYPHONENUM, COMPANYINFO) VALUES (?, ?, ?, ?, ?)";
//	private String sql_SELECTALL = "";
	private String sql_SELECTONE = "SELECT COMPANYNAME, COMPANYCEO, COMPANYEMAIL, COMPANYPHONENUM, COMPANYINFO FROM FOOTER WHERE COMPANYNAME = ?";
//	private String sql_UPDATE = "";
	private String sql_DELETE = "DELETE FROM FOOTER WHERE COMPANYNAME = ?";
	
	// JDBC(자바 데이터베이스 커넥트) 도구
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public boolean insert(FooterVO fVO) {
		try { // 의도하지 않은 프로그램종료 예방
			conn = JDBCUtil.connect();
			// SQL 쿼리문을 입력하는 저장공간 생성
			pstmt = conn.prepareStatement(sql_INSERT);

			// SQL 쿼리문 수정
			pstmt.setString(1, fVO.getCompanyName());
			pstmt.setString(2, fVO.getCompanyCEO());
			pstmt.setString(3, fVO.getCompanyEmail());
			pstmt.setString(4, fVO.getCompanyPhoneNum());
			pstmt.setString(5, fVO.getCompanyInfo());

			// SQL 쿼리문 실행 및 결과 저장
			int result = pstmt.executeUpdate();

			// 사용한 도구 정리
			JDBCUtil.disconnect(pstmt, conn);

			// 검색 결과 리턴
			if (result >= 1) {
				return true;
			}
		} catch (SQLException e) { // 의도하지 않은 오류가 발생했을 경우
			e.printStackTrace(); // 예외정보 출력
			return false;
		}
		return false;
	}

	public List<FooterVO> selectAll(FooterVO fVO) {
		// TODO Auto-generated method stub
		return null;
	}

	public FooterVO selectOne(FooterVO fVO) {
		FooterVO fdata = null; // 저장할 공간
		
		try { // 예외방지
			// 도구 연결
			conn = JDBCUtil.connect();
			
			// SQL 쿼리문 입력
			pstmt = conn.prepareStatement(sql_SELECTONE);
			
			// SQL 쿼리문 수정
			pstmt.setString(1, fVO.getCompanyName());

			// SQL 쿼리문 실행
			rs = pstmt.executeQuery();

			// 가져온 정보 저장
			if (rs.next()) {
				fdata=new FooterVO();
				
				fdata.setCompanyName(rs.getString("COMPANYNAME"));
				fdata.setCompanyCEO(rs.getString("COMPANYCEO"));
				fdata.setCompanyEmail(rs.getString("COMPANYEMAIL"));
				fdata.setCompanyPhoneNum(rs.getString("COMPANYPHONENUM"));
				fdata.setCompanyInfo(rs.getString("COMPANYINFO"));
			}
			// 사용한 도구 정리
			JDBCUtil.disconnect(rs, pstmt, conn);

			return fdata;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean update(FooterVO fVO) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(FooterVO fVO) {

		try { // 의도하지 않은 프로그램종료 예방
			// 도구 연결
			conn = JDBCUtil.connect();
			
			// SQL 쿼리문 입력
			pstmt = conn.prepareStatement(sql_DELETE);
			
			// SQL 쿼리문 수정
			pstmt.setString(1, fVO.getCompanyName());

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
