(function () {
	"use strict";

	var option = {
		series: [{
			type: "gauge",
			min: 0,
			max: 100,
			progress: { show: true, width: 14 },
			axisLine: { lineStyle: { width: 14 } },
			axisTick: { show: false },
			splitLine: { length: 12 },
			pointer: { show: false },
			detail: { valueAnimation: true, formatter: "{value}%", fontSize: 22, offsetCenter: [0, 0] },
			data: [{ value: 72, name: "예산 집행률" }]
		}]
	};

	var chart = echarts.init(document.getElementById("chart"));
	chart.setOption(option);
	window.addEventListener("resize", function () { chart.resize(); });
})();
