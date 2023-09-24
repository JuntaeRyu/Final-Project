<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="NPNC"%>

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
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>

<style type="text/css">
/* 모달 스타일 */
label {
	text-align: center;
}

button {
	background-color: #4CAF50;
	color: white;
	border: none;
	padding: 8px 16px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 14px;
	margin-top: 10px;
	cursor: pointer;
	border-radius: 4px;
	text-transform: none;
	font-family: inherit;
	line-height: inherit;
	-webkit-appearance: button;
	overflow: visible;
	box-sizing: border-box !important;
}

.modal {
	box-sizing: border-box !important;
	transition: ease all 0.5s;
	display: none;
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: rgba(0, 0, 0, 0.7);
}

.modal-content {
	font-weight: bold;
	background-color: #fff;
	margin: 15% auto;
	padding: 20px;
	border: 1px solid #888;
	max-width: 500px;
	position: relative;
	display: -ms-flexbox;
	display: flex;
	-ms-flex-direction: column;
	flex-direction: column;
	width: 100%;
	pointer-events: auto;
	background-clip: padding-box;
	border-radius: 0.3rem;
	outline: 0;
}

.close {
	color: #aaa;
	text-align: right;
	float: right;
	font-size: 28px;
	font-weight: bold;
	cursor: pointer;
	box-sizing: border-box !important;
	transition: ease all 0.5s;
}

.close:hover, .close:focus {
	color: black;
	text-decoration: none;
	cursor: pointer;
}

a {
	cursor: pointer;
}

#passwordMember {
	border: 1px solid rgba(0, 0, 0, .2);
	border-width: 2px;
	border-style: inset;
	border-color: -internal-light-dark(rgb(118, 118, 118),
		rgb(133, 133, 133));
	border-image: initial;
	box-sizing: border-box !important;
	transition: ease all 0.5s;
	overflow: visible;
	margin: 0;
	font-family: inherit;
	font-size: inherit;
	line-height: inherit;
}

label {
	box-sizing: border-box !important;
	transition: ease all 0.5s;
	display: inline-block;
}

#title-cell {
	width: 300px; /* 원하는 크기로 조정할 수 있습니다 */
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}

ul.pagination {
	list-style: none;
	display: flex;
	justify-content: center;
	align-items: center;
	bottom: 150px;
}

ul.pagination li {
	margin: 5px;
	padding: 8px 12px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

ul.pagination li.active {
	background-color: #007bff;
	color: #fff;
}

ul.pagination li a {
	text-decoration: none;
	color: #007bff;
}
</style>


</head>
<body class="is-preload">

	<div id="page-wrapper">

		<!-- Header -->
		<header id="header">
			<div class="logo container">
				<div>
					<h1 id="logo">관리자 권한 부여</h1>
				</div>
			</div>
		</header>

		<!-- Nav -->
		<NPNC:healthDuo_nav />

		<!-- Main -->
		<section id="main">
			<div class="container">
				<div class="row">
					<div class="col-3 col-12-medium">
						<div class="sidebar">

							<!-- Sidebar -->

							<!-- Recent Posts -->
							<section>
								<h2 class="major">
									<span>사용 목록</span>
								</h2>
								<ul class="divided">
									<li>
										<article class="box post-summary">
											<h3>
												<a class="chooseBtn" href="adminPage.do">전체목록</a>
											</h3>
										</article>
									</li>

									<li>
										<article class="box post-summary">
											<h3>
												<a class="chooseBtn" href="adminPage.do?role=3">회원목록</a>
											</h3>
										</article>
									</li>

									<li>
										<article class="box post-summary">
											<h3>
												<a class="chooseBtn" href="adminPage.do?role=2">관리자목록</a>
											</h3>
										</article>
									</li>

								</ul>
							</section>

						</div>
					</div>
					<div class="col-9 col-12-medium imp-medium">
						<div class="content">

							<!-- Content -->
							<h2 style="color: #bead7c;">검색창</h2>
							<section id="adminInfo">
								<form name="adminPage.do">
									<input type="hidden" name="role" value="${searchRole}">
									<table class="pull-right">
										<tr>
											<td class="adminTd1"><select class="form-control"
												name="searchType">
													<c:forEach var="v" items="${searchMap}">
														<option value="${v.value}">${v.key}</option>
													</c:forEach>
											</select></td>
											<td class="adminTd2"><input type="text" class="form-control" name="searchText" maxlength="100"
												placeholder="검색어를 입력하세요."></td>
											
											<td class="adminTd3"><input type="submit" class="searchBtn" value="검색"></td>
										</tr>
									</table>
								</form>
							</section>

							<hr>

							<section id="memberListBox">
								<c:if test="${fn:length(mdatas) <= 0}">
									<h2 style="text-align: center;">불러올 목록이 없습니다</h2>
								</c:if>
								<c:if test="${fn:length(mdatas) > 0}">
								<table class="meta">
									<thead>
										<tr class="tab">
											<td class="icon solid fa-id-card"><span> 아이디</span></td>
											<td class="icon solid fa-address-card"><span> 이름</span></td>
											<td class="icon fa-address-card"><span> 닉네임</span></td>
											<td class="icon solid fa-phone"><span> 전화번호</span></td>
											<td class="icon solid fa-venus-mars"><span> 성별</span></td>
											<td class="icon solid fa-address-book"><span>
													관리자여부</span></td>
										</tr>
									</thead>
									<tbody id="adminTableBody">
										<c:forEach var="v" items="${mdatas}">
											<tr>
												<td>${v.memberID}</td>
												<td>${v.name}</td>
												<td>${v.nickName}</td>
												<td class="icon solid fa-phone">${v.phoneNum}</td>
												<c:if test="${v.gender eq 1}">
													<td class="icon solid fa-mars">남성</td>
												</c:if>
												<c:if test="${v.gender eq 2}">
													<td class="icon solid fa-venus">여성</td>
												</c:if>
												<c:if test="${v.role eq 2}">
													<td>관리자</td>
												</c:if>
												<c:if test="${v.role eq 3}">
													<td>회원</td>
												</c:if>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								</c:if>
							</section>
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
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.dropotron.min.js"></script>
	<script src="assets/js/jquery.scrolly.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/nav.util.js"></script>
	<c:choose>
		<c:when test="${empty memberID}">
			<script src="assets/js/main.js"></script>
		</c:when>
		<c:otherwise>
			<c:if test="${role eq 3}">
				<script src="assets/js/main2.js"></script>
			</c:if>
			<c:if test="${role eq 2}">
				<script src="assets/js/main3.js"></script>
			</c:if>
		</c:otherwise>
	</c:choose>
</body>
<script type="text/javascript">
	// JavaScript 함수: 페이지 이동 시 탭 상태를 유지하고 해당 탭의 페이지로 이동하는 함수
	function changePage(page) {
		// 예제에서는 페이지를 새로고침하는 방식으로 처리하였지만, 실제로는 AJAX를 사용하여 비동기적으로 페이지를 변경하는 것이 좋습니다.
		window.location.href = "boardListPage.do?page=" + page;
	}
</script>

</html>