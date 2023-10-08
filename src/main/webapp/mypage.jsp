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
	width: 100%;
}

.modal {
	box-sizing: border-box !important;
	transition: ease all 0.5s;
	display: block;
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

textarea.fixed-width {
	resize: vertical;
	width: 850px;
}
</style>

</head>
<body class="is-preload">
	
	<NPNC:resign />

	<div id="page-wrapper">

		<!-- Header -->
		<header id="header">
			<div class="logo container">
				<div>
					<h1 id="logo">마이페이지</h1>
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
												<a href="ownBoardListPage.do">내가 작성한 글목록</a>
											</h3>
										</article>
									</li>

									<li>
										<article class="box post-summary">
											<h3>
												<a href="ownMatchPage.do">나의 매칭</a>
											</h3>
										</article>
									</li>

									<li>
										<article class="box post-summary">
											<h3>
												<a href="updateProfilePage.do">프로필 변경</a>
											</h3>
										</article>
									</li>
									<li>
										<article class="box post-summary">
											<h3>
												<a href="updateInfoPage.do">정보 변경</a>
											</h3>
										</article>
									</li>
									<li>
										<article class="box post-summary">
											<h3>
												<a id="openModalBtnMember">회원 탈퇴</a>
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

							<article class="box page-content">
								<section id="mypageinfo">
									<h2>기본정보</h2>
									<h3>아이디</h3>
									<section class="info">
										<h1>${mdata.memberID}</h1>
									</section>
									<h3>닉네임</h3>
									<section class="info">
										<h1>${mdata.nickName}</h1>
									</section>
									<h3>이메일</h3>
									<section class="info">
										<h1>${mdata.email}</h1>
									</section>
								</section>
								<section id="mypageinfo" style="padding: 20px 20px 20px 20px;">
									<h3> 누적경고 횟수 : ${mdata.warnCnt}</h3>
									<section class="info">
									<c:forEach var="wdata" items="${wdatas}">
										<c:if test="${wdata.warnType eq 1}">
											<h1 style="margin-bottom: 0;">경고 사유: 부적절한 게시글 신고로 인한 경고 / ${wdata.warnDate}</h1>
										</c:if>
										<c:if test="${wdata.warnType eq 2}">
											<h1 style="margin-bottom: 0;">경고 사유: 부적절한 프로필 신고로 인한 경고 / ${wdata.warnDate} </h1>
										</c:if>
									</c:forEach>
									</section>
								</section>

								<section id="mypageprofile">
								<h3>프로필</h3>
									<c:if test="${not empty mpdata.profileImg}">
										<span class="image featured"><img src="images/profileImg/${mpdata.profileImg}"
												alt="images/default.png" /></span>
									</c:if>
									<c:if test="${empty mpdata.profileImg}">
										<span class="image featured"><img src="images/default.png" alt="images/default.png" /></span>
									</c:if>
								</section>
								<section id="mypageShortIntroduction">
								<h3>한줄 소개글</h3>
									<textarea class="fixed-width" readonly>${mpdata.shortIntro}</textarea>
								</section>
								<section id="mypageintroduction">
								<h3>소개글</h3>
									<textarea class="fixed-width" readonly>${mpdata.intro}</textarea>
								</section>
							</article>

						</div>
					</div>
				</div>
			</div>
		</section>


		<!-- Footer -->
		<NPNC:healthDuo_footer />

	</div>
	<script type="text/javascript">
	 // 회원탈퇴 버튼을 눌렀다면 모달창 생성
    $('#openModalBtnMember').click(function () {
    	$('#deleteMemberModal').show();
    })
	</script>
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
</html>