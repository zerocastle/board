<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../fixSection/header.jsp"%>

<script src="/resources/js/dropzone.js"></script>
<link href="/resources/css/dropzone.css" rel="stylesheet">

<script>
Dropzone.autoDiscover = false;
var formData = new FormData();
var file_name;

$(document).ready(function() {
	
	var myDropzone = new Dropzone("div#dZUpload", 
			
		{ 	url: "/board/board_file",
			addRemoveLinks :true,
			removedfile: function(file) {
				formData.delete(file.name);
				file.previewElement.remove(); 
			}
		});
	
	myDropzone.on("thumbnail", function(file){
		formData.append(file.name, dataURLtoBlob(file.dataURL), file.name);
	});
});
	
	//글쓰기
	$(document).on("click", "#write", function(e){
		var form = $('#board_form');
		e.preventDefault();
		$.ajax({
			url: "/board/fileUpload",
			type: "POST",
			contentType : false,
	        processData : false,
			data: formData,
			success : function(data){
				file_name = data;
				console.log(data);
				console.log(data.length);
				var li="";
				for(var i = 0; i < data.length; i++){
					li += "<input name='fileUploadVO["+i+"].uploadPath' value='"+data[i].uploadPath+"'/>";
					li += "<input name='fileUploadVO["+i+"].fileName' value='"+data[i].fileName+"'/>";
					alert(data[i].fileName);
					$('#hiddenAppend').append(li);
				}
			
				form.append(li);
				//$("#board_form").append(str);
				/* $("#board_form").append('<input type="submit" id="submit"/>');
				$("#submit").trigger("click"); */ 
			}
		}).done(function(){
			
			if(($('#title').val() == '') || ($('#content').val() == '')){
				alert("제목 또는 내용을 입력해주세요 ~~");
				return false;
			} 
			alert("전송하자구");
			form.submit();
		});
		
		window.alert("hellow");
		
		
	});

//**dataURL to blob**
function dataURLtoBlob(dataurl) {
    var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
    while(n--){
        u8arr[n] = bstr.charCodeAt(n);
    }
    return new Blob([u8arr], {type:mime});
}

</script>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">작성하기</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="container">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">

				<div class="panel-heading">작성하기</div>
				<!-- /.panel-heading -->
				<div class="panel-body">

					<form id="board_form" role="form" action="/board/register"
						method="post">
						<div class="form-group">
							<label>제목</label> <input class="form-control" name='title'
								id='title'>
						</div>


						<div class="form-group">
							<label>content</label>
							<textarea class="form-control" rows="3" name='content'
								id='content'></textarea>
						</div>

						<!-- 이미지 드래그엔 드랍 -->
						<div class="outerDorpzone">
							<br />
							<div id="dZUpload" class="dropzone">
								<div class="dz-default dz-message">Drop files here or
									click to upload.</div>
							</div>
						</div>
						<!-- 잠시대기 -->
						<!-- 	<button id="write">파일업로드 테스트</button> -->



						<div class="form-group">
							<label>작성자</label> <input type="hidden" class="form-control"
								name='id' value="${sessionScope.member.id }" /> <input
								value="${sessionScope.member.name}" readonly />
						</div>
						<!-- 파일 업로드 부분 프로미스로 먼저 실행뒤 submit -->
						<div class="hiddenAppend" style="display: none;"></div>
					</form>
					<a href="#" id="write" class="btn btn-info">저장하기</a> <a
						href="/move/board" class="btn btn-info">목록으로</a>
				</div>
				<!--  end panel-body -->

			</div>
			<!--  end panel-body -->
		</div>
		<!-- end panel -->
	</div>
</div>

<script>
$(function() {
	var param = getParams();
	console.log(param);

	if (param.signal == "fail") {
		window.alert("게시글을 쓰는데 문제가 생겼습니다.");
	}

	function getParams() {
		// 파라미터가 담길 배열
		var param = new Array();

		// 현재 페이지의 url
		var url = decodeURIComponent(location.href);
		// url이 encodeURIComponent 로 인코딩 되었을때는 다시 디코딩 해준다.
		url = decodeURIComponent(url);

		var params;
		// url에서 '?' 문자 이후의 파라미터 문자열까지 자르기
		params = url.substring(url.indexOf('?') + 1, url.length);
		// 파라미터 구분자("&") 로 분리
		params = params.split("&");

		// params 배열을 다시 "=" 구분자로 분리하여 param 배열에 key = value 로 담는다.
		var size = params.length;
		var key, value;
		for (var i = 0; i < size; i++) {
			key = params[i].split("=")[0];
			value = params[i].split("=")[1];

			param[key] = value;
		}

		return param;
	}

})
</script>

<%@ include file="../fixSection/footer.jsp"%>