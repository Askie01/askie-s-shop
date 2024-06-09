package services.register;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repositories.UserRepository;
import users.User;

import java.util.Optional;

@WebServlet("/register/register")
public class RegisterServlet extends HttpServlet {
    private final Logger logger = LogManager.getLogger(RegisterServlet.class.getName());

    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        final UserRepository repository = new UserRepository();
        final User user = (User) request.getAttribute("user");
        final Optional<User> optionalUser = repository.find(user.getUsername());

        if (optionalUser.isEmpty()) {
            repository.save(user);
            logger.info("Successfully registered user: '{}'", user);
            request.setAttribute("successfulRegistration", true);
            request.getRequestDispatcher("successful_registration.jsp")
                    .forward(request, response);

        } else {
            logger.warn("User: '{}' already exists.", user);
            response.sendRedirect("register.jsp");
        }
    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.sendRedirect("register.jsp");
    }
}
