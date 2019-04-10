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

@Repository
public class LogDaoImpl extends JdbcDaoSupport implements LogDao{

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }


    @Override
    public int insert(Log log)  {


        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) {
                PreparedStatement statement;
                try {
                    statement = con.prepareStatement("INSERT INTO log (type, text) VALUES (?, ?) ", Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, log.getType());
                    statement.setString(2, log.getText());
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
