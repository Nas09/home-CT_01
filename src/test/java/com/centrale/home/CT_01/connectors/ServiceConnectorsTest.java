package com.centrale.home.CT_01.connectors;

import com.centrale.home.CT_01.connectors.impl.BlackListServiceConnectorImpl;
import com.centrale.home.CT_01.connectors.impl.QuotationServiceConnectorImpl;
import com.centrale.home.CT_01.entities.Vehicle;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
@RestClientTest
public class ServiceConnectorsTest {

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private RestTemplateBuilder builder;


    @InjectMocks
    private BlackListServiceConnectorImpl blackListServiceConnector;
    @InjectMocks
    private QuotationServiceConnectorImpl quotationServiceConnector;

    public static final String HTTP_LOCALHOST_8182_CHECK_REGISTER_NUMBER = "http://localhost:8182/check/registerNumber";
    public static final String HTTP_LOCALHOST_8182_QUOTATION = "http://localhost:8182/quotation";


    @Mock
    private RestTemplate restTemplate;

    private Vehicle vehicle;

    private ResponseEntity<String> responseEntity;

    @Before
    public void init() {
        //Given
        vehicle = new Vehicle();
        vehicle.setCategory("SUV_4X4_CROSSOVER");
        vehicle.setMake("HONDA");
        vehicle.setMileage(100000);
        vehicle.setModel("CR-V");
        vehicle.setRegisterNumber("AA123AA");
    }


    @Test
    public void should_return_quotation_price() throws JsonProcessingException {
        //Given
        String quotation= "35000";
        responseEntity = new ResponseEntity<>(quotation, HttpStatus.OK);

        Mockito.when(restTemplate.exchange(Mockito.eq(HTTP_LOCALHOST_8182_QUOTATION), Mockito.eq(HttpMethod.POST), Mockito.anyObject(), Mockito.eq(String.class))).thenReturn(responseEntity);
        //When
        Long quotation_price = quotationServiceConnector.getQuotation(vehicle);
        //Then
        Assert.assertEquals(Long.valueOf(quotation), quotation_price);
    }

    @Test
    public void should_return_blacklist_check() throws JsonProcessingException {
        //Given
        String blackListCheck= "true";
        responseEntity = new ResponseEntity<>(blackListCheck, HttpStatus.OK);
        Mockito.when(restTemplate.exchange(Mockito.eq(HTTP_LOCALHOST_8182_CHECK_REGISTER_NUMBER), Mockito.eq(HttpMethod.GET), Mockito.anyObject(), Mockito.eq(String.class))).thenReturn(responseEntity);
        //When
        boolean blackList = blackListServiceConnector.getBlackList(vehicle.getRegisterNumber());
        //Then
        Assert.assertEquals(Boolean.valueOf(blackListCheck), blackList);
    }




}
