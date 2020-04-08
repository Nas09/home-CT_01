package com.centrale.home.CT_01.serverMocks.impl;

import com.centrale.home.CT_01.entities.Vehicle;
import com.centrale.home.CT_01.serverMocks.ServerResponseExpectation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.Header;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@Component
public class ServerResponseExpectationImpl implements ServerResponseExpectation {
    @Override
    public void createExpectationForQuotationService(Vehicle vehicle) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        new MockServerClient("localhost", 8182)
                .when(
                        request()
                                .withMethod("POST")
                                .withPath("/quotation")
                                .withHeaders(
                                        new Header("Content-Type", MediaType.APPLICATION_JSON.toString())
                                )
                                .withBody(objectMapper.writeValueAsString(vehicle)),
                        exactly(1)
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "text/plain;charset=UTF-8")
                                )
                                .withBody("35000")
                                .withDelay(TimeUnit.MILLISECONDS, 50)
                );
    }

    @Override
    public void createExpectationForBlackListService(String registerNumber) {
        ObjectMapper objectMapper = new ObjectMapper();
        if ("AA123AA".equals(registerNumber)) {
            new MockServerClient("localhost", 8182)
                    .when(
                            request()
                                    .withMethod("GET")
                                    .withPath("/check/registerNumber")
                                    .withHeaders(
                                            new Header("register-number", registerNumber)
                                    ),
                            exactly(1)
                    )
                    .respond(
                            response()
                                    .withStatusCode(200)
                                    .withHeaders(
                                            new Header("Content-Type", "text/plain;charset=UTF-8")
                                    )
                                    .withBody("true")
                                    .withDelay(TimeUnit.MILLISECONDS, 50)
                    );
        } else {
            new MockServerClient("localhost", 8182)
                    .when(
                            request()
                                    .withMethod("GET")
                                    .withPath("/check/registerNumber")
                                    .withHeaders(
                                            new Header("register-number", registerNumber)
                                    ),
                            exactly(1)
                    )
                    .respond(
                            response()
                                    .withStatusCode(200)
                                    .withHeaders(
                                            new Header("Content-Type", "text/plain;charset=UTF-8")
                                    )
                                    .withBody("false")
                                    .withDelay(TimeUnit.MILLISECONDS, 50)
                    );
        }

    }

}
