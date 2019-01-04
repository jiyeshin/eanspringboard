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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<body>
	<div>
		<form id="replyForm" name="replyForm" method="post">
			<div>
				<h2>답글 쓰기</h2>
				<div>
					<table>
						<tr>
							<th>제목</th>
							<td><input style="width: 500px" type="text" id="title"
								name="title" /></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea style="width: 500px" rows="10" cols="10"
									id="content" name="content"></textarea></td>
						</tr>
						<tr>
							<th>작성자</th>
							<td><input style="width: 500px" type="text" id="writer"
								name="writer" /></td>
						</tr>
					</table>
					<div>
						<a href='#' onClick='fn_addtoBoard()'>답글 달기 </a> <br/>
						<a href='#' onClick='fn_cancel()'>목록</a>
					</div>
				</div>
			</div>
			<input type="hidden" name="boardcode" value="${boardcode}"/>
		</form>
		<script>
			//글쓰기
			function fn_addtoBoard() {

				var form = document.getElementById("replyForm");

				form.action = "<c:url value='/reply.do'/>";
				form.submit();
			}

			//목록
			function fn_cancel() {
				location.href = "boardList.do";			
			}
		</script>
	</div>
</body>
</html>
