<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>HealthDuo</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="icon" href="assets/css/images/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="assets/css/main.css" />
</head>
<body class="homepage is-preload">
	<script src="https://cdn.ckeditor.com/ckeditor5/39.0.1/classic/ckeditor.js"></script>
	<script src="https://cdn.ckeditor.com/ckeditor5/39.0.1/classic/translations/ko.js"></script>
</head>
<body>
	<div id="page-wrapper">
		<!-- Header -->
		<header id="header">
			<div class="logo container">
				<div>
					<h1 id="logo">게시글 작성</h1>
				</div>
			</div>
		</header>

		<!-- Nav -->
		<nav id="nav">
			<ul>
				<nav id="nav1">
					<li class="current"><a href="main.do" class="icon solid fa-home"> 메인</a></li>
					<li><a href="#" class="icon solid fa-comment"> 소식</a>
						<ul>
							<li><a href="noticeListPage.do">공지사항</a></li>
						</ul></li>
					<li><a href="#" class="icon solid fa-comments"> 커뮤니티</a>
						<ul>
							<li><a href="boardListPage.do">전체</a></li>
							<li><a href="infoListPage.do">정보</a></li>
							<li><a href="chatListPage.do">잡담</a></li>
						</ul></li>
					<li><a href="#" class="icon solid fa-users"> 매칭</a>
						<ul>
							<li><a href="memberList.do">전체회원</a></li>
						</ul></li>
				</nav>
				<c:choose>
					<c:when test="${empty mid}">
						<a href="loginPage.do" class="icon solid fa-lock login" value="로그인" title="로그인"> 로그인</a>
						<a href="signupPage.do" class="icon solid fa-user-plus signup" value="회원가입" title="회원가입"> 회원가입</a>
					</c:when>
					<c:otherwise>
						<a href="logout.do" class="icon solid fa-lock-open logout" value="로그아웃" title="로그아웃"> 로그아웃</a>
						<a href="mypage.do" class="icon solid fa-user mypage" value="마이페이지" title="마이페이지"> 마이페이지</a>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>
		<!-- Main -->
		<section id="main">
			<div class="container">
				<div class="row">
					<div class="col-12">
						<div class="content">
							<article class="box page-content">
								<section id="insertBoardBox">
									<form action="insertBaord.do" method="POST">
										<h1>카테고리 선택</h1>
										<div style="text-align: left;">
											<input type="radio" id="info" name="category" value="1" checked> <label for="info">정보</label> <input type="radio" id="chat" name="category" value="2"> <label for="chat">잡담</label>
										</div>
										<input style="width: 100%; padding-right: 10px;" type="text" name="title" placeholder="제목">
										<textarea name="content" id="editor"></textarea>
										<input type="hidden" name="mid" value="${mid}"> <input type="submit" value="작성하기" />
									</form>
								</section>
							</article>
						</div>
					</div>
				</div>
			</div>
		</section>

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
								This is <strong>TXT</strong>, yet another free responsive site template designed by <a href="http://twitter.com/ajlkn">AJ</a> for <a href="http://html5up.net">HTML5 UP</a>. It's released under the <a href="http://html5up.net/license/">Creative Commons Attribution</a> license so feel free to use it for whatever you're working on (personal or commercial), just be sure to give us credit for the design. That's basically it :)
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
								<li><a class="icon brands fa-facebook-f" href="#"><span class="label">Facebook</span></a></li>
								<li><a class="icon brands fa-twitter" href="#"><span class="label">Twitter</span></a></li>
								<li><a class="icon brands fa-instagram" href="#"><span class="label">Instagram</span></a></li>
								<li><a class="icon brands fa-dribbble" href="#"><span class="label">Dribbble</span></a></li>
								<li><a class="icon brands fa-linkedin-in" href="#"><span class="label">LinkedIn</span></a></li>
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
	<script>
		ClassicEditor.create(document.querySelector('#editor'), {
		  toolbar: ['heading', '|', 'bold', 'italic'],
		  language: 'ko',
		});
	</script>
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.dropotron.min.js"></script>
	<script src="assets/js/jquery.scrolly.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
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