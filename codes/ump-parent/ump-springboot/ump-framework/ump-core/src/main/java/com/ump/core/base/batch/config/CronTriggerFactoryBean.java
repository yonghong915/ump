package com.ump.core.base.batch.config;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component("cronTriggerFactoryBean")
public class CronTriggerFactoryBean {
	@Autowired
     private SchedulerFactoryBean schedulerFactoryBean;

	/**
                 * 添加或修改一个定时任务
      */
	 public void createNewTask(String expression, int taskId) throws SchedulerException {
         TriggerKey triggerKey =TriggerKey.triggerKey("TASK-" + taskId, "JOB-" +taskId);
         CronTrigger trigger = null;
         // 不存在，创建一个
//         JobKey jobKey = new JobKey("TASK-" + taskId, "JOB-" + taskId);
//         JobDetail jobDetail = JobBuilder.newJob(SpringQuartzJob.class).withIdentity(jobKey).build();
//         // 稽核任务基础信息
//         jobDetail.getJobDataMap().put("taskId",taskId);
//         // 表达式调度构建器
//         CronScheduleBuilder cronScheduleBuilder = null;
//         cronScheduleBuilder =CronScheduleBuilder.cronSchedule(expression);
//         // 按cronExpression表达式构建一个新的trigger
//         trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).startAt(new Date()).withSchedule(cronScheduleBuilder).build();
//         // 加入任务队列
//         Scheduler scheduler =schedulerFactoryBean.getScheduler();
//         scheduler.scheduleJob(jobDetail,trigger);
//         scheduler.rescheduleJob(triggerKey,trigger);
  }
}
