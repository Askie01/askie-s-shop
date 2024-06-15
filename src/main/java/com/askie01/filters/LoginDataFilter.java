package com.askie01.filters;

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
import com.askie01.validators.UserValidator;

@WebFilter("/login")
public class LoginDataFilter implements Filter {
    private final Logger log = LogManager.getLogger(LoginDataFilter.class.getName());

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final String login = httpRequest.getParameter("login");
        final String password = httpRequest.getParameter("password");
        final UserValidator userValidator = new UserValidator();
        final boolean isValidLoginData = userValidator.isValidLoginData(login, password);

        if (isValidLoginData) {
            log.info("Received valid login data: \nLogin: '{}' \nPassword: '{}'", login, password);
            chain.doFilter(httpRequest, response);
        } else {
            log.warn("Received invalid login data: \nLogin: '{}' \nPassword: '{}'", login, password);
            final HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("login_page.jsp");
        }
    }
}
