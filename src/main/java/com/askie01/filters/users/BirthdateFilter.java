package com.askie01.filters.users;

import com.askie01.controllers.RedirectController;
import com.askie01.controllers.UserController;
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
public class BirthdateFilter implements Filter {
    private final Logger log = LogManager.getLogger(BirthdateFilter.class.getName());

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        final String birthdate = request.getParameter("birthdate");
        final boolean validBirthdate = new UserController().isValid(birthdate);

        if (validBirthdate) {
            log.info("Valid birthdate: {}", birthdate);
            chain.doFilter(request, response);
        } else {
            log.warn("Invalid birthdate: {}", birthdate);
            final HttpServletRequest httpRequest = (HttpServletRequest) request;
            final HttpServletResponse httpResponse = (HttpServletResponse) response;
            final RedirectController redirectController = new RedirectController();
            redirectController.redirect(httpRequest, httpResponse);
        }
    }
}
