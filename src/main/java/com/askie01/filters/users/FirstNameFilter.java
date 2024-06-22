package com.askie01.filters.users;

import com.askie01.controllers.RedirectController;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebFilter({"/registration/register_user", "/user/update_account"})
public class FirstNameFilter implements Filter {
    private final Logger log = LogManager.getLogger(FirstNameFilter.class.getName());

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        final String regex = "^[A-Z][a-zA-Z]{3,25}";
        final String firstName = request.getParameter("first-name");
        final boolean validFirstName = firstName.matches(regex);

        if (validFirstName) {
            log.info("Valid firstName: {}", firstName);
            chain.doFilter(request, response);
        } else {
            log.warn("Invalid firstName: {}", firstName);
            final HttpServletRequest httpRequest = (HttpServletRequest) request;
            final HttpServletResponse httpResponse = (HttpServletResponse) response;
            final RedirectController redirectController = new RedirectController();
            redirectController.redirect(httpRequest, httpResponse);
        }
    }
}
