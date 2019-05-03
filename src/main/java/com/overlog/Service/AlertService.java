package com.overlog.Service;


import java.sql.Timestamp;

public interface AlertService {

    public void sendSimpleMessage(String to, String subject, String text);
    public void alertController(Timestamp time1, Timestamp time2);

}