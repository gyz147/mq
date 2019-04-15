package com.gyz.jmsQueue;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author gyz
 * @date 2019/4/10 0010
 */
public class AppConsumer {
    public static final String url = "failover:(tcp://10.0.24.174:61616,tcp://10.0.24.174:61617,tcp://10.0.24.174:61618)?randomize=true";
    public static final String queueName = "queue-test";

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
        Queue queue = session.createQueue(queueName);
        //创建生产者
        MessageConsumer consumer = session.createConsumer(queue);
        //创建监听器
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("接受消息：" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //不要关闭连接,因为消息的监听事一个异步的过程，程序是一直启动的，线程是阻塞状态的，如果这个时候把连接关闭了会接收不到监听消息
        //connection.close();
    }
}
