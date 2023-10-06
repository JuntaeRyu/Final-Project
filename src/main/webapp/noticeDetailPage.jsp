<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="NPNC"%>

<!DOCTYPE HTML>
<!--
	TXT by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<style>
.anotherTitle {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	margin: 0.5em 0;
}

#topContent {
	overflow: hidden;
	text-overflow: ellipsis;
	margin: 0.5em 0;
	display: -webkit-box;
	-webkit-line-clamp: 3; /* 여기서 숫자를 조절하여 표시할 줄 수를 지정할 수 있습니다 */
	-webkit-box-orient: vertical;
	overflow: hidden;
}

i {
	cursor: pointer;
}

.insertReplyBtn {
	text-align: center;
	width: 100px;
	font-size: 12px;
	padding: 10px;
	margin-bottom: 7px;
	margin-right: 12px;
}
</style>
<head>
<title>HealthDuo</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="icon" href="assets/css/images/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="assets/css/main.css" />
</head>
<body class="is-preload">
	<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
	<div id="page-wrapper">

		<!-- Header -->
		<header id="header">
			<div class="logo container">
				<div>
					<h1 id="logo">공지사항</h1>
				</div>
			</div>
		</header>

		<!-- Nav -->
		<NPNC:healthDuo_nav />

		<!-- Main -->
		<section id="main">
			<div class="container">
				<div class="row">
					<div class="col-3 col-12-medium" style="padding-left: 20px;">
						<NPNC:sidebarNotice />
					</div>
					<div class="col-9 col-12-medium imp-medium">
						<div class="content">

							<!-- Content -->
							<article class="box page-content">
								<section id="datailBoardBox">
									<c:if test="${memberID eq bdata.memberID }">
										<i id="boardButton" class="icon solid fa-bars"></i>
									</c:if>
									<ul id="menuList">
										<li><form action="updateNoticePage.do" method="post">
											<input type="hidden" name="boardNum" value="${bdata.boardNum}" />
											<button type="submit" style="background: none; border: none; text-decoration: none; color: #6b7770;">
												수정</button>
										</form></li>
										
										<li>
										<form action="deleteNotice.do" method="post">
											<input type="hidden" name="boardNum" value="${bdata.boardNum}" />
											<button type="submit" style="background: none; border: none; text-decoration: none; color: #6b7770;">
												삭제</button>
										</form>
										</li>
									</ul>
									<header>
										<p id="boardTitle">${bdata.title}</p>
										<ul class="meta">
											<li class="icon solid fa-user">${bdata.nickName}</li>
											<li class="icon fa-clock">${bdata.boardDate}</li>
											<c:if test="${not empty memberID}">
												<li><i id="rc" class="icon fa-heart" style="color: #f22202;" title="추천"></i> 
												<p class="cnt" style="display: inline-block;">${bdata.recommendCnt}</p>
												</li>
											</c:if>
											<!-- <li class="icon fa-comments">${csdatas.size()}</li> -->
										</ul>
									</header>
									<!-- 
								<section>
									<!-- 이미지 변수 아직 안만들어서 냅둠
									<span class="image featured"><img src="images/pic05.jpg"
										alt="" /></span>
								</section>
								 -->
									<section>
										<div id="editor">
											<p>${bdata.content}</p>
										</div>
									</section>
								</section>
								<!-- 댓글 작성 -->
								<c:if test="${not empty memberID}">
									<section id="insertCommentBox">
										<form id="insertComment" action="insertComment.do" method="POST">
											<input type="hidden" name="boardNum" value="${bdata.boardNum}"> <input type="text" name="comments" placeholder="댓글 작성 내용" required> <input style="width: 80px;" type="submit" value="작성">
										</form>
									</section>
								</c:if>
								<!-- 댓글 -->
								<section id="commentBox">
									<c:if test="${empty cdatas}">
										<p>현재 작성된 댓글이 없습니다</p>
									</c:if>
									<c:if test="${not empty cdatas}">
										<c:forEach var="cdata" items="${cdatas}">
											<ul class="meta" style="text-align: left;">
												<li class="icon solid fa-user">${cdata.nickName}</li>
												<li class="icon fa-clock">${cdata.commentsDate }</li>
											</ul>
											<h1>${cdata.comments}</h1>
											<ul class="meta">
												<li class="icon fa-heart">댓글 추천수 변수 없음</li>
												<li class="icon solid fa-ban">${cdata.prohibitCnt}</li>
											</ul>

											<!-- 대댓글 -->
											<c:forEach var="rdata" items="${rdatas}">
												<c:if test="${cdata.commentsNum eq rdata.commentsNum}">
													<p id="replyIcon" class="icon solid fa-reply"></p>
													<section id="replyBox">
														<h1>${rdata.reply}</h1>
														<ul class="meta">
															<li class="icon solid fa-user">${rdata.nickName}</li>
															<li class="icon fa-clock">${rdata.replyDate}</li>
														</ul>
													</section>
												</c:if>
											</c:forEach>
											<!-- 대댓글 작성 -->
											<div style="text-align: right;">
												<c:if test="${not empty mid}">
													<button class="insertReplyBtn">대댓글 작성</button>
												</c:if>
												<section id="replyInsertBox" class="insertReply" style="display: none;">
													<form id="replyInsert" action="insertReply.do" method="POST">
														<input type="hidden" name="boardNum" value="${cdata.boardNum}"> <input type="hidden" name="commentsNum" value="${cdata.commentsNum}"> <input type="text" name="reply" placeholder="대댓글 작성 내용" required> <input style="width: 80px;" type="submit" value="작성">
													</form>
												</section>
											</div>
											<!-- 대댓글 여기까지 -->

											<hr style="border: 0; border-top: solid 1px #a7b5ac; margin: 1em 0.5em 1em 0;">
											<!-- 댓글 여기까지 -->
										</c:forEach>
									</c:if>
								</section>

							</article>

						</div>
					</div>
					<div class="col-12"></div>
				</div>
			</div>
		</section>
		<button id="scrollToTop" onclick="scrollToTop()" class="icon solid fa-chevron-up"></button>
		<!-- Footer -->
		<NPNC:healthDuo_footer />

	</div>

	<!-- Scripts -->
	<!-- <script src="https://cdn.ckeditor.com/ckeditor5/39.0.1/classic/ckeditor.js"></script>
	<script src="https://cdn.ckeditor.com/ckeditor5/39.0.1/classic/translations/ko.js"></script>
	<script>
		ClassicEditor.create(document.querySelector('#editor'), {
		  toolbar: false ,
		  language: 'ko'
		});
	</script>  -->
	<script type="text/javascript">
   $(document).ready(function(){
      var recommend = parseInt(${recommend});

      if(recommend > 0){
         $("#rc").removeClass("fa-heart").addClass("solid fa-heart");
      } else {
         $("#rc").removeClass("solid fa-heart").addClass("fa-heart");
      }

      $("#rc").on("click", function(){
         $.ajax({
            url: 'boardRecommend.do?rcresult=' + recommend +'&boardNum=' + parseInt(${bdata.boardNum}),
            type: 'POST',
            success: function(rcresult){
               console.log('rcresult [' + rcresult + ']');
               if (rcresult == 1) {
                  $("#rc").removeClass("fa-heart").addClass("solid fa-heart");
                  recommend = 1;
                  var cnt = parseInt($('.cnt').text()) + 1;
                  console.log(cnt);
                  $('.cnt').text(cnt);
               } else if (rcresult == 0) {
                  $("#rc").removeClass("solid fa-heart").addClass("fa-heart");
                  recommend = 0;
                  var cnt = parseInt($('.cnt').text()) - 1;
                  console.log(cnt);
                  $('.cnt').text(cnt);
               }
            },
            error: function(error){
               history.go(goback.jsp);
            }
         });
      });
   });
   const boardButton = document.getElementById('boardButton');
   const menuList = document.getElementById('menuList');

   let menuVisible = false;
	if(boardButton != null && boardButton != undefined){
  		boardButton.addEventListener('click', () => {
   	menuVisible = !menuVisible;
    
	     if (menuVisible) {
	 	      menuList.style.display = 'block';
	     } else {
		      menuList.style.display = 'none';
	     }
	   });
	}
   
   document.addEventListener("DOMContentLoaded", function() {
	    const insertReplyBtns = document.querySelectorAll('.insertReplyBtn');

	    insertReplyBtns.forEach(function(insertReplyBtn) {
	      const insertReplyContainer = insertReplyBtn.parentElement;
	      const insertReply = insertReplyContainer.querySelector('.insertReply');

	      insertReplyBtn.addEventListener('click', () => {
	        if (insertReply.style.display === 'none') {
	          insertReplyBtn.textContent = "작성 취소";
	          insertReply.style.display = 'block';
	        } else {
	          insertReplyBtn.textContent = "대댓글 작성";
	          insertReply.style.display = 'none';
	        }
	      });
	    });
	  });
   
	console.log(window.location.pathname);
	function scrollToTop() {
       window.scrollTo({
           top: 0,
           behavior: 'smooth' // 부드러운 스크롤 효과 사용
       });
   }

   // 스크롤 위치에 따라 버튼을 표시 또는 숨깁니다.
   window.onscroll = function() {
       var button = document.getElementById("scrollToTop");
       if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
           button.style.display = "block";
       } else {
           button.style.display = "none";
       }
   };
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