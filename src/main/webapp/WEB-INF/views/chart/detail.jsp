<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="${sample.title()} - 샘플 차트" active="chart-sample">
	<style>
		body {
			margin: 0;
			font-family: -apple-system, "Apple SD Gothic Neo", "Segoe UI", sans-serif;
			background: #f5f6f8;
			color: #222;
		}
		.layout {
			max-width: 1240px;
			margin: 0 auto;
			padding: 0 24px 60px;
			display: grid;
			grid-template-columns: 220px 1fr;
			gap: 28px;
			align-items: start;
		}
		.side {
			position: sticky;
			top: 16px;
			background: #fff;
			border: 1px solid #e5e7eb;
			border-radius: 6px;
			padding: 14px;
			font-size: 13px;
			max-height: calc(100vh - 90px);
			overflow-y: auto;
		}
		.side a.back {
			display: inline-block;
			margin-bottom: 12px;
			color: #2b6cb0;
			text-decoration: none;
			font-size: 12px;
		}
		.side h3 {
			font-size: 11px;
			color: #9ca3af;
			text-transform: uppercase;
			letter-spacing: 0.04em;
			margin: 14px 0 6px;
		}
		.side h3:first-of-type { margin-top: 0; }
		.side ul { list-style: none; margin: 0; padding: 0; }
		.side li a {
			display: block;
			padding: 5px 8px;
			border-radius: 4px;
			color: #374151;
			text-decoration: none;
			font-size: 13px;
		}
		.side li a:hover { background: #f0f4f8; }
		.side li a.active { background: #2b6cb0; color: #fff; }

		.main h1 { font-size: 20px; margin: 0 0 4px; }
		.main .desc { color: #666; font-size: 13px; margin: 0 0 20px; }
		.chart-card {
			background: #fff;
			border: 1px solid #e5e7eb;
			border-radius: 6px;
			padding: 16px;
		}
		.chart { width: 100%; height: 420px; }

		.code-card {
			margin-top: 20px;
			background: #1e1e1e;
			border-radius: 6px;
			overflow: hidden;
		}
		.code-head {
			display: flex;
			align-items: center;
			justify-content: space-between;
			padding: 8px 14px;
			background: #2a2a2a;
			color: #cbd5e1;
			font-size: 12px;
		}
		.code-head button {
			background: #374151;
			color: #f5f5f5;
			border: none;
			border-radius: 4px;
			padding: 5px 12px;
			font-size: 12px;
			cursor: pointer;
		}
		.code-head button:hover { background: #4b5563; }
		.code-head button.copied { background: #2f855a; }
		pre.code-block {
			margin: 0;
			padding: 16px;
			overflow-x: auto;
			font-size: 12.5px;
			line-height: 1.6;
			color: #e5e7eb;
		}
		pre.code-block code { font-family: Consolas, "D2Coding", "Courier New", monospace; }
	</style>
	<div class="layout">
		<aside class="side">
			<a class="back" href="<c:url value="/charts/sample"/>">← 샘플 차트 목록</a>
			<c:forEach items="${categories}" var="category">
				<h3><c:out value="${category.name()}"/></h3>
				<ul>
					<c:forEach items="${category.samples()}" var="s">
						<li>
							<a href="<c:url value="/charts/sample/${s.id()}"/>"
							   class="${s.id() == sample.id() ? 'active' : ''}"><c:out value="${s.title()}"/></a>
						</li>
					</c:forEach>
				</ul>
			</c:forEach>
		</aside>

		<section class="main">
			<h1><c:out value="${sample.title()}"/></h1>
			<p class="desc"><c:out value="${sample.description()}"/></p>

			<div class="chart-card">
				<div id="chart" class="chart"></div>
			</div>

			<div class="code-card">
				<div class="code-head">
					<span>/js/charts/<c:out value="${sample.id()}"/>.js</span>
					<button type="button" id="copyBtn">코드 복사</button>
				</div>
				<pre class="code-block"><code id="sourceCode"><c:out value="${sourceCode}"/></code></pre>
			</div>
		</section>
	</div>

	<script src="<c:url value="/js/vendor/echarts.min.js"/>"></script>
	<script src="<c:url value="/js/charts/${sample.id()}.js"/>"></script>
	<script>
		document.getElementById("copyBtn").addEventListener("click", function () {
			var text = document.getElementById("sourceCode").textContent;
			var btn = this;
			navigator.clipboard.writeText(text).then(function () {
				var original = btn.textContent;
				btn.textContent = "복사됨!";
				btn.classList.add("copied");
				setTimeout(function () {
					btn.textContent = original;
					btn.classList.remove("copied");
				}, 1500);
			});
		});
	</script>
</t:layout>
