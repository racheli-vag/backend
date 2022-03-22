package com.messaging.service.service;

import com.messaging.service.model.Message;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(MessagingService.class)
public class MessagingServiceTest {

    @InjectMocks
    private MessagingService messagingService;

    @Test
    public void whenSaveMsg_shouldReturnMsg() {
        Message message = new Message();
        message.setText("Test Name");
        String recipient = "recipient";
        String sender = "sender";
        message.setSender(sender);
        message.setRecipient(recipient);

        messagingService.addMessage(message);
        List<String> text = messagingService.getMessageByRecipient(recipient);
        String formattedText = String.format("sent by %s:%s", sender, message.getText());
        assertThat(text).isEqualTo(Arrays.asList(formattedText));
    }
}