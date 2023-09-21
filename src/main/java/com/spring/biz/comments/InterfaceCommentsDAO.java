package com.spring.biz.comments;

import java.util.List;

public interface InterfaceCommentsDAO {
	public CommentsVO selectOne(CommentsVO cVO);
	public List<CommentsVO> selectAll(CommentsVO cVO);
	
	public boolean insert(CommentsVO cVO);
	public boolean update(CommentsVO cVO);
	public boolean delete(CommentsVO cVO);
}
