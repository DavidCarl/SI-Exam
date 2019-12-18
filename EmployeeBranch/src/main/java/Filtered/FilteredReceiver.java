package Filtered;

/*
 * Example implementations for Enterprise Integration Patterns
 * www.EnterpriseIntegrationPatterns.com
 *
 * Simple example of Message Filter with RabbitMQ
 */

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FilteredReceiver {

    private final static String QUEUE_NAME = "eipqueue";
    private static final String EXCHANGE_NAME = "quote";

    public static void main(String[] argv) throws java.io.IOException, java.lang.InterruptedException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.0.124");
        factory.setUsername("root");
        factory.setPassword("myPassword");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        filteredReceive(channel, "customer_app", EXCHANGE_NAME);
        filteredReceive(channel, "employ_app", EXCHANGE_NAME);

    }

    static void filteredReceive(final Channel channel, String filter, String exchangeName) throws IOException {
        System.out.println(filter + " waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(filter + " Received '" + message + "'");
            }
        };

        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, exchangeName, filter);

        boolean autoAck = true;
        channel.basicConsume(queueName, autoAck, consumer);
    }

}