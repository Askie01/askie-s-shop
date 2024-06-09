package services.login;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repositories.UserRepository;
import users.User;

import java.util.Optional;

@WebServlet("/login/login")
public class LoginServlet extends HttpServlet {
    private final Logger logger = LogManager.getLogger(LoginServlet.class.getName());

    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        final UserRepository repository = new UserRepository();
        final Optional<User> userOptional = repository.find(username, password);

        if (userOptional.isPresent()) {
            final HttpSession session = request.getSession();
            session.setAttribute("user", userOptional.get());
            logger.info("Successfully logged Username: '{}' Password: '{}'", username, password);
            response.sendRedirect("/Servlet_Hibernate_project_war_exploded/user/UserPage.jsp");
        } else {
            logger.warn("Failed to log in Username: '{}' Password: '{}'", username, password);
            response.sendRedirect("login.jsp");
        }
    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.sendRedirect("login.jsp");
    }

}
