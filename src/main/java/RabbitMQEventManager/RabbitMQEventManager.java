package RabbitMQEventManager;

import com.rabbitmq.client.*;

public class RabbitMQEventManager {
    public RabbitMQEventManager(String queue_name){
        this.queue_name = queue_name;
    }
    private final String queue_name;
    private static final String RABBITMQ_HOST = "localhost";
    private static final String RABBITMQ_USERNAME = "guest";
    private static final String RABBITMQ_PASSWORD = "guest";

    public static void main(String[] args) {
        RabbitMQEventManager rabbitMQEventManager = new RabbitMQEventManager( "added_favourite_pokemon");
        rabbitMQEventManager.sendMessage();
        rabbitMQEventManager.receiveMessage();
    }
    public void sendMessage() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBITMQ_HOST);
        factory.setUsername(RABBITMQ_USERNAME);
        factory.setPassword(RABBITMQ_PASSWORD);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // Check if the queue already exists
            boolean queueExists = channel.queueDeclarePassive(queue_name).getMessageCount() > 0;

            if (!queueExists) {
                // Declare the queue with the desired properties
                boolean durable = false; // Set the desired durability (true or false)
                channel.queueDeclare(queue_name, durable, false, false, null);
            }

            String message = "New favorite Pokemon added: Pikachu";
            channel.basicPublish("", queue_name, null, message.getBytes("UTF-8"));
            System.out.println("Event published: " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void receiveMessage() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBITMQ_HOST);
        factory.setUsername(RABBITMQ_USERNAME);
        factory.setPassword(RABBITMQ_PASSWORD);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(queue_name, false, false, false, null);

            // Create a new consumer and define the callback for received messages
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws java.io.IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("Received message: " + message);
                }
            };

            // Start consuming messages from the queue
            channel.basicConsume(queue_name, true, consumer);

            // Keep the application running to continue consuming messages
            while (true) {
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}