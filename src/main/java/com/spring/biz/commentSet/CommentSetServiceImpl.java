package com.spring.biz.commentSet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.biz.board.BoardVO;

@Service("commentSetService")
public class CommentSetServiceImpl implements CommentSetService{

	@Autowired
	private CommentSetDAO2 csDAO;
	
	@Override
	public List<CommentSet> selectAll(BoardVO bVO) {
		return csDAO.selectAll(bVO);
	}

}
