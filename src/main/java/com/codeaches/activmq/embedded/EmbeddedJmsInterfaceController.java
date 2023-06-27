package com.codeaches.activmq.embedded;

import org.apache.activemq.artemis.api.core.management.Parameter;
import org.springframework.web.bind.annotation.*;

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
