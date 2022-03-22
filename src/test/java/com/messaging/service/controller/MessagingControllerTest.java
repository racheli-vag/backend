package com.messaging.service.controller;

import com.messaging.service.MainApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.messaging.service.model.Message;
import com.messaging.service.service.MessagingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {MessagingController.class})
public class MessagingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private MessagingService ms;

    @Test
    public void listAllMessagesPerRecipient__WhenGetMethod()
            throws Exception {

        Message message = new Message();
        String text = "text";
        message.setText(text);
        String sender = "sender";
        message.setSender(sender);
        String recipient = "recipient";

        String formattedText = String.format("sent by %s:%s", sender, message.getText());
        List<String> formattedListText = Arrays.asList(formattedText);

        String json = new Gson().toJson(formattedListText);

        given(ms.getMessageByRecipient(recipient))
                .willReturn(formattedListText);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/message/" + recipient))
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }

    @Test
    public void createMessage_success() throws Exception {
        Message message = new Message();
        String text = "text";
        message.setText(text);
        message.setSender("sender");
        String recipient = "recipient";
        message.setRecipient(recipient);


        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/message")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(message));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());

    }
}

