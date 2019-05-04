package com.overlog.Service;


import com.overlog.Model.Dao.LogDao;
import com.overlog.Model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogDao logDao;


    @Override
    public long selectLog(Timestamp time1, Timestamp time2, String type) {
        return logDao.selectLog(time1, time2, type);
    }

    @Override
    public int insert(Log log) {
        return logDao.insert(log);
    }
}
