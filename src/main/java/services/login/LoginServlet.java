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
    private final Logger log = LogManager.getLogger(LoginServlet.class.getName());

    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        final String login = request.getParameter("login");
        final String password = request.getParameter("password");
        final UserRepository repository = new UserRepository();
        final Optional<User> userOptional = repository.find(login, password);

        if (userOptional.isPresent()) {
            final HttpSession session = request.getSession();
            final User user = userOptional.get();
            session.setAttribute("user", user);
            log.info("Successfully logged user: '{}'", user.getUsername());
            response.sendRedirect("../user/user_page.jsp");
        } else {
            log.warn("Failed to log in user with login data: \nLogin: '{}' \nPassword: '{}'", login, password);
            response.sendRedirect("login_page.jsp");
        }
    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.sendRedirect("login_page.jsp");
    }

}
