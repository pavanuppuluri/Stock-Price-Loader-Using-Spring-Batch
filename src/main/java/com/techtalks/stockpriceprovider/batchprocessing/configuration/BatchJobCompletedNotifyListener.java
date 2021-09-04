package com.techtalks.stockpriceprovider.batchprocessing.configuration;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class BatchJobCompletedNotifyListener extends JobExecutionListenerSupport {

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            // In real scenario, we can trigger batch job completion
            // notification here.
            System.out.println("Stock prices loaded successfully");
        }
        if (jobExecution.getStatus() == BatchStatus.FAILED)
        {
            // In real scenario, we can trigger batch job failed
            // notification here.
            System.out.println("Stock prices loaded successfully");
        }
    }
}
