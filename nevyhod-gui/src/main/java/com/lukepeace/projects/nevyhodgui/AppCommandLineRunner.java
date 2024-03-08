package com.lukepeace.projects.nevyhodgui;

import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.common.util.GlobalConfiguration;
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

    @Override
    public void run(String... args) throws Exception {

        log.info("Build properties info: ");
        boolean devProfile = false;

//        if (globalConfiguration.getIsDev()) {
//            initUsers();
//        }

//        buildProperties.forEach( o -> {
//            log.info("{} = {}", o.getKey(), o.getValue());
//        });
    }

    public void initUsers() throws GeneralException {
        log.info("Database of dummy users");
//        for (UserRoleVO obj : MockDataHelper.dummyList4UserRole()) {
//            log.info("created " + userRoleService.create(obj).toString());
//        }
//        for (UserVO obj : MockDataHelper.dummyList4User()) {
//            log.info("created " + userService.create(obj).toString());
//        }
    }
}
