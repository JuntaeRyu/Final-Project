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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.15.5/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.15.5/dist/sweetalert2.min.js"></script>

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

.Abtn {
	width: 200px !important;
	border: none;
	background-color: #4CAF50;
	border-radius: 5px;
	color: white;
	margin-bottom: 10px;
	text-decoration: none !important;
    height: 30px;
    padding: 0px !important;
    font-family: 'Open Sans Condensed', sans-serif;
    font-size: 1.25em;
    font-weight: bold;
	line-height: initial;
}
</style>


</head>
<body class="is-preload">
	<!-- 회원탈퇴 모달 -->
	<div id="deleteMemberModal" class="modal">
		<div class="modal-content">
			<span class="close">&times;</span> <label style="font-size: 25px;"
				for="password">비밀번호 확인</label> <input type="password"
				id="passwordMember" placeholder="비밀번호를 입력하세요">
			<button id="submitBtnMember" onclick="clickbtn()">확인</button>
		</div>
	</div>

	<!-- 회원탈퇴 최종확인 -->
	<div id="checkModal" class="modal">
		<div class="modal-content">
			<label>정말로 탈퇴하시겠습니까?</label>
			<button id="checkBtn">확인</button>
			<button id="cancleBtn">취소</button>
		</div>
	</div>

	<div id="page-wrapper">

		<!-- Header -->
		<header id="header">
			<div class="logo container">
				<div>
					<h1>
						<a href="main.do" id="logo">매칭 리스트</a>
					</h1>
					<p></p>
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
							<article class="box page-content" style="margin-bottom: 2em;">
								<c:set var="receiverTotalItems"
									value="${fn:length(receiverdatas)}" />
								<c:set var="receiverEndRnum" value="3" />
								<!-- Content -->

								<h3>수신 리스트</h3>
								<section id="box features"
									style="display: flex; flex-wrap: wrap; background-color: #f2fde9; margin-bottom: 10px;">
									<c:if test="${empty receiverdatas}">
										<h2 style="text-align: center; margin: 10px auto;">받은
											매칭신청이 없습니다.</h2>
									</c:if>
									<c:if test="${not empty receiverdatas}">
										<c:forEach var="receiverdata" items="${receiverdatas}"
											begin="0" end="${receiverTotalItems}"
											varStatus="receiverStatus">
											<div id="${receiverStatus.index}"
												class="col-3 col-6-medium col-12-small receiverItem"
												style="display: none; margin: 6px; width: 260px; border: 1px solid #b9deb4; border-radius: 10px;">
												<section class="box feature" style="margin-bottom: 1em;">
													<c:if test="${empty receiverdata.profileImg}">
														<a
															href="profileDetailPage.do?profileNum=${receiverdata.profileNum}"
															class="image profileimage featured"> <img
															src="images/default.png" alt="images/default.png" /></a>
													</c:if>
													<c:if test="${not empty receiverdata.profileImg}">
														<a
															href="profileDetailPage.do?profileNum=${receiverdata.profileNum}"
															class="image profileimage featured"> <img
															src="images/profileImg/${receiverdata.profileImg}"
															alt="${receiverdata.profileImg}" /></a>
													</c:if>
													<h3>
														<a
															href="profileDetailPage.do?profileNum=${receiverdata.profileNum}">
															${receiverdata.senderNickName}</a>
													</h3>
													<p>${receiverdata.shortIntro}</p>
												</section>
												<c:if test="${receiverdata.accept eq 0}">
													<div
														style="text-align: center; display: grid; justify-content: center;">
														<form action="acceptMatching.do" method="POST">
															<input type="hidden" name="matchingNum" value="${receiverdata.matchingNum}">
															<input id="acceptBtn" type="submit" class="Abtn" value="수락">
														</form>
														<form action="deleteMatching.do" method="POST">
															<input type="hidden" name="matchingNum" value="${receiverdata.matchingNum}">
															<input type="hidden" name="SearchCondition"	value="거절">
															<input id="refuseBtn" type="submit" class="Abtn" style="background-color: #FA5858;" value="거절">
														</form>

													</div>
												</c:if>
												<c:if test="${receiverdata.accept eq 1}">
													<div
														style="text-align: center; display: grid; justify-content: center;">
														<form action="chat.do" method="POST">
															<input type="submit" class="Abtn" value="채팅방가기">
														</form>
														<form action="deleteMatching.do" method="POST">
															<input type="hidden" name="matchingNum" value="${receiverdata.matchingNum}">
															<input type="hidden" name="SearchCondition"	value="삭제">
															<input id="receiverDeleteBtn" type="submit" class="Abtn" style="background-color: #FA5858;" value="삭제">
														</form>
													</div>
												</c:if>
											</div>
										</c:forEach>
									</c:if>
								</section>
								<div class="col-12"
									style="text-align: right; padding-top: 20px; margin-right: 10px;">
									<button id="receiverMoreBtn" class="icon solid fa-plus">더보기</button>
								</div>
							</article>
							<hr>
							<article class="box page-content" style="margin-bottom: 2em;">
								<!-- Content -->
								<c:set var="senderTotalItems" value="${fn:length(senderdatas)}" />
								<c:set var="senderEndRnum" value="3" />

								<h3>발신 리스트</h3>
								<section id="box features"
									style="display: flex; flex-wrap: wrap; background-color: #f2fde9; margin-bottom: 10px;">
									<c:if test="${empty senderdatas}">
										<h2 style="text-align: center; margin: 10px auto;">보낸
											매칭신청이 없습니다.</h2>
									</c:if>
									<c:if test="${not empty senderdatas}">
										<c:forEach var="senderdata" items="${senderdatas}" begin="0"
											end="${senderTotalItems}" varStatus="senderStatus">
											<div id="${senderStatus.index}"
												class="col-3 col-6-medium col-12-small senderItem"
												style="display: none; margin: 6px; width: 260px; border: 1px solid #b9deb4; border-radius: 10px;">
												<section class="box feature" style="margin-bottom: 1em;">
													<c:if test="${empty senderdata.profileImg}">
														<a
															href="profileDetailPage.do?profileNum=${senderdata.profileNum}"
															class="image profileimage featured"> <img
															src="images/default.png" alt="images/default.png" /></a>
													</c:if>
													<c:if test="${not empty senderdata.profileImg}">
														<a
															href="profileDetailPage.do?profileNum=${senderdata.profileNum}"
															class="image profileimage featured"> <img
															src="images/profileImg/${senderdata.profileImg}"
															alt="${senderdata.profileImg}" /></a>
													</c:if>
													<h3>
														<a
															href="profileDetailPage.do?profileNum=${senderdata.profileNum}">
															${senderdata.receiverNickName}</a>
													</h3>
													<p>${senderdata.shortIntro}</p>
												</section>
												<c:if test="${senderdata.accept eq 0}">
													<div
														style="text-align: center; display: grid; justify-content: center;">
														<a class="Abtn" style="background-color: #6E6E6E;">대기중</a>
														<form action="deleteMatching.do" method="POST" style="text-align: center; display: grid; justify-content: center;">
															<input type="hidden" name="matchingNum" value="${senderdata.matchingNum}">
															<input type="hidden" name="SearchCondition"	value="취소">
															<input id="cancelBtn" type="submit" class="Abtn" style="background-color: #FA5858;" value="취소">
														</form>
													</div>
												</c:if>
												<c:if test="${senderdata.accept eq 1}">
													<form action="chat.do" method="post" style="text-align: center; display: grid; justify-content: center;">
														<input type="submit" class="Abtn" value="채팅방가기">
													</form>
													<form action="deleteMatching.do" method="post" style="text-align: center; display: grid; justify-content: center;">
														<input type="hidden" name="matchingNum"	value="${senderdata.matchingNum}">
														<input type="hidden" name="SearchCondition"	value="삭제">
														<input id="senderDeleteBtn"	type="submit" class="Abtn" style="background-color: #FA5858;" value="삭제">
													</form>
												</c:if>
											</div>
										</c:forEach>
									</c:if>
								</section>
								<div class="col-12"
									style="text-align: right; padding-top: 20px; margin-right: 10px;">
									<button id="senderMoreBtn" class="icon solid fa-plus">더보기</button>
								</div>
							</article>
						</div>
					</div>
				</div>
			</div>
		</section>

		<!-- Footer -->
		<NPNC:healthDuo_footer />

	</div>

	<!-- Scripts -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"
		integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
		crossorigin="anonymous"></script>
	
	<script type="text/javascript">
	$("#acceptBtn").click(function (event) {
		event.preventDefault();
		
		Swal.fire({
			title: '수락 하시겠습니까?',
			icon: 'qeustion',
			showCancelButton: true,
			confirmButtonText: '확인',
			cancelButtonText: '취소'
		}).then((result) => {
			if (result.isConfirmed) {
				$(this).closest('form').submit();
			}
		});
	});
	$("#refuseBtn").click(function (event) {
		event.preventDefault();
		
		Swal.fire({
			title: '거절 하시겠습니까?',
			icon: 'qeustion',
			showCancelButton: true,
			confirmButtonText: '확인',
			cancelButtonText: '취소'
		}).then((result) => {
			if (result.isConfirmed) {
				$(this).closest('form').submit();
			}
		});
	});
	$("#receiverDeleteBtn").click(function (event) {
		event.preventDefault();
		
		Swal.fire({
			title: '삭제 하시겠습니까?',
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
	$("#cancelBtn").click(function (event) {
		event.preventDefault();
		
		Swal.fire({
			title: '취소 하시겠습니까?',
			icon: 'qeustion',
			showCancelButton: true,
			confirmButtonText: '확인',
			cancelButtonText: '취소'
		}).then((result) => {
			if (result.isConfirmed) {
				$(this).closest('form').submit();
			}
		});
	});
	$("#senderDeleteBtn").click(function (event) {
		event.preventDefault();
		
		Swal.fire({
			title: '삭제 하시겠습니까?',
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
	
	<script>
	const openModalBtnMember = document.getElementById("openModalBtnMember");
	
    const passwordModalMember = document.getElementById("deleteMemberModal");
    const checkModal = document.getElementById("checkModal");
    
    const closeBtnMember = passwordModalMember.querySelector(".close");
    
    const submitBtnMember = document.getElementById("submitBtnMember");
    const checkBtn = document.getElementById("checkBtn");
    const cancleBtn = document.getElementById("cancleBtn");
    
    const passwordInputMember = document.getElementById("passwordMember");

    // 회원탈퇴 버튼을 눌렀다면 모달창 생성
    openModalBtnMember.addEventListener("click", () => {
    	passwordModalMember.style.display = "block";
    });

    // 모달창의 x버튼을 눌렀다면 모달창 끄기
    closeBtnMember.addEventListener("click", () => {
   		passwordModalMember.style.display = "none";
    });

    // 최종 확인에서 탈퇴를 선택했다면
    checkBtn.addEventListener("click", () => {
    	checkModal.style.display = "none";
    	location.href = "deleteMember.do";
    });

    // 최종 확인에서 취소를 했다면
    cancleBtn.addEventListener("click", () => {
    	checkModal.style.display = "none";
    });

    function clickbtn(){
    	const enteredPassword = passwordInputMember.value;
        
        if (enteredPassword === '${mdata.mpw}') { // ${mdata.mpw}
  			// 비밀번호가 일치하면 최종확인
  			passwordModalMember.style.display = "none";
  			checkModal.style.display = "block";
  		} else {
  			alert("비밀번호가 일치하지 않습니다");
  			passwordModalMember.style.display = "none";
  		}
    };
    
    // 내가 받은 목록
    $(document).ready(function(){
		var receiverEndRnum = ${receiverEndRnum};
                  
		for (i = 0; i < receiverEndRnum; i++){
			document.getElementsByClassName("col-3 col-6-medium col-12-small receiverItem")[i].style.display="block";
		}
                  
		$('#receiverMoreBtn').click(function(){
			receiverEndRnum += 3;
			if(receiverEndRnum >= ${receiverTotalItems}){
				var con = document.getElementById("receiverMoreBtn");
				con.style.display = "none";
			}
			for (i = 0; i < receiverEndRnum; i++){
				document.getElementsByClassName("col-3 col-6-medium col-12-small receiverItem")[i].style.display="block";
			}
		});
           
           if(receiverEndRnum >= ${receiverTotalItems}){
             var con = document.getElementById("receiverMoreBtn");
             con.style.display = "none";
          }

	});
    
    // 내가 보낸 목록
    $(document).ready(function(){
		var senderEndRnum = ${senderEndRnum};
                  
		for (i = 0; i < senderEndRnum; i++){
			document.getElementsByClassName("col-3 col-6-medium col-12-small senderItem")[i].style.display="block";
		}
                  
		$('#senderMoreBtn').click(function(){
			senderEndRnum += 3;
			if(senderEndRnum >= ${senderTotalItems}){
				var con = document.getElementById("senderMoreBtn");
				con.style.display = "none";
			}
			for (i = 0; i < senderEndRnum; i++){
				document.getElementsByClassName("col-3 col-6-medium col-12-small senderItem")[i].style.display="block";
			}
		});
           
           if(senderEndRnum >= ${senderTotalItems}){
             var con = document.getElementById("senderMoreBtn");
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
<script type="text/javascript">
    // JavaScript 함수: 페이지 이동 시 탭 상태를 유지하고 해당 탭의 페이지로 이동하는 함수
    function changePage(page) {
        // 예제에서는 페이지를 새로고침하는 방식으로 처리하였지만, 실제로는 AJAX를 사용하여 비동기적으로 페이지를 변경하는 것이 좋습니다.
        window.location.href = "boardListPage.do?page=" + page;
    }
</script>
</html>