<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
#imgBox {
	display: flex;
    flex-direction: column; /* 이미지를 세로로 나열합니다. */
    gap: 10px;
    align-items: flex-start;
}
#imgBox img {
	width: auto; /* 이미지가 부모 요소인 imgBox의 너비에 맞게 조절됩니다. */
    max-height: 380px;
}
#imgSection {
	display: flex;
    flex-direction: column;
}
</style>
<head>
<title>HealthDuo</title>
<meta charset="utf-8" />
<!-- <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" /> -->
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
					<h1 id="logo">게시글</h1>
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
						<NPNC:sidebar />
					</div>
					<div class="col-9 col-12-medium imp-medium">
						<div class="content">

							<!-- Content -->
							<article class="box page-content">
								<section id="datailBoardBox">
									<c:if test="${memberID eq bdata.memberID || role eq 2}">
										<i id="boardButton" class="icon solid fa-bars"></i>
									</c:if>
									<ul id="menuList">
										<c:if test="${role ne 2}">
											<li>
												<form action="updateBoardPage.do" method="post">
													<input type="hidden" name="boardNum" value="${bdata.boardNum}" />
													<button type="submit" style="background: none; border: none; text-decoration: none; color: #6b7770; padding: 0;">수정</button>
												</form>
											</li>
										</c:if>

										<li>
											<form action="deleteBoard.do" method="post">
												<input type="hidden" name="boardNum" value="${bdata.boardNum}" />
												<button type="submit" style="background: none; border: none; text-decoration: none; color: #6b7770; padding: 0;">삭제</button>
											</form>
										</li>
									</ul>
									<header style="margin: 0 0 0.5em 0;">
										<p id="boardTitle">${bdata.title}</p>
										<ul class="meta">
											<li class="icon solid fa-user"><a href="profileDetailPage.do?memberID=${bdata.memberID}">${bdata.nickName}</a></li>
											<li class="icon fa-clock">${bdata.boardDate}</li>
											<c:if test="${not empty memberID}">
												<li><i id="rc" class="icon fa-heart" style="color: #f22202;" title="추천"></i>
													<p class="cnt" style="display: inline-block;">${bdata.recommendCnt}</p></li>
												<li><i id="ph" class="icon solid fa-ban" title="신고"></i></li>
											</c:if>
										</ul>
									</header>
										<c:if test="${not empty bdata.boardImgList}">
											<section id="imgSection">
											<div id="imgBox">
											<c:forEach var="boardImg" items="${bdata.boardImgList}">
												<img src="${boardImg}" alt="" />
											</c:forEach>
											</div>
											</section>
										</c:if>
										<div id="editor">
											<p>${bdata.content}</p>
										</div>
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
												<li class="icon fa-clock">${cdata.commentsDate}</li>
												<c:if test="${memberID eq cdata.memberID || role eq 2}">
													<c:if test="${cdata.comments ne null}">
														<i class="icon solid fa-bars commentsButton"></i>
													</c:if>
												</c:if>
											</ul>
											<ul class="commentsMenuList">
												<c:if test="${role ne 2}">
													<li>
														<!-- 수정 버튼의 type을 "button"으로 변경하고 onclick 이벤트 추가 -->
														<button type="button" style="background: none; border: none; text-decoration: none; color: #6b7770; padding: 0;" onclick="updateComments('${cdata.commentsNum}')">수정</button>
													</li>
												</c:if>

												<li>
													<form action="deleteComment.do" method="post">
														<input type="hidden" name="boardNum" value="${bdata.boardNum}"> <input type="hidden" name="commentsNum" value="${cdata.commentsNum}" />
														<button id="deleteComments" type="submit" style="background: none; border: none; text-decoration: none; color: #6b7770; padding: 0;">삭제</button>
													</form>
												</li>
											</ul>

											<ul class="meta">
												<c:if test="${cdata.comments ne null}">
													<c:if test="${cdata.check eq '0' }">
														<li><i class="icon solid fa-ban comments" style="color: #c2bcbc" onclick="javascript:funcComments('${cdata.commentsNum}' , '${cdata.check}', this)" title="신고"></i></li>
													</c:if>
													<c:if test="${cdata.check eq '1' }">
														<li><i class="icon solid fa-ban comments" style="color: #f58300;" onclick="javascript:funcComments('${cdata.commentsNum}' , '${cdata.check}', this)" title="신고"></i></li>
													</c:if>
												</c:if>
											</ul>
											<c:if test="${cdata.comments eq null}">
												<h1>삭제된 댓글입니다.</h1>
											</c:if>
											<c:if test="${cdata.comments ne null}">
												<h1>${cdata.comments}</h1>
											</c:if>

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
																	<button type="button" style="background: none; border: none; text-decoration: none; color: #6b7770; padding: 0;" onclick="updateReply('${rdata.replyNum}')">수정</button>
																</li>
															</c:if>

															<li>
																<form action="deleteReply.do" method="post">
																	<input type="hidden" name="boardNum" value="${bdata.boardNum}"> <input type="hidden" name="replyNum" value="${rdata.replyNum}">
																	<button id="deleteReply" type="submit" style="background: none; border: none; text-decoration: none; color: #6b7770; padding: 0;">삭제</button>
																</form>
															</li>
														</ul>
														<h1>${rdata.reply}</h1>

														<ul class="meta" style="text-align: right">
															<li class="icon solid fa-user" style="float: left;">${rdata.nickName}</li>
															<li class="icon fa-clock" style="float: left;">${rdata.replyDate}</li>

															<c:if test="${rdata.check eq '0'}">
																<li><i class="icon solid fa-ban reply" style="color: #c2bcbc;" onclick="javascript:funcReply('${rdata.replyNum}', '${rdata.check}', this)" title="신고"></i></li>
															</c:if>
															<c:if test="${rdata.check eq '1'}">
																<li><i class="icon solid fa-ban reply" style="color: #f58300;" onclick="javascript:funcReply('${rdata.replyNum}', '${rdata.check}', this)" title="신고"></i></li>
															</c:if>
														</ul>
													</section>
												</c:if>
											</c:forEach>
											<!-- 대댓글 작성 -->
											<div style="text-align: right;">
												<c:if test="${not empty memberID}">
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

	<script>
	function funcComments(commentsNum, prohibit, iconColor){
		   console.log(commentsNum);
		   console.log(prohibit);
		   $.ajax({
	           url: 'commentsProhibit.do?phresult=' + prohibit + '&commentsNum=' + commentsNum,
	           type: 'POST',
	           success: function(phresult){
	              console.log('phresult [' + phresult + ']');
	              if (phresult == 1) {
	                 $(iconColor).css("color", "#f58300");
	              } else if (phresult == 0) {
	                 $(iconColor).css("color", "#c2bcbc");
	              };
	           },
	           error: function(error){
	              alert('error [' + error + ']');
	           }
	        });
	   }
	
	function funcReply(replyNum, prohibit, iconColor){
		   console.log(replyNum);
		   console.log(prohibit);
		   $.ajax({
	           url: 'replyProhibit.do?phresult=' + prohibit + '&replyNum=' + replyNum,
	           type: 'POST',
	           success: function(phresult){
	              console.log('phresult [' + phresult + ']');
	              if (phresult == 1) {
	                 $(iconColor).css("color", "#f58300");
	              } else if (phresult == 0) {
	                 $(iconColor).css("color", "#c2bcbc");
	              };
	           },
	           error: function(error){
	              alert('error [' + error + ']');
	           }
	        });
	   }
	</script>
	
	<script>
	$("#deleteComments").click(function (event) {
		event.preventDefault();
		
		Swal.fire({
			title: '정말로 삭제 하시겠습니까?',
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: '승인',
			cancelButtonText: '취소'
		}).then((result) => {
			if (result.isConfirmed) {
				$(this).closest('form').submit();
			}
		});
	});

	$("#deleteReply").click( function (event) {
		event.preventDefault();
		
		Swal.fire({
			title: '정말로 삭제 하시겠습니까?',
			icon: 'warning',
			showCancelButton: true,
			confirmButtonText: '승인',
			cancelButtonText: '취소'
		}).then((result) => {
			if (result.isConfirmed) {
				$(this).closest('form').submit();
			}
		});
	});
	</script>

	<script type="text/javascript">
	 function updateComments(commentsNum) {
         Swal.fire({
             title: '댓글 수정',
             html: '<textarea id="commentText" class="swal2-textarea"></textarea>',
             showCancelButton: true,
             confirmButtonText: '확인',
             cancelButtonText: '취소',
             preConfirm: () => {
                 const commentText = document.getElementById('commentText').value;
                 // 데이터를 서버로 보내고 POST 요청 보냄
                 postCommentsData(commentText, commentsNum);
             }
         });
     }

     // POST 요청을 보내는 함수
     function postCommentsData(commentText, commentsNum) {
         $.ajax({
             type: 'POST',
             url: 'updateComment.do',
             data: {
                 comments: commentText,
                 boardNum: '${bdata.boardNum}',
                 commentsNum: commentsNum
             },
             success: function(response) {
                 // 성공적으로 서버 응답을 받았을 때 실행할 코드
                 console.log(response);
                 // 원하는 후속 작업 수행
                 alert('댓글 수정 완료');
                 location.reload();
             },
             error: function(xhr, status, error) {
                 // Ajax 오류 처리
                 console.error(error);
             }
         });
     }
     
	 function updateReply(replyNum) {
         Swal.fire({
             title: '대댓글 수정',
             html: '<textarea id="replyText" class="swal2-textarea"></textarea>',
             showCancelButton: true,
             confirmButtonText: '확인',
             cancelButtonText: '취소',
             preConfirm: () => {
                 const replyText = document.getElementById('replyText').value;
                 // 데이터를 서버로 보내고 POST 요청 보냄
                 console.log(replyText, replyNum);
                 postReplyData(replyText, replyNum);
             }
         });
     }

     // POST 요청을 보내는 함수
     function postReplyData(replyText, replyNum) {
         $.ajax({
             type: 'POST',
             url: 'updateReply.do',
             data: {
                 reply: replyText,
                 boardNum: '${bdata.boardNum}',
                 replyNum: replyNum
             },
             success: function(response) {
                 // 성공적으로 서버 응답을 받았을 때 실행할 코드
                 console.log(response);
                 // 원하는 후속 작업 수행
                 alert('대댓글 수정 완료');
                 
                 location.reload();
             },
             error: function(xhr, status, error) {
                 // Ajax 오류 처리
                 console.error(error);
             }
         });
     }
	
   $(document).ready(function(){
      var recommend = parseInt(${recommend});
      var prohibit = parseInt(${prohibit});

      if(recommend > 0){
         $("#rc").removeClass("fa-heart").addClass("solid fa-heart");
      } else {
         $("#rc").removeClass("solid fa-heart").addClass("fa-heart");
      }

      if(prohibit > 0){
         $("#ph").css("color", "#f58300");
      } else {
         $("#ph").css("color", "#c2bcbc");
      }

      $("#rc").on("click", function(){
         $.ajax({
            url: 'boardRecommend.do?rcresult=' + recommend + '&boardNum=' + parseInt(${bdata.boardNum}),
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
            	alert('error [' + error + ']');
            }
         });
      });

      $("#ph").on("click", function(){
         $.ajax({
            url: 'boardProhibit.do?phresult=' + prohibit +'&boardNum=' + parseInt(${bdata.boardNum}),
            type: 'POST',
            success: function(phresult){
               console.log('phresult [' + phresult + ']');
               if (phresult == 1) {
                  $("#ph").css("color", "#f58300");
                  prohibit = 1;
               } else if (phresult == 0) {
                  $("#ph").css("color", "#c2bcbc");
                  prohibit = 0;
               }
            },
            error: function(error){
               alert('error [' + error + ']');
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
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
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