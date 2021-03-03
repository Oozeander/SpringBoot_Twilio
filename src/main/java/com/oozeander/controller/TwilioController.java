package com.oozeander.controller;

import com.oozeander.model.SmsRequest;
import com.oozeander.service.impl.SmsServiceTwilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/sms")
@CrossOrigin
public class TwilioController {
    private final SmsServiceTwilio smsService;

    @Autowired
    public TwilioController(@Qualifier("twilio") SmsServiceTwilio smsService) {
        this.smsService = smsService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void sendSMS(@Valid @RequestBody SmsRequest smsRequest) {
        smsService.sendSms(smsRequest);
    }
}