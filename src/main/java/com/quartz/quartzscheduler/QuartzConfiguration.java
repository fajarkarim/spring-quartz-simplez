package com.quartz.quartzscheduler;

import org.quartz.*;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@Configuration
@EnableScheduling
public class QuartzConfiguration {

    @Autowired
    private SchedulesService service;

    @Bean
    public JobFactory jobFactory(ApplicationContext context) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(context);

        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource, JobFactory jobFactory) throws Exception {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();

        factory.setOverwriteExistingJobs(true);
        factory.setJobFactory(jobFactory);
        factory.setDataSource(dataSource);
        factory.setQuartzProperties(quartzProperties());
        factory.afterPropertiesSet();

        Scheduler scheduler = factory.getScheduler();

        scheduler.start();
        registerAllScheduler(scheduler);

        return factory;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();

        return propertiesFactoryBean.getObject();
    }

    private void registerAllScheduler(Scheduler scheduler) throws SchedulerException {
        List<Schedule> schedules = service.fetchAllSchedules();

        for (Schedule schedule : schedules) {
            if (!scheduler.checkExists(jobKey(schedule.getName_job(), schedule.getGroup_job()))) {
                JobDetail jobDetail = jobDetail(schedule.getName_job(), schedule.getGroup_job());
                Trigger trigger = trigger(schedule.getName_trigger(), schedule.getGroup_trigger(), schedule.getCron_schedule());

                scheduler.scheduleJob(jobDetail, trigger);
            }
        }
    }

    private JobKey jobKey(String name_job, String group_job) {
        return new JobKey(name_job, group_job);
    }

    private JobDetail jobDetail(String name_job, String group_job) {

        return JobBuilder.newJob(HelloJob.class)
                .storeDurably()
                .withIdentity(jobKey(name_job, group_job))
                .usingJobData("name", name_job)
                .usingJobData("group", group_job)
                .requestRecovery(true)
                .build();
    }

    private Trigger trigger(String name_trigger, String group_trigger, String cron_schedule) {
        return TriggerBuilder
                .newTrigger()
                .withIdentity(name_trigger, group_trigger)
                .withSchedule(cronSchedule(cron_schedule))
                .build();
    }
}
