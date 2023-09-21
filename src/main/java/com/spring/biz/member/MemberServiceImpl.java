package com.spring.biz.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService{

	@Autowired
	private InterfaceMemberDAO mDAO;
	
	@Override
	public MemberVO selectOne(MemberVO mVO) {
		return mDAO.selectOne(mVO);
	}

	@Override
	public List<MemberVO> selectAll(MemberVO mVO) {
		return mDAO.selectAll(mVO);
	}

	@Override
	public boolean insert(MemberVO mVO) {
		return mDAO.insert(mVO);
	}

	@Override
	public boolean update(MemberVO mVO) {
		return mDAO.update(mVO);
	}

	@Override
	public boolean delete(MemberVO mVO) {
		return mDAO.delete(mVO);
	}

}
