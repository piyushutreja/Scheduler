package com.example.tasklets;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.services.SecondService;
import com.example.services.ThirdService;

@Component("taskThree")
public class TaskThree implements Tasklet {
	
	@Autowired
	private ThirdService thirdService;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		thirdService.methodThree();
		return RepeatStatus.FINISHED;
	}



}
