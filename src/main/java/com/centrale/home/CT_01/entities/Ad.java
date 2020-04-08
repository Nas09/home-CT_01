package com.centrale.home.CT_01.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.Valid;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "contacts",
        "creationDate",
        "price",
        "publicationOptions",
        "reference",
        "vehicle"
})
public class Ad {
    @JsonProperty("contacts")
    @Valid
    private Contacts contacts;
    @JsonProperty("creationDate")
    private String creationDate;
    @JsonProperty("price")
    @Valid
    private Long price;
    @JsonProperty("publicationOptions")
    private List<String> publicationOptions = null;
    @JsonProperty("reference")
    private String reference;
    @JsonProperty("vehicle")
    @Valid
    private Vehicle vehicle;

    @JsonProperty("contacts")
    public Contacts getContacts() {
        return contacts;
    }

    @JsonProperty("contacts")
    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    @JsonProperty("creationDate")
    public String getCreationDate() {
        return creationDate;
    }

    @JsonProperty("creationDate")
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @JsonProperty("price")
    public Long getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Long price) {
        this.price = price;
    }

    @JsonProperty("publicationOptions")
    public List<String> getPublicationOptions() {
        return publicationOptions;
    }

    @JsonProperty("publicationOptions")
    public void setPublicationOptions(List<String> publicationOptions) {
        this.publicationOptions = publicationOptions;
    }

    @JsonProperty("reference")
    public String getReference() {
        return reference;
    }

    @JsonProperty("reference")
    public void setReference(String reference) {
        this.reference = reference;
    }

    @JsonProperty("vehicle")
    public Vehicle getVehicle() {
        return vehicle;
    }

    @JsonProperty("vehicle")
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
