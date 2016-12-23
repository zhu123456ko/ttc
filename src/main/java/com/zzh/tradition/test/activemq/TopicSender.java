package com.zzh.tradition.test.activemq;
 
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
 
/**
 * @作者 Goofy
 * @邮件 252878950@qq.com
 * @日期 2014-4-1上午9:40:32
 * @描述 发送消息到主题
 */
@Component
public class TopicSender {
     
    @Autowired
    @Qualifier("jmsTopicTemplate")
    private JmsTemplate jmsTemplate;
     
    /**
     * 发送一条消息到指定的队列（目标）
     * @param queueName 队列名称
     * @param message 消息内容
     */
    public void send(String topicName,final String message){
        jmsTemplate.send(topicName, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
            	
                return session.createTextMessage(message);
            }
        });
    }
 
}