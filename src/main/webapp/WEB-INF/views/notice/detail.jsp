<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="tb" uri="http://egov.go.kr/pungdong/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="${notice.title}" active="notice">
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
		.badge-important {
			display: inline-block;
			background: #fdecea;
			color: #c0392b;
			font-size: 12px;
			padding: 3px 8px;
			border-radius: 3px;
			margin-bottom: 8px;
		}
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
		.files a { color: var(--accent); text-decoration: none; font-size: 14px; }
		.files a:hover { text-decoration: underline; }
		.files .file-size { color: var(--muted); font-size: 12px; margin-left: 6px; }
	</style>
	<div class="wrap">
		<div class="card">
			<div class="card-header">
				<c:if test="${notice.importantYn == 'Y'}">
					<span class="badge-important">중요</span>
				</c:if>
				<h1><c:out value="${notice.title}"/></h1>
				<div class="meta">
					<span>작성자: <c:out value="${notice.regId}"/></span>
					<span>등록일: ${tb:formatDateTime(notice.regDt, 'yyyy-MM-dd HH:mm')}</span>
					<span>조회수: <c:out value="${notice.viewCnt}"/></span>
				</div>
			</div>
			<div class="card-body"><c:out value="${notice.content}"/></div>
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
			<a href="<c:url value="/notices"/>">← 목록으로</a>
			<a href="<c:url value="/notices/${notice.noticeId}/edit"/>">수정</a>
		</div>
	</div>
</t:layout>
