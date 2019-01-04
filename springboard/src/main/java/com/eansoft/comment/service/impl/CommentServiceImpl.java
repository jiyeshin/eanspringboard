package com.eansoft.comment.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eansoft.comment.service.CommentVO;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDAO commentDAO;
	
	@Override
	public List<CommentVO> selectCommentList(int boardcode) throws Exception {
		
		return commentDAO.selectCommentList(boardcode);
	}

	@Override
	public void insertComment(CommentVO vo) throws Exception {
		commentDAO.insertComment(vo);		
	}

	@Override
	public void updateComment(CommentVO vo) throws Exception {
		commentDAO.updateComment(vo);
		
	}

	@Override
	public void deleteComment(int commentcode) throws Exception {
		commentDAO.deleteComment(commentcode);
		
	}

	// 대댓글 입력
	@Override
	public void insertSubComment(CommentVO vo) throws Exception {
		commentDAO.commentOrdergain(vo);
		
		commentDAO.insertSubComment(vo);
		
	}

	// 댓글 가져오기
	@Override
	public CommentVO selectCommentByCommentcode(int commentcode) throws Exception {
		
		return commentDAO.selectCommentByCommentcode(commentcode);
	}

}
