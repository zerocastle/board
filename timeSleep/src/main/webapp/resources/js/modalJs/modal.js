///**
// * 모달 부분 자바스크립트
// */
//
//$(function() {
//	$("#bnt_login_modal").click(function(event) {
//		event.preventDefault();
//		alert("로그인 버튼 ");
//		var data = JSON.stringify({
//			id : $('.id').val(),
//			pw : $('.pw').val()
//		});
//
//		$.ajax({
//			type : "POST",
//			url : "/member/login",
//			data : data,
//			dataType : "json",
//			contentType : "application/json;charset=UTF-8",
//			success : function(data) {
//				console.log(data);
//				// var reuslt = JSON.parse(data);
//				if (data.signal == "success") {
//					window.alert("로그인에 성공했습니다.");
//					$('#result').html(data.name);
//					var sessionSuccess = data.sessionName;
//					console.log(sessionSuccess);
//
//				} else
//					window.alert("로그인에 실패 했습니다.");
//			}
//		})
//	});
//})
