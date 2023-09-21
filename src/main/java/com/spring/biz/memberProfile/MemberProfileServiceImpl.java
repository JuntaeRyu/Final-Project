package com.spring.biz.memberProfile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberProfileService")
public class MemberProfileServiceImpl implements MemberProfileService{

	@Autowired
	private InterfaceMemberProfileDAO mpDAO;
	
	@Override
	public MemberProfileVO selectOne(MemberProfileVO mpVO) {
		return mpDAO.selectOne(mpVO);
	}
	
	@Override
	public List<MemberProfileVO> selectAll(MemberProfileVO mpVO) {
		return mpDAO.selectAll(mpVO);
	}

	@Override
	public boolean insert(MemberProfileVO mpVO) {
		return mpDAO.insert(mpVO);
	}

	@Override
	public boolean update(MemberProfileVO mpVO) {
		return mpDAO.update(mpVO);
	}

	@Override
	public boolean delete(MemberProfileVO mpVO) {
		return mpDAO.delete(mpVO);
	}


}
