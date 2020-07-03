package com.quartz.quartzscheduler;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Component
public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        System.out.println("Hello Quartz! " + dataMap.getString("name") + ", " + dataMap.getString("group"));
    }
}
