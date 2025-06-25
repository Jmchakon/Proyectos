package com.tuempresa.sistemadereservas;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ContentTypeLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof HttpServletRequest httpRequest) {
            String contentType = httpRequest.getHeader("Content-Type");
            System.out.println("🔍 Content-Type recibido: " + contentType);
        }

        chain.doFilter(request, response);
    }
}
