<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<script src="assets/js/jquery.min.js"></script>

	<!-- 회원탈퇴 모달 -->
	<div id="deleteMemberModal" class="modal">
		<div class="modal-content">
			<form>
				<span class="close">&times;</span>
				<label style="font-size: 25px;" for="password">비밀번호 확인</label>
				<input style="width: 100%" type="password" id="passwordMember" placeholder="비밀번호를 입력하세요" required>
				<button id="submitBtnMember" onclick="clickbtn()">확인</button>
			</form>
		</div>
	</div>

	<!-- 회원탈퇴 최종확인 -->
	<div id="checkModal" class="modal">
		<div class="modal-content">
			<label>정말로 탈퇴하시겠습니까?</label>
			<button id="checkBtn">확인</button>
			<button id="cancelBtn">취소</button>
		</div>
	</div>
	
	<!-- Scripts -->
	<script>
    // 페이지 준비 완료 시 모달창 숨김
    $(function () {
    	$('.modal').hide();
    });
    
    // 모달창의 x버튼을 눌렀다면 모달창 끄기
    $('.close').click(function(){
    	$('.modal').hide();
		$('#passwordMember').val(""); // 입력된 값을 비움
    });

    // 최종 확인에서 탈퇴를 선택했다면
    $('#checkBtn').click(function(){
    	$('#checkModal').hide();
		$('#passwordMember').val(""); // 입력된 값을 비움
		var form = $('<form>', {
			  'method': 'POST',
			  'action': 'deleteMember.do'
		});
		$('body').append(form);
		form.submit();
    });

    // 최종 확인에서 취소를 했다면
    $('#cancelBtn').click(function(){
    	$('#checkModal').hide();
		$('#passwordMember').val(""); // 입력된 값을 비움
    });
    
    // 비밀번호 입력 후 확인버튼을 눌럿을 떄
    function clickbtn(){
        if ($('#passwordMember').val() === '${mdata.memberPW}') {
  			// 비밀번호가 일치하면 최종확인
  			$('#deleteMemberModal').hide();
  			$('#passwordMember').val(""); // 입력된 값을 비움
			$('#checkModal').show();
  		} else {
  			alert("비밀번호가 일치하지 않습니다");
  			$('#deleteMemberModal').hide();
  			$('#passwordMember').val(""); // 입력된 값을 비움
  		}
    };
	</script>

