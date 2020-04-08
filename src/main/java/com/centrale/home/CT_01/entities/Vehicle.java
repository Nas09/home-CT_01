package com.centrale.home.CT_01.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "make",
        "model",
        "version",
        "category",
        "registerNumber",
        "mileage"
})
public class Vehicle {

    @JsonProperty("make")
    private String make;
    @JsonProperty("model")
    private String model;
    @JsonProperty("version")
    private String version;
    @JsonProperty("category")
    private String category;
    @JsonProperty("registerNumber")
    private String registerNumber;
    @JsonProperty("mileage")
    private Integer mileage;

    @JsonProperty("make")
    public String getMake() {
        return make;
    }

    @JsonProperty("make")
    public void setMake(String make) {
        this.make = make;
    }

    @JsonProperty("model")
    public String getModel() {
        return model;
    }

    @JsonProperty("model")
    public void setModel(String model) {
        this.model = model;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("registerNumber")
    public String getRegisterNumber() {
        return registerNumber;
    }

    @JsonProperty("registerNumber")
    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    @JsonProperty("mileage")
    public Integer getMileage() {
        return mileage;
    }

    @JsonProperty("mileage")
    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

}
