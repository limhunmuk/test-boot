<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="게시글 수정" active="post">
	<style>
		:root {
			--border: #e2e2e2;
			--muted: #888;
			--accent: #2b6cb0;
			--error: #c0392b;
			--danger: #c0392b;
		}
		body {
			font-family: -apple-system, "Apple SD Gothic Neo", "Segoe UI", sans-serif;
			margin: 0;
			background: #fafafa;
			color: #222;
		}
		.wrap { max-width: 640px; margin: 0 auto; padding: 40px 24px; }
		h1 { font-size: 20px; margin: 0 0 20px; }
		.card {
			background: #fff;
			box-shadow: 0 1px 3px rgba(0,0,0,0.06);
			border-radius: 4px;
			padding: 28px;
		}
		.field { margin-bottom: 18px; }
		.field label { display: block; font-size: 13px; color: var(--muted); margin-bottom: 6px; }
		.field input[type="text"], .field textarea {
			width: 100%;
			box-sizing: border-box;
			padding: 10px 12px;
			border: 1px solid var(--border);
			border-radius: 4px;
			font-size: 14px;
			font-family: inherit;
		}
		.field textarea { resize: vertical; }
		.field input:focus, .field textarea:focus { outline: none; border-color: var(--accent); }
		.error { color: var(--error); font-size: 12px; margin: 6px 0 0; }
		.actions { display: flex; gap: 8px; margin-top: 24px; }
		.actions button, .actions a {
			padding: 9px 18px;
			border-radius: 4px;
			font-size: 14px;
			text-decoration: none;
			cursor: pointer;
			border: none;
		}
		.actions button[type="submit"] { background: var(--accent); color: #fff; }
		.actions button[type="submit"]:hover { background: #235a92; }
		.actions a { border: 1px solid var(--border); color: #444; background: #fff; }
		.actions a:hover { background: #eef2f8; }

		.existing-files { margin-top: 6px; }
		.existing-files ul { list-style: none; margin: 0; padding: 0; }
		.existing-files li {
			display: flex;
			align-items: center;
			justify-content: space-between;
			padding: 6px 0;
			border-bottom: 1px solid #f0f0f0;
			font-size: 13px;
		}
		.existing-files .file-name { color: #333; }
		.existing-files .file-size { color: var(--muted); margin-left: 6px; font-size: 12px; }
		.existing-files form { margin: 0; }
		.existing-files button.delete-btn {
			background: none;
			border: 1px solid var(--border);
			color: var(--danger);
			font-size: 12px;
			padding: 4px 10px;
			border-radius: 4px;
		}
		.existing-files button.delete-btn:hover { background: #fdecea; border-color: var(--danger); }
		.no-files { color: var(--muted); font-size: 13px; }

		.file-chip-list { list-style: none; margin: 8px 0 0; padding: 0; display: flex; flex-direction: column; gap: 6px; }
		.file-chip {
			display: flex;
			align-items: center;
			justify-content: space-between;
			padding: 6px 10px;
			background: #f5f6f8;
			border-radius: 4px;
			font-size: 13px;
		}
		.file-chip-name { overflow: hidden; text-overflow: ellipsis; white-space: nowrap; }
		.file-chip-remove {
			background: none;
			border: none;
			color: var(--muted);
			font-size: 16px;
			line-height: 1;
			cursor: pointer;
			padding: 0 4px;
			margin-left: 8px;
		}
		.file-chip-remove:hover { color: var(--error); }
	</style>
	<div class="wrap">
		<h1>게시글 수정</h1>
		<div class="card">
			<c:url value="/posts/${articleId}/edit" var="formAction"/>
			<form:form action="${formAction}" modelAttribute="postUpdateRequest" method="post"
				  enctype="multipart/form-data">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<div class="field">
					<label for="title">제목</label>
					<form:input id="title" path="title"/>
					<form:errors path="title" element="p" cssClass="error"/>
				</div>
				<div class="field">
					<label for="content">내용</label>
					<form:textarea id="content" path="content" rows="10"/>
				</div>

				<div class="field">
					<label>기존 첨부파일</label>
					<div class="existing-files">
						<c:if test="${empty files}">
							<p class="no-files">첨부된 파일이 없습니다.</p>
						</c:if>
						<c:if test="${not empty files}">
							<ul>
								<c:forEach items="${files}" var="file">
									<li>
										<span>
											<span class="file-name"><c:out value="${file.orgFileNm}"/></span>
											<span class="file-size"><fmt:formatNumber value="${file.fileSize / 1024.0}" minFractionDigits="1" maxFractionDigits="1"/> KB</span>
										</span>
										<form action="<c:url value="/files/${file.uploadFileId}/delete"/>" method="post">
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
											<input type="hidden" name="redirectUrl" value="<c:url value="/posts/${articleId}/edit"/>" />
											<button type="submit" class="delete-btn">삭제</button>
										</form>
									</li>
								</c:forEach>
							</ul>
						</c:if>
					</div>
				</div>

				<div class="field">
					<label for="files">파일 추가 (여러 개 선택 가능)</label>
					<input type="file" id="files" name="files" multiple />
					<ul id="fileList" class="file-chip-list"></ul>
				</div>

				<div class="actions">
					<button type="submit">저장</button>
					<a href="<c:url value="/posts/${articleId}"/>">취소</a>
				</div>
			</form:form>
		</div>
	</div>
	<script src="<c:url value="/js/file-picker.js"/>"></script>
	<script>
		initFilePicker('files', 'fileList');
	</script>
</t:layout>
