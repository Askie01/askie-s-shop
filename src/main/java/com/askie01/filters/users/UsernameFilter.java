package com.askie01.filters.users;

import com.askie01.controllers.RedirectController;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebFilter({"/registration/register_user"})
public class UsernameFilter implements Filter {
    private final Logger log = LogManager.getLogger(UsernameFilter.class.getName());

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        final String regex = "^[A-Z][a-zA-Z0-9]{3,25}";
        final String username = request.getParameter("username");
        final boolean validUsername = username.matches(regex);

        if (validUsername) {
            log.info("Valid username: {}", username);
            chain.doFilter(request, response);
        } else {
            log.warn("Invalid username: {}", username);
            final HttpServletRequest httpRequest = (HttpServletRequest) request;
            final HttpServletResponse httpResponse = (HttpServletResponse) response;
            final RedirectController redirectController = new RedirectController();
            redirectController.redirect(httpRequest, httpResponse);
        }
    }
}
