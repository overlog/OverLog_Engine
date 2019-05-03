package com.overlog.Model.Dao;

import com.overlog.Model.Log;

import java.sql.SQLException;
import java.sql.Timestamp;

public interface LogDao {

    long selectLog(Timestamp time1, Timestamp time2);

    int insert(Log log) ;

}
