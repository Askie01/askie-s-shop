package com.askie01.services.user;

import com.askie01.entities.User;
import com.askie01.repositories.UserRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;

@WebServlet("/user/delete_account")
public class DeleteAccountServlet extends HttpServlet {
    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        final UserRepository repository = new UserRepository();
        final HttpSession session = httpRequest.getSession();
        final User user = (User) session.getAttribute("user");
        repository.remove(user);
        session.invalidate();
        httpResponse.sendRedirect("../home.jsp");
    }
}
