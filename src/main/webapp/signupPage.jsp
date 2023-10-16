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
										<input type="text" name="memberID" id="memberIDInput" placeholder="아이디 입력" required>
										<p id="memberIDMessage">4~10글자 사이로 입력해주세요 (영어, 숫자만 입력 가능합니다)</p>
										<h1><i class="icon solid fa-star" title="필수입력" style="color: #ff6c00;"></i> 비밀번호</h1>
										<input type="password" name="memberPW" placeholder="비밀번호 입력" required>
										<p>8~13글자 사이로 입력해주세요(영어,숫자만 입력가능)</p>
										<h1><i class="icon solid fa-star" title="필수입력" style="color: #ff6c00;"></i> 닉네임</h1>
										<input type="text" name="nickName" id="memberNickNameInput" placeholder="닉네임 입력" required>
										<p id="memberNickNameMessage">최대 12글자입니다</p>
										<h1><i class="icon solid fa-star" title="필수입력" style="color: #ff6c00;"></i> 이름</h1>
										<input type="text" name="name" placeholder="이름 입력" required>
										<p>실명으로 입력해주세요</p>
										<h1><i class="icon solid fa-star" title="필수입력" style="color: #ff6c00;"></i> 핸드폰 번호</h1>
										<div>
										<input id="phoneInput" type="text" name="phoneNum" placeholder="핸드폰 번호 입력"
											required>
										<div class="verification-container">
											<input id="phoneNumVerificationInput" type="text" class="checkInfo" placeholder="인증번호 입력" disabled required>
											<button onclick="phoneNumVerificationNumSend(event)">인증번호 받기</button>
										</div>
										</div>
										<p>&nbsp;</p>
										<h1>이메일</h1>
										<input type="email" name="email" id="memberEmailInput" placeholder="이메일 입력">
										<p id="memberEmailMessage">이메일은 필수입력이 아닙니다.</p>
										<h1><i class="icon solid fa-star" title="필수입력" style="color: #ff6c00;"></i> 성별</h1>
										<div style="text-align: left; margin-left: 20px;">
											<input type="radio" id="male" name="gender" value="1" checked>
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
											<input type="text" id="sample6_address" name="address" placeholder="주소"><br>
											<input type="text" id="sample6_extraAddress" name="detailAddress" placeholder="상세주소">
										</div>
										<p>원활한 매칭을 위해 동까지만 저장됩니다</p>
										<p>&nbsp;</p>
										<input id="signupBtn" type="submit" value="회원가입">
									</form>
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

	<!-- Scripts -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.15.5/dist/sweetalert2.min.js"></script>
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
            	if (randomNumber === '1') {
            		swal.fire({
               			title: '전화번호 중복!',
               			html: '이미 해당 번호의 계정이 있습니다<br>다른 번호를 사용해주세요',
               			icon: 'warning',
               			confirmButtonText: '확인'
               		});
            	} else {
					phoneCheckNum = randomNumber
					console.log('phoneCheckNum [' + phoneCheckNum + ']');
					var phoneNumVerificationInput = document.getElementById('phoneNumVerificationInput');
					phoneNumVerificationInput.removeAttribute('disabled');
            	}
            },
            error: function(error){
            	swal.fire({
           			title: '발송실패!',
           			text: '인증번호가 발송되지 않았습니다',
           			icon: 'warning',
           			confirmButtonText: '확인'
           		});
            }
         });
    }
	
	$('form').submit(function (event) {
		event.preventDefault();
		
	    // 인증번호 확인 로직 추가
	    var enteredVerificationCode = $("#phoneNumVerificationInput").val();

	    if (enteredVerificationCode === phoneCheckNum) {
	        // 인증번호가 일치하면 폼을 서버로 제출
	        Swal.fire({
				title: '가입하시겠습니까?',
				icon: 'question',
				showCancelButton: true,
				confirmButtonText: '확인',
				cancelButtonText: '취소'
			}).then((result) => {
				if (result.isConfirmed) {
					$(this).submit();
				}
			});
	    } else {
	        // 인증번호가 일치하지 않으면 사용자에게 메시지를 표시하거나 처리할 로직을 추가하세요
	        Swal.fire({
	        	title: '인증번호가 일치하지 않습니다',
	        	icon: 'warning',
	        	confirmButtonText: '확인'
	        });
	    }
	});
	
	////// 아이디
    var memberIDInput = document.getElementById("memberIDInput");
    var memberIDMessage = document.getElementById("memberIDMessage");
    // input 이벤트를 처리하는 함수를 정의합니다.
    function memberIDInputEvent() {
        var memberIDInputValue = memberIDInput.value; // 입력된 텍스트 가져오기
        var isValid = /^[A-Za-z0-9]{4,10}$/.test(memberIDInputValue); // 영어와 숫자로만 구성된 4~10글자 검증

        if (isValid) {
        	$.ajax({
                url: 'duplicateID.do?memberID=' + memberIDInputValue,
                type: 'POST',
                success: function(result){
                	if(result == 0){
                		memberIDMessage.style.color = "red";
        				memberIDMessage.textContent = "중복된아이디입니다.. 다른아이디를 입력해주세요";
                	}
                	else if(result == 1){
                		memberIDMessage.style.color = "#6B7770";
        				memberIDMessage.textContent = "회원가입 가능!!";
                	}
                },
                error: function(error){
                	swal.fire({
                		title: '잘못된 접근!',
               			icon: 'error',
               			confirmButtonText: '확인'
               		});
                }
             });
        	event.preventDefault();
        } else {
        	memberIDMessage.style.color = "#6B7770";
        	memberIDMessage.textContent = "4~10글자 사이로 입력해주세요 (영어, 숫자만 입력 가능합니다)";
        }
    }
    // input 이벤트 리스너를 등록합니다.
    memberIDInput.addEventListener("input", memberIDInputEvent);
	/////////////////////////////
    /////////////////////////////
    ////// 닉네임
    var memberNickNameInput = document.getElementById("memberNickNameInput");
    var memberNickNameMessage = document.getElementById("memberNickNameMessage");
    // input 이벤트를 처리하는 함수를 정의합니다.
    function nickNameInputEvent() {
        var nickNameInputValue = memberNickNameInput.value; // 입력된 텍스트 가져오기
        var isValid = /^[A-Za-z0-9가-힣]{1,12}$/.test(nickNameInputValue);

        if (isValid) {
        	$.ajax({
                url: 'duplicateNickName.do?nickName=' + nickNameInputValue,
                type: 'POST',
                success: function(result){
                	if(result == 0){
                		memberNickNameMessage.style.color = "red";
                		memberNickNameMessage.textContent = "중복된닉네임입니다.. 다른닉네임을 입력해주세요"; // 유효한 경우 에러 메시지를 지웁니다.
                	}
                	else if(result == 1){
                		memberNickNameMessage.style.color = "#6B7770";
                		memberNickNameMessage.textContent = "회원가입 가능!!"; // 유효한 경우 에러 메시지를 지웁니다.
                	}
                },
                error: function(error){
                	swal.fire({
              			title: '잘못된 접근!',
              			icon: 'error',
              			confirmButtonText: '확인'
              		});
                }
             });
        	event.preventDefault();
        } else {
            memberNickNameMessage.style.color = "#6B7770";
        	memberNickNameMessage.textContent = "최대 12글자입니다";
        }
    }
    // input 이벤트 리스너를 등록합니다.
    memberNickNameInput.addEventListener("input", nickNameInputEvent);
    /////////////////////////////
    /////////////////////////////
    ////// 이메일
    var memberEmailInput = document.getElementById("memberEmailInput");
    var memberEmailMessage = document.getElementById("memberEmailMessage");
    // input 이벤트를 처리하는 함수를 정의합니다.
    function emailInputEvent() {
        var EmailInputValue = memberEmailInput.value; // 입력된 텍스트 가져오기
        var isValid = memberEmailInput.validity.valid; 

        if (isValid) {
        	$.ajax({
                url: 'duplicateEmail.do?email=' + EmailInputValue,
                type: 'POST',
                success: function(result){
                	if(result == 0){
                		memberEmailMessage.style.color = "red";
                		memberEmailMessage.textContent = "중복된이메일입니다.. 다른이메일을 입력해주세요"; 
                	}
                	else if(result == 1){
                		memberEmailMessage.textContent = "회원가입 가능!!";
                	}
                	else if(result == 2){
                		memberEmailMessage.textContent = "이메일은 필수입력이 아닙니다.";
                	}
                },
                error: function(error){
                   swal.fire({
              			title: '잘못된 접근!',
              			icon: 'error',
              			confirmButtonText: '확인'
              		});
                   
                }
             });
        	event.preventDefault();
        } else {
        	memberEmailMessage.textContent = "다시입력해주세요";
        }
    }
    // input 이벤트 리스너를 등록합니다.
    memberEmailInput.addEventListener("input", emailInputEvent);
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