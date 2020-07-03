--liquibase formatted sql
--changeset aharoldk:1.1

DROP TABLE IF EXISTS quartz_job_detail;

CREATE TABLE quartz_job_detail (
  id INT NOT NULL AUTO_INCREMENT,
  name_job VARCHAR(20) NOT NULL,
  group_job VARCHAR(20) NOT NULL,
  name_trigger VARCHAR(20) NOT NULL,
  group_trigger VARCHAR(20) NOT NULL,
  cron_schedule VARCHAR(20) NOT NULL,
  PRIMARY KEY (id)
);
