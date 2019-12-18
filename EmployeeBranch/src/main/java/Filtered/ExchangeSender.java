package Filtered;

/*
 * Example implementations for Enterprise Integration Patterns
 * www.EnterpriseIntegrationPatterns.com
 *
 * Simple example of Message Filter with RabbitMQ
 */

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ExchangeSender {

    private final static String QUEUE_NAME = "eipqueue";
    private static final String EXCHANGE_NAME = "quote";

    public static void message(String filter, String message) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("192.168.0.124");
            factory.setUsername("root");
            factory.setPassword("myPassword");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "direct");

            sendMessage(channel, EXCHANGE_NAME, filter, message);

            channel.close();
            connection.close();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void sendMessage(Channel channel, String exchange, String key, String message) throws IOException {
        channel.basicPublish(exchange, key, null, message.getBytes());
        //System.out.println(" [x] Sent '" + message + "'");
    }

}