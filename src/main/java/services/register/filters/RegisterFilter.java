package services.register.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import validators.UsernamePasswordValidator;

@WebFilter("/register/register")
public class RegisterFilter implements Filter {
    @Override
    @SneakyThrows
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        System.out.println("Message from register filter.");
        final UsernamePasswordValidator validator = new UsernamePasswordValidator();
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");

        if (validator.isValid(username, password)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect("register.jsp");
        }
    }
}
