<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="NPNC"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>HealthDuo</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="icon" href="assets/css/images/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.15.5/dist/sweetalert2.min.css">
</head>
<body class="homepage is-preload">
	<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.15.5/dist/sweetalert2.min.js"></script>
	<script src="https://cdn.ckeditor.com/ckeditor5/38.1.0/classic/ckeditor.js"></script>
	<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/translations/ko.js"></script>
	<script type="module">
    import * as LR from "https://cdn.jsdelivr.net/npm/@uploadcare/blocks@0.25.0/web/lr-file-uploader-regular.min.js";

    LR.registerBlocks(LR);
	</script>
</head>
<body>
	<div id="page-wrapper">
		<!-- Header -->
		<header id="header">
			<div class="logo container">
				<div>
					<h1 id="logo">게시글 작성</h1>
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
							<article class="box page-content">
								<section id="insertBoardBox">
									<form action="insertBoard.do" method="POST">
										<h1>카테고리 선택</h1>
										<div style="text-align: left;">
											<input type="radio" id="info" name="category" value="1" checked> <label for="info">정보</label> <input type="radio" id="chat" name="category" value="2"> <label for="chat">잡담</label>
										</div>
										<input style="width: 100%; padding-right: 10px;" type="text" name="title" placeholder="제목" required>
										<div style="text-align:left">※이미지는 게시글 작성 후 수정할 수 없습니다</div>
										<lr-config
					    					ctx-name="boardImg"
					    					pubkey="da833dfe1dc16760f1e6"
					    					max-local-file-size-bytes="10000000"
					    					multiple-max="1"
					    					img-only="true" ></lr-config>
										<lr-file-uploader-minimal
					   						css-src="https://cdn.jsdelivr.net/npm/@uploadcare/blocks@0.25.0/web/lr-file-uploader-minimal.min.css"
					    					ctx-name="boardImg"
					   						class="my-config" > </lr-file-uploader-minimal>
										<lr-data-output ctx-name="boardImg" use-console use-input use-group use-event ></lr-data-output>
										<textarea name="content" id="editor"></textarea>
										<input type="hidden" name="memberID" value="${memberID}"> <input id="completeBtn" type="submit" value="작성하기" />
										<div id="boardImg"></div>
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
	<script>
	const boardImgContainer = document.getElementById("boardImg");
	const dataOutput = document.querySelector('lr-data-output');
	
	let imageCount = 0;
	
	ClassicEditor.create(document.querySelector("#editor"), {
		toolbar: ['heading', '|', 'bold', 'italic' ],
		language: "ko",
	}).catch((error) => {
		console.error(error);
	});
	
	window.addEventListener('lr-data-output', (event) => {
		boardImgContainer.replaceChildren();
		
		for (var i = 0; i < event.detail.data.files.length; i++) {
        	
			console.log(event.detail.data.files.length);
			console.log(event.detail.data.files[i].cdnUrl);
        	
	    	const boardImg = document.createElement("input");
	    	boardImg.type = "hidden";
	    	boardImg.value = event.detail.data.files[i].cdnUrl;
	    	boardImg.setAttribute("name", "boardImg");
	    	boardImgContainer.appendChild(boardImg);
	    	console.log(boardImg.value);
		}
    });
	
	</script>
	
	<script>
	console.log("1");
	$("form").submit(function (event){
		event.preventDefault();
		console.log("3");
		
		Swal.fire({
			title: '작성완료 하시겠습니까?',
			icon: 'question',
			showCancelButton: true,
			confirmButtonText: '승인',
			cancelButtonText: '취소'
		}).then((result) => {
			if (result.isConfirmed) {
				console.log("4");
				$(this).submit();
			}
		});
	});
	console.log("2");
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