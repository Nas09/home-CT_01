package com.centrale.home.CT_01.connectors;

import com.centrale.home.CT_01.entities.Vehicle;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

@Component
public interface QuotationServiceConnector {
    public Long getQuotation(Vehicle vehicle) throws JsonProcessingException;
}
