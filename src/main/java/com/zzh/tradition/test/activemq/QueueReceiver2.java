package com.zzh.tradition.test.activemq;
 
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
 
import org.springframework.stereotype.Component;
 
/**
 * @作者 Goofy
 * @邮件 252878950@qq.com
 * @日期 2014-4-1上午10:11:51
 * @描述 队列消息监听器
 */
@Component
public class QueueReceiver2 implements MessageListener {
 
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("QueueReceiver2接收到消息:"+((TextMessage)message).getText());

            /*if((Integer.parseInt(((TextMessage)message).getText().substring(((TextMessage)message).getText().length())))%2==0){
            	message.acknowledge();
            }*/
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
 
}