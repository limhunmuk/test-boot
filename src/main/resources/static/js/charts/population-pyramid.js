(function () {
	"use strict";

	var ageBands = ["0-9", "10-19", "20-29", "30-39", "40-49", "50-59", "60-69", "70+"];
	var male = [1200, 1400, 1800, 2100, 2400, 2600, 2000, 1500];
	var female = [1100, 1300, 1700, 2000, 2300, 2500, 2200, 1900];

	var option = {
		tooltip: {
			trigger: "axis",
			axisPointer: { type: "shadow" },
			formatter: function (params) {
				var maleValue = Math.abs(params[0].value);
				var femaleValue = Math.abs(params[1].value);
				return params[0].name + "<br/>남: " + maleValue + "명<br/>여: " + femaleValue + "명";
			}
		},
		legend: { data: ["남", "여"], top: 0 },
		grid: { left: 60, right: 30, top: 40, bottom: 30 },
		xAxis: {
			type: "value",
			axisLabel: { formatter: function (v) { return Math.abs(v); } }
		},
		yAxis: { type: "category", data: ageBands },
		series: [
			{ name: "남", type: "bar", stack: "pyramid", data: male.map(function (v) { return -v; }), itemStyle: { color: "#3182ce" } },
			{ name: "여", type: "bar", stack: "pyramid", data: female, itemStyle: { color: "#e53e8c" } }
		]
	};

	var chart = echarts.init(document.getElementById("chart"));
	chart.setOption(option);
	window.addEventListener("resize", function () { chart.resize(); });
})();
