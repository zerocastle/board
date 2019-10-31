<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="container section">
	<h3>회원가입창</h3>
	<div>
		<form action="">
			<div>
				아이디 : <input type="text" placeholder="아이디를 입력">
				<button>아이디 중복 체크</button>
			</div>
			<div>
				비밀번호 : <input type="text" placeholder="비밀번호를 입력">
			</div>
			<div>
				닉넥임 : <input type="text" placeholder="닉네임  입력">
				<button>닉네입 중복 체크</button>
			</div>
		</form>
	</div>
	<div class="result" style="color: red;">회원가입이 아직불가함</div>
</div>

