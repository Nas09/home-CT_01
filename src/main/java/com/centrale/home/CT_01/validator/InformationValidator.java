package com.centrale.home.CT_01.validator;

import com.centrale.home.CT_01.connectors.BlackListServiceConnector;
import com.centrale.home.CT_01.connectors.QuotationServiceConnector;
import com.centrale.home.CT_01.entities.Ad;
import com.centrale.home.CT_01.entities.Results;
import com.centrale.home.CT_01.enums.Rules;
import com.centrale.home.CT_01.serverMocks.ServerResponseExpectation;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Component
public class InformationValidator extends StepExecutionListenerSupport
        implements Validator<Ad>, InitializingBean {

    public static final String CONTACTS_FIRST_NAME = "contacts.firstName";
    public static final String SIZE_MUST_BE_BETWEEN_3_AND_2147483647 = "size must be between 3 and 2147483647";
    public static final String CONTACTS_LAST_NAME = "contacts.lastName";
    private javax.validation.Validator validator;
    private List<String> violatedRules;

    @Autowired
    private QuotationServiceConnector quotationServiceConnector;
    @Autowired
    private BlackListServiceConnector blackListServiceConnector;
    @Autowired
    private ServerResponseExpectation serverResponseExpectation;

    private StepExecution stepExecution;


    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

    @Override
    public void validate(Ad ad) throws ValidationException {
        Set<ConstraintViolation<Object>> rulesViolation = validator.validate(ad);
        violatedRules = new ArrayList<>();
        ExecutionContext executionContext = stepExecution.getJobExecution().getExecutionContext();
        Results results = (Results) executionContext.get("results");

        if (!rulesViolation.isEmpty()) {
            for (ConstraintViolation constraintViolation : rulesViolation ) {
                if(constraintViolation.getPropertyPath().toString().equals(CONTACTS_FIRST_NAME)
                && constraintViolation.getMessage().equals(SIZE_MUST_BE_BETWEEN_3_AND_2147483647)) {
                    violatedRules.add(Rules.FIRSTNAME_LENGTH.getMessage());
                    results.setScam(true);
                } else if (constraintViolation.getPropertyPath().toString().equals(CONTACTS_LAST_NAME)
                        && constraintViolation.getMessage().equals(SIZE_MUST_BE_BETWEEN_3_AND_2147483647)) {
                    violatedRules.add(Rules.LASTNAME_LENGTH.getMessage());
                    results.setScam(true);
                }
            }
        }
        if (!is_email_alpha_rate_valid(ad)) {
            violatedRules.add(Rules.EMAIL_ALPHA_RATE.getMessage());
            results.setScam(true);
        } if (!is_email_number_rate_valid(ad)) {
            violatedRules.add(Rules.EMAIL_NUMBER_RATE.getMessage());
            results.setScam(true);
        }
        try {
            if (!is_price_quotation_rate_valid(ad)) {
                violatedRules.add(Rules.PRICE_QUOTATION_RATE.getMessage());
                results.setScam(true);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (is_register_number_blacklist(ad)) {
            violatedRules.add(Rules.REGISTER_NUMBER_BLACKLIST.getMessage());
            results.setScam(true);
        }

        results.setRules(violatedRules);

    }

    public boolean is_email_alpha_rate_valid(Ad ad) {
        String email = ad.getContacts().getEmail();
        String[] emailParts = email.split("@", 2);
        String emailFirstPart = emailParts[0];
        Pattern p1 = Pattern.compile("[a-zA-Z0-9]");
        String emailWithoutAlpha = emailFirstPart.replaceAll(p1.pattern(), "");
        int numberOfAlpha = emailFirstPart.length() - emailWithoutAlpha.length();
        int sizeEmailFirstPart = emailFirstPart.length();
        float percentage = numberOfAlpha * 100 / sizeEmailFirstPart;
        return percentage > 70;
    }

    public boolean is_email_number_rate_valid(Ad ad) {
        String email = ad.getContacts().getEmail();
        String[] emailParts = email.split("@", 2);
        String emailFirstPart = emailParts[0];
        Pattern p1 = Pattern.compile("[0-9]");
        String emailWithoutNum = emailFirstPart.replaceAll(p1.pattern(), "");
        int numberOfNum = emailFirstPart.length() - emailWithoutNum.length();
        int sizeEmailFirstPart = emailFirstPart.length();
        float percentage = numberOfNum * 100 / sizeEmailFirstPart;
        return percentage < 30;
    }

    public boolean is_price_quotation_rate_valid(Ad ad) throws JsonProcessingException {
        serverResponseExpectation.createExpectationForQuotationService(ad.getVehicle());
        Long quotation =  quotationServiceConnector.getQuotation(ad.getVehicle());
        Long price = ad.getPrice();
        Long diff = Math.abs(price - quotation);
        float percentage = diff * 100 / quotation;
        return percentage>20;
    }

    public boolean is_register_number_blacklist(Ad ad) {
        serverResponseExpectation.createExpectationForBlackListService(ad.getVehicle().getRegisterNumber());
        Boolean isBlackList = blackListServiceConnector.getBlackList(ad.getVehicle().getRegisterNumber());
        return isBlackList;
    }

}
