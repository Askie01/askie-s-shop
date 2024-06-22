package com.askie01.filters.cache;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

@WebFilter({"/user/account.jsp", "/user/edit_account.jsp", "/user/list_products.jsp", "/user/purchase_history.jsp", "/user/sales_history.jsp"})
public class UserCacheControlFilter implements Filter {
    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final Object user = httpRequest.getSession().getAttribute("user");
        final boolean userExists = user != null;

        if (!userExists) {
            httpResponse.sendRedirect("../home.jsp");
        }

        httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        httpResponse.setHeader("Pragma", "no-cache");
        httpResponse.setDateHeader("Expires", 0);
        chain.doFilter(request, response);
    }
}
