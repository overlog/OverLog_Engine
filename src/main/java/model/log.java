package model;
import com.sun.org.apache.xpath.internal.operations.String;

import javax.persistence.*;


@Entity
@Table(name = "log")


public class log {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name= "type")
    private String type;
    @Column(name = "text")
    private String text;

    public log(String type, String text){
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
