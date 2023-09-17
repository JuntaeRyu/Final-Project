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
<style>
p {
	margin-bottom: 0;
	margin-right: 20px;
	font-size: 15px;
	font-weight: bold;
}
button {
	height: 50px;
    background-color: #8ec991;
    color: white;
    border: none;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    cursor: pointer;
    border-radius: 4px;
    text-transform: none;
    font-family: inherit;
    line-height: inherit;
    -webkit-appearance: button;
    overflow: visible;
    box-sizing: border-box !important;
    width: 350px;
    margin-top: 10px;
    margin-right: 14px;
    font-size: 1.25em;
    padding: 10px 10px 10px 10px;
}
.verification-container {
	display: flex;
	align-items: center;
}
</style>
</head>
<body class="is-preload">
	<div id="page-wrapper">

		<!-- Header -->
		<header id="header">
			<div class="logo container">
				<div>
					<h1 id="logo">회원가입</h1>
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
								<h1 style="text-align:right;"><i class="icon solid fa-star" title="필수입력" style="color: #ff6c00;"></i> 필수입력입니다</h1>
								<section id="signupBox">
									<form id="signup" action="signup.do" method="post">
										<h1><i class="icon solid fa-star" title="필수입력" style="color: #ff6c00;"></i> 아이디</h1>
										<input type="text" name="memberID" placeholder="아이디 입력" required>
										<p>4~10글자 사이로 입력해주세요(영어,숫자만 입력가능)</p>
										<h1><i class="icon solid fa-star" title="필수입력" style="color: #ff6c00;"></i> 비밀번호</h1>
										<input type="password" name="memberPW" placeholder="비밀번호 입력" required>
										<p>8~13글자 사이로 입력해주세요(영어,숫자만 입력가능)</p>
										<h1><i class="icon solid fa-star" title="필수입력" style="color: #ff6c00;"></i> 닉네임</h1>
										<input type="text" name="nickName" placeholder="닉네임 입력" required>
										<p>최대 12글자</p>
										<h1><i class="icon solid fa-star" title="필수입력" style="color: #ff6c00;"></i> 이름</h1>
										<input type="text" name="name" placeholder="이름 입력" required>
										<p>실명으로 입력해주세요</p>
										<h1><i class="icon solid fa-star" title="필수입력" style="color: #ff6c00;"></i> 핸드폰 번호</h1>
										<div>
										<input id="phoneInput" type="text" name="phoneNum" placeholder="핸드폰 번호 입력"
											required>
										<div class="verification-container">
											<input id="phoneNumVerificationInput" type="text" class="checkInfo" placeholder="인증번호 입력" disabled>
											<button onclick="phoneNumVerificationNumSend(event)">인증번호 받기</button>
										</div>
										</div>
										<p>&nbsp;</p>
										<h1>이메일</h1>
										<input type="email" name="email" placeholder="이메일 입력">
										<p>&nbsp;</p>
										<h1><i class="icon solid fa-star" title="필수입력" style="color: #ff6c00;"></i> 성별</h1>
										<div style="text-align: left; margin-left: 20px;">
											<input type="radio" id="male" name="gender" value="1">
											<label for="male">남자</label>
											<input type="radio" id="female" name="gender" value="2">
											<label for="female">여자</label>
										</div>
										<p>&nbsp;</p>
										<h1><i class="icon solid fa-star" title="필수입력" style="color: #ff6c00;"></i> 주소</h1>
										<div class="verification-container">
											<input type="text" id="sample6_postcode" placeholder="우편번호">
											<button onclick="sample6_execDaumPostcode()">우편번호 찾기</button>
										</div>
										<div class="verification-container">
											<input type="text" id="sample6_address" placeholder="주소"><br>
											<input type="text" id="sample6_extraAddress" name="address" placeholder="상세주소">
										</div>
										<p>원활한 매칭을 위해 동까지만 저장됩니다</p>
										<p>&nbsp;</p>
										<input type="submit" value="회원가입">
									</form>
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
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
	<script>
    function sample6_execDaumPostcode() {
    	event.preventDefault();
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = extraAddr;
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
            }
        }).open();
    }
	</script>
	<script>
	var phoneCheckNum =null;
	function phoneNumVerificationNumSend(event) {
		event.preventDefault();
		
    	var phoneNum = $("#phoneInput").val();
    	$.ajax({
            url: 'signupPhoneCheck.do?phoneNum=' + phoneNum,
            type: 'POST',
            success: function(randomNumber){
				phoneCheckNum = randomNumber
				console.log('phoneCheckNum [' + phoneCheckNum + ']');
				var phoneNumVerificationInput = document.getElementById('phoneNumVerificationInput');
				phoneNumVerificationInput.removeAttribute('disabled');
            },
            error: function(error){
               alert("인증번호 발송실패");
            }
         });
    }
	
	$('#signup').on('submit', function(event) {

	    // 인증번호 확인 로직 추가
	    var enteredVerificationCode = $("#phoneNumVerificationInput").val();

	    if (enteredVerificationCode === phoneCheckNum) {
	        // 인증번호가 일치하면 폼을 서버로 제출
	        return true; // 폼 제출을 허용하고 페이지를 리로드 또는 이동
	    } else {
	        // 인증번호가 일치하지 않으면 사용자에게 메시지를 표시하거나 처리할 로직을 추가하세요
	        alert("인증번호가 일치하지 않습니다.");
	        return false; // 폼 제출을 취소하고 페이지를 리로드 또는 이동하지 않음
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