package com.messaging.service.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private String text;

    private String recipient;

    private String sender;

}
