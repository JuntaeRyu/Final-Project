<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- Nav -->
		<nav id="nav">
			<ul>
				<nav id="nav1">
					<li id="option1"><a href="main.do" class="icon solid fa-home">
							메인</a></li>
					<li id="option2"><a href="#" class="icon solid fa-comment">
							소식</a>
						<ul>
							<li><a href="noticeListPage.do">공지사항</a></li>
						</ul></li>
					<li id="option3"><a href="#" class="icon solid fa-comments">
							커뮤니티</a>
						<ul>
							<li><a href="boardListPage.do">전체</a></li>
							<li><a href="infoListPage.do">정보</a></li>
							<li><a href="chatListPage.do">잡담</a></li>
							<c:if test="${role eq 2}">
								<li><a href="prohibitListPage.do">신고글 목록</a></li>
							</c:if>
						</ul></li>
					<li id="option4"><a href="#" class="icon solid fa-users">
							매칭</a>
						<ul>
							<li><a href="matchingPage.do">전체회원</a></li>
							<c:if test="${role eq 2}">
								<li><a href="prohibitMemberListPage.do">회원관리</a></li>
							</c:if>
						</ul></li>
				</nav>
				<c:choose>
					<c:when test="${empty memberID}">
						<a href="loginPage.do" class="icon solid fa-lock login"
							value="로그인" title="로그인"> 로그인</a>
						<a href="signupPage.do" class="icon solid fa-user-plus signup"
							value="회원가입" title="회원가입"> 회원가입</a>
					</c:when>
					<c:otherwise>
						<a href="logout.do" class="icon solid fa-lock-open logout"
							value="로그아웃" title="로그아웃"> 로그아웃</a>
						<c:if test="${role eq 3}">
							<a href="mypage.do" class="icon solid fa-user mypage"
								value="마이페이지" title="마이페이지"> 마이페이지</a>
						</c:if>
						<c:if test="${role eq 2}">
							<a href="adminPage.do" class="icon solid fa-user adminPage"
								value="관리자페이지" title="관리자페이지"> 관리자페이지</a>
						</c:if>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>