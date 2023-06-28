package com.codeaches.activmq.embedded;

import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

public class DynamicJmsConsumer {

    private final ConnectionFactory connectionFactory;
    private final JmsTemplate jmsTemplate;
    private final Logger log = LoggerFactory.getLogger(JmsProducer.class);
    public DynamicJmsConsumer(@Qualifier("jmsConnectionFactory") ConnectionFactory connectionFactory, JmsTemplate jmsTemplate) {
        this.connectionFactory = connectionFactory;
        this.jmsTemplate = jmsTemplate;
    }

    public void createConsumer(String destinationName) {
        String subscriberId = UUID.randomUUID().toString();
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setDestinationName(destinationName);
        container.setMessageListener((MessageListener) message -> {
            try {
                System.out.println("Received message='{}' on destination='{}'" + ((TextMessage) message).getText() + destinationName);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
        container.afterPropertiesSet();
        container.start();
        log.info("Consumer/Queue created successfully - {}", destinationName);
    }
}
