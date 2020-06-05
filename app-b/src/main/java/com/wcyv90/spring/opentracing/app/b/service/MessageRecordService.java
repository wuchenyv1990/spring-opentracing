package com.wcyv90.spring.opentracing.app.b.service;

import com.wcyv90.spring.opentracing.app.b.dao.MessageRecordRepository;
import com.wcyv90.spring.opentracing.app.b.domain.MessageRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageRecordService {

    @Autowired
    private MessageRecordRepository messageRecordRepository;

    @Transactional
    public MessageRecord recordMessage(String key, String message) {
        return messageRecordRepository.saveOrUpdateMessage(key, message);
    }

}
