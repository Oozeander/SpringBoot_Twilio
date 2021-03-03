package com.oozeander.service;

import com.oozeander.model.SmsRequest;

public interface SmsService {
    void sendSms(SmsRequest smsRequest);
}