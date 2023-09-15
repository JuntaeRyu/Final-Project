package com.spring.biz.comments;

import java.util.List;

public interface CommentsService {
	public CommentsVO selectOne(CommentsVO cVO);
	public List<CommentsVO> selectAll(CommentsVO cVO);
	
	public boolean insert(CommentsVO cVO);
	public boolean update(CommentsVO cVO);
	public boolean delete(CommentsVO cVO);
}
