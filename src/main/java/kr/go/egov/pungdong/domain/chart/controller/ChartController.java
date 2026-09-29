package kr.go.egov.pungdong.domain.chart.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import kr.go.egov.pungdong.domain.chart.catalog.ChartCatalog;
import kr.go.egov.pungdong.domain.chart.catalog.ChartSample;

@Controller
@RequestMapping("/charts/sample")
public class ChartController {

	@GetMapping
	public String index(Model model) {
		model.addAttribute("categories", ChartCatalog.categories());
		return "chart/index";
	}

	@GetMapping("/{chartId}")
	public String detail(@PathVariable String chartId, Model model) {
		ChartSample sample = ChartCatalog.find(chartId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 차트입니다."));

		model.addAttribute("categories", ChartCatalog.categories());
		model.addAttribute("sample", sample);
		model.addAttribute("sourceCode", loadSource(chartId));
		return "chart/detail";
	}

	private String loadSource(String chartId) {
		try {
			ClassPathResource resource = new ClassPathResource("static/js/charts/" + chartId + ".js");
			return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "차트 소스를 찾을 수 없습니다.");
		}
	}

}
