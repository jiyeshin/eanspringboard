<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="/css/bootstrap.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</head>
<body>
		<!-- 게시글 목록보기 -->
		<form id="boardForm" name="boardForm" method="post">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>날짜</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="result" items="${list }" varStatus="status">						
						<tr>
						<td><c:out value="${result.code }" /></td>
						<c:if test="${result.groupLayer eq 1}">
							<td><a href='#' onClick='fn_view(${result.code})'> 
							RE: <c:out value="${result.title }" /></a></td>
						</c:if>
							
							<td><a href='#' onClick='fn_view(${result.code})'><c:out
										value="${result.title }" /></a></td>
							<td><c:out value="${result.writer }" /></td>
							<td><c:out value="${result.regdate }" /></td>
							<td><c:out value="${result.readcnt }" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
		
		<!-- 검색 도구 -->
		<form id="searchForm" method="get" action="/board/boardList.do">
			<select name="searchOption">
				<option value="all"
					<c:out value="${searchOption == 'all'?'selected':''}"/>>
					전체보기 </option>
				<option value="titleContent"
					<c:out value="${searchOption == 'titleContent'?'selected':''}"/>>
					제목 + 내용</option>
				<option value="content"
					<c:out value="${searchOption == 'content'?'selected':''}"/>>
					내용</option>
				<option value="title"
					<c:out value="${searchOption == 'title'?'selected':''}"/>>
					제목</option>
				<option value="writer"
					<c:out value="${searchOption == 'writer'?'selected':''}"/>>
					글쓴이</option>
			</select> 
			<input name="keyword" value="${keyword}">
			 <input type="submit" value="검색">
		</form>
		<div>
			<a href='#' onClick='fn_write()' class="btn btn-success">글쓰기</a>
		</div>

		<!-- 페이징 처리 -->
		<div>
			<!--  처음 페이지로 이동: 현재 페이지가 1보다 크면 [처음]을 화면에 출력 -->
			<c:if test="${boardPager.curBlock > 1 }">
				<a href="javascript:list('1')">[처음]</a>
			</c:if>
			
			<!-- 이전 페이지 블록으로 이동: 현재 페이지 블록이 1보다 크면 [이전]을 화면에 출력 -->
			<c:if test="${boardPager.curPage > 1}">
				<a href="javascript:list('${boardPager.prevPage}')">[이전]</a>
			</c:if>
			
			<!-- 하나의 블록에서 반복문 수행: 시작 페이지부터 끝 페이지까지 -->
			<c:forEach var="pageNum" begin="${boardPager.blockBegin}" end="${boardPager.blockEnd}">
				<c:choose>
					<c:when test="${pageNum eq  boardPager.curPage}">
						<span style="font-weight: bold;">${pageNum}</span>&nbsp;
					</c:when>
					<c:otherwise>
						<a href="javascript:list('${pageNum}')">${pageNum }</a>&nbsp;
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<!-- 다음 페이지 블록으로 이동: 현재 페이지 블록이 전체 페이지 블록보다 작거나 같으면 [다음]을 화면에 출력 -->
			<%-- <c:if test="${boardPager.curBlock <= boardPager.totalBlock+1}">
				<a href="javascript:list('${boardPager.nextPage}')">[다음]</a>
			</c:if> --%>
			<c:if test="${boardPager.curPage < boardPager.totalPage}">
				<a href="javascript:list('${boardPager.nextPage}')">[다음]</a>
			</c:if>
			
			<!-- 끝 페이지로 이도: 현재 페이지가 전체 페이지보다 작거나 같으면 [끝]을 화면에 출력 -->
			<c:if
				test="${boardPager.curPage <= boardPager.totalPage}">
				<a href="javascript:list('${boardPager.totalPage}')">[끝]</a>
			</c:if>
		</div>
</body>
	
<script>
// 원하는 페이지로 이동 시 검색조건, 키워드 값을 유기하기 위해
function list(page){
	location.href = "${path}/board/boardList.do?curPage=" + page + "&searchOption=${searchOption}&keyword=${keyword}";
}


// 글쓰기
function fn_write(){
    
    var form = document.getElementById("boardForm");
    
    form.action = "<c:url value='/writeForm.do'/>";
    form.submit();   
}
 
// 글조회
function fn_view(code){
    
    var form = document.getElementById("boardForm");
    var url = "<c:url value='/viewContent.do'/>";
    url = url + "?code=" + code;
    
    form.action = url;    
    form.submit(); 
}
</script>
	
</html>
