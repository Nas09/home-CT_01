package com.centrale.home.CT_01.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "reference",
        "scam",
        "rules"
})
public class Results implements Serializable {

    @JsonProperty("reference")
    private String reference;
    @JsonProperty("scam")
    private boolean scam;
    @JsonProperty("rules")
    private List<String> rules;

    @JsonProperty("reference")
    public String getReference() {
        return reference;
    }

    @JsonProperty("reference")
    public void setReference(String reference) {
        this.reference = reference;
    }

    @JsonProperty("scam")
    public boolean getScam() {
        return scam;
    }

    @JsonProperty("scam")
    public void setScam(boolean scam) {
        this.scam = scam;
    }

    @JsonProperty("rules")
    public List<String> getRules() {
        return rules;
    }

    @JsonProperty("rules")
    public void setRules(List<String> rules) {
        this.rules = rules;
    }

}
