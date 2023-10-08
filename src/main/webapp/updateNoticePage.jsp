<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="NPNC"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>HealthDuo</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="icon" href="assets/css/images/favicon.ico"
	type="image/x-icon" />
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.15.5/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.15.5/dist/sweetalert2.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body class="homepage is-preload">
	<script
		src="https://cdn.ckeditor.com/ckeditor5/39.0.1/classic/ckeditor.js"></script>
	<script
		src="https://cdn.ckeditor.com/ckeditor5/39.0.1/classic/translations/ko.js"></script>
	<style>
.ck-editor__editable {
	height: 400px;
}

.ck-content {
	font-size: 12px;
}

input {
	margin-top: 10px;
	margin-bottom: 10px;
}

</style>
</head>
<body>
	<div id="page-wrapper">
		<!-- Header -->
		<header id="header">
			<div class="logo container">
				<div>
					<h1 id="logo">공지사항 수정
					</h1>
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
								<section id="signupBox">
									<form action="updateNotice.do" method="POST">
										<input type="hidden" name="boardNum" value="${bdata.boardNum}">
										<c:if test="${bdata.category eq 0 }">
											<input type="text" value="공지사항" disabled>
											<input type="hidden" name="category" value="0">
                                        </c:if>
										<input type="text" name="title" placeholder="제목" value="${bdata.title}" required>
										<textarea name="content" id="editor">${bdata.content}</textarea>
										<input id="editBtn" type="submit" value="수정하기" />
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
	<script type="text/javascript">
	$("form").submit(function (event){
		event.preventDefault();
		
		Swal.fire({
			title: '수정 완료하시겠습니까?',
			icon: 'warning',
			showCancelButton: true,
			confirmButtonText: '확인',
			cancelButtonText: '취소'
		}).then((result) => {
			if (result.isConfirmed) {
				$(this).submit();
			}
		});
	});
	</script>
	<script>
		ClassicEditor.create(document.querySelector('#editor'), {
		  toolbar: ['heading', '|', 'bold', 'italic'],
		  language: 'ko',
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