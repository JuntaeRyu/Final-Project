<%@ tag language="java" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="sidebar">

							<!-- Sidebar -->

							<!-- Recent Posts -->
							<section>
								<h2 class="major">
									<span>�ش� �Խù� �ۼ����� �ٸ� �Խù���</span>
								</h2>
								<ul class="divided">
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
								</ul>
								<a href="boardListPage.do" class="button alt">Ŀ�´��� ��������</a>
							</section>

							<!-- Something -->
							<section>
								<h2 class="major">
									<span>�� ���� �Խù�</span>
								</h2>
								<a href="boardDetail.do?boardNum=${topbdata.boardNum }" class="image featured"><img
									src="images/boardImg/${topbdata.boardImg }" alt="" /></a>
								<p id="topContent">${topbdata.content}</p>
								<a href="boardListPage.do" class="button alt">Ŀ�´��� ��������</a>
							</section>

						</div>