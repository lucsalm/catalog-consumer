package com.lucsalmd.catalogconsumer.listener;

import software.amazon.awssdk.services.sqs.model.Message;

public interface SQSListener {

    void receiveMessage(Message message);
}
