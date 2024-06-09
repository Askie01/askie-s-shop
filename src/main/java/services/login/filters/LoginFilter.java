package services.login.filters;

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

import java.util.regex.Pattern;

@WebFilter("/login")
public class LoginFilter implements Filter {
    private final Logger logger = LogManager.getLogger(LoginFilter.class.getName());

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");

        if (isValid(username, password)) {
            logger.info("Username: '{}', Password: '{}' are valid login information.", username, password);
            filterChain.doFilter(request, servletResponse);
        } else {
            logger.warn("Username: '{}', Password: '{}' are invalid login information.", username, password);
            final HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect("login.jsp");
        }
    }

    private boolean isValid(String username, String password) {
        final String regex = "^[A-Z][a-zA-Z0-9]{3,25}";
        final boolean hasValidUsername = Pattern.matches(regex, username);
        final boolean hasValidPassword = Pattern.matches(regex, password);
        return hasValidUsername && hasValidPassword;
    }
}
