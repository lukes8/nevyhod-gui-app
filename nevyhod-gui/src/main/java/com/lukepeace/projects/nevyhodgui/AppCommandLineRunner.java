package com.lukepeace.projects.nevyhodgui;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppCommandLineRunner implements CommandLineRunner {

//    @Autowired private BuildProperties buildProperties;

    @Override
    public void run(String... args) throws Exception {

        log.info("Build properties info: ");
//        buildProperties.forEach( o -> {
//            log.info("{} = {}", o.getKey(), o.getValue());
//        });


    }
}
