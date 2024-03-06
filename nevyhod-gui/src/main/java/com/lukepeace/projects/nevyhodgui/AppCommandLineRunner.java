package com.lukepeace.projects.nevyhodgui;

import com.lukepeace.projects.nevyhodcore.service.UserService;
import com.lukepeace.projects.nevyhodcore.util.MockDataHelper;
import com.lukepeace.projects.nevyhodcore.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class AppCommandLineRunner implements CommandLineRunner {

//    @Autowired private BuildProperties buildProperties;
    @Autowired private Environment environment;
    @Autowired private UserService userService;

    @Override
    public void run(String... args) throws Exception {

        log.info("Build properties info: ");
        boolean devProfile = false;

        if (Arrays.asList(environment.getActiveProfiles()).contains("DEV_PROFILE")) {
            devProfile = true;
        }

        if (devProfile) {
            log.info("Database of dummy users");
            for (UserVO obj : MockDataHelper.dummyList()) {
                log.info("created " + userService.create(obj).toString());
            }
        }
//        buildProperties.forEach( o -> {
//            log.info("{} = {}", o.getKey(), o.getValue());
//        });


    }
}
