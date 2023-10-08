<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="NPNC"%>

<!DOCTYPE html>
<!--
   TXT by HTML5 UP
   html5up.net | @ajlkn
   Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<style>
.anotherTitle {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	margin: 0.5em 0;
}
</style>
<head>
<title>HealthDuo</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="icon" href="assets/css/images/favicon.ico" type="image/x-icon" />
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
		<NPNC:healthDuo_nav />

		<!-- Banner -->
		<c:if test="${empty memberID}">
			<section id="bannermember">
				<div class="content">
					<h2>Welcome to HealthDuo</h2>
				</div>
			</section>
		</c:if>
		<c:if test="${not empty memberID}">
			<c:choose>
				<c:when test="${role eq 3}">
					<section id="bannermember">
						<div class="content">
							<h2>Welcome to HealthDuo</h2>
							<p>${nickName}님환영합니다</p>
						</div>
					</section>
				</c:when>
				<c:when test="${role eq 2}">
					<section id="banner">
						<div class="content">
							<h2>Welcome to HealthDuo</h2>
							<p>${nickName}관리자님환영합니다</p>
						</div>
					</section>
				</c:when>
			</c:choose>
		</c:if>
		<!-- Main -->
		<section id="main">
			<c:if test="${role ne 2}">
				<NPNC:advertisement />
			</c:if>
			<div class="container">
				<div class="row gtr-200">
					<!-- 여기부터 -->
					<c:if test="${role eq 2}">
						<div class="col-12">
							<!-- Highlight -->
							<section class="box highlight">
								<ul class="special">
									<li><a href="prohibitListPage.do" class="icon solid fa-comment-slash" title="신고글 목록"><span class="label"></span></a></li>
									<li><a href="boardListPage.do" class="icon solid fa-book-open" title="커뮤니티 목록"><span class="label"></span></a></li>
									<li><a href="prohibitMemberListPage.do" class="icon solid fa-users" title="회원 관리"><span class="label"></span></a></li>
									<li><a href="insertNoticePage.do" class="icon solid fa-bullhorn" title="공지사항 작성"><span class="label"></span></a></li>
								</ul>
							</section>
						</div>
					</c:if>
					<!-- 여기까지 -->
					<div class="col-12">
						<!-- Features -->
						<section class="box features">
							<h2 class="major">
								<span>매칭 프로필</span>
							</h2>
							<div>
								<c:if test="${empty mempdatas}">
								현재 가입된 프로필이 없습니다.
							</c:if>
								<div class="row">
									<c:forEach var="mpdata" items="${mempdatas}">
										<div class="col-3 col-6-medium col-12-small">
											<!-- Feature -->
											<section class="box feature">
												<c:if test="${empty mpdata.profileImg}">
													<a href="profileDetailPage.do?profileNum=${mpdata.profileNum}" class="profileimage image featured"> <img src="images/default.png" alt="images/default.png" /></a>
												</c:if>
												<c:if test="${not empty mpdata.profileImg}">
													<a href="profileDetailPage.do?profileNum=${mpdata.profileNum}" class="profileimage image featured"> <img src="images/profileImg/${mpdata.profileImg}" alt="images/default.png" /></a>
												</c:if>
												<h3>
													<a href="profileDetailPage.do?profileNum=${mpdata.profileNum}" class="icon solid fa-user">${mpdata.nickName}</a>
												</h3>
												<c:if test="${empty mpdata.shortIntro}">
													<p>작성된 한줄 소개가 없습니다.</p>
												</c:if>
												<c:if test="${not empty mpdata.shortIntro}">
													<p>${mpdata.shortIntro}</p>
												</c:if>
											</section>
										</div>
									</c:forEach>
									<div class="col-12">
										<ul class="actions">
											<li><a href="matchingPage.do" class="button large">매칭하러가기</a></li>
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
											<c:if test="${not empty firstBdata}">
												<article class="box post">
													<header>
														<h3 class="icon solid fa-crown" style="color: #fbdf50"></h3>
														<h2>
															<a href="boardDetailPage.do?boardNum=${firstBdata.boardNum}">${firstBdata.title}</a>
														</h2>
														<h1 class="icon solid fa-user">${firstBdata.nickName}</h1>
														<ul class="meta">
															<c:if test="${firstBdata.recommendCnt == 0}">
																<li class="icon fa-heart">${firstBdata.recommendCnt}</li>
															</c:if>
															<c:if test="${firstBdata.recommendCnt != 0}">
																<li class="icon solid fa-heart">${firstBdata.recommendCnt}</li>
															</c:if>
															<c:if test="${firstBdata.boardCommentsCnt == 0}">
																<li class="icon fa-comments">${firstBdata.boardCommentsCnt}</li>
															</c:if>
															<c:if test="${firstBdata.boardCommentsCnt != 0}">
																<li class="icon solid fa-comments">${firstBdata.boardCommentsCnt}</li>
															</c:if>
															<li class="icon fa-clock">${firstBdata.boardDate}</li>
														</ul>
													</header>
													<a href="boardDetailPage.do?boardNum=${firstBdata.boardNum}" class="image featured"><img src="${firstBdata.boardImg}" alt="" /></a>
													<p style="white-space: pre-wrap">${firstBdata.content}</p>
													<div style="text-align:right;">
													<a href="boardDetailPage.do?boardNum=${firstBdata.boardNum}" class="button">글 상세보기</a>
													</div>
												</article>
											</c:if>
										</div>
									</div>
									<div class="col-3 col-12-medium">
										<div class="sidebar">
											<!-- Archives -->
											<ul class="divided">
												<c:forEach var="bdata" items="${bdatas}" varStatus="status">
													<c:choose>
														<c:when test="${status.index == 0}">
															<li>
																<article class="box post-summary">
																	<h1 class="icon solid fa-crown" style="color: #9a9a98"></h1>
																	<h4 class="anotherTitle">
																		<a href="boardDetailPage.do?boardNum=${bdata.boardNum}">${bdata.title}</a>
																	</h4>
																	<h6 class="icon solid fa-user">${bdata.nickName}</h6>
																	<ul class="meta">
																		<c:if test="${bdata.recommendCnt == 0}">
																			<li class="icon fa-heart">${bdata.recommendCnt}</li>
																		</c:if>
																		<c:if test="${bdata.recommendCnt != 0}">
																			<li class="icon solid fa-heart">${bdata.recommendCnt}</li>
																		</c:if>
																		<c:if test="${bdata.boardCommentsCnt == 0}">
																			<li class="icon fa-comments">${bdata.boardCommentsCnt}</li>
																		</c:if>
																		<c:if test="${bdata.boardCommentsCnt != 0}">
																			<li class="icon solid fa-comments">${bdata.boardCommentsCnt}</li>
																		</c:if>
																		<li class="icon fa-clock">${bdata.boardDate}</li>
																	</ul>
																</article>
															</li>
														</c:when>
														<c:when test="${status.index == 1}">
															<li>
																<article class="box post-summary">
																	<h1 class="icon solid fa-crown" style="color: #92692f"></h1>
																	<h4 class="anotherTitle">
																		<a href="boardDetailPage.do?boardNum=${bdata.boardNum}">${bdata.title}</a>
																	</h4>
																	<h6 class="icon solid fa-user">${bdata.nickName}</h6>
																	<ul class="meta">
																		<c:if test="${bdata.recommendCnt == 0}">
																			<li class="icon fa-heart">${bdata.recommendCnt}</li>
																		</c:if>
																		<c:if test="${bdata.recommendCnt != 0}">
																			<li class="icon solid fa-heart">${bdata.recommendCnt}</li>
																		</c:if>
																		<c:if test="${bdata.boardCommentsCnt == 0}">
																			<li class="icon fa-comments">${bdata.boardCommentsCnt}</li>
																		</c:if>
																		<c:if test="${bdata.boardCommentsCnt != 0}">
																			<li class="icon solid fa-comments">${bdata.boardCommentsCnt}</li>
																		</c:if>
																		<li class="icon fa-clock">${bdata.boardDate}</li>
																	</ul>
																</article>
															</li>
														</c:when>
														<c:otherwise>
															<li>
																<article class="box post-summary">
																	<h4 class="anotherTitle">
																		<a href="boardDetailPage.do?boardNum=${bdata.boardNum}">${bdata.title}</a>
																	</h4>
																	<h6 class="icon solid fa-user">${bdata.nickName}</h6>
																	<ul class="meta">
																		<c:if test="${bdata.recommendCnt == 0}">
																			<li class="icon fa-heart">${bdata.recommendCnt}</li>
																		</c:if>
																		<c:if test="${bdata.recommendCnt != 0}">
																			<li class="icon solid fa-heart">${bdata.recommendCnt}</li>
																		</c:if>
																		<c:if test="${bdata.boardCommentsCnt == 0}">
																			<li class="icon fa-comments">${bdata.boardCommentsCnt}</li>
																		</c:if>
																		<c:if test="${bdata.boardCommentsCnt != 0}">
																			<li class="icon solid fa-comments">${bdata.boardCommentsCnt}</li>
																		</c:if>
																		<li class="icon fa-clock">${bdata.boardDate}</li>
																	</ul>
																</article>
															</li>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</ul>
											<div style="text-align:center;">
											<a href="boardListPage.do" class="button alt">전체보기</a>
											</div>
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
		<NPNC:healthDuo_footer />

	</div>

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