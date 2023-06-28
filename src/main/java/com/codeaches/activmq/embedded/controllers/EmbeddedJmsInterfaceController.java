package com.codeaches.activmq.embedded.controllers;

import com.codeaches.activmq.embedded.DynamicJmsConsumer;
import com.codeaches.activmq.embedded.JmsProducer;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmbeddedJmsInterfaceController {
    private final JmsProducer jmsProducer;
    private final DynamicJmsConsumer dynamicJmsConsumer;

    public EmbeddedJmsInterfaceController(JmsProducer jmsProducer, DynamicJmsConsumer dynamicJmsConsumer) {
        this.jmsProducer = jmsProducer;
        this.dynamicJmsConsumer = dynamicJmsConsumer;
    }

    @PostMapping("/send")
    public void sendDataToJms(@RequestBody String message) {
        jmsProducer.send(message);
    }

    @PostMapping("/send/{queueName}")
    public void sendDataToJms(@PathVariable() String queueName, @RequestBody String message) {
        jmsProducer.send(queueName, message);
    }

    @GetMapping("/create/{queueName}")
    public void createQueue(@PathVariable() String queueName) {
        dynamicJmsConsumer.createConsumer(queueName);
    }
}
