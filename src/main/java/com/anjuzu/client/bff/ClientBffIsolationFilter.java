package com.anjuzu.client.bff;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ClientBffIsolationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        if (RouteGuard.isBlocked(request.getRequestURI())) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "admin routes are not exposed on client-bff");
            return;
        }
        chain.doFilter(request, response);
    }
}
