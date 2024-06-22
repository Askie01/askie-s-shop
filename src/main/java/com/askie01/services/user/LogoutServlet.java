package com.askie01.services.user;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;

@WebServlet("/user/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        final HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("../home.jsp");
    }
}
