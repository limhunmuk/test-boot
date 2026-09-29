<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="tb" uri="http://egov.go.kr/pungdong/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="게시글 목록" active="post">
	<link rel="stylesheet" href="<c:url value="/css/pikaday.css"/>">
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
		h1 { font-size: 22px; margin: 0 0 4px; }
		.sub { color: var(--muted); font-size: 13px; margin: 0 0 20px; }
		.search-box {
			background: #fff;
			border: 1px solid var(--border);
			border-radius: 4px;
			padding: 16px;
			margin-bottom: 20px;
			display: flex;
			flex-wrap: wrap;
			gap: 10px;
			align-items: flex-end;
		}
		.search-field { display: flex; flex-direction: column; gap: 4px; }
		.search-field label { font-size: 12px; color: var(--muted); }
		.search-field input {
			padding: 7px 10px;
			border: 1px solid var(--border);
			border-radius: 4px;
			font-size: 13px;
			font-family: inherit;
		}
		.search-field input[type="text"] { width: 140px; }
		.search-date-sep { padding-bottom: 8px; color: var(--muted); }
		.search-submit {
			padding: 8px 18px;
			border: none;
			border-radius: 4px;
			background: var(--accent);
			color: #fff;
			font-size: 13px;
			cursor: pointer;
		}
		.search-submit:hover { background: #235a92; }
		table {
			border-collapse: collapse;
			width: 100%;
			background: #fff;
			box-shadow: 0 1px 3px rgba(0,0,0,0.06);
		}
		th, td { border-bottom: 1px solid var(--border); padding: 10px 14px; text-align: left; font-size: 14px; }
		th { background: #f5f6f8; color: #555; font-weight: 600; }
		td.num, th.num { width: 60px; color: var(--muted); }
		td.writer, th.writer { width: 110px; color: var(--muted); }
		td.view, th.view { width: 90px; color: var(--muted); }
		td.date, th.date { width: 140px; color: var(--muted); }
		tr:hover td { background: #fbfcff; }
		a.title-link { color: #222; text-decoration: none; }
		a.title-link:hover { color: var(--accent); text-decoration: underline; }
		.empty { color: var(--muted); padding: 24px; text-align: center; background: #fff; }
		.pagination { margin-top: 20px; display: flex; gap: 4px; align-items: center; justify-content: center; }
		.pagination a {
			display: inline-block;
			min-width: 28px;
			padding: 5px 8px;
			text-align: center;
			text-decoration: none;
			color: #444;
			border-radius: 4px;
			font-size: 13px;
		}
		.pagination a:hover { background: #eef2f8; }
		.pagination a.active { background: var(--accent); color: #fff; font-weight: 600; }
		.pagination a.disabled { color: #ccc; pointer-events: none; }
		.header-row { display: flex; align-items: flex-end; justify-content: space-between; margin-bottom: 4px; }
		.write-btn {
			display: inline-block;
			padding: 8px 16px;
			background: var(--accent);
			color: #fff;
			border-radius: 4px;
			text-decoration: none;
			font-size: 13px;
		}
		.write-btn:hover { background: #235a92; }
	</style>
	<div class="wrap">
		<div class="header-row">
			<h1>게시글 목록</h1>
			<a class="write-btn" href="<c:url value="/posts/new"/>">글쓰기</a>
		</div>
		<p class="sub">전체 ${postPage.totalCount}건 · ${postPage.currentPage} / ${postPage.totalPages} 페이지</p>

		<form class="search-box" action="<c:url value="/posts"/>" method="get">
			<div class="search-field">
				<label for="searchTitle">제목</label>
				<input type="text" id="searchTitle" name="title" value="<c:out value="${condition.title}"/>" placeholder="제목 검색" />
			</div>
			<div class="search-field">
				<label for="searchRegId">등록자</label>
				<input type="text" id="searchRegId" name="regId" value="<c:out value="${condition.regId}"/>" placeholder="등록자 검색" />
			</div>
			<div class="search-field">
				<label for="searchStartDate">등록일</label>
				<input type="text" id="searchStartDate" name="startDate" class="js-datepicker" maxlength="10"
					   value="${condition.startDate}" placeholder="시작일 (예: 2026-01-01)" />
			</div>
			<span class="search-date-sep">~</span>
			<div class="search-field">
				<label for="searchEndDate">&nbsp;</label>
				<input type="text" id="searchEndDate" name="endDate" class="js-datepicker" maxlength="10"
					   value="${condition.endDate}" placeholder="종료일 (예: 2026-01-31)" />
			</div>
			<button type="submit" class="search-submit">검색</button>
		</form>

		<c:if test="${not empty postPage.posts}">
			<table>
				<thead>
					<tr>
						<th class="num">번호</th>
						<th>제목</th>
						<th class="writer">등록자</th>
						<th class="view">조회수</th>
						<th class="date">등록일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${postPage.posts}" var="post">
						<tr>
							<td class="num"><c:out value="${post.articleId}"/></td>
							<td>
								<a class="title-link" href="<c:url value="/posts/${post.articleId}"/>"><c:out value="${post.title}"/></a>
							</td>
							<td class="writer"><c:out value="${post.regId}"/></td>
							<td class="view"><c:out value="${post.viewCnt}"/></td>
							<td class="date">${tb:formatDateTime(post.regDt, 'yyyy-MM-dd HH:mm')}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>

		<c:if test="${empty postPage.posts}">
			<p class="empty">등록된 게시글이 없습니다.</p>
		</c:if>

		<c:if test="${postPage.totalPages > 1}">
			<script>
				function fn_egov_post_linkPage(pageNo) {
					var params = new URLSearchParams(window.location.search);
					params.set('page', pageNo);
					location.href = '<c:url value="/posts"/>?' + params.toString();
				}
			</script>
			<ui:pagination paginationInfo="${paginationInfo}" type="app" jsFunction="fn_egov_post_linkPage" />
		</c:if>
	</div>

	<script src="<c:url value="/js/vendor/pikaday.js"/>"></script>
	<script>
		function fn_pad(n) { return n < 10 ? '0' + n : '' + n; }
		function fn_formatIsoDate(date) { return date.getFullYear() + '-' + fn_pad(date.getMonth() + 1) + '-' + fn_pad(date.getDate()); }
		function fn_parseIsoDate(value) {
			if (!value) { return null; }
			var parts = value.split('-');
			if (parts.length !== 3) { return null; }
			return new Date(Number(parts[0]), Number(parts[1]) - 1, Number(parts[2]));
		}
		function fn_autoDashDateInput(e) {
			var digits = e.target.value.replace(/[^0-9]/g, '').slice(0, 8);
			var formatted = digits;
			if (digits.length > 4) { formatted = digits.slice(0, 4) + '-' + digits.slice(4); }
			if (digits.length > 6) { formatted = digits.slice(0, 4) + '-' + digits.slice(4, 6) + '-' + digits.slice(6); }
			e.target.value = formatted;
		}
		document.getElementById('searchStartDate').addEventListener('input', fn_autoDashDateInput);
		document.getElementById('searchEndDate').addEventListener('input', fn_autoDashDateInput);
		new Pikaday({ field: document.getElementById('searchStartDate'), toString: fn_formatIsoDate, parse: fn_parseIsoDate });
		new Pikaday({ field: document.getElementById('searchEndDate'), toString: fn_formatIsoDate, parse: fn_parseIsoDate });
	</script>
</t:layout>
