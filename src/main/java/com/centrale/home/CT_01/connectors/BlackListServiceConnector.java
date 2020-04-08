package com.centrale.home.CT_01.connectors;

import org.springframework.stereotype.Component;

@Component
public interface BlackListServiceConnector {
    public boolean getBlackList(String registerNumber);
}
