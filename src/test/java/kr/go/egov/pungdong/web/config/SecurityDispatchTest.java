package kr.go.egov.pungdong.web.config;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import jakarta.servlet.DispatcherType;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import kr.go.egov.pungdong.domain.member.service.MemberService;
import java.util.concurrent.atomic.AtomicBoolean;

class SecurityDispatchTest {
    @Configuration
    @EnableWebMvc
    static class TestConfig {
        @Bean MemberService memberService() { return mock(MemberService.class); }
    }

    @Test void permitsPublicUrlsAndJspForwardButProtectsDirectRequests() throws Exception {
        try (var context = new AnnotationConfigWebApplicationContext()) {
            context.setServletContext(new MockServletContext());
            context.register(TestConfig.class, SecurityConfig.class);
            context.refresh();
            FilterChainProxy security = context.getBean(FilterChainProxy.class);
            assertAllowed(security, "/", DispatcherType.REQUEST, true);
            assertAllowed(security, "/login", DispatcherType.REQUEST, true);
            assertAllowed(security, "/WEB-INF/views/login.jsp", DispatcherType.FORWARD, true);
            assertAllowed(security, "/WEB-INF/views/home.jsp", DispatcherType.FORWARD, true);
            assertAllowed(security, "/admin/menus", DispatcherType.REQUEST, false);
            assertAllowed(security, "/WEB-INF/views/login.jsp", DispatcherType.REQUEST, false);
        }
    }

    private void assertAllowed(FilterChainProxy security, String path, DispatcherType type, boolean expected) throws Exception {
        var request = new MockHttpServletRequest("GET", path);
        request.setServletPath(path);
        request.setDispatcherType(type);
        var response = new MockHttpServletResponse();
        var reached = new AtomicBoolean(false);
        security.doFilter(request, response, (req, res) -> reached.set(true));
        assertEquals(expected, reached.get(), path + " " + type);
        if (!expected) assertEquals(302, response.getStatus());
    }
}
