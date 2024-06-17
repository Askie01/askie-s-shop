package com.askie01.filters;

import com.askie01.controllers.UserController;
import com.askie01.repositories.UserRepository;
import com.askie01.users.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebFilter("/registration/register")
public class RegistrationFilter implements Filter {
    private final Logger log = LogManager.getLogger(RegistrationFilter.class.getName());

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        final UserController controller = new UserController();
        final UserRepository repository = new UserRepository();
        final User user = repository.get(request);
        final boolean registrable = controller.registrable(user);

        if (registrable) {
            request.setAttribute("user", user);
            log.info("'{}' is registrable", user);
            chain.doFilter(request, response);
        } else {
            final HttpServletResponse httpResponse = (HttpServletResponse) response;
            log.warn("'{}' already exists", user);
            httpResponse.sendRedirect("registration.jsp");
        }
    }
}
