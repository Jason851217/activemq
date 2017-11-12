package com.learningcenter;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Enumeration;

/**
 * Author:Jason
 * Date:2017/10/29
 */
public class Consumer {
    public static void main(String[] args) throws Exception{
        //连接工厂：连接到activemq服务器
//        ConnectionFactory connectionFactory  = new ActiveMQConnectionFactory("tcp://192.168.102.100:61616");
        ConnectionFactory connectionFactory  = new ActiveMQConnectionFactory("tcp://192.168.102.10:41616");
        //创建连接
        Connection connection =  connectionFactory.createConnection();
        //启动连接
        connection.start();

        Enumeration<String> names = connection.getMetaData().getJMSXPropertyNames();

        while(names.hasMoreElements()){
            String jmsxPropertyName = names.nextElement();
            System.out.println("jmsxPropertyName item:"+jmsxPropertyName);
        }

        //建立会话
        Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);//是否事务，是否需要消息确认
        //创建目的地，消息发送到什么地方（存放消息的队列）
        Destination destination =  session.createQueue("my-queue");
        //创建消息的生产方，也就是消息的发送方
        MessageConsumer consumer = session.createConsumer(destination);

        for (int i=0;i<10;i++) {
           // TextMessage txtMsg =  (TextMessage)consumer.receive();

            MapMessage msg =  (MapMessage)consumer.receive();//receive是消息同步模式，会阻塞，直到有消息发送过来为止
            session.commit();
            System.out.println("接收到消息："+msg.getString("key"+i)+" ,property:"+msg.getStringProperty("property key"+i));
        }
        session.close();
        connection.close();
    }

}
