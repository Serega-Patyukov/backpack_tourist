package ru.patyukov.backpack_tourist.web.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.patyukov.backpack_tourist.authentication.LoginPasswordAuthentication;
import ru.patyukov.backpack_tourist.web.constant.WebConstant;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class InitialAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager manager;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            Authentication a = new LoginPasswordAuthentication(login, password);
            a = manager.authenticate(a);

            var auth = new LoginPasswordAuthentication(a.getName(), null, a.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);

            response.sendRedirect(WebConstant.VERSION_URL + "/user");
        } catch (BadCredentialsException exc) {
            response.sendRedirect(WebConstant.VERSION_URL + "/home?msg=error");
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        return !path.equals(WebConstant.VERSION_URL + "/login");
    }
}
