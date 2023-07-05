package shared;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.ArrayList;

public class RabbitMQEventPublisher implements EventPublisher {


    private static final String RABBITMQ_HOST = "localhost";
    private static final String RABBITMQ_USERNAME = "guest";
    private static final String RABBITMQ_PASSWORD = "guest";

    public void sendMessage() {

    }

    @Override
    public void publish(DomainEvent event) {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost( RABBITMQ_HOST);
    factory.setUsername(RABBITMQ_USERNAME);
    factory.setPassword(RABBITMQ_PASSWORD);

    try (Connection connection = factory.newConnection();
         Channel channel = connection.createChannel()) {

        // Check if the queue already exists
        String queue_name = event.type();
        boolean queueExists = false;
        try {
            channel.queueDeclarePassive(queue_name);
            queueExists = true;
        } catch (IOException ex) {
            ex.printStackTrace();
            // The queue does not exist, it will be declared later
        }

        if (!queueExists) {
            // Declare the queue with the desired properties
            boolean durable = false; // Set the desired durability (true or false)
            channel.queueDeclare(queue_name, durable, false, false, null);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String message = objectMapper.writeValueAsString(event);
        channel.basicPublish("", queue_name, null, message.getBytes("UTF-8"));
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    @Override
    public void publish(ArrayList<DomainEvent> events) {
        events.forEach(this::publish);
    }
}