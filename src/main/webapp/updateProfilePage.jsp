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
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="icon" href="assets/css/images/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="assets/css/main.css" />

<style type="text/css">
/* 프로필 스타일 */
.useId {
	line-height: 0px;
	text-align: center;
	box-sizing: border-box;
	font-size: 35px;
	font-weight: 700;
	color: #1e1e23;
}

.useEmail {
	padding: 2px 20px 8pxpx;
	text-align: center;
	box-sizing: border-box;
	font-size: 25px;
	color: #929294;
}

.useId, .useEmail {
	margin: 0.5em 0;
}

#mypageprofile {
	text-align: center;
	position: relative;
}

.image-container {
	display: flex;
	justify-content: center;
}

.centered-image {
	width: 500px;
	height: auto;
	border-radius: 50%;
}

/* 사진첨부 모달 */
#updateProfileModal {
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

.update-profile-content {
	width: 500px;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	position: relative;
	margin: auto;
	margin-top: 10%;
	padding: 20px;
	background-color: #f9f9f9;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
	text-align: center;
}

.update-profile-content .close {
	position: absolute;
	top: 10px;
	right: 10px;
	font-size: 20px;
	color: #555; /* 변경된 색상 */
	cursor: pointer;
	transition: color 0.3s;
}

.update-profile-content label {
	margin-bottom: 10px;
	font-size: 20px;
	font-weight: bold;
	color: #333; /* 변경된 색상 */
}

.update-profile-content form {
	display: flex;
	flex-direction: column;
	align-items: center;
}

.update-profile-content .file-label {
	display: inline-block;
	background-color: #3498db;
	color: white;
	padding: 10px 20px;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s;
	margin-bottom: 0px;
	align-self: center;
}

.update-profile-content .file-label:hover {
	background-color: #2980b9;
}

.update-profile-content .file-icon {
	margin-right: 10px;
}

.update-profile-content .file-input {
	display: none;
}

.update-profile-content button {
	background-color: #4CAF50;
	color: white;
	border: none;
	padding: 10px 20px;
	margin-top: 20px;
	border-radius: 5px;
	cursor: pointer;
	font-size: 16px;
	transition: background-color 0.3s;
	align-self: center;
	width: 100%;
	box-sizing: border-box;
}

.update-profile-content button:hover {
	background-color: #45a049;
}

/* 이미지 컨테이너 스타일 */
#selectedImageContainer {
	text-align: center;
	margin-bottom: 20px;
	width: 180px;
}

#selectedImage {
	max-width: 180px;
	min-width: 180px;
	max-height: 180px;
	min-height: 180px;
	border: 2px solid #3498db;
	border-radius: 50%;
}

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

/* 첨부파일 css */
.file-label {
	text-align: center;
	width: 180px;
	display: inline-block;
	background-color: #3498db;
	color: white;
	padding: 10px 20px;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.file-label:hover {
	background-color: #2980b9;
}

.icon.solid.fa-cog:hover {
	transform: rotate(45deg);
	transition: transform 0.3s ease;
	cursor: pointer;
}

.file-icon {
	margin-right: 10px;
}

.file-input {
	display: none;
}
</style>

</head>
<body class="is-preload">
	<!-- 회원탈퇴 모달 -->
	<div id="deleteMemberModal" class="modal">
		<div class="modal-content">
			<span class="close">&times;</span> <label style="font-size: 25px;" for="password">비밀번호 확인</label> <input type="password" id="passwordMember" placeholder="비밀번호를 입력하세요">
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
					<h1 id="logo">프로필 변경
					</h1>
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
							<article class="box page-content">

								<section id="mypageprofile">
									<h2 style="color: #bead7c;">프로필</h2>
									<div class="imageContainer">
										<img class="centered-image" src="assets/css/images/log.png.jpg" alt="" /> <i style="font-size: 50px;" id="iconModalBtn" class="icon solid fa-cog" onclick=""></i>
									</div>
									<p class="useId">준게이</p>
									<p class="useEmail" style="margin-bottom: 50px;">jungay@gaygay.gay</p>

									<div id="updateProfileModal" class="modal">
										<div class="update-profile-content">
											<span class="close">&times;</span> <label style="font-size: 25px;"> 프로필 변경</label>
											<form action=".do" method="post" enctype="multipart/form-data">
												<div id="selectedImageContainer">
													<img id="selectedImage" src="images/default.png" alt="프로필이미지">
												</div>
												<label for="profileUpdate" class="file-label"> <span class="file-icon">📁</span> 파일 선택 <input name="file" id="profileUpdate" type="file" class="file-input">
												</label>
												<button type="submit">수정</button>
											</form>
										</div>
									</div>

								</section>
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
	const openModalBtnMember = document.getElementById("openModalBtnMember");
	const iconModalBtn	= document.getElementById("iconModalBtn");
	const updateProfileModal = document.getElementById("updateProfileModal");
	
    const passwordModalMember = document.getElementById("deleteMemberModal");
    const checkModal = document.getElementById("checkModal");
    
    const closeBtnMember = passwordModalMember.querySelector(".close");
    const closeBtnIcon = updateProfileModal.querySelector(".close");
    
    const submitBtnMember = document.getElementById("submitBtnMember");
    const checkBtn = document.getElementById("checkBtn");
    const cancleBtn = document.getElementById("cancleBtn");
    
    const passwordInputMember = document.getElementById("passwordMember");

    // icon 클릭시 모달창 생성
    iconModalBtn.addEventListener("click", () => {
    	updateProfileModal.style.display = "block";
    });

    closeBtnIcon.addEventListener("click", () => {
    	updateProfileModal.style.display = "none";
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
    });

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
    
    
	/* 첨부된 사진 예시로 볼수있는 코드 */    
    const profileUpdateInput = document.getElementById("profileUpdate");
    const selectedImage = document.getElementById("selectedImage");

    profileUpdateInput.addEventListener("change", function() {
        const file = this.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(event) {
                selectedImage.src = event.target.result;
            };
            reader.readAsDataURL(file);
        } else {
            selectedImage.src = "images/default.png";
        }
    });
    
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