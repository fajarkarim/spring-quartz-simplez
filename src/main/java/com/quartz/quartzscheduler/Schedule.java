package com.quartz.quartzscheduler;

import javax.persistence.*;

@Entity
@Table(name = "quartz_job_detail")
class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name_job;

    @Column
    private String group_job;

    @Column
    private String name_trigger;

    @Column
    private String group_trigger;

    @Column
    private String cron_schedule;

    public Schedule() {
    }

    public Schedule(String name_job, String group_job, String name_trigger, String group_trigger, String cron_schedule) {
        this.name_job = name_job;
        this.group_job = group_job;
        this.name_trigger = name_trigger;
        this.group_trigger = group_trigger;
        this.cron_schedule = cron_schedule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_job() {
        return name_job;
    }

    public void setName_job(String name_job) {
        this.name_job = name_job;
    }

    public String getGroup_job() {
        return group_job;
    }

    public void setGroup_job(String group_job) {
        this.group_job = group_job;
    }

    public String getName_trigger() {
        return name_trigger;
    }

    public void setName_trigger(String name_trigger) {
        this.name_trigger = name_trigger;
    }

    public String getGroup_trigger() {
        return group_trigger;
    }

    public void setGroup_trigger(String group_trigger) {
        this.group_trigger = group_trigger;
    }

    public String getCron_schedule() {
        return cron_schedule;
    }

    public void setCron_schedule(String cron_schedule) {
        this.cron_schedule = cron_schedule;
    }
}
