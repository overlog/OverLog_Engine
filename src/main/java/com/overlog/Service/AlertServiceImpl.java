package com.overlog.Service;

import com.overlog.Model.Alert;
import com.overlog.Model.Dao.AlertDao;
import com.overlog.Model.Dao.LogDao;
import com.overlog.Model.Dao.UserDao;
import com.overlog.Model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AlertServiceImpl implements AlertService {

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    LogDao logDao;

    @Autowired
    AlertDao alertDao;

    @Autowired
    UserDao userDao;



    @Override
    public void alertController(Log log){


        List<Alert> alertList = alertDao.getAlert(log.getUserID(), log.getType());
        long total;
        String email = null;
        for (Alert alert: alertList) {
            total = logDao.selectLog(alert.getStarttime(), alert.getEndtime(), alert.getType());
            if(total >= alert.getAmount()){
                if(email == null){
                    email = userDao.getUserMail((int)alert.getUserid());
                }
                String text = "There are at least " + alert.getAmount() + " logs";
                this.sendSimpleMessage(email, "Log Alert", text);
                alertDao.deleteAlert(alert.getId());
            }
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