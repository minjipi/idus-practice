package com.hongminji.idus.jwt;

import org.json.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.hongminji.idus.config.BaseResponseStatus.INVALID_USER_JWT;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        JSONObject responseJson = new JSONObject();
        responseJson.put("success", INVALID_USER_JWT.isSuccess());
        responseJson.put("code", INVALID_USER_JWT.getCode());
        responseJson.put("message", INVALID_USER_JWT.getMessage());

        response.getWriter().print(responseJson);

    }
}