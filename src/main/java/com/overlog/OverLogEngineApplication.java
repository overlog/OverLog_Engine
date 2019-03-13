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
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope,
									   AMQP.BasicProperties properties, byte[] body)
					throws IOException {


				String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + message + "'");

				message = message.replaceAll("\"", "");
				String strArray[] = message.split(",");

				System.out.println(strArray[1]);


				Log log = new Log(strArray[0], strArray[1]);
				logService.insert(log);


			}
		};
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}

}
