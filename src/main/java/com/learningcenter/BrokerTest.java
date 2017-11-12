package com.learningcenter;

import org.apache.activemq.broker.BrokerService;

/**
 * Author:Jason
 * Date:2017/10/30
 */
public class BrokerTest {
    public static void main(String[] args) throws Exception {
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://192.168.102.10:41616");
        brokerService.start();
    }
}
