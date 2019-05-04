package com.overlog.Service;


import com.overlog.Model.Log;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


public interface LogService {

    long selectLog(Timestamp time1, Timestamp time2, String type);

    public int insert(Log log);


}
