package com.askie01.filters.cache;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

@WebFilter({"/login/login.jsp", "/registration/user_registration.jsp", "/home.jsp"})
public class CacheControlFilter implements Filter {
    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final String servletPath = httpRequest.getServletPath();
        final boolean userExists = httpRequest.getSession().getAttribute("user") != null;
        final boolean containsLogin = servletPath.contains("login");
        final boolean containsRegistration = servletPath.contains("registration");

        if (userExists) {
            if (containsLogin || containsRegistration) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/user/account.jsp");
                return;
            }
        }

        httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        httpResponse.setHeader("Pragma", "no-cache");
        httpResponse.setDateHeader("Expires", 0);
        chain.doFilter(request, response);
    }
}
