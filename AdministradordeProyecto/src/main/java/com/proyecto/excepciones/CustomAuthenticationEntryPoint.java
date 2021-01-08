package com.proyecto.excepciones;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {
       res.addHeader("Authentication", "Error");
       res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
       PrintWriter write = res.getWriter();
       write.print("HTT Status 401 -" + authException.getMessage());
    }
}
