(function () {
	"use strict";

	var months = ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"];
	// [시가, 종가, 저가, 고가]
	var data = [
		[100, 108, 96, 110], [108, 104, 100, 112], [104, 112, 101, 115], [112, 118, 109, 121],
		[118, 114, 110, 122], [114, 120, 111, 124], [120, 128, 117, 130], [128, 124, 120, 132],
		[124, 130, 119, 134], [130, 126, 122, 136], [126, 132, 121, 138], [132, 138, 128, 141]
	];

	var option = {
		tooltip: { trigger: "axis" },
		grid: { left: 50, right: 20, top: 20, bottom: 30 },
		xAxis: { type: "category", data: months },
		yAxis: { type: "value", scale: true },
		series: [{
			type: "candlestick",
			data: data,
			itemStyle: { color: "#e53e3e", color0: "#3182ce", borderColor: "#e53e3e", borderColor0: "#3182ce" }
		}]
	};

	var chart = echarts.init(document.getElementById("chart"));
	chart.setOption(option);
	window.addEventListener("resize", function () { chart.resize(); });
})();
