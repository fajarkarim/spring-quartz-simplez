package com.quartz.quartzscheduler;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/schedule")
public class SchedulesController {

    private final SchedulesService service;

    @Autowired
    public SchedulesController(SchedulesService service) {
        this.service = service;
    }

    @GetMapping
    public void updateTrigger() throws SchedulerException {
        service.updateTriggerSchedule();
    }

}
