package com.spring.biz.commentSet;

import java.util.List;

import com.spring.biz.board.BoardVO;

public interface CommentSetService {
	public List<CommentSet> selectAll(BoardVO bVO);
}
