package com.lukepeace.projects.common.security;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.*;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.util.Arrays;

//@Component
public class CorsFilter extends org.springframework.web.filter.CorsFilter {
    private UrlBasedCorsConfigurationSource corsConfigurationSource;
    private CorsProcessor processor = new DefaultCorsProcessor();

    public CorsFilter(CorsConfigurationSource configSource) {
        super(configSource);
    }

    @PostConstruct
    private void configureCorsPolicies() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowCredentials(false);
        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", "app-id", "platform", "sentry-trace"));
        corsConfig.setAllowedMethods(Arrays.asList("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH", "OPTION"));
        corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfig);
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        CorsConfiguration corsConfiguration = corsConfigurationSource.getCorsConfiguration(request);
//        Boolean isValid = this.processor.processRequest(corsConfiguration, request, response);
//        if (!isValid || CorsUtils.isPreFlightRequest(request)) {
//            if (response.getStatus() == 200) {
//                response.setHeader(HttpHeaders.ALLOW, "HEAD, GET, PUT, POST, DELETE, PATCH, OPTION");
//            }
//
//            return;
//        }
//        filterChain.doFilter(request, response);
    }
}