package com.centrale.home.CT_01.validator;

import com.centrale.home.CT_01.connectors.BlackListServiceConnector;
import com.centrale.home.CT_01.connectors.QuotationServiceConnector;
import com.centrale.home.CT_01.entities.Ad;
import com.centrale.home.CT_01.entities.Contacts;
import com.centrale.home.CT_01.entities.Phone1;
import com.centrale.home.CT_01.entities.Vehicle;
import com.centrale.home.CT_01.serverMocks.ServerResponseExpectation;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class InformationValidatorTest {

    @InjectMocks
    private InformationValidator informationValidator;
    @Mock
    private QuotationServiceConnector quotationServiceConnector;
    @Mock
    private BlackListServiceConnector blackListServiceConnector;
    @Mock
    private ServerResponseExpectation serverResponseExpectation;

    private Ad ad;

    @Before
    public void init() {
        //Given
        ad = new Ad();
        Contacts contacts = new Contacts();
        contacts.setEmail("testdup@email.com");
        contacts.setFirstName("Christophe");
        contacts.setLastName("Dupont");
        Phone1 phone1 = new Phone1();
        phone1.setValue("0634134313");
        contacts.setPhone1(phone1);
        ad.setContacts(contacts);
        ad.setCreationDate("2020-01-09T09:02:22.610Z");
        ad.setPrice((long) 32000);
        List<String> publicationOptions = new ArrayList<>();
        publicationOptions.add("STRENGTHS");
        publicationOptions.add("BOOST_VO");
        ad.setPublicationOptions(publicationOptions);
        ad.setReference("B300053623");
        Vehicle vehicle = new Vehicle();
        vehicle.setCategory("SUV_4X4_CROSSOVER");
        vehicle.setMake("HONDA");
        vehicle.setMileage(100000);
        vehicle.setModel("CR-V");
        vehicle.setRegisterNumber("AA123AA");
        ad.setVehicle(vehicle);
    }

    @Test
    public void should_email_alpha_rate_valid(){

        //When
        boolean is_email_valid = informationValidator.is_email_alpha_rate_valid(ad);
        //Then
        Assert.assertTrue(is_email_valid);
    }

    @Test
    public void should_email_alpha_rate_greater_than_70_valid(){
        //given
        String email = "test-_-dupont@email.com";
        ad.getContacts().setEmail(email);
        //When
        boolean is_email_valid = informationValidator.is_email_alpha_rate_valid(ad);
        //Then
        Assert.assertTrue(is_email_valid);
    }

    @Test
    public void should_email_alpha_rate_less_than_70_not_valid(){
        //given
        String email = "test-___-_-dupont@email.com";
        ad.getContacts().setEmail(email);
        //When
        boolean is_email_valid = informationValidator.is_email_alpha_rate_valid(ad);
        //Then
        Assert.assertFalse(is_email_valid);
    }

    @Test
    public void should_number_rate_valid(){
        //When
        boolean is_email_valid = informationValidator.is_email_number_rate_valid(ad);
        //Then
        Assert.assertTrue(is_email_valid);
    }

    @Test
    public void should_number_alpha_less_than_30_valid(){
        //given
        String email = "test123dupont@email.com";
        ad.getContacts().setEmail(email);
        //When
        boolean is_email_valid = informationValidator.is_email_number_rate_valid(ad);
        //Then
        Assert.assertTrue(is_email_valid);
    }

    @Test
    public void should_number_alpha_greater_than_30_not_valid(){
        //given
        String email = "test12312dupont@email.com";
        ad.getContacts().setEmail(email);
        //When
        boolean is_email_valid = informationValidator.is_email_number_rate_valid(ad);
        //Then
        Assert.assertFalse(is_email_valid);
    }

    @Test
    public void should_price_quotation_rate_less_than_20_valid() throws JsonProcessingException {
        //given
        Mockito.when(quotationServiceConnector.getQuotation(ad.getVehicle())).thenReturn((long) 35000);
        //when
        boolean is_price_quotation_valid = informationValidator.is_price_quotation_rate_valid(ad);
        //then
        Assert.assertTrue(is_price_quotation_valid);
    }

    @Test
    public void should_price_quotation_rate_greater_than_20_not_valid() throws JsonProcessingException {
        //given
        ad.setPrice((long) 20000);
        Mockito.when(quotationServiceConnector.getQuotation(ad.getVehicle())).thenReturn((long) 35000);
        //when
        boolean is_price_quotation_valid = informationValidator.is_price_quotation_rate_valid(ad);
        //then
        Assert.assertFalse(is_price_quotation_valid);
    }

    @Test
    public void should_register_number_blacklist() throws JsonProcessingException {
        //given
        Mockito.when(blackListServiceConnector.getBlackList(ad.getVehicle().getRegisterNumber())).thenReturn(true);
        //when
        boolean is_register_number_blacklist = informationValidator.is_register_number_blacklist(ad);
        //then
        Assert.assertTrue(is_register_number_blacklist);
    }

    @Test
    public void should_register_number_not_blacklist() throws JsonProcessingException {
        //given
        Mockito.when(blackListServiceConnector.getBlackList(ad.getVehicle().getRegisterNumber())).thenReturn(false);
        //when
        boolean is_register_number_blacklist = informationValidator.is_register_number_blacklist(ad);
        //then
        Assert.assertFalse(is_register_number_blacklist);
    }

}
