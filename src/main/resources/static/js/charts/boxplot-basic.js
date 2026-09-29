(function () {
	"use strict";

	var regions = ["가평읍", "청평면", "설악면", "북면", "조종면", "상면", "하면"];
	// [최소, 1사분위, 중앙값, 3사분위, 최대] 형태의 평균 연령 분포
	var data = [
		[28, 34, 41, 48, 55],
		[30, 37, 44, 51, 58],
		[33, 40, 47, 54, 61],
		[35, 42, 49, 56, 63],
		[36, 43, 50, 57, 64],
		[34, 41, 48, 55, 62],
		[37, 44, 51, 58, 65]
	];

	var option = {
		tooltip: { trigger: "item" },
		grid: { left: 50, right: 20, top: 20, bottom: 40 },
		xAxis: { type: "category", data: regions, axisLabel: { interval: 0, rotate: 20 } },
		yAxis: { type: "value", name: "평균 연령" },
		series: [{ type: "boxplot", data: data, itemStyle: { color: "#a0aec0", borderColor: "#4a5568" } }]
	};

	var chart = echarts.init(document.getElementById("chart"));
	chart.setOption(option);
	window.addEventListener("resize", function () { chart.resize(); });
})();
