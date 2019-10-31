<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../fixSection/header.jsp"%>
<h1>영화 커뮤니티 게시판 입니다.</h1>
<div class="container">

	<!-- 검색 폼... -->
	<div class='row'>
		<div class="col-lg-12">

			<form id='searchForm' action="/admin/noticeBoard" method='get'>
				<select name='type'>
					<option value=""
						<c:out value="${pageMaker.cri.type == null?'selected':''}"/>>--</option>
					<option value="T"
						<c:out value="${pageMaker.cri.type eq 'T'?'selected':''}"/>>제목</option>
					<option value="C"
						<c:out value="${pageMaker.cri.type eq 'C'?'selected':''}"/>>내용</option>
					<option value="W"
						<c:out value="${pageMaker.cri.type eq 'W'?'selected':''}"/>>작성자</option>
					<option value="TC"
						<c:out value="${pageMaker.cri.type eq 'TC'?'selected':''}"/>>제목
						or 내용</option>
					<option value="TW"
						<c:out value="${pageMaker.cri.type eq 'TW'?'selected':''}"/>>제목
						or 작성자</option>
					<option value="TWC"
						<c:out value="${pageMaker.cri.type eq 'TWC'?'selected':''}"/>>제목
						or 내용 or 작성자</option>
				</select> <input type='text' name='keyword'
					value='<c:out value="${pageMaker.cri.keyword}"/>' /> <input
					type='hidden' name='pageNum'
					value='<c:out value="${pageMaker.cri.pageNum}"/>' /> <input
					type='hidden' name='amount'
					value='<c:out value="${pageMaker.cri.amount}"/>' />
				<button class='btn btn-default'>Search</button>
			</form>
		</div>
	</div>
	<!-- 검색 폼 끝 -->
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th>글제목</th>
				<th>글내용</th>
				<th>날짜</th>
				<th>글쓴이</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="board">
				<tr class="row_table">
					<td><c:out value="${board.title}" /></td>
					<td><c:out value="${board.content}" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd"
							value="${board.regDate}" /></td>
					<td><c:out value="${board.id}" /></td>
					<td><c:out value="${board.cnt}" /></td>
					<td style="display: none;"><input type="hidden"
						value="${board.bno}"></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<hr />
	<a href="/move/board/register" class="btn btn-info">글쓰기</a>
	<!-- 페이징 처리 -->
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">
			<c:if test="${pageMaker.prev}">
				<li class="page-item previous"><a class="page-link"
					href="${pageMaker.startPage -1}">Previous</a></li>
			</c:if>

			<c:forEach var="num" begin="${pageMaker.startPage}"
				end="${pageMaker.endPage}">
				<li class="page-item ${pageMaker.cri.pageNum == num ? "active":""} ">
					<a class="page-link" href="${num}">${num}</a>
				</li>
			</c:forEach>

			<c:if test="${pageMaker.next}">
				<li class="page-item next"><a class="page-link"
					href="${pageMaker.endPage +1 }">Next</a></li>
			</c:if>
		</ul>
	</nav>
	<!-- 페이징 끝나는 부분 -->

	<!-- 전송할 페이지 정보 -->
	<form id="actionForm" action="/move/board" method="get">
		<input type="hidden" name="pageNum" value='${pageMaker.cri.pageNum}' />
		<input type="hidden" name="amount" value='${pageMaker.cri.amount}' />

	</form>
</div>

<!-- 페이지에 대한 값 처리 -->
<script>
	var actionForm = $("#actionForm");
	$(".page-item a").on("click", function(e) {

		e.preventDefault();

		console.log('click');

		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		actionForm.submit();
	});

	//상세보기
	$(".row_table").on("click", function(e) {
		var bno = $(this).children().last().find("input").val();
		console.log("눌렀어요 : " + bno);
		var data = {
			"bno" : Number(bno)
		};
		$.ajax({
			type : "get",
			data : data,
			dataType : "json",
			url : "/board/read",
			success : function(data) {
				console.log(data);
			}

		})
	})
</script>

<%@ include file="../fixSection/footer.jsp"%>
