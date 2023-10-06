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
					<h1 id="logo">프로필 변경</h1>
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

								<section id="mypageprofile">
									<h2 style="color: #bead7c;">프로필</h2>
									<div class="imageContainer">
										<c:if test="${empty mpdata.profileImg}">
											<img class="centered-image" src="images/default.png" alt="images/default.png" /> 
										</c:if>
										<c:if test="${not empty mpdata.profileImg}">
											<img class="centered-image" src="images/profileImg/${mpdata.profileImg}" alt="images/default.png" /> 
										</c:if>
										<i style="font-size: 50px;" id="iconModalBtn" class="icon solid fa-cog" onclick=""></i>
									</div>
									<p class="useId"> ${mpdata.nickName}</p>

									<div id="updateProfileModal" class="modal">
										<div class="update-profile-content">
											<span class="close">&times;</span> <label style="font-size: 25px;"> 프로필 변경</label>
											<form action="updateProfileImg.do" method="post" enctype="multipart/form-data" onsubmit="return confirm('정말 프로필을 변경하시겠습니까?')" id="profileForm">
												<div id="selectedImageContainer">
													<c:if test="${empty mpdata.profileImg}">
														<img id="selectedImage" src="images/default.png"alt="images/default.png">
													</c:if>
													<c:if test="${not empty mpdata.profileImg}">
														<img id="selectedImage" src="images/profileImg/${mpdata.profileImg}"alt="images/default.png">
													</c:if>
												</div>
												<label for="profileImg" class="file-label"> 
													<span class="file-icon">📁</span> 파일 선택 
													<input name="profileImgUpload" id="profileImg" type="file" class="file-input">
												</label>
												<input type="hidden" name="profileNum" value="${ mpdata.profileNum }">
												<button type="submit">수정</button>
											</form>
										</div>
									</div>
								</section>
								<section id="mypageintroduction">
									<h2 style="position: absolute; color: #bead7c;">한줄 소개글</h2>

									<div id="updateShortIntroModal" class="modal">
										<div class="modal-content">
											<form action="updateShortIntro.do" method="post" onsubmit="return confirm('정말 한줄 소개글을 변경하시겠습니까?')">
												<span class="close">&times;</span> <label
													style="font-size: 25px;">한줄 소개글 변경</label>
												<textarea style="margin-top: 10px; resize: vertical; width: 458px;" name="shortIntro">${mpdata.shortIntro}</textarea>
												<input type="hidden" name="profileNum" value="${ mpdata.profileNum }">
												<button style="width:100%" type="submit">확인</button>
											</form>
										</div>
									</div>

									<input id="updateShortIntroBtn"
										style="display: fix; margin-left: 82.5%;" type="submit"
										value="수정">
									<textarea class="fixed-width" style="margin-top: 10px;" readonly>${mpdata.shortIntro}</textarea>
									
								</section>
								<section id="mypageintroduction">
									<h2 style="position: absolute; color: #bead7c;">소개글</h2>

									<div id="updateIntroModal" class="modal">
										<div class="modal-content">
											<form action="updateIntro.do" method="post" onsubmit="return confirm('정말 소개글을 변경하시겠습니까?')">
												<span class="close">&times;</span> <label
													style="font-size: 25px;">소개글 변경</label>
												<textarea style="margin-top: 10px; resize: vertical; width: 458px;" name="intro">${mpdata.intro}</textarea>
												<input type="hidden" name="profileNum" value="${ mpdata.profileNum }">
												<button style="width:100%" type="submit">확인</button>
											</form>
										</div>
									</div>

									<input id="updateIntroBtn"
										style="display: fix; margin-left: 82.5%;" type="submit"
										value="수정">
									<textarea class="fixed-width" style="margin-top: 10px;" readonly>${mpdata.intro}</textarea>
									
								</section>
							</article>
						</div>
					</div>
				</div>
			</div>
		</section>

	</div>

	<!-- Footer -->
		<NPNC:healthDuo_footer />

	<!-- Scripts -->

	<script>
	const openModalBtnMember = document.getElementById("openModalBtnMember");
	const iconModalBtn	= document.getElementById("iconModalBtn");
	const updateProfileModal = document.getElementById("updateProfileModal");
	const updateShortIntroModal = document.getElementById("updateShortIntroModal");
	const updateIntroModal = document.getElementById("updateIntroModal");
	
    const passwordModalMember = document.getElementById("deleteMemberModal");
    const checkModal = document.getElementById("checkModal");
    
    const closeBtnMember = passwordModalMember.querySelector(".close");
    const closeBtnIcon = updateProfileModal.querySelector(".close");
    
    const submitBtnMember = document.getElementById("submitBtnMember");
    const checkBtn = document.getElementById("checkBtn");
    const cancleBtn = document.getElementById("cancleBtn");
    const updateShortIntroBtn = document.getElementById("updateShortIntroBtn");
    const updateIntroBtn = document.getElementById("updateIntroBtn");
    
    const passwordInputMember = document.getElementById("passwordMember");
    const closeShortIntroBtnUpdate = updateShortIntroModal.querySelector(".close");
    const closeIntroBtnUpdate = updateIntroModal.querySelector(".close");
    const textInputUdate = document.getElementById("textUdate");

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
    
    // 한줄 소개글 모달 열었다 닫기
    updateShortIntroBtn.addEventListener("click", () => {
    	updateShortIntroModal.style.display = "block";
    });
    
    closeShortIntroBtnUpdate.addEventListener("click", () => {
    	updateShortIntroModal.style.display = "none";
    });
    // 소개글 모달 열었다 닫기
    updateIntroBtn.addEventListener("click", () => {
    	updateIntroModal.style.display = "block";
    });
    
    closeIntroBtnUpdate.addEventListener("click", () => {
    	updateIntroModal.style.display = "none";
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
        
        if (enteredPassword === '${mdata.memberPW}') { 
  			// 비밀번호가 일치하면 최종확인
  			passwordModalMember.style.display = "none";
  			checkModal.style.display = "block";
  		} else {
  			alert("비밀번호가 일치하지 않습니다");
  			passwordModalMember.style.display = "none";
  		}
    };
    
	/* 첨부된 사진 예시로 볼수있는 코드 */    
    const profileImgInput = document.getElementById("profileImg");
    const selectedImage = document.getElementById("selectedImage");

    profileImgInput.addEventListener("change", function() {
        const file = this.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(event) {
                selectedImage.src = event.target.result;
            };
            reader.readAsDataURL(file);
        } else {
            selectedImage.src = "images/${mpdata.profileImg}";
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