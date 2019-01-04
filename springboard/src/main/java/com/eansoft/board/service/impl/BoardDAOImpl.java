package com.eansoft.board.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eansoft.board.service.BoardVO;
import com.eansoft.board.service.mapper.BoardMapper;

@Service
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSession sqlSession;
	
	// 게시판 전체 리스트
	@Override
	public List<BoardVO> selectBoardList(String searchOption, String keyword, int start, int end) throws Exception {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        Map<String,Object> map = new HashMap<>();
        // 검색 설정 
        map.put("searchOption", searchOption);
        map.put("keyword", keyword);
        
        // 페이징 설정 
        map.put("start", start);
        map.put("end", end);
        
        return mapper.selectBoardList(map);
    }
	
	// 전체 리스트 개수
	@Override
	public int selectBoardListCnt(BoardVO boardVO) throws Exception {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		
        return mapper.selectBoardListCnt(boardVO);
	}
 
	// 게시글 입력 
	@Override
    public void insertBoard(BoardVO boardVO) throws Exception {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        mapper.insertBoard(boardVO);
    }
	
	// 파일 정보 입력 
	@Override
	public void insertFile(Map<String, Object> map) throws Exception {
		  BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);	      
		  mapper.insertFile(map);		
	}
 
	// 게시글 수정
    @Override
    public void updateBoard(BoardVO boardVO) throws Exception {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        mapper.updateBoard(boardVO);
    }
 
    // 게시글 삭제
    @Override
    public void deleteBoard(BoardVO boardVO) throws Exception {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        mapper.deleteBoard(boardVO);
    }
 
    // 게시글 상세보기 (게시글 가져오기) 
    @Override
    public BoardVO selectBoardByCode(int code) throws Exception {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        
        return mapper.selectBoardByCode(code);
    }
    
    // 파일 상세보기 (파일 정보 가져오기)
    @Override
	public List<Map<String, Object>> selectFileListByCode(int code) throws Exception {
    	BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
    	 
		return mapper.selectFileListByCode(code);
	}

    // 조회수 수정 
	@Override
	public void updateReadcnt(int code) throws Exception {
		   BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
	       mapper.updateReadcnt(code);	
	}

	@Override
	public Map<String, Object> selectFileByFileCode(int filecode) throws Exception {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
   	 
		return mapper.selectFileByFileCode(filecode);
	}

	// 그룹오더 수정
	@Override
	public void replyOrdergain(BoardVO boardVO) throws Exception {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
	    mapper.replyOrdergain(boardVO);	
		
	}

	// 답글 등록 
	@Override
	public void insertReply(BoardVO boardVO) throws Exception {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
	    mapper.insertReply(boardVO);		
	}

	// 답글 삭제
	@Override
	public void deleteReply(int code) throws Exception {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
	    mapper.deleteReply(code);	
		
	}

	// 게시글 삭제 시 댓글 삭제
	@Override
	public void deleteCommentByBoardcode(BoardVO boardVO) throws Exception {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
	    mapper.deleteCommentByBoardcode(boardVO);
		
	}
	

}
