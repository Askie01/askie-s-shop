package com.askie01.services.registration;

import com.askie01.controllers.UserController;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.askie01.repositories.UserRepository;
import com.askie01.users.User;

@WebServlet("/registration/register")
public class RegisterUserServlet extends HttpServlet {
    private final Logger log = LogManager.getLogger(RegisterUserServlet.class.getName());

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        final UserRepository repository = new UserRepository();
        System.out.println(request.getAttribute("user"));
        final User user = (User) request.getAttribute("user");
        final String login = user.getLogin();
        final String password = user.getPassword();
        final boolean userExists = new UserController()
                .exists(login, password);

        if (userExists) {
            log.warn("'{}' already exists.", user);
            response.sendRedirect("registration.jsp");
        } else {
            repository.save(user);
            log.info("Registered: '{}'", user);
            request.getRequestDispatcher("successful_registration.jsp")
                    .forward(request, response);
        }
    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.sendRedirect("registration.jsp");
    }
}
