package com.learningcenter;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Hello world!
 *
 */
public class Producer {
    public static void main( String[] args ) throws JMSException, InterruptedException {
        //连接工厂：连接到activemq服务器
//        ConnectionFactory connectionFactory  = new ActiveMQConnectionFactory("tcp://192.168.102.100:61616");
        ConnectionFactory connectionFactory  = new ActiveMQConnectionFactory("tcp://192.168.102.10:41616");
        //创建连接
        Connection connection =  connectionFactory.createConnection();
        //启动连接
        connection.start();
        //建立会话
        Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);//是否事务，是否需要消息确认
        //创建目的地，消息发送到什么地方（存放消息的队列）
        Destination destination =  session.createQueue("my-queue");
        //创建消息的生产方，也就是消息的发送方
        MessageProducer producer = session.createProducer(destination);

        for (int i=0;i<10;i++) {
           // TextMessage textMsg = session.createTextMessage("message"+i);//创建消息（文本消息）
           // Thread.sleep(1000);
            MapMessage msg =session.createMapMessage();
            msg.setString("key"+i,"value"+i);
            msg.setStringProperty("property key"+i,"property value"+i);
            producer.send(msg);//生产者发送消息
        }
        session.commit();
        session.close();
        connection.close();
    }
}
