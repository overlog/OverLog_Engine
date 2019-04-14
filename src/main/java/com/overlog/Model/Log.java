package com.overlog.Model;




import java.io.Serializable;

public class Log implements Serializable {


    private int id;
    private String type;
    private String text;
    private long userID;

    public Log(){}

    public Log(String type, String text, long userID){
        this.type = type;
        this.text = text;
        this.userID = userID;

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
    public java.lang.String toString() {
        return "log{" +
                "id=" + id +
                ", type=" + type +
                ", text=" + text +
                '}';
    }

}