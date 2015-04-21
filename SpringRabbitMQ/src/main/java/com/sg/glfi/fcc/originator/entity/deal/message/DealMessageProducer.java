package com.sg.glfi.fcc.originator.entity.deal.message;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sg.glfi.fcc.originator.message.MessageQueueManager;
import com.sg.glfi.fcc.originator.message.MessageQueueManagerImpl;

public class DealMessageProducer {

	private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"applicationContext.xml");

	public void send(Object obj) {

		MessageQueueManager manager = applicationContext
				.getBean(MessageQueueManagerImpl.class);
		manager.createQueue("deal1", "topicExchange");
		try {
			manager.sendMessage(obj, "deal1", "topicExchange");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			((ConfigurableApplicationContext) applicationContext).close();
		}
	}
}
