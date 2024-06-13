package filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import validators.UserValidator;

@WebFilter("/register")
public class RegistrationPersonalDataFilter implements Filter {

    private final Logger log = LogManager.getLogger(RegistrationPersonalDataFilter.class.getName());

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        final String username = request.getParameter("username");
        final String firstName = request.getParameter("firstName");
        final String lastName = request.getParameter("lastName");
        final String email = request.getParameter("email");
        final String phone = request.getParameter("phone");
        final String birthdate = request.getParameter("birthdate");

        final UserValidator userValidator = new UserValidator();
        final boolean isValidPersonalData = userValidator.isValidPersonalData(username, firstName, lastName, email, phone, birthdate);

        if (isValidPersonalData) {
            log.info("Received valid personal data for '{}'", username);
            chain.doFilter(request, response);
        } else {
            log.warn("Received an invalid personal data for '{}'", username);
            final HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("registration_page.jsp");
        }
    }
}
