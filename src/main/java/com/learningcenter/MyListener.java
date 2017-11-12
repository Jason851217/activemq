package com.learningcenter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Author:Jason
 * Date:2017/10/31
 */
public class MyListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            String msg =  ((TextMessage)message).getText();
            System.out.println("message listener:"+msg);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
