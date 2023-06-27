package com.codeaches.activmq.embedded;

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
    public void sendDataToJms(@RequestParam String message) {
        jmsProducer.send(message);
    }

    @GetMapping("/create/{queueName}")
    public void createQueue(@PathVariable() String queueName) {
        dynamicJmsConsumer.createConsumer(queueName);
    }
}
