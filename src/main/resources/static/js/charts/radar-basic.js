(function () {
	"use strict";

	var option = {
		tooltip: {},
		legend: { data: ["금년", "전년"], bottom: 0 },
		radar: {
			indicator: [
				{ name: "인구", max: 100 },
				{ name: "예산집행", max: 100 },
				{ name: "민원처리", max: 100 },
				{ name: "시설이용", max: 100 },
				{ name: "만족도", max: 100 }
			]
		},
		series: [{
			type: "radar",
			data: [
				{ value: [80, 72, 88, 65, 76], name: "금년", areaStyle: { opacity: 0.2 } },
				{ value: [70, 68, 80, 60, 70], name: "전년", areaStyle: { opacity: 0.1 } }
			]
		}]
	};

	var chart = echarts.init(document.getElementById("chart"));
	chart.setOption(option);
	window.addEventListener("resize", function () { chart.resize(); });
})();
