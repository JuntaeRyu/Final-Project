package com.spring.biz.footer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class FooterDAO2 implements InterfaceFooterDAO {
	
	// footer에 들어가는 회사 소개 등을 작성
	private String sql_INSERT = "INSERT INTO FOOTER (COMPANYNAME, COMPANYCEO, COMPANYEMAIL, COMPANYPHONENUM, COMPANYINFO) VALUES (?, ?, ?, ?, ?)";
//	private String sql_SELECTALL = "";
	// footer에 들어가는 회사 소개 출력
	private String sql_SELECTONE = "SELECT COMPANYNAME, COMPANYCEO, COMPANYEMAIL, COMPANYPHONENUM, COMPANYINFO FROM FOOTER WHERE COMPANYNAME = ?";
//	private String sql_UPDATE = "";
	// footer에 들어가는 회사 데이터 삭제
	private String sql_DELETE = "DELETE FROM FOOTER WHERE COMPANYNAME = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
/////////////////// insert ///////////////////////////////////////////////
	public boolean insert(FooterVO fVO) {

		// 쿼리문 수정 및 실행 후 결과를 저장
		int result = jdbcTemplate.update(sql_INSERT, fVO.getCompanyName(), fVO.getCompanyCEO(), fVO.getCompanyEmail(), fVO.getCompanyPhoneNum(), fVO.getCompanyInfo());

		// 검색 결과 리턴
		if (result > 0) {
			return true;
		}
		return false;
	}

/////////////////// selectAll ///////////////////////////////////////////////
	public List<FooterVO> selectAll(FooterVO fVO) {
		// TODO Auto-generated method stub
		return null;
	}

/////////////////// selectOne ///////////////////////////////////////////////
	public FooterVO selectOne(FooterVO fVO) {

		// 쿼리문 수정할 정보 저장
		Object[] args = { fVO.getCompanyName() };
				
		// 쿼리문 수정 및 실행 후 결과 리턴
		return jdbcTemplate.queryForObject(sql_SELECTONE, args, new FooterRowMapper());
	}
	
/////////////////// update ///////////////////////////////////////////////
	public boolean update(FooterVO fVO) {
		// TODO Auto-generated method stub
		return false;
	}

/////////////////// delete ///////////////////////////////////////////////
	public boolean delete(FooterVO fVO) {

		int result = jdbcTemplate.update(sql_DELETE, fVO.getCompanyName());
		
		// 검색 결과 리턴
		if (result > 0) {
			return true;
		}
		return false;
	}
}

/////////////////// rowMapper ///////////////////////////////////////////////
class FooterRowMapper implements RowMapper<FooterVO> {

	@Override
	public FooterVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		FooterVO fdata=new FooterVO();
		
		fdata.setCompanyName(rs.getString("COMPANYNAME"));
		fdata.setCompanyCEO(rs.getString("COMPANYCEO"));
		fdata.setCompanyEmail(rs.getString("COMPANYEMAIL"));
		fdata.setCompanyPhoneNum(rs.getString("COMPANYPHONENUM"));
		fdata.setCompanyInfo(rs.getString("COMPANYINFO"));
		
		return null;
	}
}