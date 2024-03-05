package com.lukepeace.projects.nevyhodcore.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component @Profile("!dev") @Slf4j
public class HeartbeatScheduler {

//    @Autowired private AuditInfoHelper auditInfoHelper;

    @Scheduled(cron = "${heartbeat.cron}")
    public void logHeatbeat() {
        log.info("Scheduled task logHeartbeat START");

        // TODO save info into database AUDIT_INFO
//        log.info("remote addr: " + auditInfoHelper.getRemoteAddress());
//        log.info("req uri: " + auditInfoHelper.getRequestURI());

        log.info("Scheduled task logHeartbeat END");
    }

}
