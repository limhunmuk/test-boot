package kr.go.egov.pungdong.web.config;

import java.util.Map;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.DefaultPaginationManager;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationManager;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.go.egov.pungdong.web.support.AppPaginationRenderer;

@Configuration
public class PaginationConfig {

	@Bean(name = "paginationManager")
	public PaginationManager paginationManager() {
		DefaultPaginationManager manager = new DefaultPaginationManager();
		manager.setRendererType(Map.<String, PaginationRenderer>of("app", new AppPaginationRenderer()));
		return manager;
	}

}
