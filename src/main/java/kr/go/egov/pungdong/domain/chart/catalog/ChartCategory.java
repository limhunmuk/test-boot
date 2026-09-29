package kr.go.egov.pungdong.domain.chart.catalog;

import java.util.List;

public record ChartCategory(String name, List<ChartSample> samples) {
}
