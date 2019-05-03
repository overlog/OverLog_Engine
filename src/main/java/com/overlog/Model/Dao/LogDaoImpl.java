package com.overlog.Model.Dao;



import com.overlog.Model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class LogDaoImpl extends JdbcDaoSupport implements LogDao{

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public long selectLog(Timestamp time1, Timestamp time2){


        String sql = "SELECT COUNT(*) FROM log WHERE date >= '" + time1 + "'AND date < '" + time2 + "'" ;

        Long total;
        try {
            total = getJdbcTemplate().queryForObject(sql, Long.class);
        }catch (Exception e){
            return -1;
        }


        return total;

    }


    @Override
    public int insert(Log log)  {


        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) {
                PreparedStatement statement;
                try {
                    statement = con.prepareStatement("INSERT INTO log (type, text, userID, date) VALUES (?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, log.getType());
                    statement.setString(2, log.getText());
                    statement.setLong(3, log.getUserID());
                    System.out.println(log.getDate());
                    statement.setTimestamp(4, log.getDate());
                    return statement;

                }
                catch (Exception e){
                    e.printStackTrace();

                }

                return null;
            }
        }, holder);

        return (int) holder.getKeys().get("id");

    }
}
