package com.askie01.services.login;

import com.askie01.repositories.UserRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.SneakyThrows;
import com.askie01.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

@WebServlet("/login/login")
public class LoginServlet extends HttpServlet {
    private final Logger log = LogManager.getLogger(LoginServlet.class.getName());

    @SneakyThrows
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        final String login = httpRequest.getParameter("login");
        final String password = httpRequest.getParameter("password");
        final Optional<User> optionalUser = new UserRepository()
                .get(login, password);
        final boolean userExists = optionalUser.isPresent();

        if (userExists) {
            final HttpSession session = httpRequest.getSession();
            final User user = optionalUser.get();
            session.setAttribute("user", user);
            log.info("Logged user: {}", user);
            httpResponse.sendRedirect("../user/account.jsp");
        } else {
            httpResponse.sendRedirect("login.jsp");
        }
    }
}
