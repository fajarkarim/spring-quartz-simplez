# Quartz Scheduler
Quartz Scheduler is an open source library, enables enterprise to schedule a job/task at a specific date and time. It provides operations to scheduling/unscheduling jobs, starting/stopping/pausing the scheduler. [Official Website Quartz]

to use the quartz scheduler we must use maven depedency:
```
<dependency>
	<groupId>org.quartz-scheduler</groupId>
	<artifactId>quartz</artifactId>
	<version>2.2.1</version>
</dependency>
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-context-support</artifactId>
	<version>${spring-framework.version}</version>
</dependency>
```
We need spring-context-support because quartz scheduler not from spring ecosystem.

### How to setup Quartz Scheduler
1. Create class AutowiringSpringBeanJobFactory and extends SpringBeanJobFactory implements ApplicationContextAware
2. Create class NameJob implements Job and set the job
3. Create class QuartzConfiguration and give annotation Configuration
4. Set context for jobFactory
5. Setup SchedulerFactoryBean for jobFactory
6. and last register all scheduler.

### You should have Quartz Table
After exploration, we should have quartz table because Quartz table will save our job and trigger and it's help us when our system down we can run our schedule automatically. So what should i do to have Quartz Table. If you download quartz zip from official website there are sql file in folder /docs/dbTables you can choose which one your database schema. After that just set the quartz.properties and dataSource to SchedulerFactoryBean.

**Happy Code**

[Official Website Quartz]: http://www.quartz-scheduler.org
