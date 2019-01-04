package com.eansoft.comment.service.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.eansoft.comment.service.CommentVO;

public interface CommentMapper {
			//댓글 목록
			public List<CommentVO> selectCommentList(int boardcode) throws Exception;

			//댓글 입력
			public void insertComment(CommentVO vo) throws Exception;
			
			//댓글 수정
			public void updateComment(CommentVO vo) throws Exception;
			
			//댓글 삭제
			public void deleteComment(int commentcode) throws Exception;

			//코멘트 그룹오더 수정
			public void commentOrdergain(CommentVO vo) throws Exception;

			//대댓글 입력
			public void insertSubComment(CommentVO vo) throws Exception;
			
			//댓글 가져오기
			public CommentVO selectCommentByCommentcode(int commentcode) throws Exception;

}
