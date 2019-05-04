package com.overlog;


import com.overlog.Model.Log;
import com.overlog.Model.User;
import com.overlog.Service.AlertService;
import com.overlog.Service.LogService;
import com.overlog.Service.UserService;
import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.ApplicationContext;


import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
		UserService userService = context.getBean(UserService.class);
		AlertService alertService = context.getBean(AlertService.class);



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

			String response = "null";

			try {
				String message = new String(delivery.getBody(), "UTF-8");
				System.out.println(" [x] Received '" + message + "'");

				message = message.replaceAll("\"", "");
				String strArray[] = message.split("seperator");

				System.out.println(strArray[1]);

				if(strArray[0].equals("log")){

					long userID = Long.valueOf(strArray[3]).longValue();


					Log log = new Log(strArray[1], strArray[2], userID, convertStringToTimestamp(strArray[4]));
					int responseID =  logService.insert(log);
					response = String.valueOf(responseID);
					alertService.alertController(log);


				}else if(strArray[0].equals("user")){
					User user = new User(strArray[1], strArray[2]);
					long responseID = userService.getUser(user);
					response = String.valueOf(responseID);
				}



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

	public static Timestamp convertStringToTimestamp(String str_date) {
		try {
			DateFormat formatter;
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// you can change format of date
			Date date = formatter.parse(str_date);
			java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());

			return timeStampDate;
		} catch (ParseException e) {
			System.out.println("Exception :" + e);
			return null;
		}
	}
}
