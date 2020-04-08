package com.centrale.home.CT_01.serverMocks;

import com.centrale.home.CT_01.entities.Vehicle;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ServerResponseExpectation {
    public void createExpectationForQuotationService(Vehicle vehicle) throws JsonProcessingException;
    public void createExpectationForBlackListService(String registerNumber);
}
