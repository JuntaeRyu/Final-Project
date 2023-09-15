<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<!-- <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" /> -->
<link rel="icon" href="assets/css/images/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="assets/css/main.css" />
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://accounts.google.com/gsi/client" sync defer>
	//Google api
</script>
<style type="text/css">
a {
	cursor: pointer;
}

label {
	box-sizing: border-box !important;
	transition: ease all 0.5s;
	display: inline-block;
	font-size: 20px;
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

.checkInfo {
	border-radius: 0.3rem;
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
	line-height: inherit !important;
	width: 100% !important;
}

.close:hover, .close:focus {
	color: black;
	text-decoration: none;
	cursor: pointer;
}

.verification-container {
	display: flex;
	align-items: center;
}
</style>
</head>
<body class="is-preload">
	<!-- 아이디 찾기 확인 모달 -->
	<div id="idCheckInfoModal" class="modal">
		<div class="modal-content">
			<form>
				<h3 style="margin: 0px; text-align: center;">아이디 찾기</h3>
				<span class="close">&times;</span>

				<!-- 라디오 버튼 -->
				<label for="contactMethod">찾기 방법 선택: </label>
				<input type="radio" id="phoneRadio" name="contactMethod" value="phone" onclick="showMidPhoneInput()" checked> 전화번호 
				<input type="radio" id="emailRadio" name="contactMethod" value="email" onclick="showMidEmailInput()"> 이메일

				<!-- 핸드폰 번호 입력란 -->
				<div id="phoneMidInputContainer" style="display: block;">
					<label for="phoneNum">핸드폰 번호 확인</label> <input type="text" id="midPhoneInput" class="checkInfo" placeholder="핸드폰 번호를 입력해주세요">
					<div class="verification-container">
						<input id="midPhoneNumVerificationInput" type="text" class="checkInfo" placeholder="인증번호 입력" disabled>
						<button style="width: 35%; height: auto; margin-top: 0px; padding: 0px;" onclick="midPhoneNumVerificationNumSend(event)">인증번호 받기</button>
					</div>
				</div>
				<!-- 이메일 입력란 -->
				<div id="emailMidInputContainer" style="display: none;">
					<label for="email">이메일 확인</label> <input type="text" id="midEmailInput" class="checkInfo" placeholder="이메일을 입력해주세요">
					<div class="verification-container">
						<input id="midEmailVerificationInput" type="text" class="checkInfo" placeholder="인증번호 입력" disabled>
						<button style="width: 35%; height: auto; margin-top: 0px; padding: 0px;" onclick="midEmailVerificationNumSend(event)">인증번호 받기</button>
					</div>
				</div>


				<button onclick="findMidClickbtn()">아이디 찾기</button>
			</form>
		</div>
	</div>
	<!-- 비밀번호 찾기 확인 모달 -->
	<div id="passwordCheckInfoModal" class="modal">
		<div class="modal-content">
			<form>
				<h3 style="margin: 0px; text-align: center;">비밀번호 찾기</h3>
				<span class="close">&times;</span>

				<!-- 라디오 버튼 -->
				<label for="contactMethod">찾기 방법 선택: </label> <input type="radio" id="phoneRadio" name="contactMethod" value="phone" onclick="showPasswordPhoneInput()" checked> 전화번호 <input type="radio" id="emailRadio" name="contactMethod" value="email" onclick="showPasswordEmailInput()"> 이메일

				<!-- 이메일 입력란 -->
				<div id="emailPasswordInputContainer" style="display: none;">
					<label for="mid">아이디 확인</label> <input type="text" class="checkInfo" placeholder="아이디를 입력해주세요" required> <label for="email">이메일 확인</label> <input type="text" id="passwordEmailInput" class="checkInfo" placeholder="이메일을 입력해주세요">
					<div class="verification-container">
						<input id="passwordEmailVerificationInput" type="text" class="checkInfo" placeholder="인증번호 입력" disabled>
						<button style="width: 35%; height: auto; margin-top: 0px; padding: 0px;" onclick="passwordEmailVerificationNumSend(event)">인증번호 받기</button>
					</div>
				</div>

				<!-- 핸드폰 번호 입력란 -->
				<div id="phonePasswordInputContainer" style="display: block;">
					<label for="mid">아이디 확인</label> <input type="text" class="checkInfo" placeholder="아이디를 입력해주세요" required> <label for="phoneNum">핸드폰 번호 확인</label> <input type="text" id="passwordPhoneInput" class="checkInfo" placeholder="핸드폰 번호를 입력해주세요">
					<div class="verification-container">
						<input id="passwordPhoneNumVerificationInput" type="text" class="checkInfo" placeholder="인증번호 입력" disabled>
						<button style="width: 35%; height: auto; margin-top: 0px; padding: 0px;" onclick="passwordPhoneNumVerificationNumSend(event)">인증번호 받기</button>
					</div>
				</div>

				<button onclick="clickbtn()">비밀번호 찾기</button>
			</form>
		</div>
	</div>

	<div id="page-wrapper">

		<!-- Header -->
		<header id="header">
			<div class="logo container">
				<div>
					<h1 id="logo">로그인 페이지</h1>
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

							<!-- Content -->

							<article class="box page-content">
								<h3 style="text-align: right; margin-right: 15%; font-size:15px; ">
									<a id="idCheckInfoBtn"> 아이디 찾기</a> | <a id="passwordCheckInfoBtn"> 비밀번호 찾기</a>
								</h3>
								<section id="loginBox">
									<form action="login.do" method="post">
										<h1>아이디</h1>
										<input type="text" name="mid" placeholder="아이디 입력" required>
										<h1>비밀번호</h1>
										<input type="password" name="mpw" placeholder="비밀번호 입력" required> <input type="submit" value="HealthDuo 로그인">
									</form>
									<div id="container_eAPI">
										<div id="g_id_onload" data-client_id="742345547790-hcm827chb6v7006pdfjcdssef10iu22l.apps.googleusercontent.com" data-callback="handleCredentialResponse"></div>
										<div class="g_id_signin" data-type="standard" data-size="medium" data-text="signin_with" data-shape="rectangular" data-width=185></div>
										<div id="g_id_onload" data-client_id="742345547790-hcm827chb6v7006pdfjcdssef10iu22l.apps.googleusercontent.com" data-callback="handleCredentialResponse"></div>
										<div class="g_id_signin" data-type="standard" data-size="medium" data-text="signin_with" data-shape="rectangular" data-width=185></div>
										<div id="g_id_onload" data-client_id="742345547790-hcm827chb6v7006pdfjcdssef10iu22l.apps.googleusercontent.com" data-callback="handleCredentialResponse"></div>
										<div class="g_id_signin" data-type="standard" data-size="medium" data-text="signin_with" data-shape="rectangular" data-width=185></div>
									</div>
								</section>
							</article>

						</div>
					</div>
					<div class="col-12"></div>
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
	// 이메일 입력란 표시
	function showMidEmailInput() {
        document.getElementById('emailMidInputContainer').style.display = 'block';
        document.getElementById('phoneMidInputContainer').style.display = 'none';
    }
    
    // 전화번호 입력란 표시
    function showMidPhoneInput() {
        document.getElementById('emailMidInputContainer').style.display = 'none';
        document.getElementById('phoneMidInputContainer').style.display = 'block';
    }
	// 이메일 입력란 표시
	function showPasswordEmailInput() {
        document.getElementById('emailPasswordInputContainer').style.display = 'block';
        document.getElementById('phonePasswordInputContainer').style.display = 'none';
    }
    
    // 전화번호 입력란 표시
    function showPasswordPhoneInput() {
        document.getElementById('emailPasswordInputContainer').style.display = 'none';
        document.getElementById('phonePasswordInputContainer').style.display = 'block';
    }
    
	const idCheckInfoOpenBtn = document.getElementById("idCheckInfoBtn");
	const psswordCheckInfoOpenBtn = document.getElementById("passwordCheckInfoBtn");
	
	const idCheckInfoModal = document.getElementById("idCheckInfoModal");
	const passwordCheckInfoModal = document.getElementById("passwordCheckInfoModal");
	
	const idCheckInfoCloseBtn = idCheckInfoModal.querySelector(".close");
	const passwordCheckInfoCloseBtn = passwordCheckInfoModal.querySelector(".close");
	
	const checkInfoName = document.getElementById("checkInfoName");
	const checkInfoPhoneNum = document.getElementById("checkInfoPhoneNum");
	
	
	// 아이디찾기 버튼을 눌렀다면 모달창 생성
    idCheckInfoOpenBtn.addEventListener("click", () => {
    	idCheckInfoModal.style.display = "block";
    });
	
	// 비밀번호찾기  버튼을 눌렀다면 모달창 생성
    psswordCheckInfoOpenBtn.addEventListener("click", () => {
    	passwordCheckInfoModal.style.display = "block";
    });

    // 아이디찾기 모달창의 x버튼을 눌렀다면 모달창 끄기
    idCheckInfoCloseBtn.addEventListener("click", () => {
		const midPhoneInput = document.getElementById("midPhoneInput");
		midPhoneInput.value = ""; 
		const midPhoneNumVerificationInput = document.getElementById('midPhoneNumVerificationInput');
		midPhoneNumVerificationInput.value = ""; 
		midPhoneNumVerificationInput.setAttribute('disabled', true);
		const midEmailInput = document.getElementById("midEmailInput");
		midEmailInput.value = ""; 
		const midEmailVerificationInput = document.getElementById('midEmailVerificationInput');
		midEmailVerificationInput.value = ""; 
		midEmailVerificationInput.setAttribute('disabled', true);
		
    	idCheckInfoModal.style.display = "none";
    });

    // 비밀번호찾기 모달창의 x버튼을 눌렀다면 모달창 끄기
    passwordCheckInfoCloseBtn.addEventListener("click", () => {
		const passwordPhoneInput = document.getElementById("passwordPhoneInput");
		passwordPhoneInput.value = ""; 
		const passwordPhoneNumVerificationInput = document.getElementById('passwordPhoneNumVerificationInput');
		passwordPhoneNumVerificationInput.value = ""; 
		passwordPhoneNumVerificationInput.setAttribute('disabled', true);
		const passwordEmailInput = document.getElementById("passwordEmailInput");
		passwordEmailInput.value = ""; 
		const passwordEmailVerificationInput = document.getElementById('passwordEmailVerificationInput');
		passwordEmailVerificationInput.value = ""; 
		passwordEmailVerificationInput.setAttribute('disabled', true);
		
    	passwordCheckInfoModal.style.display = "none";
    });
    
    ///////////////// 아이디
    let midPhoneCheckNum = null;  // 핸드폰 인증번호를 저장하기위한 공간
    /////// 핸드폰 번호로 아이디 찾기
    // 핸드폰번호로 인증번호 받기
    function midPhoneNumVerificationNumSend(event) {
    	var midPhoneNum = $("#midPhoneInput").val();
    	$.ajax({
            url: 'findMidPhoneServlet.do?phoneNum=' + midPhoneNum,
            type: 'POST',
            success: function(randomNumber){
				midPhoneCheckNum = randomNumber
				console.log('midPhoneCheckNum [' + midPhoneCheckNum + ']');
				var midPhoneNumVerificationInput = document.getElementById('midPhoneNumVerificationInput');
				midPhoneNumVerificationInput.removeAttribute('disabled');
            },
            error: function(error){
               alert("인증번호 발송실패");
            }
         });
    	event.preventDefault();
    }
    
    let midEmailCheckNum = null;  // 이메일 인증번호를 저장하기위한 공간
    /////// 이메일로 아이디 찾기
    // 이메일로 인증번호 받기
    function midEmailVerificationNumSend(event) {
    	var midEmail = $("#midEmailInput").val();
    	$.ajax({
            url: 'findMidEmailServlet.do?email=' + midEmail,
            type: 'POST',
            success: function(randomNumber){
            	midEmailCheckNum = randomNumber
				console.log('midEmailCheckNum [' + midEmailCheckNum + ']');
            	var midEmailVerificationInput = document.getElementById('midEmailVerificationInput');
        		midEmailVerificationInput.removeAttribute('disabled');
            },
            error: function(error){
               alert("인증번호 발송실패");
            }
         });
    	event.preventDefault();
    }
    
    // 아이디 찾기 버튼 눌렀을때 
    function findMidClickbtn(){
    	var midPhoneNumVerificationInput = $("#midPhoneNumVerificationInput").val();
    	var midEmailVerificationInput = $("#midEmailVerificationInput").val();
    	if (midPhoneCheckNum === midPhoneNumVerificationInput){
    		
    	}
    	else if(midEmailCheckNum === midEmailVerificationInput){
    		
    	}
    	else{
    		
    	}
    }
    
    ////////////////// 비밀번호
    // 핸드폰 번호로 비번찾기
    function passwordPhoneNumVerificationNumSend(event) {
    	event.preventDefault();
		var passwordPhoneNumVerificationInput = document.getElementById('passwordPhoneNumVerificationInput');
		passwordPhoneNumVerificationInput.removeAttribute('disabled');
    }
    // 이메일로 비번찾기
    function passwordEmailVerificationNumSend(event) {
    	event.preventDefault();
		var passwordEmailVerificationInput = document.getElementById('passwordEmailVerificationInput');
		passwordEmailVerificationInput.removeAttribute('disabled');
    }
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