package com.eansoft.comment.service.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eansoft.comment.service.CommentVO;
import com.eansoft.comment.service.mapper.CommentMapper;

@Repository
public class CommentDAOImpl implements CommentDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<CommentVO> selectCommentList(int boardcode) throws Exception {
		CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
		
		return mapper.selectCommentList(boardcode);
	}

	@Override
	public void insertComment(CommentVO vo) throws Exception {
		CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
		mapper.insertComment(vo);
	}

	@Override
	public void deleteComment(int commentcode) throws Exception {
		CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
		
		mapper.deleteComment(commentcode);	
	}
	
	@Override
	public void updateComment(CommentVO vo) throws Exception {	
		CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
		mapper.updateComment(vo);
	}

	// 코멘트 그룹오더 수정
	@Override
	public void commentOrdergain(CommentVO vo) throws Exception {
		CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
		mapper.commentOrdergain(vo);
		
	}

	// 대댓글 입력
	@Override
	public void insertSubComment(CommentVO vo) throws Exception {
		CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
		mapper.insertSubComment(vo);
		
	}

	// 댓글 가져오기
	@Override
	public CommentVO selectCommentByCommentcode(int commentcode) throws Exception {
		CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
		return mapper.selectCommentByCommentcode(commentcode);
	}

}
