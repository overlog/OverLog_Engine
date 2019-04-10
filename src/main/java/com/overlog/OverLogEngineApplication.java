package com.overlog;


import com.overlog.Model.Log;
import com.overlog.Service.LogService;
import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.ApplicationContext;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

@ComponentScan("com.overlog.Service, com.overlog.Model.Dao")
@SpringBootApplication
public class OverLogEngineApplication {


	@Autowired
	LogService logService;

	private final static String QUEUE_NAME = "overlog";

	public static void main(String[] args) throws IOException, TimeoutException {
		ApplicationContext context = SpringApplication.run(OverLogEngineApplication.class, args);
		LogService logService = context.getBean(LogService.class);



		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.queuePurge(QUEUE_NAME);

		channel.basicQos(1);

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");


		Object monitor = new Object();
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			AMQP.BasicProperties replyProps = new AMQP.BasicProperties
					.Builder()
					.correlationId(delivery.getProperties().getCorrelationId())
					.build();

			String response = "Response";

			try {
				String message = new String(delivery.getBody(), "UTF-8");
				System.out.println(" [x] Received '" + message + "'");

				message = message.replaceAll("\"", "");
				String strArray[] = message.split(",");

				System.out.println(strArray[1]);

				Log log = new Log(strArray[0], strArray[1]);
				logService.insert(log);



			} catch (RuntimeException e) {
				System.out.println(" [.] " + e.toString());
			} finally {
				channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, response.getBytes("UTF-8"));
				channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
				// RabbitMq consumer worker thread notifies the RPC server owner thread
				synchronized (monitor) {
					monitor.notify();
				}
			}
		};







		channel.basicConsume(QUEUE_NAME, false, deliverCallback, (consumerTag -> { }));
		// Wait and be prepared to consume the message from RPC client.
		while (true) {
			synchronized (monitor) {
				try {
					monitor.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
