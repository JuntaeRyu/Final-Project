<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
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
					<h1 id="logo">HealthDuo</h1>
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

		<!-- Banner -->
		<c:if test="${empty mid}">
			<section id="bannermember">
				<div class="content">
					<h2>Welcome to HealthDuo</h2>
					<c:choose>
						<c:when test="${not empty mid}">
							<p>${nickname}님환영합니다</p>
						</c:when>
					</c:choose>
					<!-- <a href="#main" class="button scrolly">Alright let's go</a> -->
				</div>
			</section>
		</c:if>
		<c:if test="${not empty mid}">
			<section id="banner">
				<div class="content">
					<h2>Welcome to HealthDuo</h2>
					<c:choose>
						<c:when test="${not empty mid}">
							<p>${nickname}님환영합니다</p>
						</c:when>
					</c:choose>
					<!-- <a href="#main" class="button scrolly">Alright let's go</a> -->
				</div>
			</section>
		</c:if>
		<!-- Main -->
		<section id="main">
			<div class="container">
				<div class="row gtr-200">
					<!-- 여기부터 -->
					<div class="col-12">
						<!-- Highlight -->
						<section class="box highlight">
							<ul class="special">
								<li><a href="ProhibitListPage.do"
									class="icon solid fa-comment-slash" title="신고글 목록"><span
										class="label"></span></a></li>
								<li><a href="boardListPage.do"
									class="icon solid fa-book-open" title="커뮤니티 목록"><span
										class="label"></span></a></li>
								<li><a href="memberManagement.do"
									class="icon solid fa-users" title="회원 관리"><span
										class="label"></span></a></li>
								<li><a href="insertNoticePage.do"
									class="icon solid fa-bullhorn" title="공지사항 작성"><span
										class="label"></span></a></li>
							</ul>
							<header>
								<h2>A random assortment of icons in circles</h2>
								<p>And some text that attempts to explain their significance</p>
							</header>
							<p>
								Phasellus quam turpis, feugiat sit amet ornare in, hendrerit in
								lectus. Praesent semper mod quis eget mi. Etiam eu<br /> ante
								risus. Aliquam erat volutpat. Aliquam luctus et mattis lectus
								amet pulvinar. Nam nec turpis consequat.
							</p>
						</section>
					</div>
					<!-- 여기까지 -->
					<div class="col-12">
						<!-- Features -->
						<section class="box features">
							<h2 class="major">
								<span>매칭 프로필</span>
							</h2>
							<div>
								<div class="row">
									<div class="col-3 col-6-medium col-12-small">
										<!-- Feature -->
										<section class="box feature">
											<a href="#" class="image featured"><img
												src="images/pic01.jpg" alt="" /></a>
											<h3>
												<a href="#" class="icon solid fa-user">${mdata.nickName}</a>
											</h3>
										</section>
									</div>
									<div class="col-3 col-6-medium col-12-small">
										<!-- Feature -->
										<section class="box feature">
											<a href="#" class="image featured"><img
												src="images/pic02.jpg" alt="" /></a>
											<h3>
												<a href="#" class="icon solid fa-user">${mdata.nickName}</a>
											</h3>
										</section>
									</div>
									<div class="col-3 col-6-medium col-12-small">
										<!-- Feature -->
										<section class="box feature">
											<a href="#" class="image featured"><img
												src="images/pic03.jpg" alt="" /></a>
											<h3>
												<a href="#" class="icon solid fa-user">${mdata.nickName}</a>
											</h3>
										</section>
									</div>
									<div class="col-3 col-6-medium col-12-small">
										<!-- Feature -->
										<section class="box feature">
											<a href="#" class="image featured"><img
												src="images/pic04.jpg" alt="" /></a>
											<h3>
												<a href="#" class="icon solid fa-user">${mdata.nickName}</a>
											</h3>
										</section>
									</div>
									<div class="col-12">
										<ul class="actions">
											<li><a href="#" class="button large">매칭하러가기</a></li>
										</ul>
									</div>
								</div>
							</div>
						</section>
					</div>
					<div class="col-12">
						<!-- Blog -->
						<section class="box blog">
							<h2 class="major">
								<span>좋아요 랭킹 순위</span>
							</h2>
							<div>
								<div class="row">
									<div class="col-9 col-12-medium">
										<div class="content">
											<!-- Featured Post -->
											<article class="box post">
												<header>
													<h3 class="icon solid fa-crown" style="color: #fbdf50"></h3>
													<h2><a href="#">글 제목이 들어가겠찌?</a></h2>
													<h1 class="icon solid fa-user">작성자</h1>
													<ul class="meta">
														<li class="icon solid fa-heart">좋아요</li>
														<li class="icon fa-comments"><a href="#">댓글 수가
																들어가야지</a></li>
														<li class="icon fa-clock">15 minutes ago</li>
													</ul>
												</header>
												<a href="#" class="image featured"><img
													src="images/pic05.jpg" alt="" /></a>
												<p>Phasellus quam turpis, feugiat sit amet ornare in, a
													hendrerit in lectus. Praesent semper mod quis eget mi.
													Etiam sed ante risus aliquam erat et volutpat. Praesent a
													dapibus velit. Curabitur sed nisi nunc, accumsan vestibulum
													lectus. Lorem ipsum dolor sit non aliquet sed, tempor et
													dolor. Praesent a dapibus velit. Curabitur accumsan.</p>
												<a href="#" class="button">1등 글 읽기</a>
											</article>
										</div>
									</div>
									<div class="col-3 col-12-medium">
										<div class="sidebar">
											<!-- Archives -->
											<ul class="divided">
												<li>
													<article class="box post-summary">
														<h1 class="icon solid fa-crown" style="color: #9a9a98"></h1>
														<h4>
															<a href="#">글2 제목이 들어가겠찌?</a>
														</h4>
														<h6 class="icon solid fa-user">작성자</h6>
														<ul class="meta">
															<li class="icon solid fa-heart">40</li>
															<li class="icon fa-comments"><a href="#">15</a></li>
															<li class="icon fa-clock">6 hours ago</li>
														</ul>
													</article>
												</li>
												<li>
													<article class="box post-summary">
														<h1 class="icon solid fa-crown" style="color: #92692f"></h1>
														<h4>
															<a href="#">글3 제목이 들어가겠찌?</a>
														</h4>
														<h6 class="icon solid fa-user">작성자</h6>
														<ul class="meta">
															<li class="icon solid fa-heart">30</li>
															<li class="icon fa-comments"><a href="#">16</a></li>
															<li class="icon fa-clock">9 hours ago</li>
														</ul>
													</article>
												</li>
												<li>
													<article class="box post-summary">
														<h4>
															<a href="#">글4 제목이 들어가겠찌?</a>
														</h4>
														<h6 class="icon solid fa-user">작성자</h6>
														<ul class="meta">
															<li class="icon solid fa-heart">20</li>
															<li class="icon fa-comments"><a href="#">4</a></li>
															<li class="icon fa-clock">Yesterday</li>
														</ul>
													</article>
												</li>
												<li>
													<article class="box post-summary">
														<h4>
															<a href="#">글5 제목이 들어가겠찌?</a>
														</h4>
														<h6 class="icon solid fa-user">작성자</h6>
														<ul class="meta">
															<li class="icon solid fa-heart">11</li>
															<li class="icon fa-comments"><a href="#">18</a></li>
															<li class="icon fa-clock">2 days ago</li>
														</ul>
													</article>
												</li>
											</ul>
											<a href="#" class="button alt">전체보기</a>
										</div>
									</div>
								</div>
							</div>
						</section>
					</div>
				</div>
			</div>
		</section>
		<button id="scrollToTop" onclick="scrollToTop()" class="icon solid fa-chevron-up"></button>
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
	</div>

	<!-- Scripts -->
	<script type="text/javascript">
		console.log(window.location.pathname);
		function scrollToTop() {
            window.scrollTo({
                top: 0,
                behavior: 'smooth' // 부드러운 스크롤 효과 사용
            });
        }

        // 스크롤 위치에 따라 버튼을 표시 또는 숨깁니다.
        window.onscroll = function() {
            var button = document.getElementById("scrollToTop");
            if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
                button.style.display = "block";
            } else {
                button.style.display = "none";
            }
        };
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