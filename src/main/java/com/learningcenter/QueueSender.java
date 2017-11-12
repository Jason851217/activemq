package com.learningcenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * Author:Jason
 * Date:2017/10/31
 */
@Service
public class QueueSender {
    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        QueueSender sender = (QueueSender) context.getBean("queueSender");
//        sender.jmsTemplate.send(new MessageCreator() {
//            public Message createMessage(Session session) throws JMSException {
//                TextMessage msg = session.createTextMessage("Spring msg");
//                return msg;
//            }
//        });
//        sender.jmsTemplate.send((Session session)->{
//            TextMessage msg = session.createTextMessage("spring msg222");
//            return msg;
//        });
        context.close();

    }
}
