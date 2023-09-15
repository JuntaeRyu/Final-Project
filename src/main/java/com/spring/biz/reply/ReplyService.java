package com.spring.biz.reply;

import java.util.List;

public interface ReplyService {
	public ReplyVO selectOne(ReplyVO rVO);
	public List<ReplyVO> selectAll(ReplyVO rVO);
	
	public boolean insert(ReplyVO rVO);
	public boolean update(ReplyVO rVO);
	public boolean delete(ReplyVO rVO);
}
