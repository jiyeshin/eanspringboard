package com.eansoft.board.service;

public class BoardPager {
	// 페이지당 게시물 수
	public static final int PAGE_SCALE = 5;
	
	// 화면당 페이지 수
	public static final int BLOCK_SCALE = 3;
	private int curPage;		// 현재 페이지
	private int prevPage;		// 이전 페이지
	private int nextPage;		// 다음 페이지
	private int totalPage;		// 전체 페이지 수
	private int totalBlock;		// 전체 페이지 블록 수
	private int curBlock;		// 현재 페이지 블록
	private int prevBlock;		// 이전 페이지 블록
	private int nextBLock;		// 다음 페이지 블록
	// where rn between #{start} and #{end}
	private int pageBegin;		// #{start}
	private int pageEnd;		// #{end}
	
	// [이전] blockBegin -> 5 6 7 8 9 [다음]
	private int blockBegin;
	
	// [이전] 5 6 7 8 9 <- blockEnd [다음]
	private int blockEnd;
	
	// 생성자
	// BoardPager(레코드 개수, 현재 페이지 번호)
	public BoardPager(int count, int curPage) {
		curBlock = 1;				//현재 페이지 블록 번호
		this.curPage = curPage;		// 현재 페이지 설정
		setTotalPage(count);		// 전제 페이지 개수 계산
		setPageRange();				// 페이지 범위
		setTotalBlock();			// 전체 페이지 블록 개수 계산
		setBlockRange();			// 페이지 블록의 시작과 끝번호 계산
	}
	
	public void setPageRange() {
		// 현재 페이지가 몇 번째 페이지 블록에 속하는지 계산.
		curBlock = (int)Math.ceil((curPage-1) / BLOCK_SCALE)+1;
		blockBegin = (curBlock-1)*BLOCK_SCALE+1;
		blockEnd = blockBegin + BLOCK_SCALE-1;
		
		// 마지막 블록이 범위를 초과하지 않도록 처리
		if(blockEnd > totalPage) blockEnd = totalPage;
		prevPage = (curPage == 1)?1:(curPage-1);
		nextPage = curPage + 1;
		// nextPage = (curPage > totalPage)?totalPage+1 : (curPage +1);
		//nextPage = (curPage > curBlock*BLOCK_SCALE)?(curBlock*BLOCK_SCALE): (curPage +1);
		//nextPage = curBlock > totalBlock ? (curBlock*BLOCK_SCALE) : (curBlock*BLOCK_SCALE);
		
		// 마지막 페이지가 범위를 초과하지 않도록 처리
		if(nextPage >= totalPage) nextPage = totalPage;
	}
	
	public void setBlockRange() {
		// where rn between #{start} and #{end}
		// 시작번호 = (현재페이지-1)*페이지당 게시물 수 +1
		pageBegin = (curPage-1)*PAGE_SCALE+1;
		
		// 끝번호 = 시작번호 + 페이지당 게시물 후 -1
		pageEnd = pageBegin + PAGE_SCALE-1;
	}
	
	

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int count) {
		totalPage = (int)Math.ceil(count*1.0 / PAGE_SCALE);
	}

	public int getTotalBlock() {
		return totalBlock;
	}

	// 페이지 블록의 개수 계산(예: 총 100페이지라면 10개의 블록)
	public void setTotalBlock() {
		// 전체 페이지 개수 / 10
		totalBlock = (int)Math.ceil(totalPage / BLOCK_SCALE);
	}

	public int getCurBlock() {
		return curBlock;
	}

	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}

	public int getPrevBlock() {
		return prevBlock;
	}

	public void setPrevBlock(int prevBlock) {
		this.prevBlock = prevBlock;
	}

	public int getNextBLock() {
		return nextBLock;
	}

	public void setNextBLock(int nextBLock) {
		this.nextBLock = nextBLock;
	}

	public int getPageBegin() {
		return pageBegin;
	}

	public void setPageBegin(int pageBegin) {
		this.pageBegin = pageBegin;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}

	public int getBlockBegin() {
		return blockBegin;
	}

	public void setBlockBegin(int blockBegin) {
		this.blockBegin = blockBegin;
	}

	public int getBlockEnd() {
		return blockEnd;
	}

	public void setBlockEnd(int blockEnd) {
		this.blockEnd = blockEnd;
	}	
}
