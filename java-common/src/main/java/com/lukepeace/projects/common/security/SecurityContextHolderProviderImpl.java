package com.lukepeace.projects.common.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jdbc.support.ConnectionUsernameProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component @Slf4j
public class SecurityContextHolderProviderImpl implements SecurityContextHolderProvider {
    @Override
    public String getUserName() {

        CurrentUser currentUser = getCurrentUser();
        if (currentUser != null) {
            return currentUser.getUsername();
        }
        return "none";
    }

    @Override
    public CurrentUser getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Object principal = auth.getPrincipal();
            Set<String> availableRoles = ((CurrentUser) principal).getAvailableRoles();
            if (availableRoles != null) {
                log.info("Available roles: " + availableRoles.stream().collect(Collectors.joining()));
            }
            return (CurrentUser)principal;
        }
        return null;
    }
}
