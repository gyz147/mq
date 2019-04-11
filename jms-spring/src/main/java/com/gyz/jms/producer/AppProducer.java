package com.gyz.jms.producer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author gyz
 * @date 2019/4/11 0011
 */
public class AppProducer {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("producer.xml");
        ProducerService producerService = context.getBean(ProducerService.class);
        for (int i = 0; i < 100; i++) {
            producerService.sendMessage("text" + i);
        }
        ((ClassPathXmlApplicationContext) context).close();
    }
}
