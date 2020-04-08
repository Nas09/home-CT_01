package com.centrale.home.CT_01.connectors.impl;

import com.centrale.home.CT_01.connectors.BlackListServiceConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BlackListServiceConnectorImpl implements BlackListServiceConnector {

    public static final String REGISTER_NUMBER = "register-number";
    public static final String HTTP_LOCALHOST_8182_CHECK_REGISTER_NUMBER = "http://localhost:8182/check/registerNumber";

    @Autowired
    RestTemplate restTemplate;

    @Override
    public boolean getBlackList(String registerNumber) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(REGISTER_NUMBER, registerNumber);

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> registerNumberCheck = restTemplate.exchange(HTTP_LOCALHOST_8182_CHECK_REGISTER_NUMBER, HttpMethod.GET, request, String.class);

        return Boolean.parseBoolean(registerNumberCheck.getBody());
    }
}
