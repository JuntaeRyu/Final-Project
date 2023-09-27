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
				<input type="radio" class="phoneRadio" name="contactMethod" value="phone" onclick="showMidPhoneInput()" checked> 전화번호 
				<input type="radio" class="emailRadio" name="contactMethod" value="email" onclick="showMidEmailInput()"> 이메일

				<!-- 핸드폰 번호 입력란 -->
				<div id="phoneMidInputContainer" style="display: block;">
					<label for="phoneNum">핸드폰 번호 확인</label> <input type="text" id="midPhoneInput" class="checkInfo" placeholder="핸드폰 번호를 입력해주세요" required>
					<div class="verification-container">
						<input id="midPhoneNumVerificationInput" type="text" class="checkInfo disabled" placeholder="인증번호 입력" disabled>
						<button style="width: 35%; height: auto; margin-top: 0px; padding: 0px;" onclick="midPhoneNumVerificationNumSend(event)">인증번호 받기</button>
					</div>
				</div>
				<!-- 이메일 입력란 -->
				<div id="emailMidInputContainer" style="display: none;">
					<label for="email">이메일 확인</label> <input type="text" id="midEmailInput" class="checkInfo" placeholder="이메일을 입력해주세요" required>
					<div class="verification-container">
						<input id="midEmailVerificationInput" type="text" class="checkInfo disabled" placeholder="인증번호 입력" disabled>
						<button style="width: 35%; height: auto; margin-top: 0px; padding: 0px;" onclick="midEmailVerificationNumSend(event)">인증번호 받기</button>
					</div>
				</div>

				<button onclick="findMidClickbtn(event)">아이디 찾기</button>
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
				<label for="contactMethod">찾기 방법 선택: </label>
				<input type="radio" class="phoneRadio" name="contactMethod" value="phone" onclick="showPasswordPhoneInput()" checked> 전화번호
				<input type="radio" class="emailRadio" name="contactMethod" value="email" onclick="showPasswordEmailInput()"> 이메일

				<!-- 이메일 입력란 -->
				<div id="emailPasswordInputContainer" style="display: none;">
					<label for="memberID">아이디 확인</label> <input type="text" class="checkInfo" id="emailPWInputID" placeholder="아이디를 입력해주세요" required>
					<label for="email">이메일 확인</label> <input type="text" id="passwordEmailInput" class="checkInfo" placeholder="이메일을 입력해주세요">
					<div class="verification-container">
						<input id="passwordEmailVerificationInput" type="text" class="checkInfo disabled" placeholder="인증번호 입력" disabled>
						<button style="width: 35%; height: auto; margin-top: 0px; padding: 0px;" onclick="passwordEmailVerificationNumSend(event)">인증번호 받기</button>
					</div>
				</div>

				<!-- 핸드폰 번호 입력란 -->
				<div id="phonePasswordInputContainer" style="display: block;">
					<label for="memberID">아이디 확인</label> <input type="text" class="checkInfo" id="phonePWInputID" placeholder="아이디를 입력해주세요" required>
					<label for="phoneNum">핸드폰 번호 확인</label> <input type="text" id="passwordPhoneInput" class="checkInfo" placeholder="핸드폰 번호를 입력해주세요">
					<div class="verification-container">
						<input id="passwordPhoneNumVerificationInput" type="text" class="checkInfo disabled" placeholder="인증번호 입력" disabled>
						<button style="width: 35%; height: auto; margin-top: 0px; padding: 0px;" onclick="passwordPhoneNumVerificationNumSend(event)">인증번호 받기</button>
					</div>
				</div>

				<button onclick="findMpwClickbtn(event)">비밀번호 찾기</button>
			</form>
		</div>
	</div>
	
	<!-- 아이디 보여주는 모달 -->
	<div id="showIDModal"class="modal">
		<div class="modal-content">
			<form>
				<h3 style="margin: 0px; text-align: center;">아이디</h3>
				<input style="width: 100%; text-align: center;" type="text" id="showID" class="checkInfo" placeholder="아이디 확인용 모달입니다" readonly>
				<button onclick="closeShowIDModal(event)" >확인</button>
			</form>
			
			<button onclick="showModalUpadtePW()" >비밀번호 재설정</button>
		</div>
	</div>

	<!-- 비밀번호 변경하는 모달 -->
	<div id="updatePWModal"class="modal">
		<div class="modal-content">
			<form>
				<h3 style="margin: 0px; text-align: center;">비밀번호 변경</h3>
				<input type="hidden" id="memberID" class="checkInfo" required>
				<input style="width: 100%" type="text" id="inputPW" class="checkInfo" placeholder="변경할 비밀번호를 입력해주세요" required>
				<button onclick="updatePW(event)" >확인</button>
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
		<NPNC:healthDuo_nav />

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
										<input type="text" name="memberID" placeholder="아이디 입력" required>
										<h1>비밀번호</h1>
										<input type="password" name="memberPW" placeholder="비밀번호 입력" required> <input type="submit" value="HealthDuo 로그인">
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
    /////// 핸드폰 번호로 아이디 찾기
    let PhoneCheckNum = null;  // 핸드폰 인증번호를 저장하기위한 공간
    // 핸드폰번호로 인증번호 받기
    function midPhoneNumVerificationNumSend(event) {
		var params = { phoneNum : $("#midPhoneInput").val() };
    	$.ajax({
            url: 'findIDPhoneCheck.do',
            type: 'POST',
            data: params,
            success: function(randomNumber){
            	PhoneCheckNum = randomNumber;
				console.log('PhoneCheckNum [' + PhoneCheckNum + ']');
				var midPhoneNumVerificationInput = document.getElementById('midPhoneNumVerificationInput');
				midPhoneNumVerificationInput.removeAttribute('disabled');
            },
            error: function(error){
               alert("인증번호 발송실패");
            }
         });
    	event.preventDefault();
    }
    
    /////// 이메일로 아이디 찾기
    let EmailCheckNum = null;  // 이메일 인증번호를 저장하기위한 공간
    // 이메일로 인증번호 받기
    function midEmailVerificationNumSend(event) {
    	var params = { email : $("#midEmailInput").val() }; 
    	$.ajax({
            url: 'findIDEmailCheck.do',
            type: 'POST',
            data: params,
            success: function(randomNumber){
            	EmailCheckNum = randomNumber;
				console.log('EmailCheckNum [' + EmailCheckNum + ']');
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
    function findMidClickbtn(event){
    	var midPhoneNumVerificationInput = $("#midPhoneNumVerificationInput").val();
    	var midEmailVerificationInput = $("#midEmailVerificationInput").val();
    	if (PhoneCheckNum != null && PhoneCheckNum === midPhoneNumVerificationInput){
    		var params = { phoneNum : $("#midPhoneInput").val() };
            var url = 'searchIDPhone.do';
            findMID(url, params);
    	}
    	else if(EmailCheckNum != null && EmailCheckNum === midEmailVerificationInput){
    		var params = { email : $("#midEmailInput").val() };
            var url = 'searchIDEmail.do';
            findMID(url, params);
    	}
    	else if (PhoneCheckNum == null && EmailCheckNum == null) {
			alert("잘못된 접근입니다");
			disabled();
    	}
    	else{
			alert("인증번호가 일치하지 않습니다");
    	}
    	event.preventDefault();
    }

    // 아이디 비동기로 찾아오는 기능
    function findMID (url, params){
    	$.ajax({
            url: url,
            type: 'POST',
            data: params,
            success: function(memberID){
            	if (memberID == null) {
            		alert("일치하는 아이디가 없습니다");
            		clear();
            		modalHide();
            	}
            	else {
            		clear();
            		modalHide();
             		$('#showID').val(memberID);
             		$('#showIDModal').css("display", "block");
            	}
            },
            error: function(error){
				alert("오류가 발생했습니다!");
				clear();
				modalHide();
            }
         });
		disabled();
    }
    
    // 아이디 출력 모달 닫는 기능
    function closeShowIDModal (event) {
 		clear();
		disabled();
		modalHide();
 		event.preventDefault();
    }
    
    // 아이디 출력 후 비밀번호 재설정 할 경우
    function showModalUpadtePW (){
    	$("#memberID").val($('#showID').val());
    	$('#showID').val("");
    	modalHide();
    	$('#updatePWModal').css("display", "block");
    }
    
    ////////////////// 비밀번호
    /////// 핸드폰 번호로 비밀번호 찾기
    // 핸드폰으로 인증번호 받기
    function passwordPhoneNumVerificationNumSend(event) {
    	var passwordPhoneNum = $("#passwordPhoneInput").val();
    	var params = { phoneNum : passwordPhoneNum };
    	$.ajax({
    		url: 'findPWPhoneCheck.do',
    		type: 'POST',
    		data: params,
    		success:function(randomNumber){
    			PhoneCheckNum = randomNumber;
    			console.log('PhoneCheckNum [' + PhoneCheckNum + ']');
				var passwordPhoneNumVerificationInput = document.getElementById('passwordPhoneNumVerificationInput');
				passwordPhoneNumVerificationInput.removeAttribute('disabled');
    		},
    		error: function(error){
                alert("인증번호 발송실패");
            }
    	});
    	event.preventDefault();
    }
    
	/////// 이메일로 비밀번호 찾기
    // 이메일로 인증번호 받기
    function passwordEmailVerificationNumSend(event) {
    	var passwordEmail = $("#passwordEmailInput").val();
    	var params = { email: passwordEmail };
    	$.ajax({
            url: 'findPWEmailCheck.do',
            type: 'POST',
            data: params,
            success: function(randomNumber){
            	EmailCheckNum = randomNumber;
				console.log('EmailCheckNum [' + EmailCheckNum + ']');
				var passwordEmailVerificationInput = document.getElementById('passwordEmailVerificationInput');
				passwordEmailVerificationInput.removeAttribute('disabled');
            },
            error: function(error){
               alert("인증번호 발송실패");
            }
         });
    	event.preventDefault();
    }
    
	// 비밀번호 찾기 버튼 눌렀을때 
    function findMpwClickbtn(event){
    	var mpwPhoneNumVerificationInput = $("#passwordPhoneNumVerificationInput").val();
    	var mpwEmailVerificationInput = $("#passwordEmailVerificationInput").val();
    	if (PhoneCheckNum != null && PhoneCheckNum === mpwPhoneNumVerificationInput){
    		disabled();
    		var url = 'checkMemberPhoneNum.do';
    		var params = {
    				memberID : $('#phonePWInputID').val(),
    				phoneNum : $('#passwordPhoneInput').val()
    		};
    		checkMember(url,params);
    	}
    	else if(EmailCheckNum != null && EmailCheckNum === mpwEmailVerificationInput){
    		var url = 'checkMemberEmail.do';
    		var params = {
    				memberID : $('#emailPWInputID').val(),
    				email : $('#passwordEmailInput').val()
    		};
    		checkMember(url,params);
    	}
    	else if (PhoneCheckNum == null && EmailCheckNum == null) {
			alert("잘못된 접근입니다");
			clear();
    		modalHide();
    		disabled();
    	}
    	else{
			alert("인증번호가  일치하지 않습니다");
    	}
    	event.preventDefault();
    }
	
	// 비밀번호 변경하기 전 계정 확인하는 기능
	function checkMember (url, params) {
		$.ajax({
            url: url,
            type: 'POST',
            data: params,
            success: function(memberID){
            	if (memberID == null) {
            		alert("일치하는 계정이 없습니다");
            		clear();
            		modalHide();
            	}
            	else {
            		clear();
            		modalHide();
            		$("#memberID").val(memberID);
             		$('#updatePWModal').css("display", "block");
            	}
            },
            error: function(error){
				alert("오류가 발생했습니다!");
				clear();
				modalHide();
            }
         });
		disabled();
	}
	
	// 비밀번호 변경 기능
	function updatePW (event) {
		if (PhoneCheckNum == null && EmailCheckNum == null) { // 둘다 비어있다면 인증을 받지 않았음
			alert("잘못된 접근입니다");
    	}
		else {
    		var params = {
    			memberID: $("#memberID").val(),
    			memberPW: $("#inputPW").val()
    		};
    	
			$.ajax({
        	    url: 'updateMemberPWLogin.do',
        	    type: 'POST',
        	    data: params,
        	    success: function(check){
        	    	if (check == null) {
        	    		alert("변경 실패!");
        	    	} else {
        	    		alert("변경되었습니다!");
        	    	}
        	    },
	    	    error: function(error){
	    	    	alert("오류 발생!\n관리자에게 문의해주세요");
	    	    }
	        });
		}
		clear();
		disabled();
		modalHide();
		event.preventDefault();
	}
	
	// 입력값 비우는 기능
	function clear() {
		$('.checkInfo').val("");
	}
	
	// 인증번호 다시 잠구는 기능
	function disabled() {
		$('.disabled').prop('disabled', true);
	}
	
	// 모달창 숨기는 기능
	function modalHide() {
		$('.modal').css("display", "none");
	}
    
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