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
</head>
<body class="homepage is-preload">
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
									<form action="insertBaord.do" method="POST">
										<h1>카테고리 선택</h1>
										<div style="text-align: left;">
											<input type="radio" id="info" name="category" value="1" checked> <label for="info">정보</label> <input type="radio" id="chat" name="category" value="2"> <label for="chat">잡담</label>
										</div>
										<input style="width: 100%; padding-right: 10px;" type="text" name="title" placeholder="제목" required>
										<lr-config
					    					ctx-name="boardImg"
					    					pubkey="da833dfe1dc16760f1e6"
					    					max-local-file-size-bytes="10000000"
					    					multiple-max="10"
					    					img-only="true" ></lr-config>
										<lr-file-uploader-minimal
					   						css-src="https://cdn.jsdelivr.net/npm/@uploadcare/blocks@0.25.0/web/lr-file-uploader-minimal.min.css"
					    					ctx-name="boardImg"
					   						class="my-config" > </lr-file-uploader-minimal>
										<lr-data-output ctx-name="boardImg" use-console use-input use-group use-event ></lr-data-output>
										<textarea name="content" id="editor"></textarea>
										<input type="hidden" name="memberID" value="${memberID}"> <input type="submit" value="작성하기" />
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