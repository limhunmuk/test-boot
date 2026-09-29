(function () {
	"use strict";

	var months = ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"];

	var option = {
		tooltip: { trigger: "axis" },
		legend: { data: ["기준금리"], top: 0 },
		grid: { left: 50, right: 20, top: 40, bottom: 30 },
		xAxis: { type: "category", data: months },
		yAxis: { type: "value", name: "%" },
		series: [{
			name: "기준금리",
			type: "line",
			step: "middle",
			data: [3.5, 3.5, 3.5, 3.25, 3.25, 3.25, 3.0, 3.0, 3.0, 3.0, 2.75, 2.75],
			itemStyle: { color: "#805ad5" }
		}]
	};

	var chart = echarts.init(document.getElementById("chart"));
	chart.setOption(option);
	window.addEventListener("resize", function () { chart.resize(); });
})();
