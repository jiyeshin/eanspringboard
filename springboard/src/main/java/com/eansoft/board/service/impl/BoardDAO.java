package com.eansoft.board.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.eansoft.board.service.BoardVO;
import com.eansoft.board.service.mapper.BoardMapper;

public interface BoardDAO {
	 
	// 게시판 전체 리스트
    public List<BoardVO> selectBoardList(String searchOption, String keyword, int start, int end) throws Exception;
    
    // 전체 리스트 개수
    public int selectBoardListCnt(BoardVO boardVO) throws Exception;
    
    // 게시글 입력 
    public void insertBoard(BoardVO boardVO) throws Exception;
    
    // 파일 정보 입력 
    public void insertFile(Map<String, Object> map) throws Exception;
    
    // 게시글 상세보기 (게시글 가져오기) 
    public BoardVO selectBoardByCode(int code) throws Exception;
    
    // 파일 상세보기 (파일 정보 가져오기)
    public List<Map<String, Object>> selectFileListByCode(int code) throws Exception;
    
    // 게시글 수정
    public void updateBoard(BoardVO boardVO) throws Exception;
    
    // 조회수 수정 
	public void updateReadcnt(int code) throws Exception; 
    
    // 게시글 삭제
    public void deleteBoard(BoardVO boardVO) throws Exception;
  
    // 파일 다운로드 (파일코드로 파일 정보 가져오기)
    public Map<String, Object> selectFileByFileCode (int filecode) throws Exception;
    
    // 그룹오더 수정
    public void replyOrdergain(BoardVO boardVO) throws Exception;
    
    // 답글 등록
    public void insertReply(BoardVO boardVO) throws Exception;
    
    // 답글 삭제
    public void deleteReply(int code) throws Exception;

    // 게시글 삭제 시 댓글 삭제
	public void deleteCommentByBoardcode(BoardVO boardVO) throws Exception;
    
}
