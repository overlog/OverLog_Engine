package com.overlog.Model.Dao;

import com.overlog.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;


import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;


@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public long getUser(User user){

        String sql = "select id from users where username = '"+ user.getUsername() +"' and passwd = '"+ user.getPasswd() +"'" ;
        return getJdbcTemplate().queryForObject(sql, long.class);




    }
}


/*
new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) {
                PreparedStatement statement;
                try {
                    statement = con.prepareStatement("select id from users where username = ? and passwd = ?");
                    statement.setString(1, user.getUsername());
                    statement.setString(2, user.getPasswd());
                    return statement;

                } catch (Exception e) {
                    e.printStackTrace();

                }

                return null;
            }
            }

 */