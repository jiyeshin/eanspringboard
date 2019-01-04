package com.eansoft.comment.service;

public class CommentVO {
	
	private int commentcode;			// 댓글 번호 
	private int boardcode;				// 게시글 번호
	private String comments;			// 댓글 내용
	private String commentwriter;		// 댓글 작성자
	private String commentregdate;		// 댓글 작성일자
	
	private int commentorder;			// 대댓글을 포함한 댓글 순서
	private int commentlayer;			
	private int commentgroup;			// 부모 댓글 번호
	
	
	
	public int getCommentorder() {
		return commentorder;
	}
	public void setCommentorder(int commentorder) {
		this.commentorder = commentorder;
	}
	public int getCommentlayer() {
		return commentlayer;
	}
	public void setCommentlayer(int commentlayer) {
		this.commentlayer = commentlayer;
	}
	public int getCommentgroup() {
		return commentgroup;
	}
	public void setCommentgroup(int commentgroup) {
		this.commentgroup = commentgroup;
	}
	public int getCommentcode() {
		return commentcode;
	}
	public void setCommentcode(int commentcode) {
		this.commentcode = commentcode;
	}
	public int getBoardcode() {
		return boardcode;
	}
	public void setBoardcode(int boardcode) {
		this.boardcode = boardcode;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getCommentwriter() {
		return commentwriter;
	}
	public void setCommentwriter(String commentwriter) {
		this.commentwriter = commentwriter;
	}
	public String getCommentregdate() {
		return commentregdate;
	}
	public void setCommentregdate(String commentregdate) {
		this.commentregdate = commentregdate;
	}
	@Override
	public String toString() {
		return "CommentVO [commentcode=" + commentcode + ", boardcode=" + boardcode + ", comments=" + comments
				+ ", commentwriter=" + commentwriter + ", commentregdate=" + commentregdate + ", commentorder="
				+ commentorder + ", commentlayer=" + commentlayer + ", commentgroup=" + commentgroup + "]";
	}
	

	
	
	
}
