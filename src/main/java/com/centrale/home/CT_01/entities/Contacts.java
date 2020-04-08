package com.centrale.home.CT_01.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "firstName",
        "lastName",
        "email",
        "phone1"
})
public class Contacts {

    @JsonProperty("firstName")
    @Size(min = 3)
    private String firstName;
    @JsonProperty("lastName")
    @Size(min = 3)
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phone1")
    private Phone1 phone1;

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("phone1")
    public Phone1 getPhone1() {
        return phone1;
    }

    @JsonProperty("phone1")
    public void setPhone1(Phone1 phone1) {
        this.phone1 = phone1;
    }

}