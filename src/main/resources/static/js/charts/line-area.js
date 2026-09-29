(function () {
	"use strict";

	var months = ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"];

	var option = {
		tooltip: { trigger: "axis" },
		legend: { data: ["전입", "전출"], top: 0 },
		grid: { left: 50, right: 20, top: 40, bottom: 30 },
		xAxis: { type: "category", data: months, boundaryGap: false },
		yAxis: { type: "value", name: "건수" },
		series: [
			{ name: "전입", type: "line", areaStyle: {}, smooth: true, data: [120, 132, 101, 134, 90, 230, 210, 180, 160, 150, 140, 170], itemStyle: { color: "#38a169" } },
			{ name: "전출", type: "line", areaStyle: {}, smooth: true, data: [80, 92, 110, 90, 100, 140, 130, 120, 110, 105, 100, 115], itemStyle: { color: "#dd6b20" } }
		]
	};

	var chart = echarts.init(document.getElementById("chart"));
	chart.setOption(option);
	window.addEventListener("resize", function () { chart.resize(); });
})();
