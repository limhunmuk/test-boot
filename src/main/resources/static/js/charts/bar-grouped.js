(function () {
	"use strict";

	var option = {
		tooltip: { trigger: "axis" },
		legend: { data: ["남", "여"], top: 0 },
		grid: { left: 50, right: 20, top: 40, bottom: 30 },
		xAxis: {
			type: "category",
			data: ["가평읍", "청평면", "설악면", "북면", "조종면", "상면", "하면"]
		},
		yAxis: { type: "value", name: "인구(명)" },
		series: [
			{ name: "남", type: "bar", data: [6300, 4200, 2700, 2000, 1800, 2000, 1400], itemStyle: { color: "#3182ce" } },
			{ name: "여", type: "bar", data: [6100, 4100, 2500, 2100, 1800, 1900, 1400], itemStyle: { color: "#e53e8c" } }
		]
	};

	var chart = echarts.init(document.getElementById("chart"));
	chart.setOption(option);
	window.addEventListener("resize", function () { chart.resize(); });
})();
