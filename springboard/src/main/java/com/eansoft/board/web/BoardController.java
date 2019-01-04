package com.eansoft.board.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eansoft.board.service.BoardPager;
import com.eansoft.board.service.BoardVO;
import com.eansoft.board.service.impl.BoardService;
import com.eansoft.board.util.CommonUtils;
import com.eansoft.board.util.FileUtil;
import com.eansoft.comment.service.CommentVO;
import com.eansoft.comment.service.impl.CommentService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardServiceImpl;
	
	@Resource(name="fileUtil")
    private FileUtil fileUtil;
	
	@Autowired
	CommentService commentService;

     // 게시판 전체 리스트
    @RequestMapping(value="/boardList.do")
    public String boardList(@ModelAttribute("boardVO") BoardVO boardVO, Model model, 		 
    		@RequestParam(defaultValue="all") String searchOption, 
    		@RequestParam(defaultValue="") String keyword, 
    		@RequestParam(defaultValue="1")int curPage
            ) throws Exception{  	
        
        // 전체리스트 개수
        int count = boardServiceImpl.selectBoardListCnt(boardVO);
        
        // 페이징 관련 처리
        BoardPager boardPager = new BoardPager(count, curPage);
        int start = boardPager.getPageBegin();
        int end = boardPager.getPageEnd();

        // 전체리스트 조회
        List<BoardVO> list = boardServiceImpl.selectBoardList(searchOption, keyword, start, end);
     
        model.addAttribute("list", list);
        model.addAttribute("listCnt", count);
        model.addAttribute("boardPager", boardPager);
      
        return "board/boardList";
    }    

     // 글쓰기 페이지로 이동
    @RequestMapping(value="/writeForm.do")
    public String writeBoardForm() throws Exception{       
        return "board/writeForm";
    }
    
    // 게시글 등록 
    @RequestMapping(value="write.do")
    public String write(@ModelAttribute("boardVO") BoardVO boardVO, 
    					Model model, 
    					HttpServletRequest request, 
    					MultipartHttpServletRequest multi) throws Exception{    
    			
    	// 게시글 입력 
        boardServiceImpl.insertBoard(boardVO);       
      
        // 저장할 경로 생성
        String uploadPath = request.getRealPath("/resources/img");	
		File file = new File(uploadPath);		
		if (file.exists() == false) {
			file.mkdirs();						
		} 
		
		// 부모글 번호 가져오기 
		String boardIdx = Integer.toString(boardVO.getCode());
		
		Map<String, Object> fileMap = null;
			
		Iterator<String> iterator = multi.getFileNames();
		
		while(iterator.hasNext()) {
			MultipartFile multipartFile = multi.getFile(iterator.next());
			//System.out.println("멀티파트파일 이름: " + multipartFile.getName());
			
			if (multipartFile.isEmpty() == false) {
				String originalFileName = multipartFile.getOriginalFilename();				
				String originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
				String storedFileName = CommonUtils.getRandomString() + "." + originalFileExtension;
							
				String imagepath = uploadPath + "\\" + storedFileName;

				file = new File(imagepath);
				multipartFile.transferTo(file);		
				
				fileMap = new HashMap<String, Object>();
				
				fileMap.put("imagepath", imagepath);
				fileMap.put("boardcode", boardIdx);
				fileMap.put("originalfilename", originalFileName);
				fileMap.put("storedfilename", storedFileName);
				fileMap.put("filesize", multipartFile.getSize());
				fileMap.put("fileextension", originalFileExtension);
				
				boardServiceImpl.insertFile(fileMap);
			}
		}
								
       return "redirect:boardList.do";
 
    }
    
    // 게시글 상세보기 
    @RequestMapping(value="/viewContent.do")
    public String viewForm(Model model, HttpServletRequest request) throws Exception{
           	
    	System.out.println("타입: " + request.getParameter("code").getClass());
        int code = Integer.parseInt(request.getParameter("code"));       
        System.out.println("코드: " + code);
        
        // 게시글 가져오기 
        BoardVO resultVO = boardServiceImpl.selectBoardByCode(code);
               
        // 파일 정보 가져오기 
        List<Map<String, Object>> list = boardServiceImpl.selectFileListByCode(code);
       
        model.addAttribute("result", resultVO);
        model.addAttribute("filelist", list); 
              
        return "board/viewForm";
    }
    
    // 게시글 수정 페이지로 이동
    @RequestMapping(value="/update.do")
    public String updateView(@ModelAttribute("boardVO") BoardVO boardVO, Model model, HttpServletRequest request) throws Exception{
           
    	int code = Integer.parseInt(request.getParameter("code"));  
        model.addAttribute("result", boardServiceImpl.selectBoardByCode(code));                 
        
        return "board/updateForm";      
    }
    
    // 게시글 수정
    @RequestMapping(value="/updateboard.do")
    public String updateBoard(@ModelAttribute("boardVO") BoardVO boardVO, Model model) throws Exception{

        boardServiceImpl.updateBoard(boardVO);
                 
        return "redirect:/boardList.do";       
    }
      
    // 게시글 삭제
    @RequestMapping(value="/deleteBoard.do")
    public String deleteBoard(@ModelAttribute("boardVO") BoardVO boardVO, Model model, HttpServletRequest request) throws Exception{
        
        int code = Integer.parseInt(request.getParameter("code"));
        int groupcode = Integer.parseInt(request.getParameter("groupcode"));
        boardVO.setCode(code);
        boardVO.setGroupCode(groupcode);
        
        boardServiceImpl.deleteBoard(boardVO);
                
        return "redirect:/boardList.do";
    }	
    
    // 답글 달기 페이지로 이동
    @RequestMapping(value="replyboard.do")
    public String replyView(HttpServletRequest request, Model model) throws Exception{
    	
    	String boardcode = request.getParameter("code");
    	//System.out.println(boardcode);
    	
    	model.addAttribute("boardcode", boardcode);
    	
    	return "board/replyForm";
    }
    
    // 답글 달기
    @RequestMapping(value="reply.do")
    public String reply(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request) throws Exception{
    	int boardcode = Integer.parseInt(request.getParameter("boardcode"));
    	//System.out.println(boardVO);
    	//System.out.println("여기서 받음: " + boardcode);
    	String title = "	RE: "+request.getParameter("title");
    	boardVO.setGroupCode(boardcode);
    	boardVO.setTitle(title);
    	//System.out.println("보드코드 넣은 후: " + boardVO);
    	boardServiceImpl.insertReply(boardVO);
    	
    	
    	return "redirect:/boardList.do";
    }
   /* // 답글 삭제
    @RequestMapping(value="/deleteBoard.do")
    public String deleteReply(HttpServletRequest request) throws Exception{
        
        int code = Integer.parseInt(request.getParameter("code"));
             
        boardServiceImpl.deleteReply(code);
                
        return "redirect:/boardList.do";
    }	*/
    
  
    // 댓글 수정 페이지로 이동
    @RequestMapping(value="/comment/updateView.do")
    public String commentupdateView(Model model, HttpServletRequest request) throws Exception{
            
        //model.addAttribute("result", boardServiceImpl.selectBoardByCode(boardVO));                 
        //System.out.println(request.getParameter("commentcode"));
    	model.addAttribute("code", request.getParameter("code"));
        
        return "board/commentUpdate";      
    }
    
    // 댓글 수정
    @RequestMapping(value="/comment/update.do")
    public String updateComment(@ModelAttribute CommentVO vo, Model model, HttpServletRequest request) throws Exception{

    	String comment = request.getParameter("comment");
		String commentwriter = request.getParameter("commentwriter");
		int commentcode = Integer.parseInt(request.getParameter("commentcode"));
		
		System.out.println("코멘트: " + comment + " / 작성자: " + commentwriter + " / 코멘트코드: " + commentcode);
		
		vo.setCommentcode(commentcode);
		vo.setComments(comment);
		vo.setCommentwriter(commentwriter);
		
		commentService.updateComment(vo);
                       
        return "redirect:/boardList.do";       
    }
 
    
    
    // 파일 다운로드 
    @RequestMapping(value="/filedownload.do")
    public void fileDownload(Model model, HttpServletResponse response, HttpServletRequest request) throws Exception{
     int filecode = Integer.parseInt(request.getParameter("filecode"));
     System.out.println(filecode);
     Map<String, Object> map = boardServiceImpl.selectFileByFileCode(filecode);
     
     String storedFileName = (String) map.get("STOREDFILENAME");
     String originalFileName = (String)map.get("ORIGINALFILENAME");
      
     String path = request.getRealPath("/resources/img");
     System.out.println(storedFileName + originalFileName + path);
     String fileurl = path + "\\" + storedFileName;
     System.out.println("fileurl" + fileurl);
     File file1 = new File(fileurl);
     if(!file1.exists()) {
    	 return;
     }
     
     byte[] fileByte = FileUtils.readFileToByteArray(new File(fileurl));
      
     response.setContentType("application/octet-stream");
     response.setContentLength(fileByte.length);
     response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(originalFileName,"UTF-8")+"\";");
     response.setHeader("Content-Transfer-Encoding", "binary");
     response.getOutputStream().write(fileByte);
      
     response.getOutputStream().flush();
     response.getOutputStream().close();

    }	
    
}
