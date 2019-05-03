package com.overlog.Service;

import com.overlog.Model.Dao.LogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Service
public class AlertServiceImpl implements AlertService {

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    LogDao logDao;



    @Override
    public void alertController(Timestamp time1, Timestamp time2){


        long numberOfLog = logDao.selectLog(time1, time2);

        System.out.println(numberOfLog);

        if (numberOfLog >= 5){
            this.sendSimpleMessage("aligedizx@gmail.com", "Log Alert", "There are at least 5 logs");
        }

    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}