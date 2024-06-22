package com.askie01.filters.users;

import com.askie01.controllers.RedirectController;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebFilter({"/registration/register_user", "/user/update_account"})
public class PhoneFilter implements Filter {
    private final Logger log = LogManager.getLogger(PhoneFilter.class.getName());

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        final String regex = "^[0-9]{9}$";
        final String phone = request.getParameter("phone");
        final boolean validPhone = phone.matches(regex);

        if (validPhone) {
            log.info("Valid phone: {}", phone);
            chain.doFilter(request, response);
        } else {
            log.warn("Invalid phone: {}", phone);
            final HttpServletRequest httpRequest = (HttpServletRequest) request;
            final HttpServletResponse httpResponse = (HttpServletResponse) response;
            final RedirectController redirectController = new RedirectController();
            redirectController.redirect(httpRequest, httpResponse);
        }
    }
}
