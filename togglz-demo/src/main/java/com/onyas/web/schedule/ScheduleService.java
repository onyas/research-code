package com.onyas.web.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class ScheduleService {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleService.class);

    @Scheduled(fixedRateString = "1000")
    public void schedule() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        System.out.println("fire");
        logger.info("fire");
    }
}
