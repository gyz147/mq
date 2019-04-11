package com.gyz.jmsTopic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author gyz
 * @date 2019/4/10 0010
 */
public class AppProducer {
    public static final String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    public static final String topicName = "topic-test";

    public static void main(String[] args) throws JMSException {
        //创建连接工场
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        //创建连接
        Connection connection = connectionFactory.createConnection();
        //启动连接
        connection.start();
        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建一个目标
        Topic topic = session.createTopic(topicName);
        //创建生产者
        MessageProducer producer = session.createProducer(topic);
        for (int i = 0; i < 100; i++) {
            //创建消息
            TextMessage textMessage = session.createTextMessage("test" + i);
            //发布消息
            producer.send(textMessage);
            System.out.println("发送消息："+textMessage.getText());
        }
        //关闭连接
        connection.close();
    }
}
