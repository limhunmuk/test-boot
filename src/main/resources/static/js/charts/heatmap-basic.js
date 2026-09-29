(function () {
	"use strict";

	var hours = ["09시", "10시", "11시", "12시", "13시", "14시", "15시", "16시", "17시"];
	var days = ["월", "화", "수", "목", "금"];
	// [시간 index, 요일 index, 값]
	var data = [
		[0, 0, 5], [1, 0, 12], [2, 0, 18], [3, 0, 22], [4, 0, 15], [5, 0, 20], [6, 0, 25], [7, 0, 14], [8, 0, 8],
		[0, 1, 7], [1, 1, 14], [2, 1, 20], [3, 1, 24], [4, 1, 16], [5, 1, 22], [6, 1, 27], [7, 1, 15], [8, 1, 9],
		[0, 2, 6], [1, 2, 13], [2, 2, 19], [3, 2, 23], [4, 2, 15], [5, 2, 21], [6, 2, 26], [7, 2, 14], [8, 2, 8],
		[0, 3, 9], [1, 3, 16], [2, 3, 22], [3, 3, 27], [4, 3, 18], [5, 3, 24], [6, 3, 30], [7, 3, 17], [8, 3, 10],
		[0, 4, 4], [1, 4, 10], [2, 4, 15], [3, 4, 19], [4, 4, 12], [5, 4, 17], [6, 4, 21], [7, 4, 11], [8, 4, 6]
	];

	var option = {
		tooltip: { position: "top" },
		grid: { left: 50, right: 20, top: 20, bottom: 40 },
		xAxis: { type: "category", data: hours, splitArea: { show: true } },
		yAxis: { type: "category", data: days, splitArea: { show: true } },
		visualMap: { min: 0, max: 30, calculable: true, orient: "horizontal", left: "center", bottom: 0 },
		series: [{ type: "heatmap", data: data, label: { show: false }, emphasis: { itemStyle: { shadowBlur: 10, shadowColor: "rgba(0,0,0,0.3)" } } }]
	};

	var chart = echarts.init(document.getElementById("chart"));
	chart.setOption(option);
	window.addEventListener("resize", function () { chart.resize(); });
})();
