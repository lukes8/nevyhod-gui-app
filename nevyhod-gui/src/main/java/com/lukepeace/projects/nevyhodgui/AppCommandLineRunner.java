package com.lukepeace.projects.nevyhodgui;

import com.lukepeace.projects.common.util.GlobalConfiguration;
import com.lukepeace.projects.nevyhodgui.web.controller.rest.monitoring.TestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppCommandLineRunner implements CommandLineRunner {

//    @Autowired private BuildProperties buildProperties;
//    @Autowired private UserService userService;
    @Autowired private GlobalConfiguration globalConfiguration;

    @Autowired private TestController testController;

    @Override
    public void run(String... args) throws Exception {

        log.info("Build properties info: ");
        boolean devProfile = false;

        if (globalConfiguration.getIsDev()) {
            testController.initTestDatabase();
        }
    }
}
