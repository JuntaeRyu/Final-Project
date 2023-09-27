<%@ tag language="java" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="sidebar">

							<!-- Sidebar -->

							<!-- Recent Posts -->
							<section>
								<h2 class="major">
									<span>해당 게시물 작성자의 다른 게시물들</span>
								</h2>
								<ul class="divided">
									<c:if test="${not empty writerbatas}">
									<c:forEach items="${writerbdatas}" var="post">
										<li>
											<article class="box post-summary">
												<h3 id="anotherTitle">
													<a href="boardDetailPage.do?boardNum=${post.boardNum }">${post.title}</a>
												</h3>
												<ul class="meta">
													<li class="icon fa-clock">${post.boardDate}</li>
													<li class="icon fa-comments">${post.boardCommentsCnt}</li>
												</ul>
											</article>
										</li>
									</c:forEach>
									</c:if>
									<c:if test="${empty writerbatas}">
										<li>
										해당 회원에 작성된 글이 없습니다.
										</li>
									</c:if>
								</ul>
								<a href="boardListPage.do" class="button alt">커뮤니터 보러가기</a>
							</section>

							<!-- Something -->
							<section>
								<h2 class="major">
									<span>이 달의 게시물</span>
								</h2>
								<a href="boardDetailPage.do?boardNum=${topbdata.boardNum }" class="image featured"><img
									src="images/boardImg/${topbdata.boardImg }" alt="" /></a>
								<p id="topContent">${topbdata.content}</p>
								<a href="boardListPage.do" class="button alt">커뮤니터 보러가기</a>
							</section>

						</div>