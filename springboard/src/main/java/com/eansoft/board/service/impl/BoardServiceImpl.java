package com.eansoft.board.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eansoft.board.service.BoardVO;

@Service("boardServiceImpl")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAOService;

	// 게시판 전체 리스트
	@Override
	public List<BoardVO> selectBoardList(String searchOption, String keyword, int start, int end) throws Exception {		 
		 List<BoardVO> list = boardDAOService.selectBoardList(searchOption, keyword, start, end);
		
		 return list;
	}
	
	// 전체 리스트 개수
	@Override
	public int selectBoardListCnt(BoardVO boardVO) throws Exception {
		int totalCnt = boardDAOService.selectBoardListCnt(boardVO);
		
		return totalCnt;
	}

	// 게시글 입력
	@Override
	public void insertBoard(BoardVO boardVO) throws Exception {
		boardDAOService.insertBoard(boardVO);
	}
	
	// 파일 정보 입력 
	@Override
	public void insertFile(Map<String, Object> map) throws Exception {
		boardDAOService.insertFile(map);		
	}
	
	// 게시글 상세보기 (게시글 가져오기)
	@Override
	public BoardVO selectBoardByCode(int code) throws Exception {
		// 조회수 수정 
		boardDAOService.updateReadcnt(code);

		return boardDAOService.selectBoardByCode(code);
	}
	
	// 파일 상세보기 (파일 정보 가져오기)
	@Override
	public List<Map<String, Object>> selectFileListByCode(int code) throws Exception {

		return boardDAOService.selectFileListByCode(code);
	}

	// 게시글 수정
	@Override
	public void updateBoard(BoardVO boardVO) throws Exception {
		boardDAOService.updateBoard(boardVO);
	}

	// 게시글 삭제
	@Override
	public void deleteBoard(BoardVO boardVO) throws Exception {
		boardDAOService.deleteBoard(boardVO);
		boardDAOService.deleteCommentByBoardcode(boardVO);
	}

	// 파일 다운로드
	@Override
	public Map<String, Object> selectFileByFileCode(int filecode) throws Exception {
		
		return boardDAOService.selectFileByFileCode(filecode);
	}

	// 답글 등록
	@Override
	public void insertReply(BoardVO boardVO) throws Exception {
		
		// 그룹오더 수정
		boardDAOService.replyOrdergain(boardVO);
		
		// 답글 입력
		boardDAOService.insertReply(boardVO);		
	}

	// 답글 삭제
	@Override
	public void deleteReply(int code) throws Exception {
		boardDAOService.deleteReply(code);		
	}

	

	/*@Override
	public void insertReply(BoardVO boardVO) throws Exception {
		
		//boardDAOService.insertBoard(boardVO);		
	}

	@Override
	public void updateGroupOrd(BoardVO boardVO) throws Exception {
		
		boardDAOService.updateGroupOrd(boardVO);
		
	}*/
}
