package services.logout;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;

@WebServlet("/user/logout")
public class LogoutServlet extends HttpServlet {
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        final HttpSession session = request.getSession();
//        session.removeAttribute("user");
        session.invalidate();
        response.sendRedirect("../welcome/welcome.jsp");
    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.sendRedirect("../login/login_page.jsp");
    }
}
