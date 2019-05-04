package com.overlog.Model;

import java.sql.Timestamp;

public class Alert {

    int id;
    String type;
    Timestamp starttime;
    Timestamp endtime;
    long amount;
    long userid;


    public Alert(int id, String type, Timestamp starttime, Timestamp endtime, long amount, long userid) {
        this.id = id;
        this.type = type;
        this.starttime = starttime;
        this.endtime = endtime;
        this.amount = amount;
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", amount=" + amount +
                ", userid=" + userid +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStarttime(Timestamp starttime) {
        this.starttime = starttime;
    }

    public void setEndtime(Timestamp endtime) {
        this.endtime = endtime;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Timestamp getStarttime() {
        return starttime;
    }

    public Timestamp getEndtime() {
        return endtime;
    }

    public long getAmount() {
        return amount;
    }

    public long getUserid() {
        return userid;
    }
}
