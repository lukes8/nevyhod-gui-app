package com.lukepeace.projects.nevyhodcore.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuditInfoHelper {
    @Autowired
    private HttpServletRequest request;

    public String getRemoteAddress() {
        return request.getRemoteAddr();
    }

    public String getRemoteHost() {
        return request.getRemoteHost();
    }

    public String getRequestURI() {
        return request.getRequestURI();
    }
    public String getSessionId() {
        return request.getRequestedSessionId();
    }
}
