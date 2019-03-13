package com.overlog.Model;


import java.io.Serializable;


public class Log implements Serializable {


    private int id;
    private String type;
    private String text;

    public Log(){}

    public Log(String type, String text){
        this.type = type;
        this.text = text;

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