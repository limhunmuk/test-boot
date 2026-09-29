(function () {
	"use strict";

	var option = {
		tooltip: { trigger: "axis" },
		grid: { left: 50, right: 20, top: 20, bottom: 30 },
		xAxis: {
			type: "category",
			data: ["가평읍", "청평면", "설악면", "북면", "조종면", "상면", "하면"]
		},
		yAxis: { type: "value", name: "인구(명)" },
		series: [{
			type: "bar",
			data: [12400, 8300, 5200, 4100, 3600, 3900, 2800],
			itemStyle: { color: "#2b6cb0" },
			barMaxWidth: 36
		}]
	};

	var chart = echarts.init(document.getElementById("chart"));
	chart.setOption(option);
	window.addEventListener("resize", function () { chart.resize(); });
})();
