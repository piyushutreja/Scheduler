package com.example.batch.config;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.services.joblistener.JobNotificationListener;

@Configuration
@EnableBatchProcessing
@Import({BatchScheduler.class})
public class BatchConfiguration {

	 @Autowired
	    private SimpleJobLauncher jobLauncher;

	    @Autowired
	    public JobBuilderFactory jobBuilderFactory;
	    
	    @Autowired
	    public Job job;

	    @Autowired
	    public StepBuilderFactory stepBuilderFactory;

	    @Scheduled(fixedRate=3000)
	    public void perform() throws Exception {

	        System.out.println("Job Started at :" + new Date());

	        JobParameters param = new JobParametersBuilder().addString("JobID",
	                String.valueOf(System.currentTimeMillis())).toJobParameters();

	        JobExecution execution = jobLauncher.run(job, param);

	        System.out.println("Job finished with status :" + execution.getStatus());
	    }
	    
	    
	    @Bean
	    public JobExecutionListener listener() {
	        return new JobNotificationListener();
	    }

}
