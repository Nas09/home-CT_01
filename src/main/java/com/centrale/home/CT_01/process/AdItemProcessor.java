package com.centrale.home.CT_01.process;

import com.centrale.home.CT_01.entities.Ad;
import com.centrale.home.CT_01.entities.Results;
import com.centrale.home.CT_01.validator.InformationValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class AdItemProcessor implements ItemProcessor<Ad, Results> {

    private static final Logger log = LoggerFactory.getLogger(AdItemProcessor.class);

    private Results results;

    @Autowired
    private InformationValidator informationValidator;

    private ValidatingItemProcessor<Ad> validatingItemProcessor;


    @Override
    public Results process(final Ad ad) throws Exception {
        log.info("Scam detector Start for Ad ref: " + ad.getReference() );
        results.setReference(ad.getReference());
        validatingItemProcessor.process(ad);


        ObjectMapper mapper = new ObjectMapper();
        String resultsStr = mapper.writeValueAsString(results);
        log.info("Results : " + resultsStr);

        return results;
    }


    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        ExecutionContext executionContext = stepExecution.getJobExecution().getExecutionContext();
        results = new Results();
        executionContext.put("results", results);
        validatingItemProcessor = new ValidatingItemProcessor<>();
        validatingItemProcessor.setValidator(informationValidator);
    }

}
