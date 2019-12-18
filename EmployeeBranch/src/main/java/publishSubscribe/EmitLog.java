package publishSubscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class EmitLog {

    private static final String EXCHANGE_NAME = "logs";

    public void sendLog(String logMessage){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.0.124");
        factory.setUsername("root");
        factory.setPassword("myPassword");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            String message = logMessage;

            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}