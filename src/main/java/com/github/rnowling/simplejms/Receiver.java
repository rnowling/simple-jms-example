package com.github.rnowling.simplejms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;

public class Receiver implements MessageListener
{
	TopicConnection connection;
	
	public Receiver(String topicFactory, String topicName) throws Exception
	{
		InitialContext ctx = new InitialContext();
		
		TopicConnectionFactory connFactory = (TopicConnectionFactory) ctx.lookup(topicFactory);
		connection = connFactory.createTopicConnection();
		
		TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Topic chatTopic = (Topic) ctx.lookup(topicName);
		
		TopicSubscriber subscriber = session.createSubscriber(chatTopic, null, true);
		
		subscriber.setMessageListener(this);
		
		connection.start();
	}

	@Override
	public void onMessage(Message message)
	{
		try
		{
			TextMessage textMessage = (TextMessage) message;
			System.out.println("Received: " + textMessage.getText());
		}
		catch (JMSException exception)
		{
			exception.printStackTrace();
		}
	}
	
	public void close() throws JMSException
	{
		connection.close();
	}

}
