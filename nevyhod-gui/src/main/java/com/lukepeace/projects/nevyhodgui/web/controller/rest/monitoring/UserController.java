package com.lukepeace.projects.nevyhodgui.web.controller.rest.monitoring;


import com.lukepeace.projects.common.security.SecurityContextHolderProvider;
import com.lukepeace.projects.nevyhodcore.util.AuditInfoHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping(("/rest/api/user")) @Slf4j
public class UserController {

    @Autowired private AuditInfoHelper auditInfoHelper;
    @Autowired private SecurityContextHolderProvider securityContextHolderProvider;

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {

        String username = securityContextHolderProvider.getCurrentUser().getUsername();
        String roles = securityContextHolderProvider.getCurrentUser().getAuthorities().stream().map(o -> o.getAuthority()).collect(Collectors.joining(", "));
        log.info("Audit info : " + auditInfoHelper.getRemoteAddress());
        log.info("Audit info : " + auditInfoHelper.getRequestURI());
        log.info("Audit info : " + roles);
        return username;
    }
}
