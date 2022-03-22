package com.messaging.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private String text;

    private String recipient;

    private String sender;

    public String getRecipient() {
        return recipient;
    }

    public String getText() {
        return text;
    }

    public String getSender() {
        return sender;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

}
