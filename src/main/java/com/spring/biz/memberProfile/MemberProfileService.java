package com.spring.biz.memberProfile;

import java.util.List;

public interface MemberProfileService {
	
	public MemberProfileVO selectOne(MemberProfileVO mpVO);
	public List<MemberProfileVO> selectAll(MemberProfileVO mpVO);
	
	public boolean insert(MemberProfileVO mpVO);
	public boolean update(MemberProfileVO mpVO);
	public boolean delete(MemberProfileVO mpVO);
}
