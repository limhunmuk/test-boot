(function () {
	"use strict";

	var data = [
		[22, 180], [25, 210], [28, 260], [31, 300], [34, 340], [37, 360], [40, 390],
		[43, 410], [46, 430], [49, 450], [52, 470], [55, 480], [58, 460], [61, 440],
		[64, 420], [67, 400], [70, 380], [73, 360], [76, 340], [79, 320]
	];

	var option = {
		tooltip: { trigger: "item" },
		grid: { left: 50, right: 20, top: 20, bottom: 30 },
		xAxis: { type: "value", name: "연령" },
		yAxis: { type: "value", name: "월 소비(천원)" },
		series: [{ type: "scatter", data: data, symbolSize: 8, itemStyle: { color: "#2b6cb0", opacity: 0.7 } }]
	};

	var chart = echarts.init(document.getElementById("chart"));
	chart.setOption(option);
	window.addEventListener("resize", function () { chart.resize(); });
})();
