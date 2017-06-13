package com.example.tasklets;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.services.FirstService;

@Component("taskOne")
public class TaskOne implements Tasklet {

	@Autowired
	private FirstService firstService;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
	
		firstService.methodOne();
		return RepeatStatus.FINISHED;
	}

}
