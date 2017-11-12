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
public class QueueReceiver {
    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        QueueReceiver receiver = (QueueReceiver) context.getBean("queueReceiver");
        String msg = (String)receiver.jmsTemplate.receiveAndConvert();
        System.out.println("msg:"+msg);
        context.close();
    }
}
