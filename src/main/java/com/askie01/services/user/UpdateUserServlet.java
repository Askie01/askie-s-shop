package com.askie01.services.user;

import com.askie01.repositories.UserRepository;
import com.askie01.entities.User;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.time.LocalDate;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10
)
@WebServlet("/user/update_account")
public class UpdateUserServlet extends HttpServlet {
    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        final User user = (User) httpRequest.getSession().getAttribute("user");

        final String password = httpRequest.getParameter("password");
        final String firstName = httpRequest.getParameter("firstName");
        final String lastName = httpRequest.getParameter("lastName");
        final String email = httpRequest.getParameter("email");
        final String phone = httpRequest.getParameter("phone");
        final String birthdate = httpRequest.getParameter("birthdate");
        final byte[] profilePicture = httpRequest
                .getPart("profile-picture")
                .getInputStream()
                .readAllBytes();

        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setBirthdate(LocalDate.parse(birthdate));

        if (profilePicture.length > 0) {
            user.setProfilePicture(profilePicture);
        }

        final UserRepository repository = new UserRepository();
        repository.update(user);

        httpResponse.sendRedirect("account.jsp");
    }
}
