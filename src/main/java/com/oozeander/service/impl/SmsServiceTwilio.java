package com.oozeander.service.impl;

import com.oozeander.config.TwilioConfiguration;
import com.oozeander.exception.InvalidPhoneNumberException;
import com.oozeander.model.SmsRequest;
import com.oozeander.service.SmsService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("twilio")
public class SmsServiceTwilio implements SmsService {
    private final TwilioConfiguration twilioConfiguration;
    private final static Logger LOGGER = LoggerFactory.getLogger(SmsService.class);

    @Autowired
    public SmsServiceTwilio(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @Override
    public void sendSms(SmsRequest smsRequest) {
        PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber()),
                from = new PhoneNumber(twilioConfiguration.getTrialNumber());
        String message = smsRequest.getMessage();
        MessageCreator creator = Message.creator(to, from, message);
        // Send SMS
        if (isValid(to.toString())) {
            creator.create();
            LOGGER.info("SMS sent to {}", to.toString());
        }
        else {
            LOGGER.info("Invalid number !");
            throw new InvalidPhoneNumberException(to.toString());
        }
    }

    private boolean isValid(String phoneNumber) {
        boolean isValid = true;
        if (!phoneNumber.startsWith("+33"))
            isValid = false;
        return isValid;
    }
}