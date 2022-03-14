package com.cwt.training.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AppBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	@Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.getOutputStream().println(authException.getMessage());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
	
	@Override
    public void afterPropertiesSet() {
        setRealmName("SpringBootTest");
        super.afterPropertiesSet();
    }
	
}
