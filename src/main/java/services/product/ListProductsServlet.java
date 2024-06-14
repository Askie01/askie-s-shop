package services.product;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

@WebServlet("/user/list_products")
public class ListProductsServlet extends HttpServlet {
    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
