package com.eansoft.comment.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eansoft.comment.service.CommentVO;
import com.eansoft.comment.service.impl.CommentService;

@RestController
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	// 댓글 리스트 
		@RequestMapping("/comment/listJson.do")
		public List<CommentVO> listJson(HttpServletRequest request)throws Exception{
			int boardcode = Integer.parseInt(request.getParameter("boardcode"));
			//System.out.println(boardcode);
			List<CommentVO> list = commentService.selectCommentList(boardcode);
			System.out.println(list);
			
			
			return list;
		}
	
	// 댓글 입력
	@RequestMapping("/comment/insert.do")
	public void insert(@ModelAttribute CommentVO vo, HttpServletRequest request) throws Exception{	
		String comment = request.getParameter("comment");
		String commentwriter = request.getParameter("commentwriter");
		int boardcode = Integer.parseInt(request.getParameter("boardcode"));
		//System.out.println(comment + "  " + commentwriter + "  " + boardcode);
		
		vo.setComments(comment);
		vo.setBoardcode(boardcode);
		vo.setCommentwriter(commentwriter);
		
		commentService.insertComment(vo);	
	}
	
	// 댓글 삭제
	@RequestMapping("/comment/delete.do")
	public void delete(@ModelAttribute CommentVO vo, HttpServletRequest request) throws Exception{
		
		int commentcode = Integer.parseInt(request.getParameter("commentcode"));		
		
		System.out.println(commentcode);
		
		commentService.deleteComment(commentcode);	
		//return "redirect:/comment/listJson.do";
	}
	
	// 대댓글 입력
		@RequestMapping("/comment/insertsubcomment.do")
		public void insertsubcomment(@ModelAttribute CommentVO vo, HttpServletRequest request) throws Exception{	
						
			String comments = request.getParameter("subcomment");
			String commentwriter = "RE: "+request.getParameter("subcommentwriter");
			int boardcode = Integer.parseInt(request.getParameter("boardcode"));
			int commentcode = Integer.parseInt(request.getParameter("commentcode"));
			//System.out.println("코멘트 코드: " + commentcode);
			//System.out.println(comments + "  " + commentwriter + "  " + boardcode + "   " + commentcode);
			CommentVO commentvo = commentService.selectCommentByCommentcode(commentcode);
			System.out.println("에스큐엘 보니기 전에: " + commentvo);
			int commentlayercount = commentvo.getCommentlayer();
			int commentgroup = commentvo.getCommentgroup();
			
			if(commentlayercount > 0) {
				commentvo.setComments(comments);
				commentvo.setBoardcode(boardcode);
				commentvo.setCommentgroup(commentgroup);
				commentvo.setCommentwriter(commentwriter);
				
				commentService.insertSubComment(commentvo);	
			}else {
				vo.setComments(comments);
				vo.setBoardcode(boardcode);
				vo.setCommentgroup(commentcode);
				vo.setCommentwriter(commentwriter);
				
				commentService.insertSubComment(vo);
			}
			//System.out.println("코멘트 레이어가 들어간 vo클래스 풀버전:" + vo);
			
			/*vo.setComments(comments);
			vo.setBoardcode(boardcode);
			vo.setCommentgroup(commentcode);
			vo.setCommentwriter(commentwriter);
			
			commentService.insertSubComment(vo);*/	
		}
	
	
    
    
	
	

}
