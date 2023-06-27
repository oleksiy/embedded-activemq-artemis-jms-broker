package com.codeaches.activmq.embedded;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsProducer {

    private final Logger log = LoggerFactory.getLogger(JmsProducer.class);

    private final JmsTemplate jmsTemplate;

    public JmsProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(String message) {
        jmsTemplate.convertAndSend(message);
        log.info("Sent message='{}'", message);
    }

    //alternative send method which creates a topic if it doesn't exist
    public void send(String destination, String message) {
        jmsTemplate.convertAndSend(destination, message);
        log.info("Sent message='{}' to queue='{}'", message, destination);
    }
}
