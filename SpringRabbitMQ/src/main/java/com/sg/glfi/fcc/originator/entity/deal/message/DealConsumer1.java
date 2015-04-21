package com.sg.glfi.fcc.originator.entity.deal.message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sg.glfi.fcc.originator.entity.deal.domain.Deal;

@Component
public class DealConsumer1 implements MessageListener {

	@Autowired
	private AmqpTemplate template;

	private Log log = LogFactory.getLog(DealConsumer1.class);

	@Override
	public void onMessage(Message message) {
		Deal receiveDeal = (Deal) template.receiveAndConvert("deal1");

		log.info("converted message from Deal Consumer 1: " + receiveDeal);
		System.out.println("converted msg from Deal Consumer 1: " + receiveDeal.getDealName());

		Message msg = template.receive("deal1");
		String msgBody = new String(msg.getBody());
		log.info("unconverted message from Deal Consumer 1: " + msgBody);
		System.out.println("unconverted msg from Deal Consumer 1: " + msgBody);
	}

}
