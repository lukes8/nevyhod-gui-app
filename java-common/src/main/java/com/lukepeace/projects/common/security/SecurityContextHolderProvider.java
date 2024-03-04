package com.lukepeace.projects.common.security;

import org.springframework.data.jdbc.support.ConnectionUsernameProvider;

public interface SecurityContextHolderProvider extends ConnectionUsernameProvider {
    CurrentUser getCurrentUser();
}
