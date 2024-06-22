package com.askie01.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

public class RedirectController {
    @SneakyThrows
    public void redirect(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        final String servletPath = httpRequest.getServletPath();

        if (servletPath.contains("/login")) {
            httpResponse.sendRedirect("login.jsp");
        } else if (servletPath.contains("/register_user")) {
            httpResponse.sendRedirect("user_registration.jsp");
        } else if (servletPath.contains("/edit_account")) {
            httpResponse.sendRedirect("/edit_account.jsp");
        } else {
            //  Invalid call - move to home page
            httpResponse.sendRedirect("../home.jsp");
        }
    }
}
