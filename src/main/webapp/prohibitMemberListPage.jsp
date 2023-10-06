<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="icon" href="assets/css/images/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.15.5/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.15.5/dist/sweetalert2.min.js"></script>
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
		<NPNC:healthDuo_nav />

		<!-- Main -->
		<section id="main">
			<div class="container">
				<div class="row">

					<div class="col-12">

						<!-- Features -->
						<section class="box features">
							<div id="imgBox">
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
									<c:set var="totalItems" value="${fn:length(mpdatas)}" />
									<c:set var="endRnum" value="8" />
									<c:if test="${fn:length(mpdatas) <=0}">
										<h3>현재 신고된 회원이 없습니다.</h3>
									</c:if>
									<c:if test="${fn:length(mpdatas) > 0}">
									<form action="deleteMemberProhibitList.do" method="POST">
									<div class="row">
									<c:forEach var="mpdata" items="${mpdatas}" begin="0" end="${totalItems}" varStatus="status">
										<div id="${status.index}" class="col-3 col-6-medium col-12-small mpdataItem" style="display: none;">
											<section class="box feature">
												<c:if test="${empty mpdata.profileImg}">
													<a href="profileDetailPage.do?profileNum=${mpdata.profileNum}" class="image profileimage featured" style="margin:0 0 1em 0;">
													<img src="images/default.png" alt="images/default.png" /></a>
												</c:if>
												<c:if test="${not empty mpdata.profileImg}">
													<a href="profileDetailPage.do?profileNum=${mpdata.profileNum}" class="image profileimage featured" style="margin:0 0 1em 0;">
													<img src="images/profileImg/${mpdata.profileImg}" alt="${mpdata.profileImg}" /></a>
												</c:if>
												<h3>
													<input type="checkbox" name="number" style="display:inline" value="${mpdata.profileNum}">
													<a href="profileDetailPage.do?profileNum=${mpdata.profileNum}"> ${mpdata.nickName}</a>
												</h3>
											</section>
										</div>
									</c:forEach>
									</div>
									<div class="col-12" style="text-align: right; padding-top: 20px;">
										<input id="profileReset" type="submit" value="프로필 초기화">
									</div>
									</form>
									</c:if>
							</div>
						</section>
					</div>
					<c:if test="${fn:length(mpdatas) > 8}">
						<div class="col-12" style="text-align: right; padding-top: 20px;">
							<button id="moreBtn" class="icon solid fa-plus">더보기</button>
						</div>
					</c:if>
				</div>
			</div>
		</section>

	</div>
	<button id="scrollToTop" onclick="scrollToTop()" class="icon solid fa-chevron-up"></button>
	<!-- Footer -->
		<NPNC:healthDuo_footer />

	<!-- Scripts -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
	
	<script type="text/javascript">
	$("#profileReset").click(function (event){
		event.preventDefault();
		
		Swal.fire({
			title: '정말로 리셋 하시겠습니까?',
			icon: 'warning',
			showCancelButton: true,
			confirmButtonText: '확인',
			cancelButtonText: '취소'
		}).then((result) => {
			if (result.isConfirmed) {
				$(this).closest('form').submit();
			}
		});
	});
	</script>
	
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

         $(document).ready(function(){
			var endRnum = ${endRnum};
                      
			for (i = 0; i < endRnum; i++){
				document.getElementsByClassName("col-3 col-6-medium col-12-small mpdataItem")[i].style.display="block";
			}
                      
			$('#moreBtn').click(function(){
				endRnum += 8;
				if(endRnum >= ${totalItems}){
					var con = document.getElementById("moreBtn");
					con.style.display = "none";
				}
				for (i = 0; i < endRnum; i++){
					document.getElementsByClassName("col-3 col-6-medium col-12-small mpdataItem")[i].style.display="block";
				}
                  
			});
               
               if(endRnum >= ${totalItems}){
                 var con = document.getElementById("moreBtn");
                 con.style.display = "none";
              }

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