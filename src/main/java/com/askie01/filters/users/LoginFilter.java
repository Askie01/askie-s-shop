package com.askie01.filters.users;

import com.askie01.controllers.RedirectController;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebFilter({"/login/login", "/registration/register_user"})
public class LoginFilter implements Filter {
    private final Logger log = LogManager.getLogger(LoginFilter.class.getName());

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        final String regex = "^[A-Z][a-zA-Z0-9]{3,25}";
        final String login = request.getParameter("login");
        final boolean validLogin = login.matches(regex);

        if (validLogin) {
            log.info("Valid login: {}", login);
            chain.doFilter(request, response);
        } else {
            log.warn("invalid login: {}", login);
            final HttpServletRequest httpRequest = (HttpServletRequest) request;
            final HttpServletResponse httpResponse = (HttpServletResponse) response;
            final RedirectController redirectController = new RedirectController();
            redirectController.redirect(httpRequest, httpResponse);
        }
    }
}
