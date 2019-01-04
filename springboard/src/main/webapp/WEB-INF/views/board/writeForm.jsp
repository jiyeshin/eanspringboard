<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.MultiFile.js"></script>
</head>
<body>
	
		<form id="writeForm" name="writeForm" method="post" enctype="multipart/form-data">
			<div>
				<h2>글쓰기</h2>
				<div>
					<table>
						<tr>
							<th>제목</th>
							<td><input style="width: 500px" type="text" id="title"
								name="title" /> </td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea style="width: 500px" rows="10" cols="10"
									id="content" name="content"> </textarea></td>
						</tr>
						<tr>
							<th>작성자</th>
							<td><input style="width: 500px" type="text" id="writer"
								name="writer" /> </td>
						</tr>
					</table>
					
					<div id="fileDiv">
						 <input type="file" name="file1" id="file_path1" /><br/>						 
						 <input type="file" name="file2" id="file_path2" /><br/>						 
						 <input type="file" name="file3" id="file_path3" /><br/>						 
					</div> 
										 
					</div>
					
					 <br/>
					<!--  <a href="#" onClick='fn_addFile()' id="addFile">추가</a>  -->
									
					<div>						
						<a href='#' onClick='fn_addtoBoard()'>글 등록</a> 
						<a href='#' onClick='fn_cancel()'>목록</a>
					</div>
				</div>		
		</form>		
</body>



<script>
$(function(){
	
	/*   //use jQuery MultiFile Plugin 
    $('#fileDiv input[name=file]').MultiFile({
        max: 3, //업로드 최대 파일 갯수 (지정하지 않으면 무한대)
        accept: 'jpg|png|gif', //허용할 확장자(지정하지 않으면 모든 확장자 허용)
        maxfile: 1024, //각 파일 최대 업로드 크기
        maxsize: 3024,  //전체 파일 최대 업로드 크기
        STRING: { //Multi-lingual support : 메시지 수정 가능
            remove : "제거", //추가한 파일 제거 문구, 이미태그를 사용하면 이미지사용가능
            duplicate : "$file 은 이미 선택된 파일입니다.", 
            denied : "$ext 는(은) 업로드 할수 없는 파일확장자입니다.",
            selected:'$file 을 선택했습니다.', 
            toomuch: "업로드할 수 있는 최대크기를 초과하였습니다.($size)", 
            toomany: "업로드할 수 있는 최대 갯수는 $max개 입니다.",
            toobig: "$file 은 크기가 매우 큽니다. (max $size)"
        },
        list:"#afile3-list" //파일목록을 출력할 요소 지정가능
    }); */
	
});

function fn_addtoBoard(){
	var formdata = new FormData($("#writeForm")[0]);	
	$.ajax({
			url			: "write.do",
			data		: formdata,
			contentType	: false,
			processData	: false,
			type		: "POST",
			success		: function(data){
				alert("글쓰기 성공");
				location.href="boardList.do"
				
			}
	});
}


//게시글 목록으로 가기 
function fn_cancel() {

var form = document.getElementById("writeForm");

form.action = "<c:url value='/boardList.do'/>";
form.submit();

}

	/* 	
	// 게시글 쓰기
	function fn_addtoBoard() {

	var form = document.getElementById("writeForm");

	form.action = "<c:url value='/write.do'/>";
	form.submit();

	}

	
	
	// 파일 추가
	function fn_addFile(){
		var g = 0;
		 var str = "<p><input type='file' name='file_"+(g_count++)+"'/><a href='#' name='delete' class='btn'>삭제하기</a></p> ";
         $("#fileDiv").append(str);
          
         $("a[name='delete']").on("click",function(e){
             e.preventDefault();
             fn_fileDelete($(this));         
         })
		
	}
	
	 function fn_fileDelete(obj){
         obj.parent().remove();
     }
	  */
	 
	 

/* 	$("#file_path").change(function(e){
		//선택된 파일이 있다면
		//어떤 프로그래밍 언어에서는 숫자의 경우 0이 아니면 true로 간주
		//참조형의 경우는 null이 아니면 true로 간주
		if (this.files && this.files[0]) {
			filename = this.files[0].name;
			var ext = filename.substr(filename.length - 3, filename.length);
			var isCheck = false;
			if ((ext.toLowerCase() == "jpg" || ext.toLowerCase() == "gif" || ext.toLowerCase() == "png")) {
				isCheck = true;
			}
			if (isCheck == false) {
				// 이 작업을 하지 않으면 선택한 파일의 경로가 남아있게 된다. 
				alert("jpg나 gif, png 만 업로드가 가능합니다.");
				document.getElementById('file_path').value = ""
				return;
			}
			
			//파일 내용을 읽을 수 있는 객체를 생성
			var reader = new FileReader()
			
			//파일의 내용을 읽기를 요청 - 비동기 요청
			reader.readAsDataURL(this.files[0])
			
			//파일의 내용을 전부 읽으면 호출되는 콜백 처리
			reader.onload = function(e){
				document.getElementById("img").src = e.target.result;
			}
		}
		
	}); */
	
		
	
	
	
		
		/*
		function LoadImg(value) {
			if(value.files && value.files[0]){
				var reader = new FileReader();
				reader.onload = function(e){
					$("#LoadImg").attr("src", e.target.result);
				}
				reader.readAsDataURL(value.files[0]);
			}
		}
		 $(function(){
			var file = $("#file");
			
			file.onchange = function(){
				var fileList = file.files;
				
				var fileList = new FileReader();
				 // 읽기
			    var reader = new FileReader();
			    reader.readAsDataURL(fileList [0]);

			    //로드 한 후
			    reader.onload = function  () {
			       
			        
			        //썸네일 이미지 생성
			        var tempImage = new Image(); //drawImage 메서드에 넣기 위해 이미지 객체화
			        tempImage.src = reader.result; //data-uri를 이미지 객체에 주입
			        tempImage.onload = function() {
			            //리사이즈를 위해 캔버스 객체 생성
			            var canvas = document.createElement('canvas');
			            var canvasContext = canvas.getContext("2d");
			            
			            //캔버스 크기 설정
			            canvas.width = 100; //가로 100px
			            canvas.height = 100; //세로 100px
			            
			            //이미지를 캔버스에 그리기
			            canvasContext.drawImage(this, 0, 0, 100, 100);
			            //캔버스에 그린 이미지를 다시 data-uri 형태로 변환
			            var dataURI = canvas.toDataURL("image/jpeg");
			            
			            //썸네일 이미지 보여주기
			            $("#thumbnail").src = dataURI;
			           	            
			        };
			    }; 
			};
		}) */
		
		
		/* // 파일 추가 
		function fn_addFile(){
			
			var str = "<p><input type='file' name='no_" + (gfv_count++)
			+ "'><a href='#this' class='btn' name='delete'>삭제</a></p>";
	$("#fileDiv").append(str);
	$("a[name='delete']").on("click", function(e) { //삭제 버튼
		e.preventDefault();
		fn_deleteFile($(this));
	});
		} 
		
		// 파일 삭제 
		function fn_deleteFile(){
			obj.parent().remove();
		}
		*/
		
			
		</script>


</html>
