package com.codeaches.activmq.embedded;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmbeddedJmsInterfaceController {
    private final JmsProducer jmsProducer;

    public EmbeddedJmsInterfaceController(JmsProducer jmsProducer) {
        this.jmsProducer = jmsProducer;
    }

    @PostMapping("/send")
    public void sendDataToJms(@RequestParam String message) {
        jmsProducer.send(message);
    }
}
