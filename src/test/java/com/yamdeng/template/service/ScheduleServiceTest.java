package com.yamdeng.template.service;

import com.yamdeng.template.BootStandardApplication;
import com.yamdeng.template.service.schedule.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BootStandardApplication.class)
@Slf4j
public class ScheduleServiceTest {

    @Autowired
    private ScheduleService scheduleService;

    @Test
    void createDayCommute() {
        scheduleService.createDayCommute("20221203");
    }

}
