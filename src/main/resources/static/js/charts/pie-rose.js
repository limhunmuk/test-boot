(function () {
	"use strict";

	var option = {
		tooltip: { trigger: "item" },
		legend: { bottom: 0 },
		series: [{
			type: "pie",
			radius: [20, "70%"],
			roseType: "area",
			itemStyle: { borderRadius: 4 },
			data: [
				{ name: "가평읍", value: 12400 },
				{ name: "청평면", value: 8300 },
				{ name: "설악면", value: 5200 },
				{ name: "북면", value: 4100 },
				{ name: "조종면", value: 3600 },
				{ name: "상면", value: 3900 },
				{ name: "하면", value: 2800 }
			]
		}]
	};

	var chart = echarts.init(document.getElementById("chart"));
	chart.setOption(option);
	window.addEventListener("resize", function () { chart.resize(); });
})();
