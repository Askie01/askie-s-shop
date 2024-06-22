package com.askie01.services.user;

import com.askie01.entities.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.OutputStream;

@WebServlet("/user/user_profile_picture")
public class UserProfilePictureServlet extends HttpServlet {
    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        final User user = (User) httpRequest
                .getSession()
                .getAttribute("user");

        final OutputStream outputStream = httpResponse.getOutputStream();
        httpResponse.setContentType("image/jpeg");
        outputStream.write(user.getProfilePicture());
        outputStream.close();
    }
}
