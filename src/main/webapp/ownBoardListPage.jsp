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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.15.5/dist/sweetalert2.min.css">
<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.15.5/dist/sweetalert2.min.js"></script>
<style>
#title-cell {
	width: 250px; /* 원하는 크기로 조정할 수 있습니다 */
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	margin: 0.5em 0 0.5em 0;
}

#checkBoradDelete {
	width: auto;
	text-align: center;
	font-size: medium;
	padding: 0.5em;
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
</style>
</head>
<body class="is-preload">
	<!-- 회원탈퇴 모달 -->
	<div id="deleteMemberModal" class="modal">
		<div class="modal-content">
			<form>
				<span class="close">&times;</span> <label style="font-size: 25px;" for="password">비밀번호 확인</label> <input style="width: 100%" type="password" id="passwordMember" placeholder="비밀번호를 입력하세요">
				<button id="submitBtnMember" onclick="clickbtn()">확인</button>
			</form>
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
					<h1 id="logo">내가 작성한 글</h1>
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

								<c:if test="${not empty memberID}">
									<h3 style="text-align: right; margin-right: 10px;">
										<a href="insertBoardPage.do">글 작성하기</a>
									</h3>
								</c:if>
								<section id="ownBoardListBox">
									<c:if test="${empty bdatas}">
										<h2 style="text-align: center; margin: 0.2em 0 0.2em 0;">현재 게시글이 없습니다</h2>
									</c:if>
									<c:if test="${not empty bdatas}">
										<form action="deleteOwnBoardList.do" method="POST">
											<table class="meta" style="margin-bottom: 0;">
												<thead>
													<tr class="tab">
														<th><span></span></th>
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
													<c:forEach var="v" items="${bdatas}">
														<tr>
															<td style="width: 35px;">
															<input type="checkbox" name="number" value="${v.boardNum}">
															</td>
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
															<td class="icon solid fa-user nickName">${v.nickName}</td>
															<td class="icon fa-clock boardDate">${v.boardDate}</td>
															<c:if test="${v.boardCommentsCnt == 0}">
																<td class="icon fa-comments">${v.boardCommentsCnt}</td>
															</c:if>
															<c:if test="${v.boardCommentsCnt !=0}">
																<td class="icon solid fa-comments">${v.boardCommentsCnt}</td>
															</c:if>
															<c:if test="${v.viewCnt == 0}">
																<td class="icon fa-eye">${v.viewCnt}</td>
															</c:if>
															<c:if test="${v.viewCnt !=0}">
																<td class="icon solid fa-eye">${v.viewCnt}</td>
															</c:if>
															<c:if test="${v.recommendCnt == 0}">
																<td class="icon fa-heart">${v.recommendCnt}</td>
															</c:if>
															<c:if test="${v.recommendCnt != 0}">
																<td class="icon solid fa-heart">${v.recommendCnt}</td>
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
											<input id="checkBoradDelete" type="submit" value="선택글 삭제">
										</form>
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
	$("#checkBoradDelete").click(function (event) {
		event.preventDefault();
		
		Swal.fire({
			title: '정말로 삭제 하시겠습니까?',
			icon: 'warning',
			showCancelButton: true,
			confirmButtonText: '승인',
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
    const passwordInput = document.getElementById("passwordMember");
    
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
   		passwordInput.value = ""; // 입력된 값을 비움
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
        
        if (enteredPassword === '${mdata.memberPW}') {
  			// 비밀번호가 일치하면 최종확인
  			passwordModalMember.style.display = "none";
  			passwordInput.value = ""; // 입력된 값을 비움
  			checkModal.style.display = "block";
  		} else {
  			alert("비밀번호가 일치하지 않습니다");
  			passwordModalMember.style.display = "none";
  			passwordInput.value = ""; // 입력된 값을 비움
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