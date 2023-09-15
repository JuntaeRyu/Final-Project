package com.spring.biz.commentSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.biz.board.BoardVO;
import com.spring.biz.comments.CommentsVO;
import com.spring.biz.reply.ReplyVO;

@Repository("commentSetDAO")
public class CommentSetDAO2 {
	// 댓글과 대댓글 정보를 함께 조회하는 SQL 쿼리문
	private final String sql_SELECTALL = "SELECT "
			+ " c.commentsNum AS commentsNum, "
			+ " c.comments AS comments, "
			+ " c.boardNum AS boardNum, "
			+ " c.mid AS mid, "
			+ " c.prohibitCnt AS prohibitCnt, "
			+ " m.nickname AS nickName, "
			
			+ " r.replyNum AS replyNum, "
			+ " r.reply AS reply, "
			+ " r.commentNum AS commentNum, "
			+ " r.mid AS replyMid, "
			+ " r.prohibitCnt AS replyProhibitCnt, "
			+ " m2.nickname AS replyNickName "
			
			+ "FROM " + " comments c "
			+ "LEFT JOIN " + " reply r ON c.commentsNum = r.commentNum "
			+ "LEFT JOIN " + " member m ON c.mid = m.mid "
			+ "LEFT JOIN " + " member m2 ON r.mid = m2.mid "
			+ "WHERE " + " c.boardNum = ? "
			+ "ORDER BY " + " c.commentsNum DESC, r.replyNum ASC";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<CommentSet> selectAll(BoardVO bVO) {

		Object[] args = { bVO.getBoardNum() };

		return jdbcTemplate.query(sql_SELECTALL, args, new CommentSetRowMapper());
//		return jdbcTemplate.query(sql_SELECTALL, new CommentsRowMapper(), cVO.getBoardNum()); // 두가지 다 가능

	}
}

class CommentSetRowMapper implements RowMapper<CommentSet> {

	@Override
	public CommentSet mapRow(ResultSet rs, int rowNum) throws SQLException {
		int commentsNum = rs.getInt("commentsNum");

		CommentSet commentSet = new CommentSet();
		CommentsVO comment = new CommentsVO();
		comment.setCommentsNum(commentsNum);
		comment.setComments(rs.getString("comments"));
		comment.setBoardNum(rs.getInt("boardNum"));
		comment.setMemberID(rs.getString("mid"));
		comment.setProhibitCnt(rs.getInt("prohibitCnt"));
		comment.setNickName(rs.getString("nickName"));
		commentSet.setComment(comment);

		Map<Integer, ReplyVO> replyMap = new HashMap<Integer, ReplyVO>();
		do {
			if (rs.getObject("replyNum") != null) {
				int replyNum = rs.getInt("replyNum");
				if (!replyMap.containsKey(replyNum)) {
					ReplyVO newReply = new ReplyVO();
					newReply.setReplyNum(replyNum);
					newReply.setReply(rs.getString("reply"));
					newReply.setCommentsNum(rs.getInt("commentNum"));
					newReply.setMemberID(rs.getString("replyMid"));
					newReply.setProhibitCnt(rs.getInt("replyProhibitCnt"));
					newReply.setNickName(rs.getString("replyNickName")); // Set reply nickname
					replyMap.put(replyNum, newReply);
					commentSet.addReply(newReply);
				}
			}
		} while (rs.next() && rs.getInt("commentsNum") == commentsNum);

		return commentSet;
	}

}
