package com.spring.biz.comments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("commentsService")
public class CommentsServiceImpl implements CommentsService{

	@Autowired
	private InterfaceCommentsDAO cDAO;
	
	@Override
	public CommentsVO selectOne(CommentsVO cVO) {
		return cDAO.selectOne(cVO);
	}

	@Override
	public List<CommentsVO> selectAll(CommentsVO cVO) {
		return cDAO.selectAll(cVO);
	}

	@Override
	public boolean insert(CommentsVO cVO) {
		return cDAO.insert(cVO);
	}

	@Override
	public boolean update(CommentsVO cVO) {
		return cDAO.update(cVO);
	}

	@Override
	public boolean delete(CommentsVO cVO) {
		return cDAO.delete(cVO);
	}

}
