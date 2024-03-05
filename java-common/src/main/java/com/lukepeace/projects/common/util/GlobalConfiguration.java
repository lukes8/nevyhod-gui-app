package com.lukepeace.projects.common.util;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@org.springframework.context.annotation.Configuration
@Data
public class GlobalConfiguration {

    @NotNull
    private Boolean isDev;
    @NotNull
    @Value("${configuration.profile:DEV_PROFILE}")
    private String profileName;

    public Boolean getIsDev() {
        return profileName != null && profileName.equalsIgnoreCase("DEV_PROFILE");
    }
}
