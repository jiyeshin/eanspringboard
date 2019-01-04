package com.eansoft.board.service.mapper;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.eansoft.board.service.BoardVO;

public interface BoardMapper {
	
	// 게시글 리스트 조회
    public List<BoardVO> selectBoardList(Map map) throws Exception;
    
    //전체 리스트 개수
    public int selectBoardListCnt(BoardVO boardVO) throws Exception;
    
    // 게시글 등록
    public void insertBoard(BoardVO boardVO) throws Exception;
    
    // 파일 삽입
    public void insertFile(Map<String, Object> map) throws Exception;
    
    // 게시글 수정
    public void updateBoard(BoardVO boardVO) throws Exception;
    
    // 게시글 삭제
    public void deleteBoard(BoardVO boardVO) throws Exception;
 
    // 게시글 상세보기
    public BoardVO selectBoardByCode(int code) throws Exception;
    
    // 파일 상세보기 
    public List<Map<String, Object>> selectFileListByCode(int code) throws Exception;
    
    // 조회수 증가 
    public void updateReadcnt(int code) throws Exception;
    
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
