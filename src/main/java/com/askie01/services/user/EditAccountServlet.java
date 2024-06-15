package com.askie01.services.user;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

@WebServlet("/edit_account")
public class EditAccountServlet extends HttpServlet {
    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
