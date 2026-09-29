(function () {
	"use strict";

	var months = ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"];

	var option = {
		tooltip: { trigger: "axis" },
		legend: { data: ["남", "여"], top: 0 },
		grid: { left: 55, right: 20, top: 40, bottom: 30 },
		xAxis: { type: "category", data: months },
		yAxis: { type: "value", name: "인구(명)" },
		series: [
			{ name: "남", type: "line", smooth: true, data: [20200, 20150, 20100, 20050, 20000, 19980, 19950, 19920, 19900, 19880, 19850, 19820], itemStyle: { color: "#3182ce" } },
			{ name: "여", type: "line", smooth: true, data: [20000, 19950, 19850, 19750, 19700, 19670, 19650, 19630, 19600, 19570, 19550, 19530], itemStyle: { color: "#e53e8c" } }
		]
	};

	var chart = echarts.init(document.getElementById("chart"));
	chart.setOption(option);
	window.addEventListener("resize", function () { chart.resize(); });
})();
