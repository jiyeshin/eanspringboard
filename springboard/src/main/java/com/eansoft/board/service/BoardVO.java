package com.eansoft.board.service;

import java.util.Arrays;

public class BoardVO {
	
	private int code;			// 게시글 번호
	private String title;		// 게시글 제목 
	private String content;		// 게시글 내용 
	public int getCommentcnt() {
		return commentcnt;
	}
	public void setCommentcnt(int commentcnt) {
		this.commentcnt = commentcnt;
	}
	private String writer;		// 게시글 작성자 
	private String regdate;		// 게시글 등록일 
	private int readcnt;		// 게시글 조회수
	private int commentcnt;		// 댓글 수 
	private String[] files;
	
	public String[] getFiles() {
		return files;
	}
	public void setFiles(String[] files) {
		this.files = files;
	}
	/*
     * 
     *  
     */
	private int startIndex;
	private int cntPerPage;
	
	/*
     * 계층형 게시판을 위한 추가 필드
     * originNo, groupOrd, groupLayer 
     */
    
 
    
    /** 원글(답글포함)에 대한 순서 **/
    private int groupOrd;
 
	/** 답글 계층 **/
    private int groupLayer;
    
    private int groupCode;
	
	
  
  	public int getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(int groupCode) {
		this.groupCode = groupCode;
	}
	public int getGroupOrd() {
  		return groupOrd;
  	}
  	public void setGroupOrd(int groupOrd) {
  		this.groupOrd = groupOrd;
  	}
  	public int getGroupLayer() {
  		return groupLayer;
  	}
  	public void setGroupLayer(int groupLayer) {
  		this.groupLayer = groupLayer;
  	}
    
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getCntPerPage() {
		return cntPerPage;
	}
	public void setCntPerPage(int cntPerPage) {
		this.cntPerPage = cntPerPage;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getReadcnt() {
		return readcnt;
	}
	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}
	@Override
	public String toString() {
		return "BoardVO [code=" + code + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", regdate=" + regdate + ", readcnt=" + readcnt + ", commentcnt=" + commentcnt + ", files="
				+ Arrays.toString(files) + ", startIndex=" + startIndex + ", cntPerPage=" + cntPerPage + ", groupOrd="
				+ groupOrd + ", groupLayer=" + groupLayer + ", groupCode=" + groupCode + "]";
	}
	
	
	
}
