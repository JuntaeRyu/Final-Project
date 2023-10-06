<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<link rel="stylesheet" href="assets/css/bootstrap.css" />
<link rel="stylesheet" href="assets/css/main2.css" />
<script src="assets/js/jquery-3.6.0.js"></script>
<script src="assets/js/bootstrap.js"></script>
<script src="assets/js/cookie.js"></script>

<style>
.container {
	width: 500px;
}

#list {
	height: 300px;
	padding: 15px;
	overflow: auto;
}
</style>

</head>
<body>
	<!-- chat.jsp -->
	<div class="container">
		<h1 class="page-header">HealthDuo</h1>

		<table class="table table-bordered">
			<tr>
				<td><input type="text" name="user" id="user" class="form-control" value="${nickName}" readonly></td>
				<td>
					<button type="button" class="btn btn-default" id="btnConnect">연결</button>
					<button type="button" class="btn btn-default" id="btnDisconnect" disabled>종료</button>
				</td>
			</tr>
			<tr>
				<td colspan="2"><div id="list"></div></td>
			</tr>
			<tr>
				<td colspan="2"><input type="text" name="msg" id="msg" placeholder="대화 내용을 입력하세요." class="form-control" disabled></td>
			</tr>
		</table>
		<a class="Abtn" href="mypage.do">마이페이지로 돌아가기</a>
	</div>
	<script>
   	
   		// 채팅 서버 주소
   		let url = "ws://localhost:8088/app/chatserver";
   		
   		// 웹 소켓
   		let ws;
   		
   		// 연결하기
   		$('#btnConnect').click(function() {
   			
   			// 유저명 확인
   			if ($('#user').val().trim() != '') {
   				
	   			// 연결
	   			ws = new WebSocket(url);
	   			
	   			// 소켓 이벤트 매핑
	   			ws.onopen = function (evt) {
	   				// console.log('서버 연결 성공');
	   				print($('#user').val(), ' 님이 입장했습니다.');
	   				
	   				// 현재 사용자가 입장했다고 서버에게 통지(유저명 전달)
	   				// -> 1#유저명
					ws.send('1#' + $('#user').val() + '#');
					
					$('#user').attr('readonly', true);
					$('#btnConnect').attr('disabled', true);
					$('#btnDisconnect').attr('disabled', false);
					$('#msg').attr('disabled', false);
					$('#msg').focus();
	   			};
	
	   			ws.onmessage = function (evt) {
	   				// print('', evt.data);
					let index = evt.data.indexOf("#", 2);
					let no = evt.data.substring(0, 1); 
					let user = evt.data.substring(2, index);
					let txt = evt.data.substring(index + 1);
	   				
	   				if (no == '1') {
	   					print2(user);
	   				} else if (no == '2') {
	   					print(user, txt);
	   				} else if (no == '3') {
	   					print3(user);
	   				}
	   				
	   				$('#list').scrollTop($('#list').prop('scrollHeight'));
	   			};
	   			
	   			ws.onclose = function (evt) {
	   				console.log('소켓이 닫힙니다.');
	   			};
	   			
	   			ws.onerror = function (evt) {
	   				console.log(evt.data);
	   			};
	   			
   			} else {
   				alert('유저명을 입력하세요.');
   				$('#user').focus();
   			}
   			
   		});

		function print(user, txt) {
			let temp = '';
			temp += '<div style="margin-bottom:3px;">';
			temp += '[' + user + '] : ' + txt;
			temp += ' <span style="font-size:11px;color:#777;">' + new Date().toLocaleTimeString() + '</span>';
			temp += '</div>';
			
			$('#list').append(temp);
		}
		
		function print2(user) {
			let temp = '';
			temp += '<div style="margin-bottom:3px;">';
			temp += "[" + user + "] 님이 접속했습니다." ;
			temp += ' <span style="font-size:11px;color:#777;">' + new Date().toLocaleTimeString() + '</span>';
			temp += '</div>';
			
			$('#list').append(temp);
		}
		
		function print3(user) {
			let temp = '';
			temp += '<div style="margin-bottom:3px;">';
			temp += "[" + user + "] 님이 종료했습니다." ;
			temp += ' <span style="font-size:11px;color:#777;">' + new Date().toLocaleTimeString() + '</span>';
			temp += '</div>';
			
			$('#list').append(temp);
		}
		
		//print('길동', '안녕하세요.');
		
		$('#user').keydown(function() {
			if (event.keyCode == 13) {
				$('#btnConnect').click();
			}
		});
		
		$('#msg').keydown(function() {
			if (event.keyCode == 13) {
				
				//서버에게 메시지 전달
				//2#유저명#메시지
				ws.send('2#' + $('#user').val() + '#' + $(this).val()); //서버에게
				print($('#user').val(), $(this).val()); //본인 대화창에
				
				$('#msg').val('');
				$('#msg').focus();
				
			}
		});
		
		$('#btnDisconnect').click(function() {
			 event.stopPropagation();
			ws.send('3#' + $('#user').val() + '#');
			ws.close();
			
			$('#btnConnect').attr('disabled', false);
			$('#btnDisconnect').attr('disabled', true);
			
			$('#msg').val('');
			$('#msg').attr('disabled', true);
		});
		
		window.addEventListener('beforeunload', function () {
		    ws.send('3#' + $('#user').val() + '#');
		    ws.close();
		});
		
	</script>
</body>
</html>