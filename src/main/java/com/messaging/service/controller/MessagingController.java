package com.messaging.service.controller;

import com.messaging.service.model.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.messaging.service.service.MessagingService;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessagingController {

    private MessagingService messagingService;

    @Autowired
    public MessagingController(MessagingService messagingService) {
        this.messagingService = messagingService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity setMessage(@RequestBody final Message message) {
        messagingService.addMessage(message);
        return new ResponseEntity<>("result successful result",
                HttpStatus.OK);
    }

    @RequestMapping(value = "/{recipient}", method = RequestMethod.GET, produces = "application/json")
        public List<String> getMessageByRecipient(@PathVariable String recipient) {
        return messagingService.getMessageByRecipient(recipient);
    }

}
