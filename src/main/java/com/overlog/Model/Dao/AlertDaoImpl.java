package com.overlog.Model.Dao;

import com.overlog.Model.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class AlertDaoImpl extends JdbcDaoSupport implements AlertDao {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public List<Alert> getAlert(long userid, String type) {

        String sql = "select * from alert where type = '" + type + "' and userid = " + userid ;

        List<Alert> alertList = new ArrayList<>();
        try {
            List<Map<String, Object>> rowSet = getJdbcTemplate().queryForList(sql);

            for(Map m: rowSet){
                String typee = (String) m.get("type");
                alertList.add(new Alert(
                        (int) m.get("id"),
                        (String) m.get("type"),
                        (Timestamp) m.get("starttime"),
                        (Timestamp) m.get("endtime"),
                        (long) m.get("amount"),
                        (long) m.get("userid")));
            }

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


        return alertList;
    }

    @Override
    public void deleteAlert(int id) {
        String sql = "delete from alert where id=" + id;
        System.out.println(sql);
        getJdbcTemplate().update(sql);
    }


}
