package com.quartz.quartzscheduler;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerKey.triggerKey;

@Service
public class SchedulesService {

    @Autowired
    private SchedulesRepository repository;

    @Autowired
    @Lazy
    private SchedulerFactoryBean schedulerFactoryBean;

    public List<Schedule> fetchAllSchedules() {
        return repository.findAll();
    }

    public void updateTriggerSchedule() throws SchedulerException {
        String cronSchedule = "3/10 * * * * ?";

        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        Trigger oldTrigger = scheduler.getTrigger(triggerKey("2", "2"));

        TriggerBuilder tb = oldTrigger.getTriggerBuilder();

        Trigger newTrigger = tb.withSchedule(
                cronSchedule(cronSchedule)
                        .withMisfireHandlingInstructionFireAndProceed())
                .build();

        scheduler.rescheduleJob(oldTrigger.getKey(), newTrigger);
    }
}
