(function () {
	"use strict";

	var option = {
		tooltip: { trigger: "axis" },
		grid: { left: 80, right: 30, top: 20, bottom: 30 },
		xAxis: { type: "value", name: "인구(명)" },
		yAxis: {
			type: "category",
			data: ["하면", "상면", "조종면", "북면", "설악면", "청평면", "가평읍"]
		},
		series: [{
			type: "bar",
			data: [2800, 3900, 3600, 4100, 5200, 8300, 12400],
			itemStyle: { color: "#2f855a" },
			barMaxWidth: 22
		}]
	};

	var chart = echarts.init(document.getElementById("chart"));
	chart.setOption(option);
	window.addEventListener("resize", function () { chart.resize(); });
})();
