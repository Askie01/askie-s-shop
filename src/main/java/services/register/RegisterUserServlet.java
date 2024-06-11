package services.register;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repositories.UserRepository;
import users.User;

import java.time.LocalDate;
import java.util.Optional;

@WebServlet("/register/register")
public class RegisterUserServlet extends HttpServlet {
    private final Logger log = LogManager.getLogger(RegisterUserServlet.class.getName());

    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        final UserRepository repository = new UserRepository();
        final String login = request.getParameter("login");
        final String password = request.getParameter("password");
        final String username = request.getParameter("username");

        final Optional<User> optionalUser = repository.find(login, password);

        if (optionalUser.isEmpty()) {
            final String firstName = request.getParameter("firstName");
            final String lastName = request.getParameter("lastName");
            final String email = request.getParameter("email");
            final String phone = request.getParameter("phone");
            final LocalDate birthdate = LocalDate.parse(request.getParameter("birthdate"));
            final User user = new User(login, password, username, firstName, lastName, email, phone, birthdate);

            repository.save(user);
            log.info("Successfully registered user: '{}'", username);
            request.setAttribute("successfulRegistration", true);
            request.getRequestDispatcher("successful_registration.jsp")
                    .forward(request, response);

        } else {
            log.warn("User already exists: '{}'.", username);
            response.sendRedirect("register.jsp");
        }
    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.sendRedirect("register.jsp");
    }
}
