<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="tb" uri="http://egov.go.kr/pungdong/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="내 정보" active="">
	<style>
		:root {
			--border: #e2e2e2;
			--muted: #888;
			--accent: #2b6cb0;
			--error: #c0392b;
			--success: #2f7d4f;
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
		.notice-msg { font-size: 13px; padding: 10px 12px; border-radius: 4px; margin-bottom: 18px; }
		.notice-msg.success { background: #eafaf1; color: var(--success); }
		.notice-msg.error { background: #fdecea; color: var(--error); }
		.info-row { display: flex; padding: 12px 0; border-bottom: 1px solid #f0f0f0; }
		.info-row:last-child { border-bottom: none; }
		.info-row .label { width: 110px; flex-shrink: 0; color: var(--muted); font-size: 13px; }
		.info-row .value { font-size: 14px; color: #222; }
		.actions { display: flex; gap: 8px; margin-top: 24px; }
		.actions button, .actions a {
			padding: 9px 18px;
			border-radius: 4px;
			font-size: 14px;
			text-decoration: none;
			cursor: pointer;
			border: none;
			font-family: inherit;
		}
		.actions .btn-primary { background: var(--accent); color: #fff; }
		.actions .btn-primary:hover { background: #235a92; }
		.actions .btn-secondary { border: 1px solid var(--border); color: #444; background: #fff; }
		.actions .btn-secondary:hover { background: #eef2f8; }

		.modal-backdrop {
			display: none;
			position: fixed;
			inset: 0;
			background: rgba(0,0,0,0.4);
			align-items: center;
			justify-content: center;
			z-index: 10;
		}
		.modal-backdrop.open { display: flex; }
		.modal {
			background: #fff;
			border-radius: 6px;
			padding: 24px;
			width: 320px;
		}
		.modal h2 { font-size: 16px; margin: 0 0 16px; }
		.modal .field { margin-bottom: 14px; }
		.modal .field label { display: block; font-size: 13px; color: var(--muted); margin-bottom: 6px; }
		.modal .field input {
			width: 100%;
			box-sizing: border-box;
			padding: 9px 10px;
			border: 1px solid var(--border);
			border-radius: 4px;
			font-size: 14px;
		}
		.modal .field-error { color: var(--error); font-size: 12px; margin: 6px 0 0; display: none; }
		.modal .modal-actions { display: flex; justify-content: flex-end; gap: 8px; margin-top: 18px; }
	</style>
	<div class="wrap">
		<h1>내 정보</h1>

		<c:if test="${not empty pwSuccess}">
			<p class="notice-msg success"><c:out value="${pwSuccess}"/></p>
		</c:if>
		<c:if test="${not empty pwError}">
			<p class="notice-msg error"><c:out value="${pwError}"/></p>
		</c:if>

		<div class="card">
			<div class="info-row">
				<span class="label">아이디</span>
				<span class="value"><c:out value="${member.loginId}"/></span>
			</div>
			<div class="info-row">
				<span class="label">이름</span>
				<span class="value"><c:out value="${member.memNm}"/></span>
			</div>
			<div class="info-row">
				<span class="label">닉네임</span>
				<span class="value"><c:out value="${member.nickNm}"/></span>
			</div>
			<div class="info-row">
				<span class="label">연락처</span>
				<span class="value">${not empty member.phoneNo ? member.phoneNo : '-'}</span>
			</div>
			<div class="info-row">
				<span class="label">주소</span>
				<span class="value"><c:out value="${member.addr}"/> <c:out value="${member.addrDetail}"/></span>
			</div>
			<div class="info-row">
				<span class="label">가입일</span>
				<span class="value">${tb:formatDateTime(member.joinDt, 'yyyy-MM-dd')}</span>
			</div>
			<div class="info-row">
				<span class="label">마지막 로그인</span>
				<span class="value">
					<c:choose>
						<c:when test="${not empty member.modDt}">${tb:formatDateTime(member.modDt, 'yyyy-MM-dd HH:mm')} (<c:out value="${member.modIp}"/>)</c:when>
						<c:otherwise>-</c:otherwise>
					</c:choose>
				</span>
			</div>

			<div class="actions">
				<a class="btn-primary" href="<c:url value="/mypage/edit"/>">수정</a>
				<button type="button" class="btn-secondary" id="openPwModal">비밀번호 변경</button>
			</div>
		</div>
	</div>

	<div id="pwModalBackdrop" class="${not empty pwError ? 'modal-backdrop open' : 'modal-backdrop'}">
		<div class="modal">
			<h2>비밀번호 변경</h2>
			<form action="<c:url value="/mypage/password"/>" method="post" id="pwForm">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<div class="field">
					<label for="currentPassword">현재 비밀번호</label>
					<input type="password" id="currentPassword" name="currentPassword" required />
				</div>
				<div class="field">
					<label for="newPassword">새 비밀번호</label>
					<input type="password" id="newPassword" name="newPassword" required />
				</div>
				<div class="field">
					<label for="confirmPassword">새 비밀번호 확인</label>
					<input type="password" id="confirmPassword" name="confirmPassword" required />
					<p class="field-error" id="confirmError">새 비밀번호가 일치하지 않습니다.</p>
				</div>
				<div class="modal-actions">
					<button type="button" class="btn-secondary" id="closePwModal">취소</button>
					<button type="submit" class="btn-primary">변경</button>
				</div>
			</form>
		</div>
	</div>

	<script>
		const backdrop = document.getElementById('pwModalBackdrop');
		document.getElementById('openPwModal').addEventListener('click', () => backdrop.classList.add('open'));
		document.getElementById('closePwModal').addEventListener('click', () => backdrop.classList.remove('open'));

		document.getElementById('pwForm').addEventListener('submit', (e) => {
			const newPassword = document.getElementById('newPassword').value;
			const confirmPassword = document.getElementById('confirmPassword').value;
			const confirmError = document.getElementById('confirmError');
			if (newPassword !== confirmPassword) {
				e.preventDefault();
				confirmError.style.display = 'block';
			} else {
				confirmError.style.display = 'none';
			}
		});
	</script>
</t:layout>
