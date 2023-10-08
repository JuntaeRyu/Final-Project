<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<!-- <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />-->
<link rel="icon" href="assets/css/images/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.15.5/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.15.5/dist/sweetalert2.min.js"></script>
<style type="text/css">
/* 모달 스타일 */
label {
   text-align: center;
}

button {
   background-color: #8ec991;
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
   width: 100%
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

textarea.fixed-width {
   resize: vertical;
}

.verification-container {
   display: flex;
   align-items: center;
}
</style>

</head>
<body class="is-preload">
   <!-- 회원탈퇴 모달 -->
   <div id="deleteMemberModal" class="modal">
      <div class="modal-content">
         <form>
            <span class="close">&times;</span> <label style="font-size: 25px;" for="password">비밀번호 확인</label> <input style="width: 100%" type="password" id="passwordMember" placeholder="비밀번호를 입력하세요">
            <button id="submitBtnMember" onclick="clickbtn()">확인</button>
         </form>
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
               <h1 id="logo">정보 변경</h1>
               <p></p>
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
                     <!-- 모달창 >> 정보수정 -->
                     <article class="box page-content">
                        <h2 style="color: #bead7c;">기본정보</h2>
                        <section id="mypageinfo">
                           <section style="display: flex; margin: 0;">
                           <h3>비밀번호</h3>

                              <div id="checkPasswordModal" class="modal">
                                 <div class="modal-content">
                                    <form id="checkPasswordForm">
                                       <span class="close">&times;</span> <label style="font-size: 25px;">비밀번호 확인</label> 
                                       <input style="width: 100%" name="memberPW" id="checkPassword" type="password" placeholder="현재 비밀번호 입력" required>
                                       <button id="checkPasswordBtn" type="submit">확인</button>
                                    </form>
                                 </div>
                              </div>
                              <div id="updatePasswordModal" class="modal">
                                 <div class="modal-content">
                                    <form id="updateMemberPWForm" action="updateMemberPW.do" method="post">
                                       <span class="close">&times;</span> <label style="font-size: 25px;">비밀번호 변경</label> 
                                       <input style="width: 100%" name="memberPW" id="passwordUdate" type="password" placeholder="변경할 비밀번호 입력" required>
                                       <button type="submit">확인</button>
                                    </form>
                                 </div>
                              </div>
                              <input id="updateBtn1" style="padding: 0px 40px 0px 40px; height: 40px; display: block; margin-left: auto; margin-right: 10px;" type="submit" value="수정">
                           </section>

                           <h3>닉네임</h3>
                           <section style="display: flex; justify-content: space-between; align-items: center;" class="info">
                              <h1>${mdata.nickName}</h1>

                              <div id="updateNicknameModal" class="modal">
                                 <div class="modal-content">
                                    <form id="updateNickNameForm" action="updateNickName.do" method="post">
                                       <span class="close">&times;</span> <label style="font-size: 25px;">닉네임 변경</label> 
                                       <input style="width: 100%" name="nickName" type="text" placeholder="변경할 닉네임 입력" required>
                                       <button type="submit">확인</button>
                                    </form>
                                 </div>
                              </div>

                              <input id="updateBtn2" style="display: block; margin-left: auto;" type="submit" value="수정">
                           </section>

                           <h3>이메일</h3>
                           <section style="display: flex; width: 100%;" class="info">
                              <h1>${mdata.email}</h1>

                              <div id="updateEmailModal" class="modal">
                                 <div class="modal-content">
                                    <form id="updateEmailForm" action="updateEmail.do" method="post">
                                       <span class="close">&times;</span> <label style="font-size: 25px;">이메일 변경</label>
                                       <input style="width: 100%" name="email" id="emailUdate" type="email" placeholder="변경할 이메일 입력" required>
                                       <button type="submit">확인</button>
                                    </form>
                                 </div>
                              </div>

                              <input id="updateBtn4" style="display: block; margin-left: auto;" type="submit" value="수정">
                           </section>

                           <h3>주소</h3>
                           <section style="display: flex; width: 100%;" class="info">

                              <form id="updateAddressForm" action="updateAddress.do" method="post" style="width: 100%">
                              <div class="verification-container">
                                 <input type="text" style="width: 100%" id="sample6_address" name="address" placeholder="${mdata.address}"><br>
                                 <button style="width: 20%; margin-top: 0px; height:auto;" onclick="sample6_execDaumPostcode()">우편번호 찾기</button>
                              </div>
                              <div class="verification-container">
                                 <input type="text" style="width: 100%" id="sample6_extraAddress" name="detailAddress" placeholder="${mdata.detailAddress}">
                              </div>
                                 <input id="updateAddressBtn" style="display: none; margin-left: auto; top: -60px;" type="submit" value="수정">
                              </form>
                           </section>
                           
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
	<script>
	function sample6_execDaumPostcode() {
		event.preventDefault();
		new daum.Postcode({oncomplete: function(data) {
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
				document.getElementById("updateAddressBtn").style.display = "block";

			} else {
				document.getElementById("sample6_extraAddress").value = '';
			}

			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById("sample6_address").value = addr;
			}
		}).open();
	}
	</script>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
	<script type="text/javascript">
	$("#checkPasswordBtn").click(function(event){
		event.preventDefault();
		if ("${mdata.memberPW}" === $("#checkPassword").val()) {
			$("#checkPasswordModal").css("display", "none");
			$("#checkPassword").val("");
			$("#updatePasswordModal").css("display", "block");
		} else {
			Swal.fire({
				title: '비밀번호가 일치하지 않습니다',
				icon: 'warning',
				confirmButtonText: '확인',
			}).then((result) => {
				$("#checkPasswordModal").css("display", "none");
				$("#checkPassword").val("");
			});
		}
	});
	</script>
	<script type="text/javascript">
	$("#updateMemberPWForm").submit(function (event) {
		event.preventDefault();
		
		Swal.fire({
			title: '비밀번호를 변경하시겠습니까?',
			icon: 'question	',
			showCancelButton: true,
			confirmButtonText: '확인',
			cancelButtonText: '취소'
		}).then((result) => {
			if (result.isConfirmed) {
				$(this).closest('form').submit();
			}
		});
	});
	$("#updateNickNameForm").submit(function (event) {
		event.preventDefault();
		
		Swal.fire({
			title: '닉네임을 변경하시겠습니까?',
			icon: 'question',
			showCancelButton: true,
			confirmButtonText: '확인',
			cancelButtonText: '취소'
		}).then((result) => {
			if (result.isConfirmed) {
				$(this).closest('form').submit();
			}
		});
	});
	$("#updateEmailForm").submit(function (event) {
		event.preventDefault();
		
		Swal.fire({
			title: '이메일을 변경하시겠습니까?',
			icon: 'question',
			showCancelButton: true,
			confirmButtonText: '확인',
			cancelButtonText: '취소'
		}).then((result) => {
			if (result.isConfirmed) {
				$(this).closest('form').submit();
			}
		});
	});
	$("#updateAddressForm").submit(function (event) {
		event.preventDefault();
		
		Swal.fire({
			title: '주소를 변경하시겠습니까?',
			icon: 'question',
			showCancelButton: true,
			confirmButtonText: '확인',
			cancelButtonText: '취소'
		}).then((result) => {
			if (result.isConfirmed) {
				$(this).closest('form').submit();
			}
		});
	});
	</script>
   <script>
   const checkPasswordModal = document.getElementById("checkPasswordModal");
   const updatePasswordModal = document.getElementById("updatePasswordModal");
   const updateNicknameModal = document.getElementById("updateNicknameModal");
   const updateEmailModal = document.getElementById("updateEmailModal");
   const updateAddressModal = document.getElementById("updateAddressModal");
   
   const updateBtn1 = document.getElementById("updateBtn1");
   const updateBtn2 = document.getElementById("updateBtn2");
   const updateBtn4 = document.getElementById("updateBtn4");
   
   const openModalBtnMember = document.getElementById("openModalBtnMember");
   
    const passwordModalMember = document.getElementById("deleteMemberModal");
    const checkModal = document.getElementById("checkModal");
    
    const closeBtnMember = passwordModalMember.querySelector(".close");
    const closeBtnUpdate1 = checkPasswordModal.querySelector(".close");
    const closeBtnUpdate2 = updateNicknameModal.querySelector(".close");
    const closeBtnUpdate4 = updateEmailModal.querySelector(".close");
    
    const submitBtnMember = document.getElementById("submitBtnMember");
    const checkBtn = document.getElementById("checkBtn");
    const cancleBtn = document.getElementById("cancleBtn");
    
    const passwordInputMember = document.getElementById("passwordMember");
    const passwordInputUdate = document.getElementById("passwordUdate");
    const emailInputUdate = document.getElementById("emailUdate");

    // "수정" 버튼 클릭 이벤트 처리
    updateBtn1.addEventListener("click", () => {
    	checkPasswordModal.style.display = "block";
    });
    updateBtn2.addEventListener("click", () => {
       updateNicknameModal.style.display = "block";
    });
    updateBtn4.addEventListener("click", () => {
       updateEmailModal.style.display = "block";
    });

    // "수정" 버튼에 대한 모달 닫기 버튼 클릭 이벤트 처리
    closeBtnUpdate1.addEventListener("click", () => {
    	checkPasswordModal.style.display = "none";
    });
    closeBtnUpdate2.addEventListener("click", () => {
       updateNicknameModal.style.display = "none";
    });
    closeBtnUpdate4.addEventListener("click", () => {
       updateEmailModal.style.display = "none";
    });

    // "회원 탈퇴" 버튼 클릭 이벤트 처리
    openModalBtnMember.addEventListener("click", () => {
      passwordModalMember.style.display = "block";
    });

    // "회원 탈퇴" 모달 닫기 버튼 클릭 이벤트 처리
    closeBtnMember.addEventListener("click", () => {
      passwordModalMember.style.display = "none";
    });

    // "최종 확인" 버튼 클릭 이벤트 처리
    checkBtn.addEventListener("click", () => {
      checkModal.style.display = "none";
      location.href = "deleteMember.do";
    });

    // "취소" 버튼 클릭 이벤트 처리
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