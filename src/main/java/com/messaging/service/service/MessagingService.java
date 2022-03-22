package com.messaging.service.service;

import com.messaging.service.controller.MessagingController;
import com.messaging.service.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MessagingService {
    private static Logger logger = LoggerFactory.getLogger(MessagingService.class);

    private Map<String, List<String>> messagesByUser;

    public MessagingService() {
        messagesByUser = new ConcurrentHashMap<>();
    }

    public void addMessage(Message messageDTO) {

        String text = messageDTO.getText();
        String recipient = messageDTO.getRecipient();

        logger.info("add message {} for recipient {}", text, recipient);
        String sender = messageDTO.getSender();
        String textFormatted = String.format("sent by %s:%s", sender, text);
        messagesByUser.computeIfAbsent(recipient, k -> new ArrayList<>()).add(textFormatted);

    }

    public List<String> getMessageByRecipient(String recipient) {

        logger.info("get list for recipient {}", recipient);
        return messagesByUser.get(recipient);

    }


}
