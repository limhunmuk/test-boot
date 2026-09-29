(function () {
	"use strict";

	var option = {
		tooltip: { trigger: "item" },
		legend: { bottom: 0 },
		series: [{
			type: "pie",
			radius: ["45%", "70%"],
			avoidLabelOverlap: false,
			itemStyle: { borderRadius: 6, borderColor: "#fff", borderWidth: 2 },
			label: { show: true, formatter: "{b}\n{d}%" },
			data: [
				{ name: "1인 세대", value: 3800 },
				{ name: "2인 세대", value: 3100 },
				{ name: "3인 세대", value: 1900 },
				{ name: "4인 이상", value: 1400 }
			]
		}]
	};

	var chart = echarts.init(document.getElementById("chart"));
	chart.setOption(option);
	window.addEventListener("resize", function () { chart.resize(); });
})();
