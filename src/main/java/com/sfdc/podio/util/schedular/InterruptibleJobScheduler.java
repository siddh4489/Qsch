/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfdc.podio.util.schedular;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author Siddhrajsinh_Atodari
 */
public class InterruptibleJobScheduler {

    public static SchedulerFactory schedulerFactory;
    public static Scheduler scheduler;

    public void Start() throws SchedulerException {
        this.schedulerFactory = new StdSchedulerFactory();
        this.scheduler = schedulerFactory.getScheduler();
        try {
            JobDetail jobDetail = JobBuilder.newJob(QsendPodioToSalesforce.class).withIdentity("myJob", "myGroup").build();
            //JobDetail job = new JobDetail();
            //job.setName("Job1");
            //job.setJobClass(QsendPodioToSalesforce.class);
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("myTrigger", "myGroup")
                    .startNow()
                    .withSchedule(
                            SimpleScheduleBuilder.repeatSecondlyForever(5)
                    //.simpleSchedule().withIntervalInSeconds(5)
                    )
                    .build();

            scheduler.start();

            scheduler.scheduleJob(jobDetail, trigger);
            /* try {
                System.out.println("11");
                Thread.sleep(10000);
                System.out.println("12");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
           

        } catch (Exception e) {

        }

    }

    public  void stop() throws SchedulerException {
        this.scheduler.shutdown();
    }

}
