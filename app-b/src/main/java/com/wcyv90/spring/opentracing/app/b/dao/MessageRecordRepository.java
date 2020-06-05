package com.wcyv90.spring.opentracing.app.b.dao;

import com.wcyv90.spring.opentracing.app.b.domain.MessageRecord;
import com.wcyv90.spring.opentracing.app.b.domain.MessageRecordExample;
import com.wcyv90.spring.opentracing.app.b.mapper.MessageRecordMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class MessageRecordRepository {

    @Autowired
    private MessageRecordMapper mapper;

    public MessageRecord saveOrUpdateMessage(String key, String value) {
        MessageRecordExample example = new MessageRecordExample();
        example.createCriteria().andMessageKeyEqualTo(key);
        List<MessageRecord> messageRecords = mapper.selectByExample(example);
        if (CollectionUtils.isEmpty(messageRecords)) {
            MessageRecord messageRecord = new MessageRecord();
            messageRecord.setMessageKey(key);
            messageRecord.setMessageValue(value);
            messageRecord.setUpdateTime(LocalDateTime.now());
            mapper.insert(messageRecord);
        } else {
            MessageRecord messageRecord = messageRecords.get(0);
            messageRecord.setMessageValue(value);
            messageRecord.setUpdateTime(LocalDateTime.now());
            MessageRecordExample updateExample = new MessageRecordExample();
            updateExample.createCriteria().andIdEqualTo(messageRecord.getId());
            mapper.updateByExample(messageRecord, updateExample);
        }
        return mapper.selectByExample(example).get(0);
    }

}
