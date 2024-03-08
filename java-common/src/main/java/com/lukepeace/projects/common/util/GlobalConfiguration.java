package com.lukepeace.projects.common.util;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@org.springframework.context.annotation.Configuration
@Data
public class GlobalConfiguration {

    @NotNull
    private Boolean isDev;
    @NotNull
    @Value("${configuration.profile:DEV_PROFILE}")
    private String profileName;
    @NotNull
    @Value("${test.user.username:luke}")
    private String testUser;
    @Value("${test.user.password:noop}")
    private String testPassword;
    public final String DEV_PROFILE = "DEV_PROFILE";
    @Autowired
    private Environment environment;

    public Boolean getIsDev() {
        if (Arrays.asList(environment.getActiveProfiles()).contains(DEV_PROFILE)) {
            return true;
        }
        return false;
    }
}
