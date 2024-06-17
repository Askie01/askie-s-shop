package com.askie01.filters;

import com.askie01.repositories.UserRepository;
import com.askie01.users.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

@WebFilter("/login/login")
public class LoginFilter implements Filter {
    private final Logger log = LogManager.getLogger(LoginFilter.class.getName());

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        final String login = request.getParameter("login");
        final String password = request.getParameter("password");
        final Optional<User> optionalUser = new UserRepository()
                .get(login, password);
        final boolean userExists = optionalUser.isPresent();

        if (userExists) {
            request.setAttribute("user", optionalUser.get());
            log.info("Valid Login: '{}' and Password: '{}'", login, password);
            chain.doFilter(request, response);
        } else {
            log.warn("Invalid Login: '{}' and Password: '{}'", login, password);
            final HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("login.jsp");
        }
    }
}
