package com.overlog.Model;




import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Log implements Serializable {


    private int id;
    private String type;
    private String text;
    private Timestamp date;
    private long userID;

    public Log(){}

    public Log(String type, String text, long userID, Timestamp date){
        this.type = type;
        this.text = text;
        this.userID = userID;
        this.date = date;

    }


    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getDate() {
        return date;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", text='" + text + '\'' +
                ", date='" + date + '\'' +
                ", userID=" + userID +
                '}';
    }

}