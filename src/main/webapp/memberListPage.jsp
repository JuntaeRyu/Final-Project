<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML>
<!--
	TXT by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>HealthDuo</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="icon" href="assets/css/images/favicon.ico"
	type="image/x-icon" />
<link rel="stylesheet" href="assets/css/main.css" />
</head>
<body class="homepage is-preload">
	<div id="page-wrapper">
		<!-- Header -->
		<header id="header">
			<div class="logo container">
				<div>
					<h1 id="logo">회원 목록</h1>
				</div>
			</div>
		</header>

		<!-- Nav -->
		<nav id="nav">
			<ul>
				<nav id="nav1">
					<li id="option1"><a href="main.do" class="icon solid fa-home">
							메인</a></li>
					<li id="option2"><a href="#" class="icon solid fa-comment">
							소식</a>
						<ul>
							<li><a href="noticeListPage.do">공지사항</a></li>
						</ul></li>
					<li id="option3"><a href="#" class="icon solid fa-comments">
							커뮤니티</a>
						<ul>
							<li><a href="boardListPage.do">전체</a></li>
							<li><a href="infoListPage.do">정보</a></li>
							<li><a href="chatListPage.do">잡담</a></li>
						</ul></li>
					<li id="option4"><a href="#" class="icon solid fa-users">
							매칭</a>
						<ul>
							<li><a href="memberList.do">전체회원</a></li>
						</ul></li>
				</nav>
				<c:choose>
					<c:when test="${empty mid}">
						<a href="loginPage.do" class="icon solid fa-lock login"
							value="로그인" title="로그인"> 로그인</a>
						<a href="signupPage.do" class="icon solid fa-user-plus signup"
							value="회원가입" title="회원가입"> 회원가입</a>
					</c:when>
					<c:otherwise>
						<a href="logout.do" class="icon solid fa-lock-open logout"
							value="로그아웃" title="로그아웃"> 로그아웃</a>
						<a href="mypage.do" class="icon solid fa-user mypage"
							value="마이페이지" title="마이페이지"> 마이페이지</a>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>

		<!-- Main -->
		<section id="main">
			<div class="container">
				<div class="row">

					<div class="col-12">

						<!-- Features -->
						<section class="box features">
							<div id="imgBox">
								<div class="row">
									<!-- 이만큼 forEach돌리면됨
									<div class="col-3 col-6-medium col-12-small">
										<section class="box feature">
											<a href="#" class="image featured"><img
												src="images/pic01.jpg" alt="" /></a>
											<h3>
												<a href="#">욕설러</a>
											</h3>
											<p>대충 신고 많이 먹은 사람 프로필</p>
										</section>
									</div>
									 -->
									<div class="col-3 col-6-medium col-12-small">
										<!-- Feature -->
										<section class="box feature">
											<a href="#" class="image featured"><img
												src="images/pic01.jpg" alt="" /></a>
											<h3>
												<a href="#">욕설러</a>
											</h3>
											<p>대충 신고 많이 먹은 사람 프로필</p>
										</section>
									</div>
									<div class="col-3 col-6-medium col-12-small">
										<!-- Feature -->
										<section class="box feature">
											<a href="#" class="image featured"><img
												src="images/pic01.jpg" alt="" /></a>
											<h3>
												<a href="#">욕설러</a>
											</h3>
											<p>대충 신고 많이 먹은 사람 프로필</p>
										</section>
									</div>
									<div class="col-3 col-6-medium col-12-small">
										<!-- Feature -->
										<section class="box feature">
											<a href="#" class="image featured"><img
												src="images/pic01.jpg" alt="" /></a>
											<h3>
												<a href="#">욕설러</a>
											</h3>
											<p>대충 신고 많이 먹은 사람 프로필</p>
										</section>
									</div>
									<div class="col-3 col-6-medium col-12-small">
										<!-- Feature -->
										<section class="box feature">
											<a href="#" class="image featured"><img
												src="images/pic01.jpg" alt="" /></a>
											<h3>
												<a href="#">욕설러</a>
											</h3>
											<p>대충 신고 많이 먹은 사람 프로필</p>
										</section>
									</div>
									<div class="col-3 col-6-medium col-12-small">
										<!-- Feature -->
										<section class="box feature">
											<a href="#" class="image featured"><img
												src="images/pic01.jpg" alt="" /></a>
											<h3>
												<a href="#">욕설러</a>
											</h3>
											<p>대충 신고 많이 먹은 사람 프로필</p>
										</section>
									</div>
									<div class="col-3 col-6-medium col-12-small">
										<!-- Feature -->
										<section class="box feature">
											<a href="#" class="image featured"><img
												src="images/pic01.jpg" alt="" /></a>
											<h3>
												<a href="#">욕설러</a>
											</h3>
											<p>대충 신고 많이 먹은 사람 프로필</p>
										</section>
									</div>
									<div class="col-3 col-6-medium col-12-small">
										<!-- Feature -->
										<section class="box feature">
											<a href="#" class="image featured"><img
												src="images/pic01.jpg" alt="" /></a>
											<h3>
												<a href="#">욕설러</a>
											</h3>
											<p>대충 신고 많이 먹은 사람 프로필</p>
										</section>
									</div>
									<div class="col-3 col-6-medium col-12-small">
										<!-- Feature -->
										<section class="box feature">
											<a href="#" class="image featured"><img
												src="images/pic01.jpg" alt="" /></a>
											<h3>
												<a href="#">욕설러</a>
											</h3>
											<p>대충 신고 많이 먹은 사람 프로필</p>
										</section>
									</div>
									<div class="col-3 col-6-medium col-12-small">
										<!-- Feature -->
										<section class="box feature">
											<a href="#" class="image featured"><img
												src="images/pic01.jpg" alt="" /></a>
											<h3>
												<a href="#">욕설러</a>
											</h3>
											<p>대충 신고 많이 먹은 사람 프로필</p>
										</section>
									</div>
									<div class="col-3 col-6-medium col-12-small">
										<!-- Feature -->
										<section class="box feature">
											<a href="#" class="image featured"><img
												src="images/pic01.jpg" alt="" /></a>
											<h3>
												<a href="#">욕설러</a>
											</h3>
											<p>대충 신고 많이 먹은 사람 프로필</p>
										</section>
									</div>
									<div class="col-3 col-6-medium col-12-small">
										<!-- Feature -->
										<section class="box feature">
											<a href="#" class="image featured"><img
												src="images/pic01.jpg" alt="" /></a>
											<h3>
												<a href="#">욕설러</a>
											</h3>
											<p>대충 신고 많이 먹은 사람 프로필</p>
										</section>
									</div>
									<div class="col-3 col-6-medium col-12-small">
										<!-- Feature -->
										<section class="box feature">
											<a href="#" class="image featured"><img
												src="images/pic01.jpg" alt="" /></a>
											<h3>
												<a href="#">욕설러</a>
											</h3>
											<p>대충 신고 많이 먹은 사람 프로필</p>
										</section>
									</div>
								</div>
							</div>
						</section>

					</div>

					<div class="col-12" style="text-align: right; padding-top: 20px;">
						<button id="moreBtn" class="icon solid fa-plus">더보기</button>
					</div>
				</div>
			</div>
		</section>

	</div>
	<button id="scrollToTop" onclick="scrollToTop()"
		class="icon solid fa-chevron-up"></button>
	<!-- Footer -->
	<footer id="footer">
		<div class="container">
			<div class="row gtr-200">
				<div class="col-12">

					<!-- About -->
					<section>
						<h2 class="major">
							<span>What's this about?</span>
						</h2>
						<p>
							This is <strong>TXT</strong>, yet another free responsive site
							template designed by <a href="http://twitter.com/ajlkn">AJ</a>
							for <a href="http://html5up.net">HTML5 UP</a>. It's released
							under the <a href="http://html5up.net/license/">Creative
								Commons Attribution</a> license so feel free to use it for whatever
							you're working on (personal or commercial), just be sure to give
							us credit for the design. That's basically it :)
						</p>
					</section>

				</div>
				<div class="col-12">

					<!-- Contact -->
					<section>
						<h2 class="major">
							<span>Get in touch</span>
						</h2>
						<ul class="contact">
							<li><a class="icon brands fa-facebook-f" href="#"><span
									class="label">Facebook</span></a></li>
							<li><a class="icon brands fa-twitter" href="#"><span
									class="label">Twitter</span></a></li>
							<li><a class="icon brands fa-instagram" href="#"><span
									class="label">Instagram</span></a></li>
							<li><a class="icon brands fa-dribbble" href="#"><span
									class="label">Dribbble</span></a></li>
							<li><a class="icon brands fa-linkedin-in" href="#"><span
									class="label">LinkedIn</span></a></li>
						</ul>
					</section>

				</div>
			</div>

			<!-- Copyright -->
			<div id="copyright">
				<ul class="menu">
					<li>&copy; Untitled. All rights reserved</li>
					<li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
				</ul>
			</div>

		</div>
	</footer>

	<!-- Scripts -->
	<script type="text/javascript">
		console.log(window.location.pathname);
		function scrollToTop() {
			window.scrollTo({
				top : 0,
				behavior : 'smooth' // 부드러운 스크롤 효과 사용
			});
		}

		// 스크롤 위치에 따라 버튼을 표시 또는 숨깁니다.
		window.onscroll = function() {
			var button = document.getElementById("scrollToTop");
			if (document.body.scrollTop > 20
					|| document.documentElement.scrollTop > 20) {
				button.style.display = "block";
			} else {
				button.style.display = "none";
			}
		};
		// 기존 요소를 복제하고 새로운 요소를 생성하는 함수
		function createNewElement(baseElement) {
			var newElement = baseElement.cloneNode(true);
			return newElement;
		}

		// 더보기 버튼 클릭 이벤트 리스너
		document.getElementById("moreBtn").addEventListener("click", function () {
		    var container = document.getElementById("imgBox");
		    var baseElement = document.querySelector(".col-3.col-6-medium.col-12-small");

		    // 12개의 새로운 칸을 담을 <div class="row"></div>를 생성
		    var newRow = document.createElement("div");
		    newRow.className = "row";

		    // 하나의 요소를 복제하고 12번 추가
		    var newElement = createNewElement(baseElement);
		    for (var j = 0; j < 12; j++) {
		        newRow.appendChild(newElement.cloneNode(true));
		    }

		    // newRow를 container에 추가
		    container.appendChild(newRow);
		});
	</script>
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.dropotron.min.js"></script>
	<script src="assets/js/jquery.scrolly.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/nav.util.js"></script>
	<c:choose>
		<c:when test="${empty mid}">
			<script src="assets/js/main.js"></script>
		</c:when>
		<c:otherwise>
			<script src="assets/js/main2.js"></script>
		</c:otherwise>
	</c:choose>
</body>

</html>