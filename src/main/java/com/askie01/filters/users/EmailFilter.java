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
public class EmailFilter implements Filter {
    private final Logger log = LogManager.getLogger(EmailFilter.class.getName());

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        final String regex = "^[a-zA-Z0-9._-]{3,40}@[a-zA-Z.]{2,15}$";
        final String email = request.getParameter("email");
        final boolean validEmail = email.matches(regex);

        if (validEmail) {
            log.info("Valid email: {}", email);
            chain.doFilter(request, response);
        } else {
            log.warn("Invalid email: {}", email);
            final HttpServletRequest httpRequest = (HttpServletRequest) request;
            final HttpServletResponse httpResponse = (HttpServletResponse) response;
            final RedirectController redirectController = new RedirectController();
            redirectController.redirect(httpRequest, httpResponse);
        }
    }
}
