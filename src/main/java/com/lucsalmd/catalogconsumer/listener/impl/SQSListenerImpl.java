package com.lucsalmd.catalogconsumer.listener.impl;

import com.google.gson.Gson;
import com.lucsalmd.catalogconsumer.listener.SQSListener;
import com.lucsalmd.catalogconsumer.model.SQSMessage;
import com.lucsalmd.catalogconsumer.service.OwnerService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.model.Message;

@Service
@RequiredArgsConstructor
public class SQSListenerImpl implements SQSListener {

    private final OwnerService ownerService;

    @SqsListener(value = "${aws.sqs.name}", acknowledgementMode = "ON_SUCCESS")
    public void receiveMessage(Message message) {
        final SQSMessage sqsMessage = new Gson().fromJson(message.body(), SQSMessage.class);
        ownerService.saveOwner(sqsMessage.getMessage());
    }
}
