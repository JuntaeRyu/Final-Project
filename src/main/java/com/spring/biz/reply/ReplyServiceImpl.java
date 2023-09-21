package com.spring.biz.reply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private InterfaceReplyDAO rDAO;
	
	@Override
	public ReplyVO selectOne(ReplyVO rVO) {
		return rDAO.selectOne(rVO);
	}

	@Override
	public List<ReplyVO> selectAll(ReplyVO rVO) {
		return rDAO.selectAll(rVO);
	}

	@Override
	public boolean insert(ReplyVO rVO) {
		return rDAO.insert(rVO);
	}

	@Override
	public boolean update(ReplyVO rVO) {
		return rDAO.update(rVO);
	}

	@Override
	public boolean delete(ReplyVO rVO) {
		return rDAO.delete(rVO);
	}

}
