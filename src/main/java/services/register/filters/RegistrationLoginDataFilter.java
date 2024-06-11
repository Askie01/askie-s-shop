package services.register.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import validators.UserValidator;

@WebFilter("/register/register")
public class RegistrationLoginDataFilter implements Filter {
    private final Logger log = LogManager.getLogger(RegistrationLoginDataFilter.class.getName());

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        final UserValidator userValidator = new UserValidator();

        final String login = request.getParameter("login");
        final String password = request.getParameter("password");

        if (userValidator.isValidLoginData(login, password)) {
            log.info("Received valid login data. \nLogin: '{}' \npassword: '{}'", login, password);
            chain.doFilter(request, response);
        } else {
            log.warn("Received invalid login data. \nLogin: '{}' \npassword: '{}'", login, password);
            final HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("register.jsp");
        }
    }
}
