package com.askie01.filters.users;

import com.askie01.controllers.RedirectController;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebFilter({"/login/login", "/registration/register_user", "/user/update_account"})
public class PasswordFilter implements Filter {
    private final Logger log = LogManager.getLogger(PasswordFilter.class.getName());

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        final String regex = "^[A-Z][a-zA-Z0-9]{3,25}";
        final String password = request.getParameter("password");
        final boolean validPassword = password.matches(regex);

        if (validPassword) {
            log.info("Valid password: {}", password);
            chain.doFilter(request, response);
        } else {
            log.warn("Invalid password: {}", password);
            final HttpServletRequest httpRequest = (HttpServletRequest) request;
            final HttpServletResponse httpResponse = (HttpServletResponse) response;
            final RedirectController redirectController = new RedirectController();
            redirectController.redirect(httpRequest, httpResponse);
        }
    }
}
