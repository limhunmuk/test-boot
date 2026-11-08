package kr.go.egov.pungdong.web.config;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import kr.go.egov.pungdong.domain.member.service.MemberService;
import kr.go.egov.pungdong.web.security.LoginSuccessHandler;

class LoginSuccessHandlerTest {
    @Test void redirectsHomeAndClearsPreviouslySavedJspRequest() throws Exception {
        MemberService members = mock(MemberService.class);
        LoginSuccessHandler handler = new LoginSuccessHandler(members);
        var cache = new HttpSessionRequestCache();
        var blocked = new MockHttpServletRequest("GET", "/WEB-INF/views/login.jsp");
        blocked.setQueryString("error&continue");
        cache.saveRequest(blocked, new MockHttpServletResponse());
        var login = new MockHttpServletRequest("POST", "/login");
        login.setSession(blocked.getSession());
        var response = new MockHttpServletResponse();
        handler.onAuthenticationSuccess(login, response,
            new UsernamePasswordAuthenticationToken("admin", ""));
        assertEquals("/", response.getRedirectedUrl());
        assertNull(cache.getRequest(login, response));
        verify(members).updateLastLogin(eq("admin"), anyString());
    }
}
