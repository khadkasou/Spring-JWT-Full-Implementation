package com.souraj.demo3.configuration;

import com.google.gson.Gson;
import com.souraj.demo3.exceptions.CustomAccessDeniedExceptionResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        CustomAccessDeniedExceptionResponse loginResponse = new CustomAccessDeniedExceptionResponse();
        String jsonLoginResponse = new Gson().toJson(loginResponse);

        response.setContentType("application/json");
        response.setStatus(403);
        response.getWriter().print(jsonLoginResponse);
    }
}
