package com.centrale.home.CT_01.enums;

public enum Rules {
    FIRSTNAME_LENGTH("rule::firstname::length"),
    LASTNAME_LENGTH("rule::lastname::length"),
    EMAIL_ALPHA_RATE("rule:✉:alpha_rate"),
    EMAIL_NUMBER_RATE("rule:✉:number_rate"),
    PRICE_QUOTATION_RATE("rule::price::quotation_rate"),
    REGISTER_NUMBER_BLACKLIST("rule::registernumber::blacklist");

    private String message;

    Rules(String message){
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

}

