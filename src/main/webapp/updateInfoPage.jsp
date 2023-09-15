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
<!-- <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />-->
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
	width: 100%
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

textarea.fixed-width {
	resize: vertical;
	width: 850px;
}
</style>

</head>
<body class="is-preload">
	<!-- 회원탈퇴 모달 -->
	<div id="deleteMemberModal" class="modal">
		<div class="modal-content">
			<form>
				<span class="close">&times;</span> <label style="font-size: 25px;"
					for="password">비밀번호 확인</label> <input style="width: 100%"
					type="password" id="passwordMember" placeholder="비밀번호를 입력하세요">
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
					<h1 id="logo">정보 변경
					</h1>
					<p></p>
				</div>
			</div>
		</header>

		<!-- Nav -->
		<nav id="nav">
			<ul>
				<nav id="nav1">
					<li class="current"><a href="main.do"
						class="icon solid fa-home"> 메인</a></li>
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
							<!-- 모달창 >> 정보수정 -->
							<article class="box page-content">
								<section id="mypageinfo">
									<h2 style="color: #bead7c;">기본정보</h2>
									<section
										style="display: flex; justify-content: space-between; align-items: center;"
										class="info">
										<h3>비밀번호</h3>
										<h1>${bdata.mpw}</h1>

										<div id="updatePasswordModal" class="modal">
											<div class="modal-content">
												<form action=".do" method="post">
													<span class="close">&times;</span> <label
														style="font-size: 25px;">비밀번호 변경</label> <input
														style="width: 100%" name="mpw" id="passwordUdate"
														type="password" placeholder="변경할 비밀번호 입력">
													<button type="submit">확인</button>
												</form>
											</div>
										</div>
										<input id="updateBtn1"
											style="display: block; margin-left: auto;" type="submit"
											value="수정">
									</section>

									<section
										style="display: flex; justify-content: space-between; align-items: center;"
										class="info">
										<h3>닉네임</h3>
										<h1>${bdata.nickname}</h1>

										<div id="updateNicknameModal" class="modal">
											<div class="modal-content">
												<form action=".do" method="post">
													<span class="close">&times;</span> <label
														style="font-size: 25px;">닉네임 변경</label> <input
														style="width: 100%" name="nickname" id="nicknameUdate"
														type="text" placeholder="변경할 닉네임 입력">
													<button type="submit">확인</button>
												</form>
											</div>
										</div>

										<input id="updateBtn2"
											style="display: block; margin-left: auto;" type="submit"
											value="수정">
									</section>

									<section
										style="display: flex; justify-content: space-between; align-items: center;"
										class="info">
										<h3>핸드폰번호</h3>
										<h1>${bdata.phone}</h1>

										<div id="updatePhoneModal" class="modal">
											<div class="modal-content">
												<form action=".do" method="post">
													<span class="close">&times;</span> <label
														style="font-size: 25px;">핸드폰번호 변경</label> <input
														style="width: 100%" name="phone" id="phoneUdate"
														type="text" placeholder="변경할 핸드폰번호 입력">
													<button type="submit">확인</button>
												</form>
											</div>
										</div>

										<input id="updateBtn3"
											style="display: block; margin-left: auto;" type="submit"
											value="수정">
									</section>

									<section
										style="display: flex; justify-content: space-between; align-items: center;"
										class="info">
										<h3>이메일</h3>
										<h1>${bdata.email}</h1>

										<div id="updateEmailModal" class="modal">
											<div class="modal-content">
												<form action=".do" method="post">
													<span class="close">&times;</span> <label
														style="font-size: 25px;">이메일 변경</label> <input
														style="width: 100%" name="email" id="emailUdate"
														type="text" placeholder="변경할 이메일 입력">
													<button type="submit">확인</button>
												</form>
											</div>
										</div>

										<input id="updateBtn4"
											style="display: block; margin-left: auto;" type="submit"
											value="수정">
									</section>

									<section
										style="display: flex; justify-content: space-between; align-items: center;"
										class="info">
										<h3>주소</h3>
										<h1>${bdata.address}</h1>

										<div id="updateAddressModal" class="modal">
											<div class="modal-content">
												<form action=".do" method="post">
													<span class="close">&times;</span> <label
														style="font-size: 25px;">주소 변경</label> <input
														style="width: 100%" name="address" id="addressUdate"
														type="text" placeholder="변경할 주소 입력">
													<button type="submit">확인</button>
												</form>
											</div>
										</div>

										<input id="updateBtn5"
											style="display: block; margin-left: auto;" type="submit"
											value="수정">
									</section>
								</section>

								<section id="mypageintroduction">
									<h2 style="position: absolute; color: #bead7c;">소개글</h2>

									<div id="updateTextModal" class="modal">
										<div class="modal-content">
											<form action=".do" method="post">
												<span class="close">&times;</span> <label
													style="font-size: 25px;" for="password">소개글 변경</label>
												<textarea style="margin-top: 10px;">${bdata.text}</textarea>
												<input type="hidden" id="textUdate">
												<button type="submit">확인</button>
											</form>
										</div>
									</div>

									<input id="updateBtn6"
										style="display: fix; margin-left: 82.5%;" type="submit"
										value="수정">
									<textarea class="fixed-width" style="margin-top: 10px;">${bdata.text}</textarea>
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

	<!-- Scripts -->
	<script>
	const updatePasswordModal = document.getElementById("updatePasswordModal");
	const updateNicknameModal = document.getElementById("updateNicknameModal");
	const updatePhoneModal = document.getElementById("updatePhoneModal");
	const updateEmailModal = document.getElementById("updateEmailModal");
	const updateAddressModal = document.getElementById("updateAddressModal");
	const updateTextModal = document.getElementById("updateTextModal");
	const updateBtn1 = document.getElementById("updateBtn1");
	const updateBtn2 = document.getElementById("updateBtn2");
	const updateBtn3 = document.getElementById("updateBtn3");
	const updateBtn4 = document.getElementById("updateBtn4");
	const updateBtn5 = document.getElementById("updateBtn5");
	const updateBtn6 = document.getElementById("updateBtn6");
	
	const openModalBtnMember = document.getElementById("openModalBtnMember");
	
    const passwordModalMember = document.getElementById("deleteMemberModal");
    const checkModal = document.getElementById("checkModal");
    
    const closeBtnMember = passwordModalMember.querySelector(".close");
    const closeBtnUpdate1 = updatePasswordModal.querySelector(".close");
    const closeBtnUpdate2 = updateNicknameModal.querySelector(".close");
    const closeBtnUpdate3 = updatePhoneModal.querySelector(".close");
    const closeBtnUpdate4 = updateEmailModal.querySelector(".close");
    const closeBtnUpdate5 = updateAddressModal.querySelector(".close");
    const closeBtnUpdate6 = updateTextModal.querySelector(".close");
    
    const submitBtnMember = document.getElementById("submitBtnMember");
    const checkBtn = document.getElementById("checkBtn");
    const cancleBtn = document.getElementById("cancleBtn");
    
    const passwordInputMember = document.getElementById("passwordMember");
    const passwordInputUdate = document.getElementById("passwordUdate");
    const nicknameInputUdate = document.getElementById("nicknameUdate");
    const phoneInputUdate = document.getElementById("phoneUdate");
    const emailInputUdate = document.getElementById("emailUdate");
    const addressInputUdate = document.getElementById("addressUdate");
    const textInputUdate = document.getElementById("textUdate");

 	// "수정" 버튼 클릭 이벤트 처리
    updateBtn1.addEventListener("click", () => {
      updatePasswordModal.style.display = "block";
    });
    updateBtn2.addEventListener("click", () => {
    	updateNicknameModal.style.display = "block";
    });
    updateBtn3.addEventListener("click", () => {
    	updatePhoneModal.style.display = "block";
    });
    updateBtn4.addEventListener("click", () => {
    	updateEmailModal.style.display = "block";
    });
    updateBtn5.addEventListener("click", () => {
    	updateAddressModal.style.display = "block";
    });
    updateBtn6.addEventListener("click", () => {
    	updateTextModal.style.display = "block";
    });

    // "수정" 버튼에 대한 모달 닫기 버튼 클릭 이벤트 처리
    closeBtnUpdate1.addEventListener("click", () => {
      updatePasswordModal.style.display = "none";
    });
    closeBtnUpdate2.addEventListener("click", () => {
    	updateNicknameModal.style.display = "none";
    });
    closeBtnUpdate3.addEventListener("click", () => {
    	updatePhoneModal.style.display = "none";
    });
    closeBtnUpdate4.addEventListener("click", () => {
    	updateEmailModal.style.display = "none";
    });
    closeBtnUpdate5.addEventListener("click", () => {
    	updateAddressModal.style.display = "none";
    });
    closeBtnUpdate6.addEventListener("click", () => {
    	updateTextModal.style.display = "none";
    });

    // "회원 탈퇴" 버튼 클릭 이벤트 처리
    openModalBtnMember.addEventListener("click", () => {
      passwordModalMember.style.display = "block";
    });

    // "회원 탈퇴" 모달 닫기 버튼 클릭 이벤트 처리
    closeBtnMember.addEventListener("click", () => {
      passwordModalMember.style.display = "none";
    });

    // "최종 확인" 버튼 클릭 이벤트 처리
    checkBtn.addEventListener("click", () => {
      checkModal.style.display = "none";
      location.href = "deleteMember.do";
    });

    // "취소" 버튼 클릭 이벤트 처리
    cancleBtn.addEventListener("click", () => {
      checkModal.style.display = "none";
    });
    
	 /*    // 수정 버튼을 눌렀다면 모달창 생성
     updateBtn1.addEventListener("click", () => {
    	 updatePasswordModal.style.display = "block";
    });
 	 
    // 모달창의 x버튼을 눌렀다면 모달창 끄기
     closeBtnUpdate.addEventListener("click", () => {
    	 updatePasswordModal.style.display = "none";
     });
    
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
    }); */

    //
   /* submitBtnMember.click(function(){
    	const enteredPassword = passwordInputMember.value;
    	if (enteredPassword === '${mdata.mpw}') { // ${mdata.mpw}
			// 비밀번호가 일치하면 최종확인
			passwordModalMember.style.display = "none";
			checkModal.style.display = "block";
		} else {
			alert("비밀번호가 일치하지 않습니다");
			passwordModalMember.style.display = "none";
		}
    	
    }); */
    
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
    
     /* submitBtnMember.addEventListener("click", () => {
      const enteredPassword = passwordInputMember.value;
      
      if (enteredPassword === '${mdata.mpw}') { // ${mdata.mpw}
			// 비밀번호가 일치하면 최종확인
			passwordModalMember.style.display = "none";
			checkModal.style.display = "block";
		} else {
			alert("비밀번호가 일치하지 않습니다");
			passwordModalMember.style.display = "none";
		}
      
    }); */
    
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