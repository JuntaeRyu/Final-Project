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
					<div class="col-3 col-12-medium">
						<NPNC:sidebar/>
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
									<c:if test="${ role ne 2}">
											<li><form action="updateNoticePage.do" method="post">
												<input type="hidden" name="boardNum" value="${bdata.boardNum}" />
												<button type="submit" style="background: none; border: none; text-decoration: none; color: #6b7770;">
													수정</button>
											</form></li>
										</c:if>
										
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
											<input type="hidden" name="boardNum" value="${bdata.boardNum}">
											<input type="text" name="comments" placeholder="댓글 작성 내용" required> <input style="width: 80px;" type="submit" value="작성">
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
												<c:if test="${memberID eq cdata.memberID || role eq 2}">
													<i class="icon solid fa-bars commentsButton"></i>
												</c:if>
											</ul>
											<ul class="commentsMenuList">
												<c:if test="${role ne 2}">
													<li>
														<form action="updateComment.do" method="post">
															<input type="hidden" name="boardNum"
																value="${bdata.boardNum}"> <input type="hidden"
																name="commentsNum" value="${cdata.commentsNum}" />
															<button type="submit"
																style="background: none; border: none; text-decoration: none; color: #6b7770; padding: 0;">수정</button>
														</form>
													</li>
												</c:if>

												<li>
													<form action="deleteComment.do" method="post">
														<input type="hidden" name="boardNum"
															value="${bdata.boardNum}"> <input type="hidden"
															name="commentsNum" value="${cdata.commentsNum}" />
														<button type="submit"
															style="background: none; border: none; text-decoration: none; color: #6b7770; padding: 0;">삭제</button>
													</form>
												</li>
											</ul>
											<c:if test="${cdata.comments eq null}">
												<h1>삭제된 댓글입니다.</h1>
											</c:if>
											<c:if test="${cdata.comments ne null}">
												<h1>${cdata.comments}</h1>
											</c:if>
											<ul class="meta">
												<c:if test="${cdata.check eq '0' }">
													<li><i class="icon solid fa-ban comments"
														style="color: #c2bcbc"
														onclick="javascript:funcComments('${cdata.commentsNum}' , '${cdata.check}', this)"
														title="신고"></i></li>
												</c:if>
												<c:if test="${cdata.check eq '1' }">
													<li><i class="icon solid fa-ban comments"
														style="color: #f58300;"
														onclick="javascript:funcComments('${cdata.commentsNum}' , '${cdata.check}', this)"
														title="신고"></i></li>
												</c:if>
											</ul>

											<!-- 대댓글 -->
											<c:forEach var="rdata" items="${rdatas}">
												<c:if test="${cdata.commentsNum eq rdata.commentsNum}">
													<p id="replyIcon" class="icon solid fa-reply"></p>
													<section id="replyBox">
														<ul class="meta">
															<c:if test="${memberID eq rdata.memberID || role eq 2}">
																<i class="icon solid fa-bars replyButton"></i>
															</c:if>
														</ul>
														<ul class="replyMenuList">
															<c:if test="${role ne 2}">
																<li>
																	<form action="updateReply.do" method="post">
																		<input type="hidden" name="boardNum"
																			value="${bdata.boardNum}"> <input
																			type="hidden" name="replyNum"
																			value="${rdata.replyNum}">
																		<button type="submit"
																			style="background: none; border: none; text-decoration: none; color: #6b7770; padding: 0;">수정</button>
																	</form>
																</li>
															</c:if>

															<li>
																<form action="deleteReply.do" method="post">
																	<input type="hidden" name="boardNum"
																		value="${bdata.boardNum}"> <input
																		type="hidden" name="replyNum"
																		value="${rdata.replyNum}">
																	<button type="submit"
																		style="background: none; border: none; text-decoration: none; color: #6b7770; padding: 0;">삭제</button>
																</form>
															</li>
														</ul>
														<h1>${rdata.reply}</h1>
														<ul class="meta" style="text-align: right">
															<li class="icon solid fa-user" style="float: left;">${rdata.nickName}</li>
															<li class="icon fa-clock" style="float: left;">${rdata.replyDate}</li>

															<c:if test="${rdata.check eq '0'}">
																<li><i class="icon solid fa-ban reply"
																	style="color: #c2bcbc;"
																	onclick="javascript:funcReply('${rdata.replyNum}', '${rdata.check}', this)"
																	title="신고"></i></li>
															</c:if>
															<c:if test="${rdata.check eq '1'}">
																<li><i class="icon solid fa-ban reply"
																	style="color: #f58300;"
																	onclick="javascript:funcReply('${rdata.replyNum}', '${rdata.check}', this)"
																	title="신고"></i></li>
															</c:if>
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
													<form id="replyInsert" action="insertReply.do">
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
	const commentsButtons = document.getElementsByClassName('commentsButton');
	   const commentsMenuList = document.getElementsByClassName('commentsMenuList');
	   const commentsBan = document.getElementsByClassName('icon solid fa-ban comments');
	   
	   let commentsMenuVisible = false;
	   
	   
	   if (commentsButtons.length > 0 && commentsMenuList.length > 0) {
		   for (let i = 0; i < commentsButtons.length; i++) {
		     commentsButtons[i].addEventListener('click', () => {
		       commentsMenuVisible = !commentsMenuVisible;

		       if (commentsMenuVisible) {
		         commentsMenuList[i].style.display = 'block';
		         commentsBan[i].style.display='none';
		       } else {
		         commentsMenuList[i].style.display = 'none';
		         commentsBan[i].style.display='block';
		       }
		     });
		   }
	   }
		     
		const replyButtons = document.getElementsByClassName('replyButton');
	   const replyMenuList = document.getElementsByClassName('replyMenuList');
	   const replyBan = document.getElementsByClassName('icon solid fa-ban reply');
	   
	   let replyMenuVisible = false;
	   
	   
	   if (replyButtons.length > 0 && replyMenuList.length > 0) {
		   for (let i = 0; i < replyButtons.length; i++) {
		     replyButtons[i].addEventListener('click', () => {
		       replyMenuVisible = !replyMenuVisible;

		       if (replyMenuVisible) {
		         replyMenuList[i].style.display = 'block';
		         replyBan[i].style.display='none';
		       } else {
		         replyMenuList[i].style.display = 'none';
		         replyBan[i].style.display='block';
		       }
		     });
		     
		     
		   }
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