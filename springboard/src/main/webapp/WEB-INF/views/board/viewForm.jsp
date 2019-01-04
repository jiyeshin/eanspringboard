<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>


<style>
  table {
    width: 100%;
    border: 1px solid #444444;
    border-collapse: collapse;
  }
  th, td {
    border: 1px solid #444444;
    padding: 10px;
  }
</style>
</head>


<body>	
		<form id="viewForm" name="viewForm" method="post">		
				<h2>상세보기</h2>		
					<table>
						<tr>
							<th>제목</th>
							<td>${result.title }							
							</td>
						</tr>
						<tr>
							<th>내용</th>
							<td>
								${result.content } <br/>								
								<c:forEach var='filelist' items='${filelist}'>							
									<c:if test="${filelist.FILEEXTENSION eq jpg}">																								
										<input type='hidden' id='${filelist.FILECODE }' name='filecode' value='${filelist.FILECODE }'>
										<img src="${pageContext.request.contextPath}/resources/img/${filelist.STOREDFILENAME}" width="300dp"/>	
										<br/>		
									</c:if>																								
								</c:forEach>								
							</td>					
						</tr>
						<tr>
							<th>작성자</th>
							<td>
							${result.writer }							
							</td>
						</tr>						
					</table>

					<div>
						<a href='#' onClick='fn_updatePage()'>수정</a> 
						<a href='#' onClick='fn_delete()'>삭제</a> 
						<a href='#' onClick='fn_cancel()'>목록</a>
						<a href='#' onClick='fn_relay()'>답변</a>
					</div>
				
				<input type='hidden' id='code' name='code' value='${result.code}' />
				<input type='hidden' id='groupcode' name='groupcode' value='${result.groupCode }'/>
		</form>

		<form id="fileForm" name="fileForm" method="post" enctype="multipart/form-data">
		<table>
			<tr>											
				<c:forEach var='filelist' items='${filelist}'>
						
							<td>
								<input type='hidden' id='${filelist.FILECODE }' name='filecode' value='${filelist.FILECODE }'>
								<a href="#"  name="fileview">${filelist.ORIGINALFILENAME}</a>
					      		(${filelist.FILESIZE}kb) <br />
								<img src="${pageContext.request.contextPath}/resources/img/${filelist.STOREDFILENAME}" width="200dp" />							
							</td>						
				</c:forEach>																		
			</tr>
		</table>
		</form>
		<br/>
		
		<!-- 댓글 list 출력 -->
		<div id="commentList"></div>

		<!-- 댓글 입력 -->
		<div>
			<table>
				<tr>
					<th>작성자</th>
					<td><input style="width: 500px" type="text" id="commentwriter"
						name="commentwriter"></td>
				</tr>
				<tr>
					<th>댓글입력</th>
					<td>
						<textarea style="width:500px" rows="3" cols="80" id="comment" name="comment">댓글을 입력하시오 </textarea>
					</td>
				</tr>
			</table>
			<a href='#' onClick='fn_commentinsert()'>댓글 작성</a>
		</div>
		
		
	
</body>
<script>
			
			$(function() {
				listComment();
			});

			// 목록
			function fn_cancel() {

				var form = document.getElementById("viewForm");

				form.action = "<c:url value='/boardList.do?curPage=${curPage}&searchOption=${searchOption}&keyword=${keyword}'/>";
				form.submit();
			}

			// 수정
			function fn_updatePage() {

				var form = document.getElementById("viewForm");

				form.action = "<c:url value='/update.do'/>";
				form.submit();
			}

			// 삭제
			function fn_delete() {

				var form = document.getElementById("viewForm");

				form.action = "<c:url value='/deleteBoard.do'/>";
				form.submit();
			}
			
			
			// 답글 달기
			function fn_relay(){
				var form = document.getElementById("viewForm");

				form.action = "<c:url value='/replyboard.do'/>";
				form.submit();
				
				
			}

			// 댓글 등록
			function fn_commentinsert() {
				var comment = $("#comment").val();
				var boardcode = "${result.code}";
				var commentwriter = $("#commentwriter").val();
				var param = "comment=" + comment + "&boardcode=" + boardcode
						+ "&commentwriter=" + commentwriter;
				$.ajax({
					type : "get",
					data : param,
					url : "comment/insert.do",
					success : function() {
						//alert("댓글이 등록되었습니다.");
						$("#comment").val('');
						$("#commentwriter").val('');
						listComment();
					}
				});
			}

			// 댓글 목록 
			function listComment() {
				var commentcode;
				$.ajax({
					type : "get",
					url : "comment/listJson.do?boardcode=${result.code}",
					success : function(result) {
						//console.log(result);
						var output = "<table>";
						for ( var i in result) {
							output += "<tr>";
							output += "<td>"; 																			
							output += "<div style='margin-left:";
							output += 20*result[i].commentlayer +"px'>"; 
							output += result[i].commentwriter + ":  ";
							output += result[i].comments;
							output += "<a href='#' onClick='fn_commentdelete(";
							output += result[i].commentcode;
							output += ")'>삭제</a> &nbsp;";
							output += "<a href='#' onClick='fn_commentupdate(";
							output += result[i].commentcode;
							output += ")'>수정</a> &nbsp;";		
							//output += "<a href='#' name='commentofcomment'>대댓글</a>"
						 	output += "<a href='#' name='commentofcomment' onClick='fn_commentofcomment(";
							output += result[i].commentcode;
							output += ")'>대댓글</a>";  
							output += "<br/>";							
							output += "<div id='";
							output += result[i].commentcode;
							output += "'>";
							output += "</div></div>";								           
							output += "</td>"
							output += "</tr>";
						}
						output += "</table>";
						$("#commentList").html(output);

					}
				});
			}

			// 댓글 삭제
			function fn_commentdelete(commentcode) {
				var param = "commentcode=" + commentcode;
				$.ajax({
					type : "post",
					data : param,
					url : "comment/delete.do",
					success : function() {
						alert("댓글이 삭제되었습니다.");
						listComment();
					}
				});
			}
									
			 //댓글 수정
			 function fn_commentupdate(commentcode){
																
				var param = "commentcode="+commentcode;
				location.href="comment/updateView.do?"+param;
			 } 
			
			  // 대댓글 창 열기
			  function fn_commentofcomment(commentcode){
				  
				  var commentcode = commentcode;
				  var boardcode = "${result.code}";
				 
				  //alert(commentlayer);
				  
				  var commentEditor = 
					  	"<form id='subcommentfrm'>" +
						"<table><tr>" + 
						"<td>작성자</td>" +
						"<td><input style='width: 500px' type='text'" +
						"name='subcommentwriter'></td><br/>" +
						"</tr>" +
						"<tr>" +
						"<td>댓글입력</td>" +
						"<td>" +
						"<textarea style='width:500px' rows='3' cols='80' id='subcomment' name='subcomment'>댓글을 입력하시오 </textarea>" +
						"</td>" +
						"</tr>" + 
						"<tr><td colspan='2'>" +
						"<a href='#' onClick='fn_subcommentinsert()'>대댓글 작성</a> &nbsp;" +
						"<a href='#' onClick='fn_commentofcommentcancel()'>취소</a>" +
						"</td></tr>" +
						"<input type='hidden' name='boardcode' value='" +
						boardcode + "'/>" +
						"<input type='hidden' name='commentcode' value='" + 
						commentcode + "'/>" + 						
						"</table></form>"
					 
						$("#"+commentcode).append(commentEditor);				  				  				  								 
			 }  
			  
			  // 대댓글 등록 (폼방식)
			  function fn_subcommentinsert(){
				 var queryString = $("#subcommentfrm").serialize();
				 $.ajax({
						type : "get",
						data : queryString,
						url : "comment/insertsubcomment.do",
						success : function() {
							alert("대댓글이 등록되었습니다.");
							listComment();
						}
					});
				 
			  }
			  /* 
				// 대댓글 등록 (쿼리스트링 방식)
				function fn_commentofcommentinsert() {
					var comment = $("#comment").val();
					var boardcode = "${result.code}";
					var commentwriter = $("#commentwriter").val();
					var param = "comment=" + comment + "&boardcode=" + boardcode
							+ "&commentwriter=" + commentwriter;
					$.ajax({
						type : "get",
						data : param,
						url : "comment/insert.do",
						success : function() {
							alert("댓글이 등록되었습니다.");
							listComment();
						}
					});
				} */
			 
			 

			 
			 
			 //파일 다운로드			
			 $("[name='fileview']").click(function(){
					var filecode = $(this).parent().find("input").val();
					var data = "filecode="+filecode;
					alert(data);
					location.href="filedownload.do?"+data;					
				});
					
			
		</script>
</html>
