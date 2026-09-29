(function () {
	"use strict";

	var option = {
		tooltip: { trigger: "item" },
		legend: { bottom: 0 },
		series: [{
			type: "pie",
			radius: "60%",
			data: [
				{ name: "0-9세", value: 1800 },
				{ name: "10-19세", value: 1700 },
				{ name: "20-29세", value: 2200 },
				{ name: "30-39세", value: 2400 },
				{ name: "40-49세", value: 2300 },
				{ name: "50-59세", value: 2100 },
				{ name: "60-69세", value: 1900 },
				{ name: "70세 이상", value: 1600 }
			],
			emphasis: { itemStyle: { shadowBlur: 10, shadowOffsetX: 0, shadowColor: "rgba(0,0,0,0.3)" } }
		}]
	};

	var chart = echarts.init(document.getElementById("chart"));
	chart.setOption(option);
	window.addEventListener("resize", function () { chart.resize(); });
})();
