<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<!-- <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" /> -->
<link rel="icon" href="assets/css/images/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="assets/css/main.css" />
<style>
#title-cell {
	width: 400px; /* 원하는 크기로 조정할 수 있습니다 */
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	margin: 0.5em 0 0.5em 0;
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
					<h1 id="logo">전체 게시판</h1>
				</div>
			</div>
		</header>

		<!-- Nav -->
		<NPNC:healthDuo_nav />

		<!-- Main -->
		<section id="main">
			<div class="container">
				<div class="row">
					<div class="col-12">
						<div class="content">

							<!-- Content -->

							<article class="box page-content">

								<c:if test="${role eq 3}">
									<h3 style="text-align: right; margin-right: 10px;">
										<a href="insertBoardPage.do">글 작성하기</a>
									</h3>
								</c:if>
								<c:if test="${role eq 2}">
									<h3 style="text-align: right; margin-right: 10px;">&nbsp;</h3>
								</c:if>
								<section id="boardListBox">
									<!-- <ul class="meta" >
                                       <li><h3><a href="#">제목</a></h3></li>
                                       <li class="icon solid fa-user">작성자</li>
                                       <li class="icon  fa-clock">작성날짜</li>
                                       <li class="icon solid fa-comments">34</li>
                                       <li class="icon solid fa-eye">조회수</li>
                                       <li class="icon fa-heart">34</li>
                                    </ul>
                                    <hr> -->
									<c:if test="${empty pagedata.currentPageBoards}">
										<h2 style="text-align: center; margin: 0.2em 0 0.2em 0;">현재 게시글이 없습니다</h2>
									</c:if>
									<c:if test="${not empty pagedata.currentPageBoards}">
										<table class="meta">
											<thead>
												<tr class="tab">
													<th><span>목록</span></th>
													<th><span>제목</span></th>
													<th><span>작성자</span></th>
													<th><span>날짜</span></th>
													<th><span>댓글수</span></th>
													<th><span>조회수</span></th>
													<th><span>추천수</span></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="v" items="${pagedata.currentPageBoards}">
													<tr>
														<c:if test="${v.category eq 1 }">
															<td class="icon">정보</td>
														</c:if>
														<c:if test="${v.category eq 2 }">
															<td class="icon">잡담</td>
														</c:if>
														<c:if test="${v.category eq 0 }">
															<td class="icon">공지사항</td>
														</c:if>
														<td class="title"><h1 id="title-cell">
																<a href="boardDetailPage.do?boardNum=${v.boardNum}">${v.title}</a>
															</h1></td>
														<td class="icon solid fa-user nickName"> ${v.nickName}</td>
														<td class="icon fa-clock date"> ${v.boardDate}</td>
														<c:if test="${v.boardCommentsCnt == 0}">
															<td class="icon fa-comments"> ${v.boardCommentsCnt}</td>
														</c:if>
														<c:if test="${v.boardCommentsCnt !=0}">
															<td class="icon solid fa-comments"> ${v.boardCommentsCnt}</td>
														</c:if>
														<c:if test="${v.viewCnt == 0}">
															<td class="icon fa-eye"> ${v.viewCnt}</td>
														</c:if>
														<c:if test="${v.viewCnt !=0}">
															<td class="icon solid fa-eye"> ${v.viewCnt}</td>
														</c:if>
														<c:if test="${v.recommendCnt == 0}">
															<td class="icon fa-heart"> ${v.recommendCnt}</td>
														</c:if>
														<c:if test="${v.recommendCnt != 0}">
															<td class="icon solid fa-heart"> ${v.recommendCnt}</td>
														</c:if>
													</tr>
													<!-- <tr>
                                             <td class="icon"> 정보</td>
                                             <td class="title"><h1><a href="#">asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdf</a></h1></td>
                                             <td class="icon solid fa-user"> 작성자</td>
                                             <td class="icon fa-clock"> 작성날짜</td>
                                             <td class="icon fa-comments"> 34</td>
                                             <td class="icon solid fa-eye"> 조회수</td>
                                             <td class="icon solid fa-heart"> 34</td>
                                          </tr>  -->
												</c:forEach>
											</tbody>
										</table>
										<ul class="pagination">
											<!-- 페이지네이션 내용 추가 -->
											<c:set var="totalPages" value="${(pagedata.totalPosts + pagedata.postPerPage -1) / pagedata.postPerPage}" />
											<c:forEach var="page" begin="1" end="${totalPages}">
												<c:if test="${page eq pagedata.currentPage}">
													<li class="active">${page}</li>
												</c:if>
												<c:if test="${page ne pagedata.currentPage}">
													<li><a href="javascript:void(0)" onclick="changePage(${page})">${page}</a></li>
												</c:if>
											</c:forEach>
										</ul>
									</c:if>
								</section>

							</article>

						</div>
					</div>
					<div class="col-12"></div>
				</div>
			</div>
		</section>

		<!-- Footer -->
		<NPNC:healthDuo_footer />

	</div>

	<!-- Scripts -->
	<script type="text/javascript">
		console.log(window.location.pathname);
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
<script type="text/javascript">
    // JavaScript 함수: 페이지 이동 시 탭 상태를 유지하고 해당 탭의 페이지로 이동하는 함수
    function changePage(page) {
        // 예제에서는 페이지를 새로고침하는 방식으로 처리하였지만, 실제로는 AJAX를 사용하여 비동기적으로 페이지를 변경하는 것이 좋습니다.
        window.location.href = "boardListPage.do?currentPage=" + page;
    }
</script>
</html>