package com.overlog.Model.Dao;



import com.overlog.Model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Repository
public class LogDaoImpl extends JdbcDaoSupport implements LogDao{

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }


    @Override
    public void insert(Log log) {

        /*String sql = "INSERT INTO log(type, text) VALUES (?, ?)" ;
        getJdbcTemplate().update(sql, new Object[]{
                log.getType(), log.getText()
        });*/

        String sql = "INSERT INTO log " +
                "(type, text) VALUES (?, ?)" ;
        getJdbcTemplate().update(sql, new Object[]{
                log.getText(), log.getText()
        });

    }
}
