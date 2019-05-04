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
import java.util.List;


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

        long id;
        try {
            id = getJdbcTemplate().queryForObject(
                    sql, Long.class);


        }catch (Exception e){
            return -1;
        }

        return id;
    }

    @Override
    public String getUserMail(int id){

        String sql = "select mail from users where id = " + id ;

        String  mail;
        try {
            mail = getJdbcTemplate().queryForObject(
                    sql, String.class);


        }catch (Exception e){
            return null;
        }

        return mail;


    }

}

