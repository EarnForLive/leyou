package cn.itcast.rabbitmq.topic;

import cn.itcast.rabbitmq.util.ConnectionUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 生产者，模拟为商品服务
 */

//可以认为是支持通配符的路由模式
public class Send {
    private final static String EXCHANGE_NAME = "topic_exchange_test";

    public static void main(String[] argv) throws Exception {
        // 获取到连接
        Connection connection = ConnectionUtil.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        // 声明exchange，指定类型为topic
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        // 消息内容
        String message = "新增商品 : id = 1001";
        // 发送消息，并且指定routing key 为：insert ,代表新增商品
        channel.basicPublish(EXCHANGE_NAME, "item.insert", null, message.getBytes());
        System.out.println(" [商品服务：] Sent '" + message + "'");


        // 消息内容
        String message1 = "更新商品 : id = 1001";
        // 发送消息，并且指定routing key 为：insert ,代表更新商品
        channel.basicPublish(EXCHANGE_NAME, "item.update", null, message1.getBytes());
        System.out.println(" [商品服务：] Sent '" + message1 + "'");


        channel.close();
        connection.close();
    }
}