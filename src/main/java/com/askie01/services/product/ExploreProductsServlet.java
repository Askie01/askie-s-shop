package com.askie01.services.product;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

@WebServlet("/user/explore_products")
public class ExploreProductsServlet extends HttpServlet {

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}
