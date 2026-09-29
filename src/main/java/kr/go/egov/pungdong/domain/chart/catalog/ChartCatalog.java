package kr.go.egov.pungdong.domain.chart.catalog;

import java.util.List;
import java.util.Optional;

public final class ChartCatalog {

	private static final List<ChartCategory> CATEGORIES = List.of(
		new ChartCategory("막대 차트", List.of(
			new ChartSample("bar-vertical", "세로 막대", "읍면별 인구를 세로 막대로 비교합니다."),
			new ChartSample("bar-horizontal", "가로 막대", "읍면별 인구를 가로 막대로 비교합니다."),
			new ChartSample("bar-grouped", "그룹 막대", "읍면별 남녀 인구를 나란히 비교합니다."),
			new ChartSample("bar-stacked", "누적 막대", "지역별 연령대 구성 비율을 누적으로 표시합니다."),
			new ChartSample("population-pyramid", "인구 피라미드", "음수값을 활용해 연령대별 남녀 인구를 좌우로 표시합니다.")
		)),
		new ChartCategory("라인 차트", List.of(
			new ChartSample("line-single", "단일 라인", "월별 인구 추이를 하나의 선으로 표시합니다."),
			new ChartSample("line-multi", "멀티 라인", "월별 남녀 인구 추이를 두 개의 선으로 비교합니다."),
			new ChartSample("line-area", "영역 차트", "전입/전출 추이를 영역(area)으로 표시합니다."),
			new ChartSample("line-step", "계단형 라인", "기준금리처럼 계단식으로 변하는 값을 표시합니다.")
		)),
		new ChartCategory("파이 / 도넛", List.of(
			new ChartSample("pie-basic", "파이 차트", "연령대별 비율을 파이 차트로 표시합니다."),
			new ChartSample("pie-donut", "도넛 차트", "세대 구성 비율을 도넛 차트로 표시합니다."),
			new ChartSample("pie-rose", "나이팅게일(Rose) 차트", "값의 크기에 따라 반지름이 달라지는 로즈 차트입니다.")
		)),
		new ChartCategory("산점도 / 버블", List.of(
			new ChartSample("scatter-basic", "산점도", "연령과 소비액의 분포를 점으로 표시합니다."),
			new ChartSample("scatter-bubble", "버블 차트", "값의 크기를 원의 크기로 함께 표현합니다.")
		)),
		new ChartCategory("트리 / 계층", List.of(
			new ChartSample("treemap-basic", "트리맵", "지역별 인구 비중을 사각형 면적으로 표시합니다."),
			new ChartSample("sunburst-basic", "선버스트", "권역-읍면 계층 구조를 방사형으로 표시합니다.")
		)),
		new ChartCategory("통계 / 분포", List.of(
			new ChartSample("boxplot-basic", "박스플롯", "지역별 평균 연령 분포(사분위)를 표시합니다."),
			new ChartSample("heatmap-basic", "히트맵", "요일 x 시간대별 값을 색상 농도로 표시합니다.")
		)),
		new ChartCategory("기타", List.of(
			new ChartSample("gauge-basic", "게이지", "목표 대비 달성률처럼 단일 지표를 표시합니다."),
			new ChartSample("radar-basic", "레이더", "여러 항목을 한 번에 비교합니다."),
			new ChartSample("candlestick-basic", "캔들스틱", "시가/종가/고가/저가 데이터를 표시합니다.")
		))
	);

	private ChartCatalog() {
	}

	public static List<ChartCategory> categories() {
		return CATEGORIES;
	}

	public static Optional<ChartSample> find(String chartId) {
		return CATEGORIES.stream()
			.flatMap(category -> category.samples().stream())
			.filter(sample -> sample.id().equals(chartId))
			.findFirst();
	}

}
