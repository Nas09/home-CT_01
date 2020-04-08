package com.centrale.home.CT_01.listner;

import com.centrale.home.CT_01.process.AdItemProcessor;
import org.mockserver.integration.ClientAndServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class MockServerListner implements JobExecutionListener {

    private ClientAndServer mockServer;
    private static final Logger log = LoggerFactory.getLogger(MockServerListner.class);


    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Server Starting on port : 8182");
        mockServer = ClientAndServer.startClientAndServer(8182);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("Stopping Server");
        mockServer.stop();
    }
}
