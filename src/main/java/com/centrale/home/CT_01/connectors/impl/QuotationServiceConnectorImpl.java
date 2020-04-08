package com.centrale.home.CT_01.connectors.impl;

import com.centrale.home.CT_01.connectors.QuotationServiceConnector;
import com.centrale.home.CT_01.entities.Vehicle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class QuotationServiceConnectorImpl implements QuotationServiceConnector {

    public static final String HTTP_LOCALHOST_8182_QUOTATION = "http://localhost:8182/quotation";

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Long getQuotation(Vehicle vehicle) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(vehicle), headers);

        ResponseEntity<String> response = restTemplate.exchange(HTTP_LOCALHOST_8182_QUOTATION, HttpMethod.POST, request, String.class);

        return Long.valueOf(response.getBody());
    }
}
