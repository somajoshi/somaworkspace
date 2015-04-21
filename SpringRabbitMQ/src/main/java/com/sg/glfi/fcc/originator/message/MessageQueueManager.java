package com.sg.glfi.fcc.originator.message;

import org.springframework.amqp.core.MessageListener;

/**
 * Manager responsible for interacting with RabbitMQ. Is responsible for 
 * 1. creating queues in RabbitMQ 
 * 2. sending messages to queues
 * 3. acting as a listener for messages received over the queues.
 */
public interface MessageQueueManager extends MessageListener {

 String createQueue(String queueName, String exchange);
 
 void sendMessage(Object obj, String destinationQueueName, String exchange) throws Exception;

}
