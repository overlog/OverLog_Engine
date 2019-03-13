package com.overlog.Service;


import com.overlog.Model.Dao.LogDao;
import com.overlog.Model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogDao logDao;


    @Override
    public void insert(Log log) {

        System.out.println(log.toString());
        logDao.insert(log);
    }
}
