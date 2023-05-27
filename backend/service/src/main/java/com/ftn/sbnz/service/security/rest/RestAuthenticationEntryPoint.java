package com.ftn.sbnz.service.security.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftn.sbnz.service.core.error.ApiError;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper jsonMapper;

    public RestAuthenticationEntryPoint(ObjectMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ApiError apiError = new ApiError(authException.getMessage());
        response.getOutputStream().println(serialize(apiError));
    }

    private String serialize(ApiError apiError) throws JsonProcessingException {
        int[] timestamp = getTimestamp(apiError.getTimestamp());

        HashMap<String, Object> responseMap = new HashMap<>(2);
        responseMap.put("timestamp", timestamp);
        responseMap.put("message", apiError.getMessage());

        return jsonMapper.writeValueAsString(responseMap);
    }

    private int[] getTimestamp(LocalDateTime localDateTime) {
        int year = localDateTime.getYear();
        int month = localDateTime.getMonthValue();
        int day = localDateTime.getDayOfMonth();

        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();
        int second = localDateTime.getSecond();
        int nanosecond = localDateTime.getNano();

        return new int[]{year, month, day, hour, minute, second, nanosecond};
    }
}
