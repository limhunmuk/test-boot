(function () {
	"use strict";

	var regions = ["가평읍", "청평면", "설악면", "북면"];
	var ageGroups = ["0-9세", "10-19세", "20-29세", "30-39세", "40-49세", "50-59세", "60-69세", "70세 이상"];
	var ageData = [
		[3200, 2100, 1400, 900],
		[3400, 2300, 1500, 950],
		[4100, 2600, 1700, 1000],
		[4600, 2900, 1900, 1100],
		[4800, 3000, 2000, 1150],
		[4500, 2800, 1850, 1050],
		[3800, 2400, 1550, 900],
		[3000, 1900, 1250, 750]
	];

	var series = ageGroups.map(function (age, idx) {
		return {
			name: age,
			type: "bar",
			stack: "total",
			emphasis: { focus: "series" },
			data: ageData[idx]
		};
	});

	var option = {
		tooltip: { trigger: "axis", axisPointer: { type: "shadow" } },
		legend: { data: ageGroups, top: 0, type: "scroll" },
		grid: { left: 50, right: 20, top: 50, bottom: 30 },
		xAxis: { type: "category", data: regions },
		yAxis: { type: "value", name: "인구(명)" },
		series: series
	};

	var chart = echarts.init(document.getElementById("chart"));
	chart.setOption(option);
	window.addEventListener("resize", function () { chart.resize(); });
})();
