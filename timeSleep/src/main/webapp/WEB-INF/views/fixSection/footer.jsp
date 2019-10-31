<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="/resources/footer.css" />

<!-- 모달 스크립트 부분 -->
<script src="/resources/js/modalJs/modal.js"></script>

<!-- Site footer -->
<footer class="site-footer">
	<div class="container">
		<div class="row">
			<div class="col-sm-12 col-md-6">
				<h6>About</h6>
				<p class="text-justify">Hello, this is Zerocastle. I want to see
					a movie, but it would not be good to watch it alone. We offer
					services for those people.</p>
			</div>

			<div class="col-xs-6 col-md-3">
				<h6>Categories</h6>
				<ul class="footer-links">
					<li><a href="http://scanfcode.com/category/c-language/">C</a></li>
					<li><a
						href="http://scanfcode.com/category/front-end-development/">UI
							Design</a></li>
					<li><a
						href="http://scanfcode.com/category/back-end-development/">PHP</a></li>
					<li><a
						href="http://scanfcode.com/category/java-programming-language/">Java</a></li>
					<li><a href="http://scanfcode.com/category/android/">Android</a></li>
					<li><a href="http://scanfcode.com/category/templates/">Templates</a></li>
				</ul>
			</div>

			<div class="col-xs-6 col-md-3">
				<h6>Quick Links</h6>
				<ul class="footer-links">
					<li><a href="http://scanfcode.com/about/">About Us</a></li>
					<li><a href="http://scanfcode.com/contact/">Contact Us</a></li>
					<li><a href="http://scanfcode.com/contribute-at-scanfcode/">Contribute</a></li>
					<li><a href="http://scanfcode.com/privacy-policy/">Privacy
							Policy</a></li>
					<li><a href="http://scanfcode.com/sitemap/">Sitemap</a></li>
				</ul>
			</div>
		</div>
		<hr>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-8 col-sm-6 col-xs-12">
				<p class="copyright-text">
					Copyright &copy; 2017 All Rights Reserved by <a href="#">Scanfcode</a>.
				</p>
			</div>


		</div>
	</div>
</footer>


<!-- 모달 내용 -->

<div class="container">

	<div class="modal fade" id="popUpWindow">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- header -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<!-- body -->
				<div class="modal-header">
					<form role="form">
						<div class="form-group">
							<input type="email" id="id" class="form-control" placeholder="id" />
							<input type="password" id="pw" class="form-control"
								placeholder="Password" />
						</div>
					</form>
				</div>
				<div>
					<a href="/member/register">회원가입</a>
				</div>
				<!-- footer -->
				<div class="modal-footer">
					<button class="btn btn-primary btn-block" id="bnt_login_modal">Log
						In</button>
				</div>

			</div>
		</div>
	</div>

</div>

<script>
	$(function() {
		$("#bnt_login_modal").click(function(event) {
			event.preventDefault();
			alert("로그인 버튼 ");
			var data = JSON.stringify({
				id : $('#id').val(),
				pw : $('#pw').val()
			});

			$.ajax({
				type : "POST",
				url : "/member/login",
				data : data,
				dataType : "json",
				contentType : "application/json;charset=UTF-8",
				success : function(data) {
					console.log(data);
					// var reuslt = JSON.parse(data);
					if (data.signal == "success") {
						window.alert("로그인에 성공했습니다.");
						window.location.href = "/";
						console.log(sessionSuccess);

					} else
						window.alert("로그인에 실패 했습니다.");
				}
			})
		});
	})
</script>
</body>
</html>

