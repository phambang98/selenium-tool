package com.seleniumtool.batch.job;

import com.seleniumtool.batch.tasklet.PersonTasklet;
import com.seleniumtool.batch.configuration.BatchConfiguration;
import com.example.core.enums.BatchExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class PersonJobConfiguration extends BatchConfiguration {

    @Bean
    public Job personJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("personJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(personStep1(jobRepository, transactionManager)).on(BatchExitStatus.FAILED.name())
                .to(personStep11(jobRepository, transactionManager)).on(BatchExitStatus.FAILED.name()).end()
                .from(personStep1(jobRepository, transactionManager)).on("COMPLETED")
                .to(personStep2(jobRepository, transactionManager)).on(BatchExitStatus.FAILED.name()).end()
                .from(personStep2(jobRepository, transactionManager)).on("COMPLETED")
                .to(personStep3(jobRepository, transactionManager)).end()
                .listener(jobNotificationListener)
                .build();
    }

    @Bean
    public Step personStep1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("personStep1", jobRepository)
                .tasklet(PersonTasklet.PersonTasklet1.builder(), transactionManager)
                .build();
    }

    @Bean
    public Step personStep11(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("personStep11", jobRepository)
                .tasklet(PersonTasklet.PersonTasklet11.builder(), transactionManager)
                .build();
    }

    @Bean
    public Step personStep2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("personStep2", jobRepository)
                .tasklet(PersonTasklet.PersonTasklet2.builder(), transactionManager)
                .build();
    }

    @Bean
    public Step personStep3(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("personStep3", jobRepository)
                .tasklet(PersonTasklet.PersonTasklet3.builder(), transactionManager)
                .build();
    }
}
