package services.register.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import users.User;
import validators.UserValidator;

import java.time.LocalDate;

@WebFilter("/register/register")
public class RegisterFilter implements Filter {
    private final Logger log = LogManager.getLogger(RegisterFilter.class.getName());

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        final UserValidator userValidator = new UserValidator();

        final String username = servletRequest.getParameter("username");
        final String password = servletRequest.getParameter("password");
        final String firstName = servletRequest.getParameter("firstName");
        final String lastName = servletRequest.getParameter("lastName");
        final String birthdateString = servletRequest.getParameter("birthdate");
        final LocalDate birthdate = LocalDate.parse(birthdateString);
        final String email = servletRequest.getParameter("email");
        final String phoneNumber = servletRequest.getParameter("phoneNumber");
        final User user = new User(username, password, firstName, lastName, birthdate, email, phoneNumber);

        if (userValidator.isValid(user)) {
            servletRequest.setAttribute("user", user);
            log.info("'{}' is a valid user.", user);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            log.warn("'{}' is an invalid user.", user);
            final HttpServletResponse HttpServletResponse = (HttpServletResponse) servletResponse;
            HttpServletResponse.sendRedirect("register.jsp");
        }
    }
}
