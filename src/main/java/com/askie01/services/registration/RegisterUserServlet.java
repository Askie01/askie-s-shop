package com.askie01.services.registration;

import com.askie01.controllers.UserController;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.askie01.repositories.UserRepository;
import com.askie01.entities.User;

import java.time.LocalDate;

@WebServlet("/registration/register_user")
public class RegisterUserServlet extends HttpServlet {
    private final Logger log = LogManager.getLogger(RegisterUserServlet.class.getName());

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        final UserRepository repository = new UserRepository();

        final String login = request.getParameter("login");
        final String password = request.getParameter("password");
        final String username = request.getParameter("username");
        final String firstName = request.getParameter("first-name");
        final String lastName = request.getParameter("last-name");
        final String email = request.getParameter("email");
        final String phone = request.getParameter("phone");
        final LocalDate birthdate = LocalDate.parse(request.getParameter("birthdate"));

        final User user = new User(login, password, username, firstName, lastName, email, phone, birthdate);
        final boolean registrable = new UserController().registrable(user);

        if (registrable) {
            repository.save(user);
            log.info("Registered: '{}'", user);
            response.sendRedirect("../login/login.jsp");
        } else {
            log.warn("'{}' already exists.", user);
            response.sendRedirect("user_registration.jsp");
        }
    }
}
