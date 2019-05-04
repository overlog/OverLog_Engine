package com.overlog.Service;


import com.overlog.Model.Log;

import java.sql.Timestamp;

public interface AlertService {

    public void sendSimpleMessage(String to, String subject, String text);
    public void alertController(Log log);

}