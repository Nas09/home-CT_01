package com.centrale.home.CT_01.configuration;

import com.centrale.home.CT_01.entities.Ad;
import com.centrale.home.CT_01.entities.Results;
import com.centrale.home.CT_01.listner.MockServerListner;
import com.centrale.home.CT_01.process.AdItemProcessor;
import com.centrale.home.CT_01.validator.InformationValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private InformationValidator informationValidator;

    @Autowired
    private MockServerListner mockServerListner;

    @Bean
    public JsonItemReader<Ad> reader() {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonJsonObjectReader<Ad> jsonObjectReader =
                new JacksonJsonObjectReader<>(Ad.class);
        jsonObjectReader.setMapper(objectMapper);

        return new JsonItemReaderBuilder<Ad>()
                .jsonObjectReader(jsonObjectReader)
                .resource(new ClassPathResource("ad.json"))
                .name("adJsonItemReader")
                .build();
    }

    @Bean
    public AdItemProcessor processor() {
        return new AdItemProcessor();
    }

    @Bean
    public JsonFileItemWriter<Results> writer() {
        return new JsonFileItemWriterBuilder<Results>()
                .jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
                .resource(new FileSystemResource("results.json"))
                .name("adJsonItemWriter")
                .build();
    }
    @Bean
    public Job checkAdJob(Step step) {
        return jobBuilderFactory.get("checkAdJob")
                .incrementer(new RunIdIncrementer())
                .listener(mockServerListner)
                .flow(step)
                .end()
                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step")
                .<Ad, Results> chunk(1)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .listener(informationValidator)
                .build();
    }
}
