<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="샘플 차트 (ECharts)" active="chart-sample">
	<style>
		body {
			margin: 0;
			font-family: -apple-system, "Apple SD Gothic Neo", "Segoe UI", sans-serif;
			background: #f5f6f8;
			color: #222;
		}
		.wrap {
			max-width: 1000px;
			margin: 0 auto;
			padding: 0 24px 60px;
		}
		h1 { font-size: 20px; margin: 0 0 4px; }
		.page-desc { color: #666; font-size: 13px; margin: 0 0 28px; }
		h2.section-title {
			font-size: 15px;
			color: #1f2937;
			border-left: 4px solid #2b6cb0;
			padding-left: 10px;
			margin: 32px 0 14px;
		}
		.grid {
			display: grid;
			grid-template-columns: repeat(auto-fill, minmax(260px, 1fr));
			gap: 12px;
		}
		.card {
			display: block;
			background: #fff;
			border: 1px solid #e5e7eb;
			border-radius: 6px;
			padding: 14px 16px;
			text-decoration: none;
			color: inherit;
			transition: border-color 0.15s, box-shadow 0.15s;
		}
		.card:hover {
			border-color: #2b6cb0;
			box-shadow: 0 2px 8px rgba(43, 108, 176, 0.12);
		}
		.card .card-title {
			font-size: 14px;
			font-weight: 600;
			color: #1f2937;
			margin: 0 0 4px;
		}
		.card .card-desc {
			font-size: 12px;
			color: #6b7280;
			line-height: 1.5;
			margin: 0;
		}
		.note {
			background: #fff8e6;
			border: 1px solid #f0d789;
			border-radius: 6px;
			padding: 12px 16px;
			font-size: 13px;
			color: #7a5c00;
			margin: 32px 0 0;
		}
	</style>
	<div class="wrap">
		<h1>샘플 차트 모음 (ECharts)</h1>
		<p class="page-desc">
			차트 종류별로 화면이 분리되어 있어 필요할 때 레퍼런스로 바로 열어볼 수 있습니다.
			라이브러리는 <code>/js/vendor/echarts.min.js</code> 로컬 파일을 사용하므로 폐쇄망에서도 그대로 동작합니다.
		</p>

		<c:forEach items="${categories}" var="category">
			<h2 class="section-title"><c:out value="${category.name()}"/></h2>
			<div class="grid">
				<c:forEach items="${category.samples()}" var="sample">
					<a class="card" href="<c:url value="/charts/sample/${sample.id()}"/>">
						<p class="card-title"><c:out value="${sample.title()}"/></p>
						<p class="card-desc"><c:out value="${sample.description()}"/></p>
					</a>
				</c:forEach>
			</div>
		</c:forEach>

		<p class="note">
			※ 워드클라우드는 ECharts 코어에 포함되지 않고 별도 확장 파일(echarts-wordcloud)이 필요합니다.
			필요하시면 해당 파일도 반입 목록에 추가해서 샘플을 만들어 드릴게요.
		</p>
	</div>
</t:layout>
