<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>

<body>
<div>
    <form id="commentUpdateForm" name="commentUpdateForm" method="post">
     <h2> 댓글 수정하기</h2>
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
						<textarea style="width:500px" rows="3" cols="80" id="comment" name="comment"> </textarea>
					</td>
				</tr>
			</table>
			<input type='hidden' id='commentcode' name='commentcode' value="<c:out value='${param.commentcode }'/>" />
			<input type='hidden' id='code' name='code' value="<c:out value='${code}'/>" />
			<a href='#' onClick='fn_commentUpdate()'>수정</a>
			<a href='#' onClick='fn_commentCancel()'>취소</a>
		</div>   		           
   </form>
<script>

// 취소
function fn_commentCancel(){
    window.history.back();
	
	//location.href="/board/boardList.do";
      
}
 
//수정
function fn_commentUpdate(){
    
    var form = document.getElementById("commentUpdateForm");
    
    form.action = "<c:url value='update.do'/>";
    form.submit();
}
 

</script>
</div>
</body>
</html>
