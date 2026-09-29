(function () {
	"use strict";

	var months = ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"];

	var option = {
		tooltip: { trigger: "axis" },
		grid: { left: 55, right: 20, top: 20, bottom: 30 },
		xAxis: { type: "category", data: months },
		yAxis: { type: "value", name: "인구(명)" },
		series: [{
			type: "line",
			data: [40200, 40100, 39950, 39800, 39700, 39650, 39600, 39550, 39500, 39450, 39400, 39350],
			smooth: true,
			itemStyle: { color: "#2b6cb0" }
		}]
	};

	var chart = echarts.init(document.getElementById("chart"));
	chart.setOption(option);
	window.addEventListener("resize", function () { chart.resize(); });
})();
