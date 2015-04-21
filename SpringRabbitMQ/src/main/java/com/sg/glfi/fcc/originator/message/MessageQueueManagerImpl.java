package com.sg.glfi.fcc.originator.message;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("messageQueueManager")
public class MessageQueueManagerImpl implements MessageQueueManager {
	@Autowired
	private AmqpAdmin admin;
	@Autowired
	private AmqpTemplate template;
	@Autowired
	private ConnectionFactory connectionFactory;
	@Autowired
	private SimpleMessageListenerContainer container;

	private Log log = LogFactory.getLog(MessageQueueManagerImpl.class);
	
	@Override
	public String createQueue(String queueName, String exchange) {
		log.debug("creating queue with name: " + queueName);

		// create queue
		Queue newQueue = new Queue(queueName, true, false, false);
		queueName = admin.declareQueue(newQueue);
	
		if(exchange.equalsIgnoreCase("directexchange")) {
			admin.declareExchange(new TopicExchange(ExchangeTypes.DIRECT));
		} else if(exchange.equalsIgnoreCase("topicexchange")) {
			admin.declareExchange(new TopicExchange(ExchangeTypes.TOPIC));
		} else {
			admin.declareExchange(new TopicExchange(ExchangeTypes.FANOUT));
		}
		admin.declareBinding(new Binding(queueName, DestinationType.QUEUE,
				exchange, queueName, new HashMap<String, Object>()));

		log.debug("queue successfully created: " + queueName);

		// add queue to listener
		container.addQueues(newQueue);

		// start listener
		container.start();
		return queueName;
	}

	@Override
	public void sendMessage(Object message, String destinationQueueName, String exchange)
			throws Exception {
		template.convertAndSend(exchange, destinationQueueName,
				message);
	}

	@Override
	public void onMessage(Message message) {
		log.debug(new String(message.getBody()));
		System.out.println(new String(message.getBody()));
	}
}