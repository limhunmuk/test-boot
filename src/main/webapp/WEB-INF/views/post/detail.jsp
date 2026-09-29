<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tb" uri="http://egov.go.kr/pungdong/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="${post.title}" active="post">
	<style>
		:root {
			--border: #e2e2e2;
			--muted: #888;
			--accent: #2b6cb0;
		}
		body {
			font-family: -apple-system, "Apple SD Gothic Neo", "Segoe UI", sans-serif;
			margin: 0;
			background: #fafafa;
			color: #222;
		}
		.wrap { max-width: 860px; margin: 0 auto; padding: 40px 24px; }
		.card {
			background: #fff;
			box-shadow: 0 1px 3px rgba(0,0,0,0.06);
			border-radius: 4px;
			overflow: hidden;
		}
		.card-header { padding: 24px 28px 16px; border-bottom: 1px solid var(--border); }
		h1 { font-size: 20px; margin: 0 0 12px; word-break: break-word; }
		.meta { display: flex; gap: 16px; color: var(--muted); font-size: 13px; }
		.card-body { padding: 24px 28px; min-height: 160px; line-height: 1.7; white-space: pre-wrap; word-break: break-word; }
		.actions { margin-top: 20px; display: flex; gap: 8px; }
		.actions a {
			display: inline-block;
			padding: 8px 16px;
			background: #fff;
			border: 1px solid var(--border);
			border-radius: 4px;
			color: #444;
			text-decoration: none;
			font-size: 13px;
		}
		.actions a:hover { background: #eef2f8; border-color: var(--accent); color: var(--accent); }
		.files { padding: 16px 28px 24px; border-top: 1px solid var(--border); }
		.files h2 { font-size: 13px; color: var(--muted); font-weight: 600; margin: 0 0 10px; }
		.files ul { list-style: none; margin: 0; padding: 0; }
		.files li { margin-bottom: 6px; }
		.files a {
			color: var(--accent);
			text-decoration: none;
			font-size: 14px;
		}
		.files a:hover { text-decoration: underline; }
		.files .file-size { color: var(--muted); font-size: 12px; margin-left: 6px; }

		.comments { margin-top: 24px; background: #fff; border-radius: 4px; box-shadow: 0 1px 3px rgba(0,0,0,0.06); padding: 24px 28px; }
		.comments h2 { font-size: 15px; margin: 0 0 16px; }
		.comment-error { color: #c0392b; font-size: 13px; margin: 0 0 12px; }
		.comment-list { list-style: none; margin: 0 0 20px; padding: 0; }
		.comment-item { padding: 12px 0; border-bottom: 1px solid var(--border); }
		.comment-item:last-child { border-bottom: none; }
		.comment-meta { display: flex; justify-content: space-between; align-items: center; margin-bottom: 4px; }
		.comment-writer { font-size: 13px; font-weight: 600; color: #222; }
		.comment-date { font-size: 12px; color: var(--muted); }
		.comment-content { font-size: 14px; line-height: 1.6; white-space: pre-wrap; word-break: break-word; }
		.comment-delete, .comment-reply-toggle { background: none; border: none; color: var(--muted); font-size: 12px; cursor: pointer; padding: 0; margin-right: 10px; }
		.comment-delete:hover, .comment-reply-toggle:hover { color: var(--accent); }
		.comment-delete:hover { color: #c0392b; }
		.comment-empty { color: var(--muted); font-size: 13px; padding: 8px 0; }
		.comment-actions { margin-top: 4px; }
		.reply-list { list-style: none; margin: 10px 0 0; padding: 0 0 0 24px; border-left: 2px solid var(--border); }
		.reply-item { padding: 10px 0 10px 12px; }
		.reply-item:not(:last-child) { border-bottom: 1px solid #f2f2f2; }
		.reply-form { display: none; gap: 8px; margin: 10px 0 0 24px; }
		.reply-form.open { display: flex; }
		.reply-form textarea {
			flex: 1;
			box-sizing: border-box;
			padding: 8px 10px;
			border: 1px solid var(--border);
			border-radius: 4px;
			font-size: 13px;
			font-family: inherit;
			resize: vertical;
			min-height: 36px;
		}
		.reply-form button {
			padding: 0 14px;
			border: none;
			border-radius: 4px;
			background: var(--accent);
			color: #fff;
			font-size: 13px;
			cursor: pointer;
		}
		.comment-form { display: flex; gap: 8px; }
		.comment-form textarea {
			flex: 1;
			box-sizing: border-box;
			padding: 10px 12px;
			border: 1px solid var(--border);
			border-radius: 4px;
			font-size: 14px;
			font-family: inherit;
			resize: vertical;
			min-height: 44px;
		}
		.comment-form button {
			padding: 0 18px;
			border: none;
			border-radius: 4px;
			background: var(--accent);
			color: #fff;
			font-size: 14px;
			cursor: pointer;
		}
		.comment-form button:hover { background: #235a92; }
	</style>

	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="name" var="currentUser"/>
	</sec:authorize>

	<div class="wrap">
		<div class="card">
			<div class="card-header">
				<h1><c:out value="${post.title}"/></h1>
				<div class="meta">
					<span>작성자: <c:out value="${post.regId}"/></span>
					<span>등록일: ${tb:formatDateTime(post.regDt, 'yyyy-MM-dd HH:mm:ss')}</span>
					<span>조회수: <c:out value="${post.viewCnt}"/></span>
				</div>
			</div>
			<div class="card-body"><c:out value="${post.content}"/></div>
			<c:if test="${not empty files}">
				<div class="files">
					<h2>첨부파일 (${fn:length(files)})</h2>
					<ul>
						<c:forEach items="${files}" var="file">
							<li>
								<a href="<c:url value="/files/${file.uploadFileId}/download"/>"><c:out value="${file.orgFileNm}"/></a>
								<span class="file-size"><fmt:formatNumber value="${file.fileSize / 1024.0}" minFractionDigits="1" maxFractionDigits="1"/> KB</span>
							</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
		</div>
		<div class="actions">
			<a href="<c:url value="/posts"/>">← 목록으로</a>
			<a href="<c:url value="/posts/${post.articleId}/edit"/>">수정</a>
		</div>

		<div class="comments">
			<h2>댓글 (${commentCount})</h2>
			<c:if test="${not empty commentError}">
				<p class="comment-error"><c:out value="${commentError}"/></p>
			</c:if>

			<c:if test="${not empty commentThreads}">
				<ul class="comment-list">
					<c:forEach items="${commentThreads}" var="thread">
						<li class="comment-item">
							<div class="comment-meta">
								<span class="comment-writer"><c:out value="${thread.comment.regId}"/></span>
								<span class="comment-date">${tb:formatDateTime(thread.comment.regDt, 'yyyy-MM-dd HH:mm')}</span>
							</div>
							<div class="comment-content"><c:out value="${thread.comment.content}"/></div>
							<div class="comment-actions">
								<button type="button" class="comment-reply-toggle"
										onclick="toggleReplyForm(${thread.comment.articleCommentId})">답글</button>
								<c:if test="${not empty currentUser and currentUser == thread.comment.regId}">
									<form action="<c:url value="/posts/${post.articleId}/comments/${thread.comment.articleCommentId}/delete"/>"
										  method="post"
										  onsubmit="return confirm('댓글을 삭제하시겠습니까?');">
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
										<button type="submit" class="comment-delete">삭제</button>
									</form>
								</c:if>
							</div>

							<form class="reply-form" id="reply-form-${thread.comment.articleCommentId}"
								  action="<c:url value="/posts/${post.articleId}/comments"/>" method="post">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								<input type="hidden" name="parentCommentId" value="${thread.comment.articleCommentId}" />
								<textarea name="content" placeholder="답글을 입력하세요"></textarea>
								<button type="submit">등록</button>
							</form>

							<c:if test="${not empty thread.replies}">
								<ul class="reply-list">
									<c:forEach items="${thread.replies}" var="reply">
										<li class="reply-item">
											<div class="comment-meta">
												<span class="comment-writer"><c:out value="${reply.regId}"/></span>
												<span class="comment-date">${tb:formatDateTime(reply.regDt, 'yyyy-MM-dd HH:mm')}</span>
											</div>
											<div class="comment-content"><c:out value="${reply.content}"/></div>
											<c:if test="${not empty currentUser and currentUser == reply.regId}">
												<form action="<c:url value="/posts/${post.articleId}/comments/${reply.articleCommentId}/delete"/>"
													  method="post"
													  onsubmit="return confirm('댓글을 삭제하시겠습니까?');">
													<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
													<button type="submit" class="comment-delete">삭제</button>
												</form>
											</c:if>
										</li>
									</c:forEach>
								</ul>
							</c:if>
						</li>
					</c:forEach>
				</ul>
			</c:if>
			<c:if test="${empty commentThreads}">
				<p class="comment-empty">등록된 댓글이 없습니다.</p>
			</c:if>

			<c:url value="/posts/${post.articleId}/comments" var="commentFormAction"/>
			<form:form cssClass="comment-form" action="${commentFormAction}"
				  modelAttribute="postCommentCreateRequest" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<form:textarea path="content" placeholder="댓글을 입력하세요"/>
				<button type="submit">등록</button>
			</form:form>
		</div>

		<script>
			function toggleReplyForm(commentId) {
				document.getElementById('reply-form-' + commentId).classList.toggle('open');
			}
		</script>
	</div>
</t:layout>
