(function () {
	"use strict";

	var data = [
		{ name: "가평읍", value: [1, 124, 12400] },
		{ name: "청평면", value: [2, 83, 8300] },
		{ name: "설악면", value: [3, 52, 5200] },
		{ name: "북면", value: [4, 41, 4100] },
		{ name: "조종면", value: [5, 36, 3600] },
		{ name: "상면", value: [6, 39, 3900] },
		{ name: "하면", value: [7, 28, 2800] }
	];

	var option = {
		tooltip: {
			formatter: function (p) { return p.data.name + "<br/>인구: " + p.data.value[2] + "명"; }
		},
		grid: { left: 50, right: 20, top: 20, bottom: 30 },
		xAxis: { type: "value", name: "지역 순번" },
		yAxis: { type: "value", name: "지표" },
		series: [{
			type: "scatter",
			data: data,
			symbolSize: function (val) { return Math.max(10, val[2] / 200); },
			itemStyle: { color: "#dd6b20", opacity: 0.7 }
		}]
	};

	var chart = echarts.init(document.getElementById("chart"));
	chart.setOption(option);
	window.addEventListener("resize", function () { chart.resize(); });
})();
