package com.askie01.services.login;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.SneakyThrows;
import com.askie01.users.User;

@WebServlet("/login/login")
public class LoginServlet extends HttpServlet {

    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        final User user = (User) request.getAttribute("user");
        final HttpSession session = request.getSession();
        session.setAttribute("user", user);
        response.sendRedirect("../user/user.jsp");
    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.sendRedirect("login.jsp");
    }
}
