package com.example.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@Configuration
@EnableScheduling
public class BatchScheduler {
	
	 @Bean
	    public ResourcelessTransactionManager transactionManager() {
	        return new ResourcelessTransactionManager();
	    }

	    @Bean
	    public MapJobRepositoryFactoryBean mapJobRepositoryFactory(
	            ResourcelessTransactionManager txManager) throws Exception {
	        
	        MapJobRepositoryFactoryBean factory = new 
	                MapJobRepositoryFactoryBean(txManager);
	        
	        factory.afterPropertiesSet();
	        
	        return factory;
	    }

	    @Bean
	    public JobRepository jobRepository(
	            MapJobRepositoryFactoryBean factory) throws Exception {
	        return factory.getObject();
	    }

	    @Bean
	    public SimpleJobLauncher jobLauncher(JobRepository jobRepository) {
	        SimpleJobLauncher launcher = new SimpleJobLauncher();
	        launcher.setJobRepository(jobRepository);
	        return launcher;
	    }

	    
	    @Bean
		public DataSource dataSource() {

			// no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
			EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
			EmbeddedDatabase db = builder
				.setType(EmbeddedDatabaseType.HSQL) //.H2 or .DERBY
				//.addScript("db/sql/create-db.sql")
				//.addScript("db/sql/insert-data.sql")
				.build();
			return db;
		}

}
